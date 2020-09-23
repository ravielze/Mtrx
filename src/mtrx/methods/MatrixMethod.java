package mtrx.methods;

import mtrx.matrix.Matrix;

public interface MatrixMethod {

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
    public Matrix getResult();

    /**
     * Mengembalikan apakah punya solusi atau tidak.
     * @return boolean
     */
    public boolean hasSolution();

    /**
     * Mengembalikan jumlah berapa kali ganti baris.
     * @return int
     */
    public int getSwapTimes();
    
}
