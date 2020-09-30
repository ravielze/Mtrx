package mtrx.methods;

import mtrx.augmatrix.AugMatrix;
import mtrx.matrix.Matrix;

public class InverseSPL implements SPLMethods {

    /**
     * Ax=B
     * x=Inverse(A)*B
     */

    private Matrix inverse;
    private AugMatrix aug;
    private boolean hasSolution;
    
    // Asumsi sudah pasti inverse yang valid
    public InverseSPL(final Matrix MAInverse, final Matrix MB){
        this.inverse = MAInverse;
    }

    @Override
    public Matrix getInitialMatrix() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Untuk menyelesaikan SPL dengan metode balikan.
     * @param matrix matrix yang ingin diselesaikan atau matriks B
     * @return Augmented Matriks dengan matriks sebelah kiri identitas, dan kanan hasilnya.
     */
    @Override
    public AugMatrix getResult() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean hasSolution() {
        // TODO Auto-generated method stub
        return false;
    }
    
}
