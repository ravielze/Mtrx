package mtrx.augmatrix;

import mtrx.matrix.Matrix;
import mtrx.matrix.MatrixBuilder;
import mtrx.matrix.MatrixOperation;

public class AugMatrix implements IAugMatrix {

    private Matrix left, right;

    /**
     * Constructor dengan dua matrix.
     * Bisa dipakai untuk inverse atau SPL.
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
     * Digunakan untuk SPL dan semacamnya.
     * @param matrix
     */
    public AugMatrix(Matrix matrix){

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
    public void show(boolean splFormat) {
        int i, j;
        if (splFormat) {
            for (i = 0; i < this.left.getColCount(); i++) {
                for (j = 0; j < this.left.getRowCount(); j++) {
                    System.out.print(this.left.getElement(i, j) + "X" + (j+1) + " ");
                    if (j < this.left.getRowCount()-1) System.out.print("+");
                }
                System.out.printf("= %lf\n", this.right.getElement(i, 0));
            }
        }
        else {
            for (i = 0; i < this.left.getColCount(); i++) {
                for (j = 0; j < this.left.getRowCount(); j++) {
                    System.out.printf("%.4lf ", this.left.getElement(i, j));
                    System.out.printf("%.4lf\n", this.right.getElement(i, j));
                }
            }
        }
    }

    @Override
    public void dump(String fileName, boolean splFormat) {
        // TODO Auto-generated method stub

    }

    @Override
    public void swapRow(int rowA, int rowB) {
        this.left.swapRow(rowA, rowB);
        this.right.swapRow(rowA, rowB);
    }

    @Override
    public void swapCol(int colA, int colB) {
        // TODO Auto-generated method stub

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
    
}
