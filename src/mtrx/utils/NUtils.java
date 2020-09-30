package mtrx.utils;

import java.text.DecimalFormat;

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
        String front = all.substring(0, all.indexOf("."));
        String back = all.substring(all.indexOf(".")+1);
        int backNumber = Integer.valueOf(back);

        String result = front + "." + back;
        if (backNumber%1000 == 0){
            result = front;
        } else if (backNumber%100 == 0){
            result = front + "." + Integer.toString(backNumber/100);
        } else if (backNumber%10 == 0) result = front + "." + Integer.toString(backNumber/10);
        if (result.equalsIgnoreCase("-0")) result = "0";
        return result;
    }
    
}
