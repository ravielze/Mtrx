package mtrx.augmatrix;

import mtrx.matrix.Matrix;
import mtrx.matrix.MatrixOperation;

public interface IAugMatrix {

    /**
     * Mendapatkan matrix sebelah kiri
     * @return Matrix
     */
    public Matrix getLeft();

    /**
     * Mendapatkan matrix sebelah kanan
     * @return Matrix
     */
    public Matrix getRight();

    /**
     * Mengeluarkan matrix ke dalam layar
     * @param splFormat jika true mengeluarkan dalam bentuk SPL (aX1+bX2+...=k)
     *                  jika false mengeluarkan dalam bentuk tabel dengan separasi '|'
     */
    public void show(boolean splFormat);

    /**
     * Mengeluarkan matrix ke suatu file
     * @param fileName nama file 
     * @param splFormat jika true mengeluarkan dalam bentuk SPL (aX1+bX2+...=k)
     *                  jika false mengeluarkan dalam bentuk tabel
     */
    public void dump(String fileName, boolean splFormat);

    /**
     * Menukar baris.
     * Mengubah nilai hasChanged menjadi true.
     * @param rowA baris yang akan dipindah ke tempat B
     * @param rowB baris yang akan dipindah ke tempat A
     */
    public void swapRow(int rowA, int rowB);

    /**
     * Menukar baris.
     * Mengubah nilai hasChanged menjadi true.
     * @param colA baris yang akan dipindah ke tempat B
     * @param colB baris yang akan dipindah ke tempat A
     */
    public void swapCol(int colA, int colB);

    //TODO augmatrix trait

    /**
     * Melakukan operasi pada setiap elemen di suatu baris dengan baris lain.
     * Mengubah nilai hasChanged menjadi true.
     * @param row baris yang dioperasikan 
     * @param otherRow baris lain yang dioperasikan
     * @param operation operasi yang akan dilakukan, lihat docs MatrixOperation.
     */
    public void rowOperation(int row, int otherRow, MatrixOperation operation);

    /**
     * Melakukan operasi pada setiap elemen di suatu baris dengan konstanta.
     * Mengubah nilai hasChanged menjadi true.
     * @param row baris yang dioperasikan
     * @param operation operasi yang akan dilakukan, lihat docs MatrixOperation.
     */
    public void rowOperation(int row, MatrixOperation operation);
    
    /**
     * Prosedur untuk memperbaiki presisi nilai 
     * setiap elemen pada matriks.
     */
    public void fixPrecision();


    /**
     * Mengecek apakah suatu baris semuanya bernilai X
     * @param row
     * @param value
     */
    public void isAllXinRow(int row, double value);
}
