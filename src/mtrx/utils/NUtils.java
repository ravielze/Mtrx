package mtrx.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

import mtrx.Mtrx;

public class NUtils {

    public static final boolean ISNOTEQUAL(double val, double val2){
        return Math.abs(val-val2) >= Math.pow(10, -1*Mtrx.PRECISION);
    }

    public static final double PRECISE(double val){
        if (Double.isNaN(val) || Double.isInfinite(val)) return val;
        if (Math.abs(val) < Math.pow(10, -1*Mtrx.PRECISION)) return 0.0D;
        return val;
    }

    public static final boolean ISEQUAL(double val, double val2){
        return Math.abs(val-val2) < Math.pow(10, -1*Mtrx.PRECISION);
    }
    
}
