package mtrx.matrix;

import mtrx.trait.MatrixTrait;

public interface IMatrix {

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
     * Getter Nilai Elemen
     * @param row indeks baris elemen
     * @param col indeks kolom elemen
     */
    public double getElement(int row, int col);

    /**
     * Setter Nilai Elemen.
     * Mengubah nilai hasChanged menjadi true.
     * @param row indeks baris elemen
     * @param col indeks kolom elemen
     * @param value nilai yang diset
     */
    public void setElement(int row, int col, double value);

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

    /**
     * Mengeluarkan matrix ke dalam layar
     * @param splFormat jika true mengeluarkan dalam bentuk SPL (aX1+bX2+...=k)
     *                  jika false mengeluarkan dalam bentuk tabel
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
     * Mengecek matrix memiliki suatu sifat.
     * Jika field hasChanged false, langsung kembalikan hasil.
     * Jika filed hasChanged true, gunakan prosedur updateTrait()
     * terlebih dahulu.
     * @param mt Trait matriks yang ingin diperiksa
     * @return boolean true jika iya, false jika tidak
     */
    public boolean hasTrait(MatrixTrait mt);

    /**
     * Prosedur untuk mengupdate sifat suatu matriks.
     * Dipanggil di setiap fungsi yang mengubah isi
     * suatu matriks.
     */
    public void updateTrait();

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
     * Mentranspose matriks.
     * Mengubah nilai hasChanged menjadi true.
     */
    public void transpose();

    /**
     * Mengalikan matrix ini dengan matrix lain. 
     * @param otherMatrix Matrix baru hasil perkalian.
     */
    public Matrix multiply(Matrix otherMatrix);

    /**
     * Mencari suatu baris bernilai sesuatu.
     * @param row indeks baris yang ingin dicek.
     * @param value nilainya.
     * @return boolean true jika satu baris bernilai value, lainnya false.
     */
    public boolean findRow(int row, double value);

    /**
     * Mencari suatu kolom bernilai sesuatu.
     * @param col indeks kolom yang ingin dicek.
     * @param value nilainya.
     * @return boolean true jika satu kolom bernilai value, lainnya false.
     */
    public boolean findCol(int col, double value);

    /**
     * Mencari indeks pertama bernilai X pada suatu kolom
     * @param col indeks kolom
     * @param value nilai yang dicari
     * @return indeks baris yang berisi nilai value pertama, jika tidak ada kembalikan -1
     */
    public int findFirstXinCol(int col, double value);

    /**
     * Mencari indeks pertama bernilai X pada suatu baris
     * @param row indeks baris
     * @param value nilai yang dicari
     * @return indeks kolom yang berisi nilai value pertama, jika tidak ada kembalikan -1
     */
    public int findFirstXinRow(int row, double value);

    /**
     * Mengecek apakah suatu kolom semuanya bernilai X.
     * @param col indeks kolom
     * @param value nilai
     * @return boolean true jika semuanya adalah X, false jika tidak.
     */
    public boolean isAllXinCol(int col, double value);

    /**
     * Mengecek apakah suatu baris semuanya bernilai X.
     * @param col indeks baris
     * @param value nilai
     * @return boolean true jika semuanya adalah X, false jika tidak.
     */
    public boolean isAllXinRow(int col, double value);
    
    /**
     * Mengecek frekuensi value pada kolom tertentu.
     * @param col indeks kolom yang ingin dicek.
     * @param value nilainya.
     * @return ada berapa kali kemunculan value pada kolom col.
     */
    public int countXinCol(int col, double value);

    /**
     * Mengecek frekuensi value pada baris tertentu.
     * @param col indeks baris yang ingin dicek.
     * @param value nilainya.
     * @return ada berapa kali kemunculan value pada baris row.
     */
    public int countXinRow(int row, double value);

    /**
     * Mengembalikan indeks baris yang memiliki value terbanyak.
     * @param value nilai yang dicari
     * @return indeks baris dengan value terbanyak.
     */
    public int mostXRow(double value);

    /**
     * Mengembalikan indeks kolom yang memiliki value terbanyak.
     * @param value nilai yang dicari
     * @return indeks kolom dengan value terbanyak.
     */
    public int mostXCol(double value);
    
}
