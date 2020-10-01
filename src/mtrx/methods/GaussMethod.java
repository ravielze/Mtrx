package mtrx.methods;

import mtrx.matrix.Matrix;
import mtrx.augmatrix.AugMatrix;

public interface GaussMethod {

    /**
     * Mengembalikan matriks awal.
     * @return Matriks Awal sebelum dioperasikan
     */
    public Matrix getInitialMatrix();

    /**
     * Mengembalikan matriks akhir.
     * @return Matriks Akhir setelah dioperasikan.
     */
    public AugMatrix getResult();

    /**
     * Mengembalikan apakah punya solusi atau tidak.
     * @return boolean
     */
    public boolean hasSolution();
    
}
