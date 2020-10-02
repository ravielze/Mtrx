package mtrx;

import java.util.Scanner;

import mtrx.matrix.Matrix;
import mtrx.matrix.MatrixBuilder;
import mtrx.utils.NUtils;

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
        MtrxMain.scn = new Scanner(System.in);
        try {
            Matrix m = (new MatrixBuilder()).fileInput("int.txt").buildAsInterpolation();
            m.show(true);
        } catch (Exception ignored){ignored.printStackTrace();}
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