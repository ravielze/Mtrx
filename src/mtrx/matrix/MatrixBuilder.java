package mtrx.matrix;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import mtrx.MtrxMain;

public class MatrixBuilder {

    private int row = 0, col = 0;
    private double[][] data;

    /**
     * Constructor dengan jumlah kolom dan baris 1
     */
    public MatrixBuilder(){
        this.row = 1;
        this.col = 1;
        this.data = new double[1][1];

        this.data[0][0] = 1;
    }

    /**
     * Constructor dengan jumlah kolom dan baris.
     * Mula-mula nilai elemen di diagonal di set 1, lainnya 0,
     * sehingga apabila row==col, matriks identitas akan terbentuk.
     * @param row
     * @param col
     */
    public MatrixBuilder(int row, int col){
        this.row = row;
        this.col = col;
        this.data = new double[row][col];

        for (int i = 0; i < row; i++){
            for (int j = 0; j < col; j++){
                this.data[i][j] = (i == j) ? 1 : 0;
            }
        }
    }

    /**
     * Constructor matriks persegi
     * Mula-mula terbentuk matriks identitas.
     * @param row
     * @param col
     */
    public MatrixBuilder(int n){
        this.row = n;
        this.col = n;
        this.data = new double[n][n];

        for (int i = 0; i < row; i++){
            for (int j = 0; j < col; j++){
                this.data[i][j] = (i == j) ? 1 : 0;
            }
        }
    }
    /**
     * Constructor copy matriks
     * @param m matriks yang ingin dicopy
     */
    public MatrixBuilder(Matrix m){
        this.row = m.getRowCount();
        this.col = m.getColCount();
        this.data = new double[row][col];

        for (int i = 0; i < this.row; i++){
            for (int j = 0; j < this.col; j++){
                this.data[i][j] = m.getElement(i, j);
            }
        }
    }

    /**
     * Mengubah jumlah baris
     * @param n
     * @return MatrixBuilder
     */
    public MatrixBuilder changeRow(int n){
        this.row = n;
        return this;
    }

    /**
     * Mengubah jumlah kolom
     * @param n
     * @return MatrixBuilder
     */
    public MatrixBuilder changeCol(int n){
        this.col = n;
        return this;
    }

    /**
     * Mengubah nilai suatu elemen
     * @param row
     * @param col
     * @param value
     * @return
     */
    public MatrixBuilder setElement(int row, int col, double value){
        this.data[row][col] = value;
        return this;
    }

    /**
     * Mengisi matriks dengan nilai-nilai
     * @param value
     * @return MatrixBuilder
     */
    public MatrixBuilder setValue(double... value){
        if (value.length == 0) return this;

        int k = 0;
        for (int i=0; i < this.row; i++){
            for (int j=0; j < this.col; j++){
                this.data[i][j] = (k < value.length) ? value[k] : 0;
                k++;
            }
        }

        return this;
    }

    /**
     * Mengisi matriks dengan nilai-nilai
     * @param value
     * @return MatrixBuilder
     */
    public MatrixBuilder setValue(double[][] value){
        this.row = value.length;
        this.col = value[0].length;

        double[][] newData = new double[this.row][this.col];
        for (int i=0; i < this.row; i++){
            for (int j=0; j < this.col; j++){
                newData[i][j] = value[i][j];
            }
        }

        this.data = newData;
        return this;
    }
    
    /**
     * Matrix Builder
     * @return the matrix created.
     */
    public Matrix build(){
        return new Matrix(this.row, this.col, this.data);
    }

    /**
     * Matrix Builder as Interpolation Matrix.
     * Column's size has to be 2. Ignored if greater than two.
     * Column 1: X, Column 2: Y.
     * @throws Exception if row's size less than or equal to 0 or column's size is less than 2.
     * @return the interpolation matrix created
     */
    public Matrix buildAsInterpolation() throws Exception {
        if (this.row <= 0 ) throw new Exception("Row's size cannot be less than or equal to zero.");
        if (this.col < 2 ) throw new Exception("Column's size cannot be less than 2.");
        
        int newRow = this.row;
        int newCol = this.row + 1;
        double[][] newData = new double[newRow][newCol];
        for (int i = 0; i < newRow; i++){
            double x = this.data[i][0];
            double y = this.data[i][1];
            for (int j = 0; j < newCol; j++){
                newData[i][j] = (j != newCol-1) ? Math.pow(x, j) : y;
            }
        }

        this.row = newRow;
        this.col = newCol;
        this.data = newData;
        return new Matrix(this.row, this.col, this.data);
    }

    /**
     * Fungsi menerima input terminal/console.
     * @return MatrixBuilder
     */
    public MatrixBuilder consoleInput(){
        System.out.print("Masukkan Baris: ");
        this.row = MtrxMain.scn.nextInt();
        System.out.print("Masukkan Kolom: ");
        this.col = MtrxMain.scn.nextInt();
        this.data = new double[this.row][this.col];
        for (int i=0; i<this.row; i++) {
            for (int j=0 ; j<this.col; j++){
                this.data[i][j] = MtrxMain.scn.nextDouble();
            }
        }
        return this;
    }

