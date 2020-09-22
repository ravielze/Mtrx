package mtrx.matrix;

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
     * Prosedur menerima input terminal/console.
     */
    public void consoleInput(){
        //TODO
    }

    /**
     * Prosedur menerima input dari file
     * @param fileName 
     */
    public void fileInput(String fileName){
        //TODO
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
            if (i != row) k++;

            for (int j = 0; j < this.col; j++){
                if (i != row){
                    newData[k][j] = this.data[i][j];
                }
            }
        }
        return this;
    }

    /**
     * Membuat sebuah matriks dengan menghilangkan sebuah baris dan sebuah kolom.
     * @param row indeks baris yang dihilangkan
     * @param col indeks kolom yang dihilangkan
     * @return MatrixBuilder
     */
    public MatrixBuilder subMatrix(int row, int col){
        this.cutColoumn(col);
        this.cutRow(row);
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
            for (int i = c1; i <= c2; i++){
                this.cutColoumn(i);
            }
        }
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
