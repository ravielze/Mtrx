package mtrx;

import mtrx.utils.Base26;
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
        System.out.println(NUtils.TOSTRING(3511.332D));
        System.out.println(NUtils.TOSTRING(12.5D));
        System.out.println(NUtils.TOSTRING(0.0003D*-1.0D));
        System.out.println(NUtils.TOSTRING(1.0D));
        System.out.println(NUtils.TOSTRING(1.01D));
        System.out.println(NUtils.TOSTRING(1.001D));
        System.out.println(Base26.toBase26(1));
        System.out.println(Base26.toBase26(5));
        System.out.println(Base26.toBase26(25));
        System.out.println(Base26.toBase26(27));
        System.out.println(Base26.toBase26(28));
        System.out.println(Base26.toBase26(29));
        System.out.println(Base26.toBase26(100));
        System.out.println(Base26.toBase26(26*26*26+26*26+28));
        /*Matrix m2 = (new MatrixBuilder()).consoleInput(true).build();
        Gauss g = new Gauss(m2);
        Crammer c = new Crammer(m);
        SolutionExaminer se = new SolutionExaminer(c);
        se.showSolution();
        m2.show(false);
        enter();*/
    }

}