    /**
     * Prosedur menerima input dari file
     * @param fileName 
     * @return MatrixBuilder
     */
    public MatrixBuilder fileInput(String fileName){
        try {
            File file = new File(new File("../test/"+fileName).getCanonicalPath());
            Scanner scanner = new Scanner(file);

            this.col = 0;
            this.row = 0;
            while(scanner.hasNextLine()){
                if(this.row == 0){
                    this.col = (scanner.nextLine().trim().split(" ")).length;
                }
                else scanner.nextLine();
                this.row++;
            }
            scanner.close();

            Scanner scanner2 = new Scanner(file);
            this.data = new double[this.row][this.col];
            for (int i = 0; i < this.row; i++){
                for (int j = 0; j <this.col; j++){
                    this.data[i][j] = scanner2.nextDouble();
                }
            }
            scanner2.close();
        } catch (FileNotFoundException ex){
            System.out.println("File tidak ditemukan. Membuat matrix 1x1 berisi angka 1.");
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return this;
    }

    /**
     * Prosedur menghapus sebuah kolom lalu dirapatkan.
     * @param col kolom yang akan dihapus
     * @return MatrixBuilder
     */
    public MatrixBuilder cutColoumn(int col){
        double[][] newData = new double[this.row][this.col-1];
        
        for (int i = 0; i < this.row; i++){
            int k = 0;
            for (int j = 0; j < this.col; j++){
                if (j != col){
                    newData[i][k] = this.data[i][j];
                    k++;
                }
            }
        }
        
        this.data = newData;
        this.col -= 1;
        return this;
    }

    /**
     * Prosedur menghapus sebuah baris lalu dirapatkan.
     * @param row baris yang akan dihapus
     * @return MatrixBuilder
     */
    public MatrixBuilder cutRow(int row){
        double[][] newData = new double[this.row-1][this.col];
        
        int k = 0;
        for (int i = 0; i < this.row; i++){

            for (int j = 0; j < this.col; j++){
                if (i != row){
                    newData[k][j] = this.data[i][j];
                }
            }
            if (i != row) k++;
        }

        this.data = newData;
        this.row -= 1;
        return this;
    }

    /**
     * Membuat sebuah matriks dengan menghilangkan sebuah baris dan sebuah kolom.
     * @param row indeks baris yang dihilangkan
     * @param col indeks kolom yang dihilangkan
     * @return MatrixBuilder
     */
    public MatrixBuilder subMatrix(int row, int col){
        this.cutRow(row);
        this.cutColoumn(col);
        return this;
    }

    /**
     * Memotong matriks dengan menghilangkan beberapa baris.
     * Menghapus kolom dengan indeks [col1, col2].
     * @param col1 indeks baris pertama
     * @param col2 indeks baris kedua
     * @return MatrixBuilder
     */
    public MatrixBuilder cutMultiColoumns(int col1, int col2){
        int c1 = (col1 > this.data[0].length) ? this.data[0].length : col1;
        int c2 = (col2 > this.data[0].length) ? this.data[0].length : col2;
        if (c1 != c2){
            double[][] newData = new double[this.row][this.col-(c2-c1)];
        
            for (int i = 0; i < this.row; i++){
                int k = 0;
                for (int j = 0; j < this.col; j++){
                    if (!(col1 <= j && j <= col2)){
                        newData[i][k] = this.data[i][j];
                        k++;
                    }
                }
            }
            
            this.data = newData;
            this.col -= (c2-c1);
            return this;
        }
        return this;
    }

    /**
     * Menginsert kolom dengan menggeser indeks col ke kanan lalu
     * memasukan nilainya ke indeks col.
     * @param col indeks kolom yang akan digeser lalu dimasukkan
     * @param value nilai yang akan diinsert
     * @return MatrixBuilder
     */
    public MatrixBuilder insertColoumn(int col, double[] value){
        double[][] newData = new double[this.row][this.col+1];
        
        int k = 0;
        for (int i = 0; i < this.row; i++){
            for (int j = this.col; j >= 0; j--){
                if (j == col){
                    newData[i][j] = (k > value.length) ? 0 : value[k];
                    k++;
                } else if (j > col) {
                    newData[i][j] = this.data[i][j-1];
                } else {
                    newData[i][j] = this.data[i][j];
                }
            }
        }

        this.data = newData;
        this.col += 1;
        return this;
    }

    /**
     * Cara lain untuk copy matrix
     * @param m Matrix yang ingin dicopy
     * @return matrix baru yang isinya sama
     */
    public static Matrix clone(Matrix m){
        return (new MatrixBuilder(m)).build();
    }
    
}
