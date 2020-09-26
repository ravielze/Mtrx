package mtrx.methods;

import mtrx.matrix.Matrix;
import mtrx.matrix.MatrixBuilder;
import mtrx.matrix.MatrixOperation;
import mtrx.trait.MatrixTrait;
import mtrx.utils.NUtils;
import mtrx.augmatrix.AugMatrix;

public class Gauss implements MatrixMethod{

    private Matrix matrix;
    private Matrix result;
    private int swapTimes;
    private boolean hasSolution;

    public Gauss(Matrix matrix){
        this.matrix = (new MatrixBuilder(matrix)).build();
        this.result = (new MatrixBuilder(matrix)).build();
        this.swapTimes = 0;
        this.hasSolution = false;
        this.operate();
    }

	public void operate() {
        int i, l, row, j, kValid, bValid = 0;
        boolean found, valid, need;

        for (i = 0; i < this.matrix.getRowCount(); i++) {
            for (j = 0; j < this.matrix.getColCount(); j++) {
                this.result.setElement(i, j, this.matrix.getElement(i, j));               
            }
        }

        for (i = 0; i < this.result.getRowCount(); i++) {
            found = false;
            j = 0;
                while (j < this.result.getRowCount() && !found) {
                    if (this.result.findFirstXinCol(j, 1) > i) {
                        found = true;
                        row = this.result.findFirstXinCol(j, 1);
                        this.result.swapRow(i, row);
                        this.swapTimes++;
                    }
                    if (NUtils.ISEQUAL(this.result.getElement(i, j), 0)) {
                        l = i+1;
                        need = false;
                        while (l < this.result.getRowCount() && !need) {
                            if (!NUtils.ISEQUAL(this.result.getElement(l, j), 0)) {
                                need = true;
                                found = true;
                                this.swapTimes++;
                                this.result.swapRow(i, l);
                            }
                            else l++;
                        }
                    }
                j++;
            }
        }

        for (i = 0; i < this.result.getRowCount(); i++) {
            found = false;
            j = 0;
            while (j < this.result.getRowCount() && !found) {
                if (!NUtils.ISEQUAL(this.result.getElement(i, j), 0)) {
                    found = true;
                }
                else {
                    j++;
                }
            }

            if (i == 0) this.result.rowOperation(0, (x, y) -> x/(this.result.getElement(0, 0)));
            else {
                if (NUtils.ISEQUAL(this.result.getElement(i-1, j), 0)) {
                    final double val = this.result.getElement(i, j);
                    this.result.rowOperation(i, (x, y) -> x / val);
                }
                else {
                    final double val = this.result.getElement(i, j) / this.result.getElement(i-1, j);
                    final int temp_row = i-1;
                    this.result.rowOperation(i, temp_row, (x, y) -> (x - val*(temp_row)));
                }
            }
        }

        i = this.result.getRowCount()-1;
        while (i >= 0 && !this.hasSolution) {
            kValid = 0;
            j = 0;
            valid = false;
            while (j < this.result.getColCount() && !valid) {
                if (NUtils.ISEQUAL(this.result.getElement(i, j), 0)) {
                    kValid++;
                    if (kValid == this.result.getColCount()) {
                        bValid++;
                    }
                }
                else {
                    if (j == this.result.getColCount()-1 && kValid == this.result.getColCount()-1) {
                        this.hasSolution = false;
                    }
                    if (j != this.result.getColCount()-1) {
                        valid = true;
                        this.hasSolution = true;
                    }
                }
                j++;
            }
            if (bValid == this.result.getRowCount()) this.hasSolution = false;
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
