package mtrx.methods;

import mtrx.matrix.Matrix;
import mtrx.matrix.MatrixBuilder;
import mtrx.matrix.MatrixOperation;
import mtrx.trait.MatrixTrait;
import mtrx.utils.NUtils;
import mtrx.augmatrix.AugMatrix;

public class Gauss implements MatrixMethod {

    private AugMatrix result;
    private Matrix originalMatrix;
    private int swapTimes;
    private boolean hasSolution;

    public Gauss(Matrix matrix){
        this.originalMatrix = MatrixBuilder.clone(matrix);
        this.result = new AugMatrix(matrix);
        this.swapTimes = 0;
        this.hasSolution = false;
        this.operate();
    }

    private void operate(){
        int col = 0, row = 0, colZero = 0;
        for (int j = 0; j < this.result.getColCount()-1; j++){
            colZero += (this.result.isAllXinCol(j, 0.0D) ? 1 : 0);
            if (this.result.isAllXinCol(j, 0.0D) && (!this.result.isAllXinCol(j+1, 0.0D))){
                col = j+1;
                break;
            }
        }
        if (col > this.result.getLeft().getColCount() || colZero == this.result.getLeft().getColCount()){
            this.hasSolution = false;
            return;
        }

        for (int i = 0; i < this.result.getRowCount(); i++){
            this.swapTimes += (this.result.fixZeroRow(i) ? 1 : 0);
        }

        while (true){
            if (NUtils.ISNOTEQUAL(this.result.getLeft().getElement(row, col), 1.0D)){
                this.result.divideBySingleElement(row, col);
                this.result.eliminateFromTop(row, col);
                col++;
                row++;
            } else if (NUtils.ISEQUAL(this.result.getLeft().getElement(row, col), 0.0D)){
                col++;
            } else {
                this.result.eliminateFromTop(row, col);
                col++;
                row++;
            }
            if (col >= this.result.getLeft().getColCount() || row >= this.result.getLeft().getRowCount()) break;
        }
        this.hasSolution = true;
    }
/*
	private void operate() {
        int i, l, row, j, ColNotValid, RowNotValid = 0, swapped = -1;
        boolean found, need, go;
        int finalRow = this.result.getLeft().getRowCount();
        int finalCol = this.result.getLeft().getColCount() + 1;

        // swap awal-awal
        for (i = 0; i < finalRow; i++) {
            found = false;
            j = 0;
            if (i == 0) {
                boolean isOneAndMinOne = false;
                boolean isOne = false;
                // jika kolom 0 baris 0 = 1
                if (this.result.getLeft().getElement(i, j) == 1) {
                    isOne = true;
                    // jika kolom 0 baris 0 = -1
                    if (this.result.getLeft().getElement(i, j) != -1) {
                        isOneAndMinOne = true;
                    }
                }
                // jika di baris di bawahnya ada angka 0 && kolom 1 baris 1 bukan 1
                if (this.result.findFirstXinCol(j, 1) > i && !isOne) {
                    found = true;
                    row = this.result.getLeft().findFirstXinCol(j, 1);
                    this.result.swapRow(i, row);
                    this.swapTimes++;
                }
                // menggunakan else karena prioritas 1 > -1 dan tidak bertabrakan
                else {
                    // jika di baris di bawahnya ada angka -1 && kolom 0 baris 0 bukan 1 atau -1
                    if (this.result.findFirstXinCol(j, -1) > i && !isOneAndMinOne) {
                        found = true;
                        row = this.result.getLeft().findFirstXinCol(j, -1);
                        this.result.swapRow(i, row);
                        this.result.rowOperation(i, (x, y) -> x*(-1));
                        this.swapTimes++;
                    }
                }
            }

            if (!found && swapped != i) {
                // jika baris i kolom j adalah angka 0
                if (NUtils.ISEQUAL(this.result.getLeft().getElement(i, j), 0)) {
                    l = finalRow-1;
                    need = false;
                    // dicek dari baris bawah agar yang yang 0 numpuk di bawah
                    while (l >= i+1 && !need) {
                        if (!NUtils.ISEQUAL(this.result.getLeft().getElement(l, j), 0)) {
                            // swapped dikasih nilai l agar nilai l tidak perlu dicek kembali
                            swapped = l;
                            need = true;
                            this.swapTimes++;
                            this.result.swapRow(i, l);
                        }
                        else l--;
                    }
                }
            }
        }

        // operasi menghitung
        for (i = 0; i < finalRow; i++) {
            go = true;
            j = 0;
            // jika elemen baris 0 kolom 0 bukan 1
            if (this.result.getLeft().getElement(0, 0) != 1) {
                this.result.divideBySingleElement(0, 0);
                go = false;
            }
            if (i == 0) go = false;
            // saat go = true, akan menjalankan fungsi-fungsi, seperti mengurangi dari baris atasnya
            while (go && j < finalCol) {
                int index = i;
                need = false;
                // parameternya j < finalCol karena j this.result.getLeft() terbatas
                if (j < finalCol-1) {
                    // jika matriks baris i dan j != 0
                    if (!NUtils.ISEQUAL(this.result.getLeft().getElement(i, j), 0)) {
                        need = true;
                    }
                }
                else {
                    // jika matriks baris i dan j != 0
                    if (!NUtils.ISEQUAL(this.result.getRight().getElement(i, 0), 0)) {
                        need = true;
                    }
                }
                if (need) {
                    // go = false agar berjalan ke baris selanjutnya
                    go = false;
                    // hanya perlu membagi dirinya sendiri
                    if (j == finalCol-1) {
                        Bottom_divSelf(this.result, i);
                    }
                    else {
                        // berikut parameter agar row i dikurangi dengan row i-1
                        // parameternya adalah saat row i-1 kolom j bukan 0 dan jumlah 0 i <= jumlah 0 i-1
                        if (!NUtils.ISEQUAL(this.result.getLeft().getElement(i-1, j), 0) &&
                        ZeroBeforeJ(this.result, i) <= ZeroBeforeJ(this.result, i-1)) {
                            subFromTop(this.result, i, j);
                            // jika sudah dikurangi, dicek apakah akan membentuk eselon
                            index = check_eselon(this.result, i, j);
                            if (index != i) {
                                this.result.swapRow(i, index);
                                this.swapTimes++;
                                go = true;
                                j = 0;
                            }
                            if (this.result.getLeft().getElement(i, j) != 1) go = true;
                        }
                        else {
                            Top_divSelf(this.result, i, j);
                        }
                    }
                }
                if (index == i) j++;
            }
        }
        this.result.fixPrecision();

        // untuk mengecek apakah memiliki solusi atau tidak (dicek dari bawah)
        this.hasSolution = true;
        i = finalRow-1;
        found = false;
        while (i >= 0 && !found) {
            ColNotValid = 0;
            j = 0;
            while (j < finalCol && !found) {
                // conditional hanya agar tidak ada error out of index
                if (j < finalCol-1) {
                    // untuk mengecek berapa jumlah 0 di suatu baris
                    if (NUtils.ISEQUAL(this.result.getLeft().getElement(i, j), 0)) {
                        ColNotValid++;
                    }
                }
                else {
                    // untuk mengecek berapa jumlah 0 di suatu baris
                    if (NUtils.ISEQUAL(this.result.getRight().getElement(i, 0), 0)) {
                        ColNotValid++;
                        // jika semua elemen di suatu row = 0
                        if (ColNotValid == finalCol) {
                            RowNotValid++;
                        }
                    }
                }
                j++;
            }
            // jika semua semua elemen di suatu row = 0 kecuali di kolom paling kanan
            if (ColNotValid == this.matrix.getColCount()-1) {
                found = true;
                this.hasSolution = false;
            }
            // jika semua elemen dari matriks = 0
            if (RowNotValid == this.matrix.getRowCount()) {
                found = true;
                this.hasSolution = false;
            }
            i--;
        }
    }*/

    @Override
    public Matrix getInitialMatrix() {
        return this.originalMatrix;
    }

    @Override
    public AugMatrix getResult() {
        return (this.hasSolution ? this.result : null);
    }

    @Override
    public boolean hasSolution() {
        return this.hasSolution;
    }

    @Override
    public int getSwapTimes() {
        return this.swapTimes;
    }
}
