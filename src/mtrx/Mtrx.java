package mtrx;

import java.util.Scanner;

import mtrx.matrix.Matrix;
import mtrx.matrix.MatrixBuilder;

public class Mtrx {

    /**
     * Presisi yang akan digunakan oleh program.
     * Defaultnya 0.001 atau 3
     */

    public static void enter(){
        System.out.println();
    }

    public static void main(String args[]){
        MtrxMain.scn = new Scanner(System.in);
        Matrix m = (new MatrixBuilder()).fileInput("rlb.txt").buildAsMR();
        m.show(false);
        enter();
        MtrxMain.scn.close();
        /*Matrix m2 = (new MatrixBuilder()).consoleInput(true).build();
        Gauss g = new Gauss(m2);
        Crammer c = new Crammer(m);
        SolutionExaminer se = new SolutionExaminer(c);
        se.showSolution();
        m2.show(false);
        enter();*/
    }

}