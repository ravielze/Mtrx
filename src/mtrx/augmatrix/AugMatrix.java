package mtrx.augmatrix;

import mtrx.matrix.Matrix;

public class AugMatrix {

    private Matrix left, right;

    public AugMatrix(Matrix left, Matrix right){
        this.left = left;
        this.right = right;
    }
    
}
