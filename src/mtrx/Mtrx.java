package mtrx;

import mtrx.matrix.Matrix;
import mtrx.matrix.MatrixBuilder;
import mtrx.methods.GaussJordan;
import mtrx.methods.SolutionExaminer;

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
        Matrix m = mb.fileInput("terkutuk.txt").build();
        m.show(false);
        enter();
        GaussJordan g = new GaussJordan(m);
        g.getResult().show(false);
        enter();
        SolutionExaminer se = new SolutionExaminer(g);
        se.showSolution();
        enter();
    }

}