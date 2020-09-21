package mtrx.matrix;

import java.util.function.Consumer;

import mtrx.type.MatrixOperation;
import mtrx.type.MatrixTrait;

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
    public void getElement(int row, int col);

    /**
     * Setter Nilai Elemen
     * @param row indeks baris elemen
     * @param col indeks kolom elemen
     * @param value nilai yang diset
     */
    public void setElement(int row, int col, double value);

    /**
     * Menukar baris
     * @param rowA baris yang akan dipindah ke tempat B
     * @param rowB baris yang akan dipindah ke tempat A
     */
    public void swapRow(int rowA, int rowB);

    /**
     * Menukar baris
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
     * Mengecek matrix memiliki suatu sifat
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
     * Melakukan operasi pada setiap elemen di suatu baris
     * @param row baris yang dioperasikan (value, lihat docs MatrixOperation.)
     * @param K konstanta, lihat docs MatrixOperation.
     * @param otherRow baris lain yang dioperasikan (ovalue, lihat docs MatrixOperation.)
     * @param operation operasi yang akan dilakukan, lihat docs MatrixOperation.
     */
    public void rowOperation(int row, int otherRow, double K, MatrixOperation operation);
    
}
