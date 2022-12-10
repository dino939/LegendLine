package ru.hld.legendline.api.utils;

import java.util.*;
import java.math.*;

public class MathUtils
{
    private static final Random random;
    
    public static float lerp(final float n, final float n2, final float n3) {
        return n + n3 * (n2 - n);
    }
    
    public static float clamp(float n, final float n2, final float n3) {
        if (n <= n2) {
            n = n2;
        }
        if (n >= n3) {
            n = n3;
        }
        return n;
    }
    
    static {
        random = new Random();
    }
    
    public static double round(final float n, final double n2) {
        return new BigDecimal(Math.round(n / n2) * n2).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
    
    public static float getRandomInRange(final float n, final float n2) {
        return (float)(n2 + (n - n2) * MathUtils.random.nextDouble());
    }
}
