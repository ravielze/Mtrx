package mtrx;

import mtrx.matrix.Matrix;
import mtrx.matrix.MatrixBuilder;
import mtrx.methods.Gauss;

public class Mtrx {

    /**
     * Presisi yang akan digunakan oleh program.
     * Defaultnya 0.001 atau 3
     */
    public static final int PRECISION = 3;

    public static void main(String args[]){
        MatrixBuilder mb = new MatrixBuilder();
        Matrix m = mb.fileInput("terkutuk.txt").build();
        m.show(false);
        System.out.println();
        m.show(false);
        System.out.println();
        System.out.println(m.determinant());
        System.out.println(m.determinantWithCofactor());
        System.out.println();
        Gauss g = new Gauss(m);
        g.getResult().show(true);
    }

}