package ru.hld.legendline.api.utils;

import java.awt.*;

public class ColorUtils
{
    public static int TwoColoreffect(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final double n9) {
        final float clamp = MathUtils.clamp((float)Math.sin(18.84955592153876 * (n9 / 4.0 % 1.0)) / 2.0f + 0.5f, 0.0f, 1.0f);
        return getColor((int)MathUtils.lerp((float)n, (float)n5, clamp), (int)MathUtils.lerp((float)n2, (float)n6, clamp), (int)MathUtils.lerp((float)n3, (float)n7, clamp), (int)MathUtils.lerp((float)n4, (float)n8, clamp));
    }
    
    public static int astolfoColorsCool(final int n, final int n2) {
        float n3;
        float n4;
        for (n3 = 1450.0f, n4 = System.currentTimeMillis() % (int)n3 + (float)((n2 - n) * 9); n4 > n3; n4 -= n3) {}
        float n5 = n4 / n3;
        if (n5 > 0.5) {
            n5 = 0.5f - (n5 - 0.5f);
        }
        return Color.HSBtoRGB(n5 + 0.5f, 0.67f, 1.0f);
    }
    
    public static Color TwoColoreffect(final Color color, final Color color2, final double n) {
        final float clamp = MathUtils.clamp((float)Math.sin(18.84955592153876 * (n / 4.0 % 1.0)) / 2.0f + 0.5f, 0.0f, 1.0f);
        return new Color(MathUtils.lerp(color.getRed() / 255.0f, color2.getRed() / 255.0f, clamp), MathUtils.lerp(color.getGreen() / 255.0f, color2.getGreen() / 255.0f, clamp), MathUtils.lerp(color.getBlue() / 255.0f, color2.getBlue() / 255.0f, clamp), MathUtils.lerp(color.getAlpha() / 255.0f, color2.getAlpha() / 255.0f, clamp));
    }
    
    public static int fadeColor(final int n, final int n2, final float n3, final int n4) {
        return TwoColoreffect((int)(float)(n >> 16 & 0xFF), (int)(float)(n >> 8 & 0xFF), (int)(float)(n & 0xFF), (int)(float)(n >> 24 & 0xFF), (int)(float)(n2 >> 16 & 0xFF), (int)(float)(n2 >> 8 & 0xFF), (int)(float)(n2 & 0xFF), (int)(float)(n2 >> 24 & 0xFF), Math.abs(System.currentTimeMillis() / 4L + n4) / 100.1275 * n3);
    }
    
    public static int getColor(final int n, final int n2, final int n3, final int n4) {
        return 0x0 | n4 << 24 | n << 16 | n2 << 8 | n3;
    }
    
    public static int swapAlpha(final int n, final float n2) {
        return getColor(n >> 16 & 0xFF, n >> 8 & 0xFF, n & 0xFF, (int)n2);
    }
    
    public static int getColor(final int n, final int n2, final int n3) {
        return getColor(n, n2, n3, 255);
    }
}
