package mtrx.utils;

public class Base26 {

    /**
     * Mengubah sebuah bilangan menjadi huruf.
     * @param i bilangan bulat positif termasuk nol.
     * @return base 26.
     */
    public static String toBase26(int i){
        if (i < 0) return null;

        StringBuilder result = new StringBuilder(); 
        int num = i+1;
        while (num > 0) {
            int rem = num % 26; 
            if (rem == 0) { 
                result.append("z"); 
                num = (num / 26) - 1; 
            } 
            else{ 
                result.append((char)((rem - 1) + 'a')); 
                num = num / 26; 
            } 
        } 
        return result.reverse().toString();
    }

    
}
