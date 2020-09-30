package mtrx.methods;

import mtrx.augmatrix.AugMatrix;
import mtrx.matrix.Matrix;

public interface SPLMethods {

    /**
     * Mengembalikan matriks awal.
     * @return Matriks Awal sebelum dioperasikan
     */
    public Matrix getInitialMatrix();

    /**
     * Mengembalikan matriks akhir.
     * @return Matriks Akhir setelah dioperasikan.
     * Bernilai null apabila tidak punya solusi.
     */
    public AugMatrix getResult();

    /**
     * Mengembalikan apakah punya solusi atau tidak.
     * @return boolean
     */
    public boolean hasSolution();
    
}
