package mtrx.methods;

import mtrx.augmatrix.AugMatrix;
import mtrx.matrix.Matrix;
import mtrx.matrix.MatrixBuilder;

public class InverseSPL implements SPLMethods {

    /**
     * Ax=B
     * x=Inverse(A)*B
     */

    private Matrix inverse;
    private AugMatrix aug;
    private AugMatrix result;
    private boolean hasSolution;
    
    // Asumsi sudah pasti inverse yang valid
    public InverseSPL(Matrix MAInverse, Matrix MB){
        this.inverse = MAInverse;
        this.aug = new AugMatrix(MAInverse, MB);
        operate();
        this.hasSolution = true;
    }

    public void operate() {
        Matrix mresult = new MatrixBuilder(this.aug.getRowCount(), 1).build();
        int x;
        for (int i = 0; i < this.aug.getRowCount(); i++) {
            x = 0;
            for (int j = 0; j < this.aug.getLeft().getColCount(); j++) {
                x += (this.aug.getLeft().getElement(i, j) * this.aug.getRight().getElement(j, 0));
            }
            mresult.setElement(i, 0, x);
        }

        Matrix ID = new MatrixBuilder(this.aug.getRowCount()).build();
        this.result = new AugMatrix(ID, mresult);
    }

    @Override
    public Matrix getInitialMatrix() {
        return this.inverse;
    }

    /**
     * Untuk menyelesaikan SPL dengan metode balikan.
     * @param matrix matrix yang ingin diselesaikan atau matriks B
     * @return Augmented Matriks dengan matriks sebelah kiri identitas, dan kanan hasilnya.
     */
    @Override
    public AugMatrix getResult() {
        return (this.hasSolution() ? this.result : null);
    }

    @Override
    public boolean hasSolution() {
        return this.hasSolution;
    }
    
}
