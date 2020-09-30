package mtrx.methods;

import mtrx.augmatrix.AugMatrix;
import mtrx.matrix.Matrix;
import mtrx.matrix.MatrixBuilder;
import mtrx.trait.MatrixTrait;

public class Crammer implements SPLMethods {
    private AugMatrix aug;
    private AugMatrix result;
    private boolean hasSolution;
    private Matrix originalMatrix;

    public Crammer(Matrix matrix) {
        this.originalMatrix = matrix;
        this.aug = new AugMatrix(matrix);
        if (this.aug.getLeft().hasTrait(MatrixTrait.SQUARE)){
            this.operate();
            this.hasSolution = true;
        } else {
            this.hasSolution = false;
        }
    }

    public void operate() {
        double det = this.aug.getLeft().determinant();
        double[] result = new double[this.aug.getLeft().getColCount()];
        double[] temp = new double[this.aug.getRowCount()];

        for (int i = 0; i < this.aug.getRowCount(); i++) {
            temp[i] = this.aug.getRight().getElement(i, 0);
        }

        for (int j = 0; j < this.aug.getLeft().getColCount(); j++) {
            result[j] = ((new MatrixBuilder(this.aug.getLeft())).cutColoumn(j).insertColoumn(j, temp).build()).
            determinant() / det;
        }
        Matrix mresult = new MatrixBuilder(this.aug.getLeft().getColCount()).insertColoumn(this.aug.getLeft().getColCount(), result).build();
        this.result = new AugMatrix(mresult);
    }

    @Override
    public Matrix getInitialMatrix() {
        return this.originalMatrix;
    }

    @Override
    public AugMatrix getResult() {
        return (this.hasSolution() ? this.result : null);
    }

    @Override
    public boolean hasSolution() {
        return this.hasSolution;
    }
}