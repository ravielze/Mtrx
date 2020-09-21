package mtrx.type;

public enum MatrixOperation {

    /**
     * Operasi perkalian K*value + ovalue*0;
     */
    MULTIPLY,

    /**
     * Operasi tambah-tambahan value + ovalue*K;
     */
    ADDITION,

    /**
     * Operasi kurang-kurangan value - ovalue*K;
     */
    REDUCTION;
    
}
