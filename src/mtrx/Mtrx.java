package mtrx;

import mtrx.matrix.Matrix;
import mtrx.matrix.MatrixBuilder;
import mtrx.methods.Gauss;
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
        Matrix m = (new MatrixBuilder()).fileInput("t.txt").build();
        Gauss g = new Gauss(m);
        enter();
        g.getResult().show();
        SolutionExaminer se = new SolutionExaminer(g);
        /*Matrix m2 = (new MatrixBuilder()).consoleInput(true).build();
        Gauss g = new Gauss(m2);
        Crammer c = new Crammer(m);
        SolutionExaminer se = new SolutionExaminer(c);
        se.showSolution();
        m2.show(false);
        enter();*/
    }

}