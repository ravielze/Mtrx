package mtrx.augmatrix;

import mtrx.matrix.Matrix;
import mtrx.matrix.MatrixBuilder;
import mtrx.matrix.MatrixOperation;
import mtrx.utils.NUtils;

public class AugMatrix implements IAugMatrix {

    private Matrix left, right;

    /**
     * Constructor dengan dua matrix.
     * Bisa dipakai untuk inverse.
     * Kalau SPL matrix kanan harus 1 kolom saja.
     * @param left matrix kanan
     * @param right matrix kiri
     */
    public AugMatrix(Matrix left, Matrix right) {
        this.left = left;
        this.right = right;
    }

    /**
     * Constructor dengan matrix biasa.
     * Digunakan untuk SPL.
     * @param matrix
     */
    public AugMatrix(Matrix matrix){
        this.left = (new MatrixBuilder(matrix)).cutColoumn(matrix.getColCount()-1).build();
        this.right = (new MatrixBuilder(matrix)).cutMultiColoumns(0, matrix.getColCount()-2).build();
    }

    @Override
    public Matrix getLeft() {
        return this.left;
    }

    @Override
    public Matrix getRight() {
        return this.right;
    }

    @Override
    public void show() {
        int i, j;
        for (i = 0; i < this.left.getRowCount(); i++) {
            for (j = 0; j < this.left.getColCount(); j++) {
                System.out.printf("%s ", NUtils.TOSTRING(this.left.getElement(i, j)));
            }
            System.out.printf("| %s\n", NUtils.TOSTRING(this.right.getElement(i, 0)));
        }
    }

    @Override
    public void swapRow(int rowA, int rowB) {
        this.left.swapRow(rowA, rowB);
        this.right.swapRow(rowA, rowB);
    }

    @Override
    public void rowOperation(int row, int otherRow, MatrixOperation operation) {       
        this.left.rowOperation(row, otherRow, operation);
        this.right.rowOperation(row, otherRow, operation);
    }

    @Override
    public void rowOperation(int row, MatrixOperation operation) {
        this.left.rowOperation(row, operation);
        this.right.rowOperation(row, operation);
    }

    @Override
    public void fixPrecision() {
        this.left.fixPrecision();
        this.right.fixPrecision();
    }

    @Override
    public void divideBySingleElement(int row, int col) {
        double val;
        if (col < this.left.getColCount()){
            val = this.left.getElement(row, col);
        } else {
            val = this.right.getElement(row, col-this.left.getColCount());
        }
        this.rowOperation(row, (x,y) -> x/val);
    }

    @Override
    public int findFirstXinCol(int col, double value){
        return (col < this.left.getColCount() ? 
            this.left.findFirstXinCol(col, value) : this.right.findFirstXinCol(col-this.left.getColCount(), value));
    }

    @Override
    public boolean isAllXinCol(int col, double value){
        return (col < this.left.getColCount() ? 
            this.left.isAllXinCol(col, value) : this.right.isAllXinCol(col-this.left.getColCount(), value));
    }

    @Override
    public void eliminateFromTop(int row, int col) {
        for (int i = row+1; i < this.left.getRowCount(); i++) {
            double val = this.left.getElement(i, col) / this.left.getElement(row, col);
            this.rowOperation(i, row, (x, y) -> x - val*y);
        }
    }

    @Override
    public void eliminateFromBottom(int row, int col) {
        for (int i = row-1; i >= 0; i--) {
            double val = this.left.getElement(i, col) / this.left.getElement(row, col);
            this.rowOperation(i, row, (x, y) -> x - val*y);
        }
    }
    
    @Override
    public int countZeroinRowUntilX(int row){
        int count = 0;
        boolean found = false;
        int j = 0;
        while (j <= this.left.getColCount() && !found) {
            if (j < this.left.getColCount()) {
                if (NUtils.ISEQUAL(this.left.getElement(row, j), 0.0D)) {
                    count++;
                }
                else {
                    found = true;
                }
            } else if (NUtils.ISEQUAL(this.right.getElement(row, 0), 0.0D)) {
                count++;
            }
            j++;
        }
        return count;
    }

    @Override
    public boolean fixZeroRow(int row) {
        int zero = countZeroinRowUntilX(row);
        int idx = row;
        for (int i = this.left.getRowCount()-1; i >= row+1; i--) {
            int now = countZeroinRowUntilX(i);
            if (now < zero) {
                zero = now;
                idx = i;
            }
        }
        if (idx != row){
            this.left.swapRow(idx, row);
            this.right.swapRow(idx, row);
        }
        return (idx != row);
    }

    @Override
    public int getRowCount() {
        return this.left.getRowCount();
    }

    @Override
    public int getColCount() {
        return this.left.getColCount()+this.right.getColCount();
    }

    @Override
    public boolean isAllXinRow(int row, double value) {
        return this.left.isAllXinRow(row, value) && this.right.isAllXinRow(row, value);
    }

}
