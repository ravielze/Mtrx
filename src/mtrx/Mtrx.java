package mtrx;

import mtrx.matrix.MatrixOperation;

public class Mtrx {

    /**
     * Presisi yang akan digunakan oleh program.
     * Defaultnya 0.001 atau 3
     */
    public static final int PRECISION = 3;

    public static void main(String args[]){
        System.out.println("Hello World!");
        
        MatrixOperation mo = (double a, double b) -> a+2*b;
        System.out.println(mo.operate(5, 3));
    }

}