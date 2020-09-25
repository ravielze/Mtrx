package mtrx.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

import mtrx.Mtrx;

public class NUtils {

    public static final double PRECISE(double val){
        return BigDecimal.valueOf(val).setScale(Mtrx.PRECISION, RoundingMode.HALF_UP).doubleValue();
    }

    public static final boolean ISEQUAL(double val, double val2){
        return Math.abs(val-val2) < Math.pow(10, -1*Mtrx.PRECISION);
    }
    
}
