package mtrx.methods;

import mtrx.matrix.Matrix;
import mtrx.matrix.MatrixBuilder;
import mtrx.matrix.MatrixOperation;
import mtrx.trait.MatrixTrait;
import mtrx.utils.NUtils;
import mtrx.augmatrix.AugMatrix;

public class Gauss implements MatrixMethod {

    private Matrix matrix;
    private AugMatrix result;
    private int swapTimes;
    private boolean hasSolution;
    private Matrix temp_left;
    private Matrix temp_right;

    public Gauss(Matrix matrix){
        this.matrix = (new MatrixBuilder(matrix)).build();
        this.temp_left = (new MatrixBuilder(this.matrix.getRowCount(), this.matrix.getColCount()-1)).build();
        this.temp_right = (new MatrixBuilder(this.matrix.getRowCount(), 1)).build();
        this.result = new AugMatrix(this.temp_left, this.temp_right);
        this.swapTimes = 0;
        this.hasSolution = false;
        this.operate();
    }

    // fungsi agar nilai elemen baris row kolom col menjadi 1 (berlaku untuk bukan baris terakhis)
    private void Top_divSelf(AugMatrix aug, int row, int col) {
        double val = aug.getLeft().getElement(row, col);
        aug.rowOperation(row, (x, y) -> x/val);
    }

    // fungsi untuk mengurangi dari row sampai finalRow-1 dengan row-1 (supaya bawahnya jadi 0 semua)
    private void subFromTop (AugMatrix aug, int row, int col) {
        double top = aug.getLeft().getElement(row-1, col);
        int idx = row-1;
        for (int i = row; i < aug.getLeft().getRowCount(); i++) {
            double val = aug.getLeft().getElement(i, col) / top;
            aug.rowOperation(i, idx, (x, y) -> x - val*y);
        }
    }

    // fungsi agar nilai elemen baris row kolom col menjadi 1 (berlaku untuk baris terakhir doang)
    private void Bottom_divSelf (AugMatrix aug, int row) {
        double val = aug.getRight().getElement(row, 0);
        aug.rowOperation(row, (x, y) -> x/val);
    }

    // fungsi untuk menghitung ada berapa 0 sebelum kolom j
    private int ZeroBeforeJ (AugMatrix aug, int row) {
        int count = 0;
        boolean found = false;
        int j = 0;
        while (j <= aug.getLeft().getColCount() && !found) {
            // conditional hanya untuk mencegah error out of index
            if (j < aug.getLeft().getColCount()) {
                if (NUtils.ISEQUAL(aug.getLeft().getElement(row, j), 0)) {
                    count++;
                }
                else found = true;
            }
            else {
                if (NUtils.ISEQUAL(aug.getRight().getElement(row, 0), 0)) count++;
            }
            j++;
        }
        return count;
    }

    // fungsi untuk mengecek apakah matriks akan membentuk eselon, jika tidak maka akan
    // mengembalikan indeks yang seharusnya ditempati (dicek dari bawah)
    private int check_eselon (AugMatrix aug, int row, int col) {
        int zero = ZeroBeforeJ(this.result, row);
        int idx = row;
        for (int i = aug.getLeft().getRowCount()-1; i >= row+1; i--) {
            if (ZeroBeforeJ(this.result, i) < zero) {
                zero = ZeroBeforeJ(this.result, i);
                idx = i;
            }
        }
        return idx;
    }


	private void operate() {
        int i, l, row, j, ColNotValid, RowNotValid = 0, swapped = -1;
        int finalRow = this.matrix.getRowCount();
        int finalCol = this.matrix.getColCount();
        boolean found, need, go;

        // untuk alokasi Matrix this.matrix ke AugMatrix this.result
        for (i = 0; i < finalRow; i++) {
            for (j = 0; j < finalCol; j++) {
                if (j <= finalCol-2) {
                    this.temp_left.setElement(i, j, this.matrix.getElement(i, j));
                }
                else this.temp_right.setElement(i, 0, this.matrix.getElement(i, j));
            }
        }

        this.result = new AugMatrix(temp_left, temp_right);

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
                if (this.matrix.findFirstXinCol(j, 1) > i && !isOne) {
                    found = true;
                    row = this.result.getLeft().findFirstXinCol(j, 1);
                    this.result.swapRow(i, row);
                    this.swapTimes++;
                }
                // menggunakan else karena prioritas 1 > -1 dan tidak bertabrakan
                else {
                    // jika di baris di bawahnya ada angka -1 && kolom 0 baris 0 bukan 1 atau -1
                    if (this.matrix.findFirstXinCol(j, -1) > i && !isOneAndMinOne) {
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
                Top_divSelf(this.result, 0, 0);
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

        // untuk precise
        for (i = 0; i < finalRow; i++) {
            for (j = 0; j < finalCol; j++) {
                if (j < finalCol-1) {
                    this.result.getLeft().setElement(i, j, NUtils.PRECISE(this.result.getLeft().getElement(i, j)));
                }
                else {
                    this.result.getLeft().setElement(i, 0, NUtils.PRECISE(this.result.getRight().getElement(i, 0)));
                }
            }
        }

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
    }

    @Override
    public Matrix getInitialMatrix() {
        return this.matrix;
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
