package mtrx.methods;

import mtrx.matrix.Matrix;
import mtrx.matrix.MatrixBuilder;
import mtrx.matrix.MatrixOperation;
import mtrx.trait.MatrixTrait;
import mtrx.utils.NUtils;
import mtrx.augmatrix.AugMatrix;

public class Gauss implements MatrixMethod {

    private Matrix matrix;
    private Matrix result;
    private int swapTimes;
    private boolean hasSolution;
    private int swapped;

    public Gauss(Matrix matrix){
        this.matrix = (new MatrixBuilder(matrix)).build();
        this.result = (new MatrixBuilder(matrix)).build();
        this.swapTimes = 0;
        this.hasSolution = false;
        this.operate();
        this.swapped = -1;
    }

    private void bResult(AugMatrix a) {
        for (int i = 0; i < a.getLeft().getRowCount(); i++) {
            for (int j = 0; j <= a.getLeft().getColCount(); j++) {
                if (j < a.getLeft().getColCount()) this.result.setElement(i, j, a.getLeft().getElement(i, j));
                else this.result.setElement(i, j, a.getRight().getElement(i, 0));
            }
        }
    }

    private boolean b4_sndiri(AugMatrix aug, int row, int col) {
        int count = 0;
        boolean found = false;
        if (col == 0 && row == 0) {
            if (aug.getLeft().findFirstXinCol(col, 1) > row || aug.getLeft().findFirstXinCol(col, -11) > row)
                return true;
            else return false;
        }
        if (row == 0) return false;
        if (row != aug.getLeft().getRowCount()-1) {
            for (int j = 0; j <= col; j++) {
                if (j == aug.getLeft().getColCount()) {
                    if (NUtils.ISEQUAL(aug.getRight().getElement(row, 0), 0)) {
                        found = true;
                        count++;
                    }
                }
                else {
                    if (NUtils.ISEQUAL(aug.getLeft().getElement(row, j), 0)) {
                        found = true;
                        count++;
                    }
                }
            }
            if (count == col && found) return true;
            else return false;
        }
        else return true;
    }

    private boolean b4_atas(AugMatrix aug, int row, int col) {
        int count = 0;
        boolean found = false;
        if (col != 0) {
            for (int j = 0; j < col; j++) {
                if (NUtils.ISEQUAL(aug.getLeft().getElement(row-1, j), 0)) {
                    found = true;
                    count++;
                }
            }
            if (found && count == col) return true;
            else return false;
        }
        else return false;
    }

    private void subTop(AugMatrix aug, int row, int col) {
        double val = aug.getLeft().getElement(row, col);
        aug.rowOperation(row, (x, y) -> x/val);
    }

    private void subFromTop (AugMatrix aug, int row, int col) {
        double top = aug.getLeft().getElement(row-1, col);
        int idx = row-1;
        for (int i = row; i < aug.getLeft().getRowCount(); i++) {
            double val = aug.getLeft().getElement(i, col) / top;
            aug.rowOperation(i, idx, (x, y) -> x - val*y);
        }
    }

    private void subDown (AugMatrix aug, int row, int col) {
        double val = aug.getRight().getElement(row, 0);
        aug.rowOperation(row, (x, y) -> x/val);
    }

	public void operate() {
        Matrix temp_left = (new MatrixBuilder(this.result.getRowCount(), this.result.getColCount()-1)).build();
        Matrix temp_right = (new MatrixBuilder(this.result.getRowCount(), 1)).build();
        int i, l, row, j, kValid, bValid = 0;
        boolean found, valid, need, go;
        this.swapped = -1;

        for (i = 0; i < this.result.getRowCount(); i++) {
            for (j = 0; j < this.result.getColCount(); j++) {
                if (j != this.result.getColCount()-1) {
                    temp_left.setElement(i, j, this.result.getElement(i, j));
                }
                else
                temp_right.setElement(i, 0, this.result.getElement(i, j));
            }
        }

        AugMatrix a = new AugMatrix(temp_left, temp_right);

        // iya ini swap doang, panjang ._. semoga udah fiks banget bener
        for (i = 0; i < a.getLeft().getRowCount(); i++) {
            found = false;
            j = 0;
            while (j < a.getLeft().getColCount() && !found && this.swapped != i && !b4_sndiri(a, i, j)) {
                if (this.result.findFirstXinCol(j, 1) > i && a.getLeft().getElement(i, j) != 1 &&
                j == 0) {
                    found = true;
                    row = a.getLeft().findFirstXinCol(j, 1);
                    a.swapRow(i, row);
                    this.swapTimes++;
                }
                if (this.result.findFirstXinCol(j, -1) > i && a.getLeft().getElement(i, j) != -1 &&
                j == 0) {
                    found = true;
                    row = a.getLeft().findFirstXinCol(j, -1);
                    a.swapRow(i, row);
                    a.rowOperation(i, (x, y) -> x*(-1));
                    this.swapTimes++;
                }
                if (NUtils.ISEQUAL(a.getLeft().getElement(i, j), 0)) {
                    l = a.getLeft().getRowCount()-1;
                    need = false;
                    while (l >= i+1 && !need) {
                        if (!NUtils.ISEQUAL(a.getLeft().getElement(l, j), 0)) {
                            this.swapped = l;
                            need = true;
                            found = true;
                            this.swapTimes++;
                            a.swapRow(i, l);
                        }
                        else l--;
                    }
                }
            j++;
            }
        }

        for (i = 0; i < a.getLeft().getRowCount(); i++) {
            go = true;
            j = 0;
            if (a.getLeft().getElement(0, 0) != 1) {
                subTop(a, 0, 0);
                go = false;
            }
            if (i == 0) go = false;
            while (go && j <= a.getLeft().getColCount()) {
                need = false;
                if (j < a.getLeft().getColCount()) {
                    if (!NUtils.ISEQUAL(a.getLeft().getElement(i, j), 0)) {
                        need = true;
                    }
                }
                else {
                    if (!NUtils.ISEQUAL(a.getRight().getElement(i, 0), 0)) {
                        need = true;
                    }
                }
                if (need) {
                    go = false;
                    if (j == a.getLeft().getColCount()) {
                        subDown(a, i, j);
                    }
                    else {
                        if (!NUtils.ISEQUAL(a.getLeft().getElement(i-1, j), 0) &&
                            !(!b4_atas(a, i, j) ^ !b4_sndiri(a, i, j))) {
                            subFromTop(a, i, j);
                            go = true;
                        }
                        else {
                            subTop(a, i, j);
                        }
                    }
                }
                j++;
            }
        }
    
        bResult(a);

        i = this.result.getRowCount()-1;
        found = false;
        while (i >= 0 && !found) {
            kValid = 0;
            j = 0;
            valid = false;
            while (j < this.result.getColCount() && !valid && !found) {
                if (NUtils.ISEQUAL(this.result.getElement(i, j), 0)) {
                    kValid++;
                    if (kValid == this.result.getColCount()) {
                        bValid++;
                    }
                }
                else {
                    if (j == this.result.getColCount()-1 && kValid == this.result.getColCount()-1) {
                        found = true;
                        this.hasSolution = false;
                    }
                    if (j != this.result.getColCount()-1) {
                        valid = true;
                        found = true;
                        this.hasSolution = true;
                    }
                }
                j++;
            }
            if (kValid == this.result.getColCount()-1) {
                found = true;
                this.hasSolution = false;
            }
            if (bValid == this.result.getRowCount()) {
                found = true;
                this.hasSolution = false;
            }
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
