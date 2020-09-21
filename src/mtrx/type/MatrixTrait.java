package mtrx.type;

public enum MatrixTrait {

    /**
     * Matrix yang memiliki ukuran NxN
     */
    SQUARE,

    /**
     * Matriks persegi yang di atas garis diagonal utama nol
     */
    UPPER_TRIANGLE,

    /**
     * Matriks persegi yang di bawah garis diagonal utama nol
     */
    LOWER_TRIANGLE,

    /**
     * Matriks eselon
     */
    ESELON,

    /**
     * Alias untuk Eselon Baris terreduksi
     */
    ESELON_R;
    
}
