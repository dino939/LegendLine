package ru.hld.legendline.api.font;

import net.minecraft.client.renderer.texture.*;
import org.lwjgl.opengl.*;
import java.util.*;
import java.awt.*;
import net.minecraft.client.renderer.*;

public final class CustomFontRenderer extends CFont
{
    private CharData[] italicChars;
    private DynamicTexture texBold;
    private final int[] colorCode;
    private DynamicTexture texItalic;
    private CharData[] boldChars;
    private DynamicTexture texItalicBold;
    private CharData[] boldItalicChars;
    
    public float drawCenteredStringWithShadow(final String s, final double n, final double n2, final int n3) {
        this.drawString(s, n - this.getStringWidth(s) / 2.0f + 0.45, n2 + 0.5, n3, true);
        return this.drawString(s, n - this.getStringWidth(s) / 2.0f, n2, n3);
    }
    
    @Override
    public void setAntiAlias(final boolean antiAlias) {
        super.setAntiAlias(antiAlias);
        this.setupBoldItalicIDs();
    }
    
    public float drawString(final String s, final double n, final double n2, final int n3) {
        return this.drawString(s, n, n2, n3, false);
    }
    
    @Override
    public void setFont(final Font font) {
        super.setFont(font);
        this.setupBoldItalicIDs();
    }
    
    public void drawCenteredStringWithOutline(final String s, final double n, final double n2, final int n3) {
        this.drawCenteredString(s, n - 0.5, n2, 0);
        this.drawCenteredString(s, n + 0.5, n2, 0);
        this.drawCenteredString(s, n, n2 - 0.5, 0);
        this.drawCenteredString(s, n, n2 + 0.5, 0);
        this.drawCenteredString(s, n, n2, n3);
    }
    
    private void drawLine(final double n, final double n2, final double n3, final double n4, final float n5) {
        GL11.glDisable(3553);
        GL11.glLineWidth(n5);
        GL11.glBegin(1);
        GL11.glVertex2d(n, n2);
        GL11.glVertex2d(n3, n4);
        GL11.glEnd();
        GL11.glEnable(3553);
    }
    
    public float drawStringWithShadow(final String s, final double n, final double n2, final int n3) {
        return Math.max(this.drawString(s, n + 0.5, n2 + 0.5, n3, true), this.drawString(s, n, n2, n3, false));
    }
    
    public float drawStringWithShadow(final String s, final float n, final float n2, final int n3) {
        return Math.max(this.drawString(s, n + 1.0, n2 + 1.0, n3, true), this.drawString(s, n, n2, n3, false));
    }
    
    public List formatString(final String s, final double n) {
        final ArrayList<String> list = new ArrayList<String>();
        String s2 = "";
        char c = '\uffff';
        final char[] charArray = s.toCharArray();
        int i = 0;
        while (i < charArray.length) {
            final char c2 = charArray[i];
            if (String.valueOf(c2).equals("§") && i < charArray.length - 1) {
                c = charArray[i + 1];
            }
            if (this.getStringWidth(String.valueOf(new StringBuilder().append(s2).append(c2))) < n) {
                s2 = String.valueOf(new StringBuilder().append(s2).append(c2));
            }
            else {
                list.add(s2);
                s2 = String.valueOf(new StringBuilder().append("").append(c).append(c2));
            }
            final Object o = null;
            ++i;
            if (o != null) {
                return null;
            }
        }
        if (s2.length() > 0) {
            list.add(s2);
        }
        return list;
    }
    
    private void setupMinecraftColorcodes() {
        int i = 0;
        while (i < 32) {
            final int n = (i >> 3 & 0x1) * 85;
            int n2 = (i >> 2 & 0x1) * 170 + n;
            int n3 = (i >> 1 & 0x1) * 170 + n;
            int n4 = (i & 0x1) * 170 + n;
            if (i == 6) {
                n2 += 85;
            }
            if (i >= 16) {
                n2 /= 4;
                n3 /= 4;
                n4 /= 4;
            }
            this.colorCode[i] = ((n2 & 0xFF) << 16 | (n3 & 0xFF) << 8 | (n4 & 0xFF));
            final int n5 = -1;
            final int n6 = 2;
            ++i;
            if (n5 >= n6) {
                return;
            }
        }
    }
    
    @Override
    public int getStringWidth(final String s) {
        if (s == null) {
            return 0;
        }
        int n = 0;
        CharData[] array = this.charData;
        int n2 = 0;
        int n3 = 0;
        final int length = s.length();
        int i = 0;
        while (i < length) {
            final char char1 = s.charAt(i);
            if (String.valueOf(char1).equals("§")) {
                final int index = "0123456789abcdefklmnor".indexOf(char1);
                if (index < 16) {
                    n2 = 0;
                    n3 = 0;
                }
                else if (index == 17) {
                    n2 = 1;
                    if (n3 != 0) {
                        array = this.boldItalicChars;
                    }
                    else {
                        array = this.boldChars;
                    }
                }
                else if (index == 20) {
                    n3 = 1;
                    if (n2 != 0) {
                        array = this.boldItalicChars;
                    }
                    else {
                        array = this.italicChars;
                    }
                }
                else if (index == 21) {
                    n2 = 0;
                    n3 = 0;
                    array = this.charData;
                }
                ++i;
            }
            else if (char1 < array.length) {
                n += array[char1].width - 8 + this.charOffset;
            }
            final int n4 = 0;
            final int n5 = 2;
            ++i;
            if (n4 >= n5) {
                return 0;
            }
        }
        return n / 2;
    }
    
