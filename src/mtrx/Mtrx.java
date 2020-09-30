package mtrx;

import mtrx.matrix.Matrix;
import mtrx.matrix.MatrixBuilder;
import mtrx.methods.Crammer;
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
        Matrix m = mb.fileInput("percobaan aug.txt").build();
        // m.show(false);
        // Matrix m2 = (new MatrixBuilder()).consoleInput(true).build();
        // m2.show(false);
        Crammer c = new Crammer(m);
        c.getResult();
        enter();
        SolutionExaminer se = new SolutionExaminer(c);
        se.showSolution();
        enter();
    }

}