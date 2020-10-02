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
    private Matrix mb;
    private AugMatrix result;
    private boolean hasSolution;
    
    // Asumsi sudah pasti inverse yang valid
    public InverseSPL(Matrix MAInverse, Matrix MB){
        this.inverse = MAInverse;
        this.mb = MB;
        this.operate();
        this.hasSolution = true;
    }

    public void operate() {
        Matrix mresult = this.inverse.multiply(this.mb);

        Matrix ID = new MatrixBuilder(this.inverse.getColCount()).build();
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
        return this.result;
    }

    @Override
    public boolean hasSolution() {
        return this.hasSolution;
    }
    
}
