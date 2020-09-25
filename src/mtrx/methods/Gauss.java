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
        int i, l, row, j = 0, kValid = 0, bValid = 0;
        boolean found, valid;
        double val;

        // buat nuker2
        for (i = 0; i < this.matrix.getRowCount(); i++) {
            found = false;

            //ini jaga-jaga aja punten
        // while (i < this.matrix.getRowCount() && swapped != 2) {
            // if (this.matrix.getElement(0, 0) == 0 && this.matrix.getElement(i, 0) != 0) {
            //     this.swapTimes++;
            //     swapped++;
            //     if (this.matrix.findCol(0, 1)) row = this.matrix.findFirstXinCol(0, 1);
            //     else row = i;
            //     this.matrix.swapRow(0, row);
                while (j < this.matrix.getRowCount() && !found) {
            // for (j = 0; j < this.matrix.getRowCount(); j++) {
                if (this.matrix.getElement(i, j) == 0 && i != j) {
                    for (l = i+1; l < this.matrix.getRowCount(); l++) {
                        if (this.matrix.getElement(l, j) != 0) {
                            found = true;
                            this.swapTimes++;
                            if (this.matrix.findCol(1, 1)) row = this.matrix.findFirstXinCol(1, 1);
                            else row = l;
                            this.matrix.swapRow(i, row);
                        }
                    }
                }
                j++;
            }
        }

        for (i = 0; i < this.matrix.getRowCount(); i++) {
            found = false;
            j = 0;

            while (j < this.matrix.getRowCount() && !found) {
                if (this.matrix.getElement(i, j) != 0) found = true;
                else j++;
            }

            if (i == 0) this.matrix.rowOperation(0, (x, y) -> x/(this.matrix.getElement(0, 0)));
            else {
                if (this.matrix.getElement(i-1, j) == 0) {
                    val = this.matrix.getElement(i, j);
                    this.matrix.rowOperation(i, j, (x, y) -> x / val);
                }
                else {
                    val = this.matrix.getElement(i, j) / this.matrix.getElement(i-1, j);
                    this.matrix.rowOperation(i, i-1, (x, y) -> x - val*(i-1));
                }
            }
        }

        i = this.matrix.getRowCount()-1;
        while (i >= 0 && !this.hasSolution) {
        // for (i = this.matrix.getRowCount()-1; i >= 0; i--) {
            kValid = 0;
            j = 0;
            valid = false;
            while (j < this.matrix.getColCount() && !valid) {
                if (this.matrix.getElement(i, j) != 0 && j != this.matrix.getColCount()-1) {
                    this.hasSolution = true;
                    valid = true;
                }

                if (this.matrix.getElement(i, j) == 0) {
                    kValid++;
                    if (kValid == this.matrix.getColCount()) {
                        bValid++;
                    }
                    if (kValid == this.matrix.getColCount()-1 &&
                    this.matrix.getElement(i, this.matrix.getColCount()-1) != 0) {
                        this.hasSolution = false;
                    }
                }
                
                if (kValid == this.matrix.getRowCount()) {
                    this.hasSolution = false;
                }
                j++;
            }
            i--;
        }
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
