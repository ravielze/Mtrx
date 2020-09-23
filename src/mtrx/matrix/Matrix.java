package mtrx.matrix;

import java.util.HashSet;
import java.util.Set;

import mtrx.trait.MatrixTrait;
import mtrx.utils.NUtils;

public class Matrix implements IMatrix {

    private int row = 0, col = 0;
    private double[][] data;
    private boolean hasChanged = true;
    private Set<MatrixTrait> traits = new HashSet<>();

    /**
     * Constructor
     */
    public Matrix(int row, int col, double[][] data){
        this.row = row;
        this.col = col;
        this.data = new double[row][col];
        for (int i = 0; i < row; i++){
            for (int j = 0; j < col; j++){
                this.data[i][j] = (data.length <= i || data[0].length <= j) ? 0 : data[i][j]; 
            }
        }
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
        return this.col;
    }

    @Override
    public double getElement(int row, int col) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void setElement(int row, int col, double value) {
        this.data[row][col] = value;
    }

    @Override
    public void swapRow(int rowA, int rowB) {
        // TODO Auto-generated method stub

    }

    @Override
    public void swapCol(int colA, int colB) {
        double[] temp = new double[this.row];
        for (int i = 0; i < this.row; i++) {
            temp[i] = this.data[i][colA];
            this.data[i][colA] = this.data[i][colB];
            this.data[i][colB] = temp[i];
        }
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
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.col; j++) {
                this.data[i][j] = NUtils.PRECISE(this.data[i][j]);
            }
        }
    }

    @Override
    public void transpose() {
        // TODO Auto-generated method stub

    }

    @Override
    public Matrix multiply(Matrix otherMatrix) {
        double[][] newData = new double[this.row][otherMatrix.getColCount()];
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < otherMatrix.getColCount(); j++) {
                for (int k = 0; k < this.col; k++) {
                    newData[i][j] += this.getElement(i, k) * otherMatrix.getElement(k, j);
                }
            }
        }
        
        Matrix newMatrix = (new MatrixBuilder(this).changeRow(this.row).
        changeCol(otherMatrix.getColCount()).setValue(newData).build());
        
        return newMatrix;
    }

    @Override
    public boolean findRow(int row, double value) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean findCol(int col, double value) {
        for (int i = 0; i < this.row; i++) {
            if (this.data[i][col] == value) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int countXinCol(int col, double value) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int countXinRow(int row, double value) {
        int count = 0;
        for (int j = 0; j < this.col; j++) {
            if (this.data[row][j] == value) {
                count += 1;
            }
        }
        return count;
    }

    @Override
    public int mostXRow(double value) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int mostXCol(double value) {
        int most = 0, idx = -1;
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.col; j++) {
                int count = countXinCol(j, value);
                if (count > most) {
                    most = count;
                    idx = j;
                }
            }
        }
        return idx;
    }

    @Override
    public int findFirstXinCol(int col, double value) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int findFirstXinRow(int row, double value) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean isAllXinCol(int col, double value) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isAllXinRow(int col, double value) {
        // TODO Auto-generated method stub
        return false;
    }

}
