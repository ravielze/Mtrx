package mtrx;

import mtrx.matrix.Matrix;
import mtrx.matrix.MatrixBuilder;

public class Mtrx {

    /**
     * Presisi yang akan digunakan oleh program.
     * Defaultnya 0.001 atau 3
     */
    public static final int PRECISION = 3;

    public static void enter(){
        System.out.println();
    }

    public static void main(String args[]){
        MatrixBuilder mb = new MatrixBuilder();
        Matrix m = mb.fileInput("t.txt").build();
        m.show(false);
        enter();
    }

}