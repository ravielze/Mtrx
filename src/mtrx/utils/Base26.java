package mtrx.utils;

public class Base26 {

    private final static char[] mapping;
    static {
        char[] dummy = new char[27];
        for (int i = 1; i <= 26; i++){
            dummy[i] = ((char) (97+i-1));
        }
        mapping = dummy;
    }

    /**
     * Mengubah sebuah bilangan menjadi huruf.
     * @param i bilangan bulat positif termasuk nol.
     * @return base 26.
     */
    public static String toBase26(int i){
        if (i == 0) return "a";
        if (i < 0) return null;

        String result = "";
        int angka = i;
        while (angka != 0){
            result += mapping[(angka%26)+1];
            angka = angka/26;
        }
        return result;
    }

    
}
