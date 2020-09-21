package mtrx.matrix;

import java.util.HashSet;
import java.util.Set;

import mtrx.type.MatrixTrait;

public abstract class Matrix implements IMatrix {

    protected int row = 0, col = 0;
    protected double[][] data;
    protected boolean hasChanged = true;
    protected Set<MatrixTrait> traits = new HashSet<>();

    /**
     * Constructor
     */
    public Matrix(int row, int col, double[][] data){
        this.row = row;
        this.col = col;
        this.data = data;
        this.updateTrait();
    }
    
}
