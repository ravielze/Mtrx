package mtrx;

import mtrx.matrix.Matrix;
import mtrx.matrix.MatrixBuilder;
import mtrx.methods.Gauss;
import mtrx.methods.SolutionExaminer;
import mtrx.utils.Base26;

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
        Matrix m = mb.fileInput("test2.txt").build();
        m.show(false);
        enter();
        Gauss g = new Gauss(m);
        g.getResult().show(false);
        enter();
        SolutionExaminer se = new SolutionExaminer(g);
        System.out.println(se.getResult());
        se.showSolution();
        enter();
    }

}