    public void drawTotalCenteredStringWithShadow(final String s, final double n, final double n2, final int n3) {
        this.drawStringWithShadow(s, n - this.getStringWidth(s) / 2, n2 - this.getHeight() / 2.0f, n3);
    }
    
    public float drawCenteredString(final String s, final float n, final float n2, final int n3) {
        return this.drawString(s, n - this.getStringWidth(s) / 2.0f, n2, n3);
    }
    
    @Override
    public void setFractionalMetrics(final boolean fractionalMetrics) {
        super.setFractionalMetrics(fractionalMetrics);
        this.setupBoldItalicIDs();
    }
    
    public int getStringWidthCust(final String s) {
        if (s == null) {
            return 0;
        }
        int n = 0;
        CharData[] array = this.charData;
        int n2 = 0;
        int n3 = 0;
        final int length = s.length();
        int i = 0;
        while (i < length) {
            final char char1 = s.charAt(i);
            if (String.valueOf(char1).equals("§") && i < length) {
                final int index = "0123456789abcdefklmnor".indexOf(char1);
                if (index < 16) {
                    n2 = 0;
                    n3 = 0;
                }
                else if (index == 17) {
                    n2 = 1;
                    if (n3 != 0) {
                        array = this.boldItalicChars;
                    }
                    else {
                        array = this.boldChars;
                    }
                }
                else if (index == 20) {
                    n3 = 1;
                    if (n2 != 0) {
                        array = this.boldItalicChars;
                    }
                    else {
                        array = this.italicChars;
                    }
                }
                else if (index == 21) {
                    n2 = 0;
                    n3 = 0;
                    array = this.charData;
                }
                ++i;
            }
            else if (char1 < array.length && char1 >= '\0') {
                n += array[char1].width - 8 + this.charOffset;
            }
            final int n4 = 4;
            final int n5 = 3;
            ++i;
            if (n4 < n5) {
                return 0;
            }
        }
        return (n - this.charOffset) / 2;
    }
    
    public CustomFontRenderer(final Font font, final boolean b, final boolean b2) {
        super(font, b, b2);
        this.colorCode = new int[32];
        this.boldChars = new CharData[256];
        this.italicChars = new CharData[256];
        this.boldItalicChars = new CharData[256];
        this.setupMinecraftColorcodes();
        this.setupBoldItalicIDs();
    }
    
    public float drawString(final String s, final float n, final float n2, final int n3) {
        return this.drawString(s, n, n2, n3, false);
    }
    
    public float drawCenteredString(final String s, final double n, final double n2, final int n3) {
        return this.drawString(s, n - this.getStringWidth(s) / 2.0f, n2, n3);
    }
    
    public float drawCenteredStringWithShadow(final String s, final float n, final float n2, final int n3) {
        this.drawString(s, n - this.getStringWidth(s) / 2.0f + 0.45, n2 + 0.5, n3, true);
        return this.drawString(s, n - this.getStringWidth(s) / 2.0f, n2, n3);
    }
    
    public List wrapWords(final String s, final double n) {
        final ArrayList<String> list = new ArrayList<String>();
        if (this.getStringWidth(s) > n) {
            final String[] split = s.split(" ");
            String s2 = "";
            char c = '\uffff';
            final String[] array = split;
            for (int length = split.length, i = 0; i < length; ++i) {
                final String s3 = array[i];
                int j = 0;
                while (j < s3.toCharArray().length) {
                    if (String.valueOf(s3.toCharArray()[j]).equals("§") && j < s3.toCharArray().length - 1) {
                        c = s3.toCharArray()[j + 1];
                    }
                    final int n2 = 3;
                    final int n3 = 0;
                    ++j;
                    if (n2 < n3) {
                        return null;
                    }
                }
                if (this.getStringWidth(String.valueOf(new StringBuilder().append(s2).append(s3).append(" "))) < n) {
                    s2 = String.valueOf(new StringBuilder().append(s2).append(s3).append(" "));
                }
                else {
                    list.add(s2);
                    s2 = String.valueOf(new StringBuilder().append("").append(c).append(s3).append(" "));
                }
            }
            if (s2.length() > 0) {
                if (this.getStringWidth(s2) < n) {
                    list.add(String.valueOf(new StringBuilder().append("").append(c).append(s2).append(" ")));
                }
                else {
                    final Iterator iterator = this.formatString(s2, n).iterator();
                    while (iterator.hasNext()) {
                        list.add(iterator.next());
                    }
                }
            }
        }
        else {
            list.add(s);
        }
        return list;
    }
    
