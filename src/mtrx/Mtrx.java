package mtrx;

import mtrx.matrix.Matrix;
import mtrx.matrix.MatrixBuilder;

public class Mtrx {

    /**
     * Presisi yang akan digunakan oleh program.
     * Defaultnya 0.001 atau 3
     */
    public static final int PRECISION = 3;

    public static void main(String args[]){
        MatrixBuilder mb = new MatrixBuilder();
        Matrix m = mb.fileInput("test.txt").build();
        m.show(false);
        m.rowOperation(1, (x,y) -> 2*x);
        System.out.println();
        Matrix m2 = m.multiply(m);
        m.show(false);
        System.out.println();
        System.out.println(m.determinant());
        System.out.println(m.determinantWithCofactor());
        System.out.println(m2.determinant());
    }

}