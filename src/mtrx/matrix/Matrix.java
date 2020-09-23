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
        this.traits.clear();
        boolean identity = false, allZero = true;
        if (this.getRowCount() == this.getColCount()){
            this.traits.add(MatrixTrait.SQUARE);
            identity = true;
        }
            
        for (int i = 0; i < this.row; i++){
            for (int j = 0; j < this.col; j++){
                allZero = allZero && (this.data[i][j] == 0);
                identity = identity && (this.data[i][j] == (((i == j) ? 1 : 0)));
            }
        }

        if (identity) this.traits.add(MatrixTrait.IDENTITY);
        if (allZero) this.traits.add(MatrixTrait.ZERO);

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

    @Override
    public double cofactor(int row, int col) {
        if (!this.hasTrait(MatrixTrait.SQUARE)) return Double.NaN;
        if (this.hasTrait(MatrixTrait.IDENTITY)) return 1;

        Matrix cf = (new MatrixBuilder(this)).subMatrix(row, col).build();
        int size = cf.getRowCount();

        if (size == 1){
            return cf.getElement(0, 0);
        } else {
            return ((row+col+2)%2==0 ? 1.0D : -1.0D)*cf.determinant();
        }
    }

    @Override
    public double determinant() {
        if (!this.hasTrait(MatrixTrait.SQUARE)) return Double.NaN;
        if (this.hasTrait(MatrixTrait.IDENTITY)) return 1;

        int size = this.row;
        
        /* Supaya kalau 2x2 tidak melewati proses yang ribet
           Melakukan ad-bc (sama saja jika tidak ada if else ini) */
        if (size == 2){
            return (this.getElement(0, 0)*this.getElement(1, 1)) - (this.getElement(0, 1)*this.getElement(1, 0));
        }

        Matrix temp = MatrixBuilder.clone(this);
        int i, j;
    
        float result = 1;
        int swapped = 0;
    
        while (size > 0){
            boolean swap = false;
            i = 0;
            if (temp.getElement(size-1, size-1) == 0){
                while (i < (size-1) && (!swap)){
                    if (temp.getElement(i, size-1) == 0){
                        i++;
                    } else {
                        swap = true;
                    }
                }
            }
            if (i == (size-1) && (size > 1)){
                return 0;
            } else if (swap){
                for(j = 0; j < size; j++){
                    temp.swapRow(i, size-1);
                }
            }
            for (i = 0; i < (size-1); i++){
                for(j=0; j<size; j++){

                    double konstanta = temp.getElement(i, size-1)/temp.getElement(size-1, size-1);
                    temp.rowOperation(i, size-1, (x,y) -> x-(y*konstanta));

                }
            }
            result *= temp.getElement(size-1, size-1);
            swapped = (swapped+(swap ? 1 : 0))%2;
            size--;
        }

        float finalresult = (result*(swapped==0 ? 1.0f : -1.0f));
        return NUtils.PRECISE(finalresult);
    }

    @Override
    public double determinantWithCofactor() {
        if (this.hasTrait(MatrixTrait.ZERO)) return 0;
        for (int i = 0; i < this.col; i++){
            if (this.isAllXinCol(i, 0.0D)) return 0; 
        }
        for (int i = 0; i < this.row; i++){
            if (this.isAllXinRow(i, 0.0D)) return 0; 
        }
        //TODO not yet done
        return 0;
    }

}
