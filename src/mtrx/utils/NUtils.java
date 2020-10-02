package mtrx.utils;

import mtrx.Mtrx;

public class NUtils {

    public static final boolean ISNOTEQUAL(double val, double val2){
        return Math.abs(val-val2) >= Math.pow(10, -1*Mtrx.PRECISION);
    }

    public static final double PRECISE(double val){
        if (Double.isNaN(val) || Double.isInfinite(val)) return val;
        if (Math.abs(val) < Math.pow(10, -1*Mtrx.PRECISION)) return 0.0D;

        String str = String.format("%." + Mtrx.PRECISION + "f", val);
        return Double.valueOf(str);
    }

    public static final boolean ISEQUAL(double val, double val2){
        return Math.abs(val-val2) < Math.pow(10, -1*Mtrx.PRECISION);
    }

    public static final String TOSTRING(double val){
        if (Double.isNaN(val)) return "NaN";
        if (Double.isInfinite(val)) return "Infinite";

        String all = String.format("%." + Mtrx.PRECISION + "f", val);
        int countZeroafterDot = 0;
        for (int i=all.indexOf(".")+1;i < all.length(); i++){
            if (all.charAt(i) == '0'){
                countZeroafterDot++;
            } else{
                break;
            }
        }
        int potong = all.indexOf(".");
        String front = all.substring(0, potong);
        String back = all.substring(potong+1);
        int backNumber = Integer.valueOf(back);

        String zero = "";
        if (countZeroafterDot > 0){
            for (int i=0; i < countZeroafterDot; i++){
                zero += "0";
            }
        }
        String result = front + "." + back;

        for (int pow=Mtrx.PRECISION;pow >= 1;pow--){
            int tenth = (int) Math.pow(10, pow);
            if (pow == Mtrx.PRECISION && backNumber%tenth == 0){
                result = front;
                break;
            } else if (backNumber%tenth == 0){
                result = front + "." + zero + Integer.toString(backNumber/tenth);
                break;
            }
        }

        if (result.equalsIgnoreCase("-0")) result = "0";
        return result;
    }
    
}
