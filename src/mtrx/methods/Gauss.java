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
        int i, l, row, j = 0, kValid, bValid = 0;
        boolean found, valid, need;
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
                    if (this.matrix.findFirstXinCol(j, 1) > i) {
                        found = true;
                        row = this.matrix.findFirstXinCol(j, 1);
                        this.matrix.swapRow(i, row);
                        this.swapTimes++;
                    }
                    if (this.matrix.getElement(i, j) == 0) {
                        l = i+1;
                        need = false;
                        while (l < this.matrix.getRowCount() && !need) {
                        // for (l = i+1; l < this.matrix.getRowCount(); l++) {
                            if (this.matrix.getElement(l, j) != 0) {
                                need = true;
                                found = true;
                                this.swapTimes++;
                                this.matrix.swapRow(i, l);
                                    // if (i == 0 && j == 0) {
                                    //     if (this.matrix.findCol(1, 1)) row = this.matrix.findFirstXinCol(1, 1);
                                    //     else row = l;
                                    // }
                            }
                            else l++;
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
                    final double val = this.matrix.getElement(i, j);
                    this.matrix.rowOperation(i, j, (x, y) -> x / val);
                }
                else {
                    final double val = this.matrix.getElement(i, j) / this.matrix.getElement(i-1, j);
                    final int temp_row = i;
                    this.matrix.rowOperation(i, i-1, (x, y) -> x - val*(temp_row-1));
                }
            }
        }

        i = this.matrix.getRowCount()-1;
        while (i >= 0 && !this.hasSolution) {
            kValid = 0;
            j = 0;
            valid = false;
            while (j < this.matrix.getColCount() && !valid) {
                if (this.matrix.getElement(i, j) == 0) {
                    kValid++;
                    if (kValid == this.matrix.getColCount()) {
                        bValid++;
                    }
                }
                if (this.matrix.getElement(i, j) != 0) {
                    if (j == this.matrix.getColCount()-1 && kValid == this.matrix.getColCount()-1) {
                        this.hasSolution = false;
                    }
                    if (j != this.matrix.getColCount()-1) {
                        valid = true;
                        this.hasSolution = true;
                    }
                }
                j++;
            }
            if (bValid == this.matrix.getRowCount()) this.hasSolution = false;
            i--;
        }
    }

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
