package mtrx.methods;

import mtrx.matrix.Matrix;
import mtrx.matrix.MatrixBuilder;
import mtrx.matrix.MatrixOperation;
import mtrx.trait.MatrixTrait;
import mtrx.augmatrix.AugMatrix;

public class Gauss implements MatrixMethod{

    private Matrix matrix;
    private Matrix result;
    private int swapTimes;
    private boolean hasSolution;

    public Gauss(Matrix matrix){
        this.matrix = (new MatrixBuilder(matrix)).build();

        this.swapTimes = 0;
        this.hasSolution = false;
        this.operate();
    }

	private void operate() {
        for (int i = 0; i < this.matrix.getColCount(); )
    }
    /**
     * 1 2 3
     * 5 7 9
     * 8 0 1
     */
    
    //Jangan lupa jadiin private
    /**
     * Melakukan operasi
     * @param row indeks baris
     * @param col indeks kolom
     */
        /* TODO
         * Cek kalau matriks bukan kayak gini
         * 0 0 0 A
         * 0 0 0 B
         * 0 0 0 C
         * (tidak ada solusi)
         * 
         * atau
         * 0 0 0 0
         * 0 0 0 0
         * 0 0 0 0
         */
    /*public void downwardElimination(int row, int col){


        int i = row+1;
        int colx = col;
        while (true){
            if (!this.matrix.isAllXinCol(colx, 0.0D)){
                if (row == this.matrix.getRowCount()-1){

                } else {
                    while (this.matrix.findFirstXinCol(colx, 0.0D) == row && (i < this.matrix.getRowCount())){
                        this.matrix.swapRow(row, i);
                        swapTimes++;
                        i++;
                    }
                }
                break;
            } else {
                colx++;
            }
        }


        if (!this.matrix.isAllXinCol(col, 0.0D)){

        } else {
            while (this.matrix.getElement(row, colStart) == 0.0D){
                colStart++;
            }
        }

        if (this.matrix.getElement(row, 0) != 1.0D){
            this.matrix.rowOperation(row, new MatrixOperation(){

                @Override
                public double operate(double x, double y) {
                    // TODO Auto-generated method stub
                    return 0;
                }
                
            });
        }
    }*/

    @Override
    public Matrix getInitialMatrix() {
        return this.matrix;
    }

    @Override
    public Matrix getResult() {
        return (this.hasSolution ? this.result : null);
    }

    @Override
    public boolean hasSolution() {
        return this.hasSolution;
    }

    @Override
    public int getSwapTimes() {
        return this.swapTimes;
    }
}
