package mtrx.matrix;

public interface MatrixOperation {

    /**
     * Operate some operations.
     * @param x value pada baris yang akan diganti
     * @param y value pada baris lain
     * @return the result of operation
     */
    double operate(double x, double y);
    
}
