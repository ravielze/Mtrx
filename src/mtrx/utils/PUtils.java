package mtrx.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

import mtrx.Mtrx;

public class PUtils {

    public static final double PRECISE(double val){
        return BigDecimal.valueOf(val).setScale(Mtrx.PRECISION, RoundingMode.HALF_UP).doubleValue();
    }
    
}