    public void drawStringWithOutline(final String s, final double n, final double n2, final int n3) {
        this.drawString(s, n - 0.5, n2, 0);
        this.drawString(s, n + 0.5, n2, 0);
        this.drawString(s, n, n2 - 0.5, 0);
        this.drawString(s, n, n2 + 0.5, 0);
        this.drawString(s, n, n2, n3);
    }
    
    public void drawTotalCenteredString(final String s, final double n, final double n2, final int n3) {
        this.drawString(s, n - this.getStringWidth(s) / 2, n2 - this.getHeight() / 2, n3);
    }
    
    private void setupBoldItalicIDs() {
        this.texBold = this.setupTexture(this.font.deriveFont(1), this.antiAlias, this.fractionalMetrics, this.boldChars);
        this.texItalic = this.setupTexture(this.font.deriveFont(2), this.antiAlias, this.fractionalMetrics, this.italicChars);
        this.texItalicBold = this.setupTexture(this.font.deriveFont(3), this.antiAlias, this.fractionalMetrics, this.boldItalicChars);
    }
    
    public float drawString(final String s, double n, double n2, int n3, final boolean b) {
        --n;
        if (s == null) {
            return 0.0f;
        }
        if (n3 == 553648127) {
            n3 = 16777215;
        }
        if ((n3 & 0xFC000000) == 0x0) {
            n3 |= 0xFF000000;
        }
        if (b) {
            n3 = ((n3 & 0xFCFCFC) >> 2 | (n3 & new Color(20, 20, 20, 200).getRGB()));
        }
        CharData[] array = this.charData;
        final float n4 = (n3 >> 24 & 0xFF) / 255.0f;
        int n5 = 0;
        int n6 = 0;
        boolean b2 = false;
        boolean b3 = false;
        n *= 2.0;
        n2 = (n2 - 3.0) * 2.0;
        GL11.glPushMatrix();
        GlStateManager.scale(0.5, 0.5, 0.5);
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(770, 771);
        GlStateManager.color((n3 >> 16 & 0xFF) / 255.0f, (n3 >> 8 & 0xFF) / 255.0f, (n3 & 0xFF) / 255.0f, n4);
        final int length = s.length();
        GlStateManager.enableTexture2D();
        GlStateManager.bindTexture(this.tex.getGlTextureId());
        GL11.glBindTexture(3553, this.tex.getGlTextureId());
        int i = 0;
        while (i < length) {
            final char char1 = s.charAt(i);
            if (String.valueOf(char1).equals("§")) {
                int index = 21;
                try {
                    index = "0123456789abcdefklmnor".indexOf(s.charAt(i + 1));
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
                if (index < 16) {
                    n5 = 0;
                    n6 = 0;
                    b3 = false;
                    b2 = false;
                    GlStateManager.bindTexture(this.tex.getGlTextureId());
                    array = this.charData;
                    if (index < 0) {
                        index = 15;
                    }
                    if (b) {
                        index += 16;
                    }
                    final int n7 = this.colorCode[index];
                    GlStateManager.color((n7 >> 16 & 0xFF) / 255.0f, (n7 >> 8 & 0xFF) / 255.0f, (n7 & 0xFF) / 255.0f, n4);
                }
                else if (index == 17) {
                    n5 = 1;
                    if (n6 != 0) {
                        GlStateManager.bindTexture(this.texItalicBold.getGlTextureId());
                        array = this.boldItalicChars;
                    }
                    else {
                        GlStateManager.bindTexture(this.texBold.getGlTextureId());
                        array = this.boldChars;
                    }
                }
                else if (index == 18) {
                    b2 = true;
                }
                else if (index == 19) {
                    b3 = true;
                }
                else if (index == 20) {
                    n6 = 1;
                    if (n5 != 0) {
                        GlStateManager.bindTexture(this.texItalicBold.getGlTextureId());
                        array = this.boldItalicChars;
                    }
                    else {
                        GlStateManager.bindTexture(this.texItalic.getGlTextureId());
                        array = this.italicChars;
                    }
                }
                else if (index == 21) {
                    n5 = 0;
                    n6 = 0;
                    b3 = false;
                    b2 = false;
                    GlStateManager.color((n3 >> 16 & 0xFF) / 255.0f, (n3 >> 8 & 0xFF) / 255.0f, (n3 & 0xFF) / 255.0f, n4);
                    GlStateManager.bindTexture(this.tex.getGlTextureId());
                    array = this.charData;
                }
                ++i;
            }
            else if (char1 < array.length) {
                GL11.glBegin(4);
                this.drawChar(array, char1, (float)n, (float)n2);
                GL11.glEnd();
                if (b2) {
                    this.drawLine(n, n2 + array[char1].height / 2.0f, n + array[char1].width - 8.0, n2 + array[char1].height / 2.0f, 1.0f);
                }
                if (b3) {
                    this.drawLine(n, n2 + array[char1].height - 2.0, n + array[char1].width - 8.0, n2 + array[char1].height - 2.0, 1.0f);
                }
                n += array[char1].width - 8 + this.charOffset;
            }
            final Object o = null;
            ++i;
            if (o != null) {
                return 0.0f;
            }
        }
        GL11.glPopMatrix();
        return (float)n / 2.0f;
    }
}
