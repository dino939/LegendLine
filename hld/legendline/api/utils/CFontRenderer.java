package ru.hld.legendline.api.utils;

import net.minecraft.client.renderer.texture.*;
import java.util.*;
import org.lwjgl.opengl.*;
import java.awt.*;
import net.minecraft.client.renderer.*;

public class CFontRenderer extends CFont
{
    protected DynamicTexture texItalicBold;
    private final String colorcodeIdentifiers = "0123456789abcdefklmnor";
    private final byte[] glyphWidth;
    private float[] charWidthFloat;
    protected CharData[] boldItalicChars;
    private final int[] colorCode;
    protected CharData[] italicChars;
    protected DynamicTexture texItalic;
    protected CharData[] boldChars;
    private final int[] charWidth;
    protected DynamicTexture texBold;
    
    public String trimStringToWidth(final String s, final int n) {
        return this.trimStringToWidth(s, n, false);
    }
    
    public int drawPassword(String replaceAll, final double n, final float n2, final int n3) {
        replaceAll = replaceAll.replaceAll("\u00c3\u201a", "");
        return 0;
    }
    
    public List wrapWords(final String s, final double n) {
        final ArrayList<String> list = new ArrayList<String>();
        if (this.getStringWidth(s) > n) {
            final String[] split = s.split(" ");
            String s2 = "";
            int n2 = 65535;
            final String[] array = split;
            for (int length = array.length, i = 0; i < length; ++i) {
                final String s3 = array[i];
                while (i < s3.toCharArray().length) {
                    if (s3.toCharArray()[i] == 'з' && i < s3.toCharArray().length - 1) {
                        n2 = s3.toCharArray()[i + 1];
                    }
                    final int n3 = 4;
                    final int n4 = -1;
                    ++i;
                    if (n3 == n4) {
                        return null;
                    }
                }
                if (this.getStringWidth(String.valueOf(new StringBuilder().append(s2).append(s3).append(" "))) < n) {
                    s2 = String.valueOf(new StringBuilder().append(s2).append(s3).append(" "));
                }
                else {
                    list.add(s2);
                    s2 = String.valueOf(new StringBuilder().append(167 + n2).append(s3).append(" "));
                }
            }
            if (s2.length() > 0) {
                if (this.getStringWidth(s2) < n) {
                    list.add(String.valueOf(new StringBuilder().append(167 + n2).append(s2).append(" ")));
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
    
    String wrapFormattedStringToWidth(final String s, final int n) {
        if (s.length() <= 1) {
            return s;
        }
        final int sizeStringToWidth = this.sizeStringToWidth(s, n);
        if (s.length() <= sizeStringToWidth) {
            return s;
        }
        final String substring = s.substring(0, sizeStringToWidth);
        final char char1 = s.charAt(sizeStringToWidth);
        return String.valueOf(new StringBuilder().append(substring).append("\n").append(this.wrapFormattedStringToWidth(String.valueOf(new StringBuilder().append(getFormatFromString(substring)).append(s.substring(sizeStringToWidth + ((char1 == ' ' || char1 == '\n') ? 1 : 0)))), n)));
    }
    
    public float drawStringWithShadow(final String s, final double n, final double n2, final int n3) {
        return Math.max(this.drawString(s, n + 0.6, n2 + 0.6, n3, true), this.drawString(s, n, n2, n3, false));
    }
    
    public void drawTotalCenteredStringWithShadow(final String s, final double n, final double n2, final int n3) {
        this.drawStringWithShadow(s, n - this.getStringWidth(s) / 2.0f, n2 - this.getStringHeight(s) / 2.0f, n3);
    }
    
    private void setupMinecraftColorcodes() {
        int i = 0;
        while (i < 32) {
            final int n = (i >> 3 & 0x1) * 85;
            int n2 = (i >> 2 & 0x1) * 170 + n;
            int n3 = (i >> 1 & 0x1) * 170 + n;
            int n4 = (i >> 0 & 0x1) * 170 + n;
            if (i == 6) {
                n2 += 85;
            }
            if (i >= 16) {
                n2 /= 4;
                n3 /= 4;
                n4 /= 4;
            }
            this.colorCode[i] = ((n2 & 0xFF) << 16 | (n3 & 0xFF) << 8 | (n4 & 0xFF));
            final Object o = null;
            ++i;
            if (o != null) {
                return;
            }
        }
    }
    
    public void drawStringWithOutline(final String s, final double n, final double n2, final int n3) {
        this.drawString(s, n - 0.5, n2 - 0.5, ColorUtils.getColor(0, 0, 0), false);
        this.drawString(s, n + 0.5, n2 + 0.5, ColorUtils.getColor(0, 0, 0), false);
        this.drawString(s, n + 0.5, n2, ColorUtils.getColor(0, 0, 0), false);
        this.drawString(s, n, n2 + 0.5, ColorUtils.getColor(0, 0, 0), false);
        this.drawString(s, n - 0.5, n2, ColorUtils.getColor(0, 0, 0), false);
        this.drawString(s, n, n2 - 0.5, ColorUtils.getColor(0, 0, 0), false);
        this.drawString(s, n, n2, n3, false);
    }
    
    public List listFormattedStringToWidth(final String s, final int n) {
        return Arrays.asList(this.wrapFormattedStringToWidth(s, n).split("\n"));
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
            if (char1 == 'з' && i < length) {
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
            final int n4 = 0;
            final int n5 = 1;
            ++i;
            if (n4 >= n5) {
                return 0;
            }
        }
        return n / 2;
    }
    
    @Override
    public void setAntiAlias(final boolean antiAlias) {
        super.setAntiAlias(antiAlias);
        this.setupBoldItalicIDs();
    }
    
    private float getCharWidthFloat(final char c) {
        if (c == 'з') {
            return -1.0f;
        }
        if (c == ' ' || c == 'а') {
            return this.charWidthFloat[32];
        }
        final int index = "\u00c0\u00c1\u00c2\u00c8\u00ca\u00cb\u00cd\u00d3\u00d4\u00d5\u00da\u00df\u00e3\u00f5\u011f\u0130\u0131\u0152\u0153\u015e\u015f\u0174\u0175\u017e\u0207\u0000\u0000\u0000\u0000\u0000\u0000\u0000 !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~\u0000\u00c7\u00fc\u00e9\u00e2\u00e4\u00e0\u00e5\u00e7\u00ea\u00eb\u00e8\u00ef\u00ee\u00ec\u00c4\u00c5\u00c9\u00e6\u00c6\u00f4\u00f6\u00f2\u00fb\u00f9\u00ff\u00d6\u00dc\u00f8?\u00d8\u00d7\u0192\u00e1\u00ed\u00f3\u00fa\u00f1\u00d1???ом???л╗\u2591\u2592\u2593\u2502\u2524\u2561\u2562\u2556\u2555\u2563\u2551\u2557\u255d\u255c\u255b\u2510\u2514\u2534\u252c\u251c\u2500\u253c\u255e\u255f\u255a\u2554\u2569\u2566\u2560\u2550\u256c\u2567\u2568\u2564\u2565\u2559\u2558\u2552\u2553\u256b\u256a\u2518\u250c\u2588\u2584\u258c\u2590\u2580\u03b1\u03b2\u0393\u03c0\u03a3\u03c3\u03bc\u03c4\u03a6\u0398\u03a9\u03b4\u221e\u2205\u2208\u2229\u2261▒\u2265\u2264\u2320\u2321\u00f7\u2248░\u2219╖\u221a\u207f?\u25a0\u0000".indexOf(c);
        if (c > '\0' && index != -1) {
            return this.charWidthFloat[index];
        }
        if (this.glyphWidth[c] != 0) {
            final int n = this.glyphWidth[c] & 0xFF;
            final int n2 = n >>> 4;
            int n3 = n & 0xF;
            return (float)((++n3 - n2) / 2 + 1);
        }
        return 0.0f;
    }
    
    private static boolean isFormatSpecial(final char c) {
        return (c >= 'k' && c <= 'o') || (c >= 'K' && c <= 'O') || c == 'r' || c == 'R';
    }
    
    public float drawCenteredStringWithShadow(final String s, final double n, final double n2, final int n3) {
        return this.drawStringWithShadow(s, n - this.getStringWidth(s) / 2.0f, n2, n3);
    }
    
    private void setupBoldItalicIDs() {
        this.texBold = this.setupTexture(this.font.deriveFont(1), this.antiAlias, this.fractionalMetrics, this.boldChars);
        this.texItalic = this.setupTexture(this.font.deriveFont(2), this.antiAlias, this.fractionalMetrics, this.italicChars);
    }
    
    @Override
    public void setFont(final Font font) {
        super.setFont(font);
        this.setupBoldItalicIDs();
    }
    
    private static boolean isFormatColor(final char c) {
        return (c >= '0' && c <= '9') || (c >= 'a' && c <= 'f') || (c >= 'A' && c <= 'F');
    }
    
    public float drawCenteredString(final String s, final double n, final double n2, final int n3) {
        return this.drawString(s, n - this.getStringWidth(s) / 2, n2, n3);
    }
    
    public static String getFormatFromString(final String s) {
        String s2 = "";
        int index = -1;
        final int length = s.length();
        while (true) {
            index = s.indexOf(167, index + 1);
            if (index == -1) {
                break;
            }
            if (index >= length - 1) {
                continue;
            }
            final char char1 = s.charAt(index + 1);
            if (isFormatColor(char1)) {
                s2 = String.valueOf(new StringBuilder().append("з").append(char1));
            }
            else {
                if (!isFormatSpecial(char1)) {
                    continue;
                }
                s2 = String.valueOf(new StringBuilder().append(s2).append("з").append(char1));
            }
        }
        return s2;
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
    
    public void drawTotalCenteredString(final String s, final double n, final double n2, final int n3) {
        this.drawString(s, n - this.getStringWidth(s) / 2.0f, n2 - this.getStringHeight(s) / 2.0f, n3);
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
        for (int i = 0; i < length; ++i) {
            final char char1 = s.charAt(i);
            if (String.valueOf(char1).equals("з")) {
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
        }
        GL11.glPopMatrix();
        return (float)n / 2.0f;
    }
    
    public float drawString(final String s, final double n, final double n2, final int n3) {
        return this.drawString(s, n, n2, n3, false);
    }
    
    public CFontRenderer(final Font font, final boolean b, final boolean b2) {
        super(font, b, b2);
        this.boldChars = new CharData[167];
        this.italicChars = new CharData[167];
        this.boldItalicChars = new CharData[167];
        this.charWidthFloat = new float[256];
        this.glyphWidth = new byte[65536];
        this.charWidth = new int[256];
        this.colorCode = new int[32];
        this.setupMinecraftColorcodes();
        this.setupBoldItalicIDs();
    }
    
    public String trimStringToWidth(final String s, final int n, final boolean b) {
        final StringBuilder sb = new StringBuilder();
        float n2 = 0.0f;
        final int n3 = b ? (s.length() - 1) : 0;
        final int n4 = b ? -1 : 1;
        int n5 = 0;
        boolean b2 = false;
        for (int n6 = n3; n6 >= 0 && n6 < s.length() && n2 < n; n6 += n4) {
            final char char1 = s.charAt(n6);
            final float charWidthFloat = this.getCharWidthFloat(char1);
            if (n5 != 0) {
                n5 = 0;
                if (char1 != 'l' && char1 != 'L') {
                    if (char1 == 'r' || char1 == 'R') {
                        b2 = false;
                    }
                }
                else {
                    b2 = true;
                }
            }
            else if (charWidthFloat < 0.0f) {
                n5 = 1;
            }
            else {
                n2 += charWidthFloat;
                if (b2) {
                    ++n2;
                }
            }
            if (n2 > n) {
                break;
            }
            if (b) {
                sb.insert(0, char1);
            }
            else {
                sb.append(char1);
            }
        }
        return String.valueOf(sb);
    }
    
    public float drawNoBSString(String replaceAll, final double n, final float n2, final int n3) {
        replaceAll = replaceAll.replaceAll("\u00c3\u201a", "");
        return this.drawString(replaceAll, n, n2, n3, false);
    }
    
    private int sizeStringToWidth(final String s, final int n) {
        final int length = s.length();
        float n2 = 0.0f;
        int i = 0;
        int n3 = -1;
        int n4 = 0;
        while (i < length) {
            final char char1 = s.charAt(i);
            Label_0167: {
                switch (char1) {
                    case 10: {
                        --i;
                        break Label_0167;
                    }
                    case 32: {
                        n3 = i;
                        break;
                    }
                    case 167: {
                        if (i >= length - 1) {
                            break Label_0167;
                        }
                        ++i;
                        final char char2 = s.charAt(i);
                        if (char2 == 'l' || char2 == 'L') {
                            n4 = 1;
                            break Label_0167;
                        }
                        if (char2 == 'r' || char2 == 'R' || isFormatColor(char2)) {
                            n4 = 0;
                        }
                        break Label_0167;
                    }
                }
                n2 += this.getCharWidthFloat(char1);
                if (n4 != 0) {
                    ++n2;
                }
            }
            if (char1 == '\n') {
                n3 = ++i;
                break;
            }
            if (Math.round(n2) > n) {
                break;
            }
            final Object o = null;
            ++i;
            if (o != null) {
                return 0;
            }
        }
        return (i != length && n3 != -1 && n3 < i) ? n3 : i;
    }
    
    public List formatString(final String s, final double n) {
        final ArrayList<String> list = new ArrayList<String>();
        String s2 = "";
        int n2 = 65535;
        final char[] charArray = s.toCharArray();
        int i = 0;
        while (i < charArray.length) {
            final char c = charArray[i];
            if (c == 'з' && i < charArray.length - 1) {
                n2 = charArray[i + 1];
            }
            if (this.getStringWidth(String.valueOf(new StringBuilder().append(s2).append(c))) < n) {
                s2 = String.valueOf(new StringBuilder().append(s2).append(c));
            }
            else {
                list.add(s2);
                s2 = String.valueOf(new StringBuilder().append(167 + n2).append(String.valueOf(c)));
            }
            final int n3 = 3;
            final int n4 = 2;
            ++i;
            if (n3 == n4) {
                return null;
            }
        }
        if (s2.length() > 0) {
            list.add(s2);
        }
        return list;
    }
    
    @Override
    public void setFractionalMetrics(final boolean fractionalMetrics) {
        super.setFractionalMetrics(fractionalMetrics);
        this.setupBoldItalicIDs();
    }
}
