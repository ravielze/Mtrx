package mtrx.methods;

import mtrx.matrix.MatrixBuilder;
import mtrx.matrix.Matrix;

public class Crammer {
    private Matrix a, b;

    public Crammer(Matrix a, Matrix b) {
        this.a = a;
        this.b = b;
    }

    public void operate() {
        double det = this.a.determinant();
        double[] result = new double[this.a.getColCount()];
        double[] temp = new double[this.b.getRowCount()];

        for (int i = 0; i < this.b.getRowCount(); i++) {
            temp[i] = this.b.getElement(i, 0);
        }

        for (int j = 0; j < this.a.getColCount(); j++) {
            result[j] = ((new MatrixBuilder(this.a)).cutColoumn(j).insertColoumn(j, temp).build()).
            determinant() / det;
        }
    }
}