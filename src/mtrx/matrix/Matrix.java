package mtrx.matrix;

import java.util.HashSet;
import java.util.Set;

import mtrx.trait.MatrixTrait;
import mtrx.utils.Base26;
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
        return this.row;
    }

    @Override
    public int getColCount() {
        return this.col;
    }

    @Override
    public double getElement(int row, int col) {
        return this.data[row][col];
    }

    @Override
    public void setElement(int row, int col, double value) {
        this.data[row][col] = value;
    }

    @Override
    public void swapRow(int rowA, int rowB) {
        double[] temp = new double[this.col];
        for (int i = 0; i < this.col; i++) {
            temp[i] = this.data[rowA][i];
            this.data[rowA][i] = this.data[rowB][i];
            this.data[rowB][i] = temp[i];
        }

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
        if (splFormat) {
            for (int i = 0; i < this.row; i++) {
                for (int j = 0; j < this.col; j++) {
                    if (j != this.col - 1) {
                        System.out.printf("%s%s ", NUtils.TOSTRING(this.data[i][j]), Base26.toBase26(j));
                    } else {
                        System.out.println("= " + this.data[i][j]);
                    }
                }
            }
        } else {
            for (int i = 0; i < this.row; i++) {
                for (int j = 0; j < this.col; j++) {
                    System.out.printf("%s ", NUtils.TOSTRING(this.data[i][j]));
                }
                System.out.println();
            }
        }
    }

    /*@Override
    public void dump(String fileName, boolean splFormat) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < this.row; i++){
			for (int j = 0; j < this.col; j++){
				builder.append(this.data[i][j]);
				if (j < this.col - 1){
					builder.append(" ");
				}
			}
			if (i != this.row){
				builder.append("\n");
			}
        }
        try {
            File file = new File(new File("../test/"+fileName).getCanonicalPath());
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(builder.toString());
            writer.close();
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }*/

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
                allZero = allZero && (NUtils.ISEQUAL(this.data[i][j], 0.0D));
                identity = identity && (NUtils.ISEQUAL(this.data[i][j], ((i == j) ? 1 : 0)));
            }
        }

        if (identity) this.traits.add(MatrixTrait.IDENTITY);
        if (allZero) this.traits.add(MatrixTrait.ZERO);

    }

    @Override
    public void rowOperation(int row, int otherRow, MatrixOperation operation) {
        this.hasChanged = true;
        
        for (int j=0; j < this.col; j++){
            this.data[row][j] = NUtils.PRECISE(operation.operate(this.data[row][j], this.data[otherRow][j]));
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
        this.hasChanged = true;

        double[][] Tdata = new double[this.col][this.row];
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.col; j++) {
                Tdata[j][i] = this.data[i][j];
            }
        }

        this.data = new double[this.col][this.row];
        this.data = Tdata;
        // **********TOLONG DICEK***********
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
        boolean allVal = true;
        int i = 0;
        while (allVal) {
            if (NUtils.ISNOTEQUAL(this.data[row][i], value)) allVal = false;
            i++;
        }
        return allVal;
    }

    @Override
    public boolean findCol(int col, double value) {
        for (int i = 0; i < this.row; i++) {
            if (NUtils.ISEQUAL(this.data[i][col], value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int countXinCol(int col, double value) {
        int count = 0;

        for (int i = 0; i < this.row; i++) {
            if (NUtils.ISEQUAL(this.data[i][col], value)) {
                count += 1;
            }
        }
        return count;
    }

    @Override
    public int countXinRow(int row, double value) {
        int count = 0;
        for (int j = 0; j < this.col; j++) {
            if (NUtils.ISEQUAL(this.data[row][j], value)) {
                count += 1;
            }
        }
        return count;
    }

    @Override
    public int mostXRow(double value) {
        int most = 0, idx = -1;
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.col; j++) {
                int count = countXinRow(i, value);
                if (count > most) {
                    most = count;
                    idx = i;
                }
            }
        }
        return idx;
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
        int idx = -1;
        
        for (int i = 0; i < this.row; i++) {
            if (NUtils.ISEQUAL(this.data[i][col], value)) {
                idx = i;
                break;
            }
        }
        return idx;
    }

    @Override
    public int findFirstXinRow(int row, double value) {
        int idx = -1;
        
        for (int i = 0; i < this.col; i++) {
            if (NUtils.ISEQUAL(this.data[row][i], value)) {
                idx = i;
                break;
            }
        }
        return idx;
    }

    @Override
    public boolean isAllXinCol(int col, double value) {
        boolean isAllX = true;

        for (int i = 0; i < this.row; i++) {
            if (NUtils.ISNOTEQUAL(this.data[i][col], value)) {
                isAllX = false;
                break;
            }
        }
        return isAllX;
    }

    @Override
    public boolean isAllXinRow(int row, double value) {
        boolean isAllX = true;

        for (int i = 0; i < this.col; i++) {
            if (NUtils.ISNOTEQUAL(this.data[row][i], value)) {
                isAllX = false;
                break;
            }
        }
        return isAllX;
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
        int i;
    
        float result = 1;
        int swapped = 0;
        while (size > 0){
            boolean swap = false;
            i = 0;
            if (NUtils.ISEQUAL(temp.getElement(size-1, size-1), 0.0D)){
                while (i < (size-1) && (!swap)){
                    if (NUtils.ISEQUAL(temp.getElement(i, size-1), 0.0D)){
                        i++;
                    } else {
                        swap = true;
                    }
                }
            }
            if (i == (size-1) && (size > 1)){
                return 0;
            } else if (swap){
                temp.swapRow(i, size-1);
            }
            for (i = 0; i < (size-1); i++){
                double konstanta = temp.getElement(i, size-1)/temp.getElement(size-1, size-1);
                temp.rowOperation(i, size-1, (x,y) -> x-(y*konstanta));
            }
            result *= temp.getElement(size-1, size-1);
            swapped = (swapped+(swap ? 1 : 0))%2;
            size--;
        }

        double finalresult = (result*(swapped==0 ? 1.0D : -1.0D));
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

        double result = 0.0D;
        if (this.mostXCol(0.0D) != -1){
            int j = this.mostXCol(0.0D);
            for (int i = 0; i < this.row; i++){
                result += this.getElement(i, j)*cofactor(i, j);
            }
        } else {
            int i = (this.mostXRow(0.0D) != -1) ? this.mostXRow(0.0D) : 0;
            for (int j = 0; j < this.col; j++){
                result += this.getElement(i, j)*cofactor(i, j);
            }
        }
        return result;
    }

    public Matrix adjoint() {
        Matrix result = (new MatrixBuilder(this)).build();

        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.col; j++) {
                result.setElement(i, j, cofactor(i, j));
            }
        }

        result.transpose();
        return result;
    }

    public Matrix inverse() {
        Matrix origin = (new MatrixBuilder(this)).build();
        Matrix result = origin.adjoint();
        double newEl;

        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.col; j++) {
                newEl = result.getElement(i, j) / origin.determinant();
                result.setElement(i, j, NUtils.PRECISE(newEl));
            }
        }

        return result;
    }
}
