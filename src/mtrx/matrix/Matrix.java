package mtrx.matrix;

import java.util.HashSet;
import java.util.Set;

import mtrx.type.MatrixTrait;

public class Matrix /* implements IMatrix */ {

    private int row, col;
    private float[][] data;
    private Set<MatrixTrait> traits = new HashSet<>();

    /**
     * Constructor
     */
    public Matrix(){

    }
    
}
