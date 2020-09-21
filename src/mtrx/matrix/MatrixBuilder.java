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
     * Mengubah jumlah baris
     * @param n
     * @return
     */
    public MatrixBuilder changeRow(int n){
        this.row = n;
        return this;
    }

    /**
     * Mengubah jumlah kolom
     * @param n
     * @return
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
     * @return
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
    
}
