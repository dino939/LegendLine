package ru.hld.legendline.api.utils;

import java.awt.*;
import net.minecraft.client.*;
import net.minecraft.util.*;

public class Fonts
{
    public static CFontRenderer iconswex_36;
    public static CFontRenderer mntsb_25;
    public static CFontRenderer mntsb_18;
    public static CFontRenderer mntsb_21;
    public static CFontRenderer iconswex_30;
    public static CFontRenderer iconswex_40;
    public static CFontRenderer mntsb_20;
    public static CFontRenderer mntsb_16;
    
    public static float blob(final String s, final int n, final String s2, final float n2, final float n3, final int n4, final boolean b) {
        return new CFontRenderer(getFontTTF(s, n), true, true).drawString(s2, n2, n3, n4, b);
    }
    
    public static Font getFontTTF(final String s, final int n) {
        Font deriveFont;
        try {
            deriveFont = Font.createFont(0, Minecraft.getMinecraft().getResourceManager().getResource(new ResourceLocation(String.valueOf(new StringBuilder().append("fonts/").append(s).append(".ttf")))).getInputStream()).deriveFont(0, (float)n);
        }
        catch (Exception ex) {
            System.out.println("Error loading font");
            deriveFont = new Font("default", 0, n);
        }
        return deriveFont;
    }
    
    static {
        Fonts.mntsb_16 = new CFontRenderer(getFontTTF("aboba", 16), true, true);
        Fonts.mntsb_18 = new CFontRenderer(getFontTTF("aboba", 18), true, true);
        Fonts.mntsb_20 = new CFontRenderer(getFontTTF("aboba", 20), true, true);
        Fonts.mntsb_21 = new CFontRenderer(getFontTTF("aboba", 21), true, true);
        Fonts.mntsb_25 = new CFontRenderer(getFontTTF("aboba", 25), true, true);
        Fonts.iconswex_30 = new CFontRenderer(getFontTTF("wex", 30), true, true);
        Fonts.iconswex_36 = new CFontRenderer(getFontTTF("wex", 36), true, true);
        Fonts.iconswex_40 = new CFontRenderer(getFontTTF("wex", 40), true, true);
    }
}
