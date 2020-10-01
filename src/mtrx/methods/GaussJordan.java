package mtrx.methods;

import mtrx.augmatrix.AugMatrix;
import mtrx.matrix.Matrix;
import mtrx.matrix.MatrixBuilder;
import mtrx.utils.NUtils;

public class GaussJordan implements GaussMethod {

    private AugMatrix result;
    private Matrix originalMatrix;
    private boolean hasSolution;

    public GaussJordan(Matrix matrix){
        this.originalMatrix = MatrixBuilder.clone(matrix);
        this.result = new AugMatrix(matrix);
        this.hasSolution = false;
        this.operate();
    }

    private void operate(){
        int col = 0, row = 0, colZero = 0;
        for (int j = 0; j < this.result.getColCount()-1; j++){
            colZero += (this.result.isAllXinCol(j, 0.0D) ? 1 : 0);
            if (this.result.isAllXinCol(j, 0.0D) && (!this.result.isAllXinCol(j+1, 0.0D))){
                col = j+1;
                break;
            }
        }
        if (col > this.result.getLeft().getColCount() || colZero == this.result.getLeft().getColCount()){
            this.hasSolution = false;
            return;
        }

        while (true){
            if (NUtils.ISEQUAL(this.result.getLeft().getElement(row, col), 0.0D)){
                col++;
            } else if (NUtils.ISNOTEQUAL(this.result.getLeft().getElement(row, col), 1.0D)){
                this.result.divideBySingleElement(row, col);
                this.result.eliminateFromTop(row, col);
                this.result.eliminateFromBottom(row, col);
                col++;
                row++;
            } else {
                this.result.eliminateFromTop(row, col);
                this.result.eliminateFromBottom(row, col);
                col++;
                row++;
            }
            for (int i = 0; i < this.result.getRowCount(); i++){
                this.result.fixZeroRow(i);
            }
            if (col >= this.result.getLeft().getColCount() || row >= this.result.getLeft().getRowCount()) break;
        }
        this.hasSolution = true;
    }

    @Override
    public Matrix getInitialMatrix() {
        return this.originalMatrix;
    }

    @Override
    public AugMatrix getResult() {
        return this.result;
    }

    @Override
    public boolean hasSolution() {
        return this.hasSolution;
    }

}
