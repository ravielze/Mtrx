package mtrx.matrix;

import java.util.HashSet;
import java.util.Set;

import mtrx.trait.MatrixTrait;

public class Matrix implements IMatrix {

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

    @Override
    public int getRowCount() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getColCount() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public double getElement(int row, int col) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void setElement(int row, int col, double value) {
        // TODO Auto-generated method stub

    }

    @Override
    public void swapRow(int rowA, int rowB) {
        // TODO Auto-generated method stub

    }

    @Override
    public void swapCol(int colA, int colB) {
        // TODO Auto-generated method stub

    }

    @Override
    public void show(boolean splFormat) {
        // TODO Auto-generated method stub

    }

    @Override
    public void dump(String fileName, boolean splFormat) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean hasTrait(MatrixTrait mt) {
        if (mt == null) return true;
        if (!hasChanged) updateTrait();

        return this.traits.contains(mt);
    }

    @Override
    public void updateTrait() {
        // TODO Auto-generated method stub

    }

    @Override
    public void rowOperation(int row, int otherRow, MatrixOperation operation) {
        this.hasChanged = true;
        
        for (int j=0; j < this.col; j++){
            this.data[row][j] = operation.operate(this.data[row][j], this.data[otherRow][j]);
        }
    }

    @Override
    public void rowOperation(int row, MatrixOperation operation) {
        this.hasChanged = true;
        
        for (int j=0; j < this.col; j++){
            this.data[row][j] = operation.operate(this.data[row][j], 0);
        }
    }

    @Override
    public void fixPrecision() {
        // TODO Auto-generated method stub

    }

    @Override
    public void transpose() {
        // TODO Auto-generated method stub

    }

    @Override
    public Matrix multiply(Matrix otherMatrix) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean checkRowValue(int row, double value) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean checkColValue(int col, double value) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public int countXCol(int col, double value) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int countXRow(int row, double value) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int mostXRow(double value) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int mostXCol(double value) {
        // TODO Auto-generated method stub
        return 0;
    }
    
}
