package mtrx.matrix;

import java.util.HashSet;
import java.util.Set;

import mtrx.type.MatrixTrait;

public abstract class Matrix /* implements IMatrix */ {

    private int row = 0, col = 0;
    private float[][] data;
    private boolean hasChanged = true;
    private Set<MatrixTrait> traits = new HashSet<>();

    /**
     * Constructor
     */
    public Matrix(){

    }
    
}
