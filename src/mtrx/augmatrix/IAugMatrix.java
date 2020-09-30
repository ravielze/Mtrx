package mtrx.augmatrix;

import mtrx.matrix.Matrix;
import mtrx.matrix.MatrixOperation;

public interface IAugMatrix {

    /**
     * Getter Getter Baris/Kolom
     * @return int jumlah baris matriks
     */
    public int getRowCount();

    /**
     * Getter Baris/Kolom
     * @return int jumlah kolom matriks
     */
    public int getColCount();

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
    //public void dump(String fileName, boolean splFormat);

    /**
     * Menukar baris.
     * Mengubah nilai hasChanged menjadi true.
     * @param rowA baris yang akan dipindah ke tempat B
     * @param rowB baris yang akan dipindah ke tempat A
     */
    public void swapRow(int rowA, int rowB);

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
     * Untuk membagi nilai pada suatu baris dengan nilai pada
     * elemen suatu indeks baris & kolom.
     * @param row indeks baris
     * @param col indeks kolom
     */
    public void divideBySingleElement(int row, int col);

    /**
     * Mengeliminasi suatu baris dari suatu elemen
     * ke bawah.
     * Hanya untuk matriks kiri.
     * @param row indeks baris
     * @param col indeks kolom
     */
    public void eliminateFromTop(int row, int col);

    /**
     * Mengeliminasi suatu baris dari suatu elemen
     * ke atas.
     * Hanya untuk matriks kiri.
     * @param row indeks baris
     * @param col indeks kolom
     */
    public void eliminateFromBottom(int row, int col);

    /**
     * Menghitung angka 0 sampai bukan 0 pada
     * suatu baris.
     * @param row indeks baris
     * @return jumlah
     */
    public int countZeroinRowUntilX(int row);

    /**
     * Merapihkan nol, baris yang memiliki nol lebih banyak ditaruh dibawah.
     * @param row
     * @return mengembalikan true jika terjadi penukaran, lainnya false
     */
    public boolean fixZeroRow(int row);

    /**
     * Mencari indeks pertama bernilai X pada suatu kolom
     * @param col indeks kolom
     * @param value nilai yang dicari
     * @return indeks baris yang berisi nilai value pertama, jika tidak ada kembalikan -1
     */
    public int findFirstXinCol(int col, double value);

    /**
     * Mengecek apakah suatu kolom semuanya bernilai X.
     * @param col indeks kolom
     * @param value nilai
     * @return boolean true jika semuanya adalah X, false jika tidak.
     */
    public boolean isAllXinCol(int col, double value);

    /**
     * Mengecek apakah suatu baris semuanya bernilai X.
     * @param row indeks baris
     * @param value nilai
     * @return boolean true jika semuanya adalah X, false jika tidak.
     */
    public boolean isAllXinRow(int row, double value);

}
