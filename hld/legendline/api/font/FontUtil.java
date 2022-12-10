package ru.hld.legendline.api.font;

import net.minecraft.util.*;
import java.awt.*;

public class FontUtil
{
    private static CustomFontRenderer fontRenderer;
    
    public static void drawStringWithShadow(final String s, final double n, final double n2, final int n3) {
        FontUtil.fontRenderer.drawStringWithShadow(s, (float)n, (float)n2, n3);
    }
    
    public static void drawString(final String s, final int n, final double n2, final int n3) {
        FontUtil.fontRenderer.drawString(s, (float)n, (float)(int)n2, n3);
    }
    
    public static void drawTotalCenteredStringWithShadow(final String s, final double n, final double n2, final int n3) {
        drawStringWithShadow(s, n - FontUtil.fontRenderer.getStringWidth(s) / 2, n2 - FontUtil.fontRenderer.getHeight() / 2.0f, n3);
    }
    
    public static void drawCenteredStringWithShadow(final String s, final double n, final double n2, final int n3) {
        drawStringWithShadow(s, n - FontUtil.fontRenderer.getStringWidth(s) / 2, n2, n3);
    }
    
    public static void drawCenteredString(final String s, final double n, final double n2, final int n3) {
        drawString(s, (int)(n - FontUtil.fontRenderer.getStringWidth(s) / 2), n2, n3);
    }
    
    public static void drawTotalCenteredString(final String s, final double n, final double n2, final int n3) {
        drawString(s, (int)(n - FontUtil.fontRenderer.getStringWidth(s) / 2), n2 - FontUtil.fontRenderer.getHeight() / 2, n3);
    }
    
    public static int getFontHeight() {
        return FontUtil.fontRenderer.getHeight();
    }
    
    public static double getStringWidth(final String s) {
        return FontUtil.fontRenderer.getStringWidth(StringUtils.stripControlCodes(s));
    }
    
    public static void setupFontUtils() {
        FontUtil.fontRenderer = new CustomFontRenderer(new Font("Arial", 0, 17), true, true);
    }
}
