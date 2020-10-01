package mtrx.methods;

import mtrx.matrix.Matrix;
import mtrx.trait.MatrixTrait;

public class InverseGaussJordan extends GaussJordan {

    private boolean hasSolution = true;

    public InverseGaussJordan(Matrix matrix) {
        super(matrix);
        if ((!this.getResult().getLeft().hasTrait(MatrixTrait.IDENTITY))){
            this.hasSolution = false;
        }
    }

    public Matrix getInvers(){
        return this.getResult().getRight();
    }

    @Override
    public boolean hasSolution(){
        return this.hasSolution;
    }
    

}
