package ru.hld.legendline.api.utils;

import net.minecraft.client.renderer.texture.*;
import java.awt.image.*;
import org.lwjgl.opengl.*;
import java.awt.*;
import java.awt.geom.*;

public class CFont
{
    protected CharData[] charData;
    protected boolean fractionalMetrics;
    private final float imgSize = 512.0f;
    protected DynamicTexture tex;
    protected Font font;
    protected int fontHeight;
    protected int charOffset;
    protected boolean antiAlias;
    
    public void setFont(final Font font) {
        this.font = font;
        this.tex = this.setupTexture(font, this.antiAlias, this.fractionalMetrics, this.charData);
    }
    
    public void setFractionalMetrics(final boolean fractionalMetrics) {
        if (this.fractionalMetrics != fractionalMetrics) {
            this.fractionalMetrics = fractionalMetrics;
            this.tex = this.setupTexture(this.font, this.antiAlias, fractionalMetrics, this.charData);
        }
    }
    
    public void setAntiAlias(final boolean antiAlias) {
        if (this.antiAlias != antiAlias) {
            this.antiAlias = antiAlias;
            this.tex = this.setupTexture(this.font, antiAlias, this.fractionalMetrics, this.charData);
        }
    }
    
    public Font getFont() {
        return this.font;
    }
    
    protected DynamicTexture setupTexture(final Font font, final boolean b, final boolean b2, final CharData[] array) {
        final BufferedImage generateFontImage = this.generateFontImage(font, b, b2, array);
        try {
            return new DynamicTexture(generateFontImage);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public int getStringWidth(final String s) {
        int n = 0;
        for (final char c : s.toCharArray()) {
            if (c < this.charData.length && c >= '\0') {
                n += this.charData[c].width - 8 + this.charOffset;
            }
        }
        return n / 2;
    }
    
    public int getStringHeight(final String s) {
        return this.getHeight();
    }
    
    protected void drawQuad(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7, final float n8) {
        final float n9 = n5 / 512.0f;
        final float n10 = n6 / 512.0f;
        final float n11 = n7 / 512.0f;
        final float n12 = n8 / 512.0f;
        GL11.glTexCoord2f(n9 + n11, n10);
        GL11.glVertex2d((double)(n + n3), (double)n2);
        GL11.glTexCoord2f(n9, n10);
        GL11.glVertex2d((double)n, (double)n2);
        GL11.glTexCoord2f(n9, n10 + n12);
        GL11.glVertex2d((double)n, (double)(n2 + n4));
        GL11.glTexCoord2f(n9, n10 + n12);
        GL11.glVertex2d((double)n, (double)(n2 + n4));
        GL11.glTexCoord2f(n9 + n11, n10 + n12);
        GL11.glVertex2d((double)(n + n3), (double)(n2 + n4));
        GL11.glTexCoord2f(n9 + n11, n10);
        GL11.glVertex2d((double)(n + n3), (double)n2);
    }
    
    public int getHeight() {
        return (this.fontHeight - 8) / 2;
    }
    
    public boolean isFractionalMetrics() {
        return this.fractionalMetrics;
    }
    
    public void drawChar(final CharData[] array, final char c, final float n, final float n2) throws ArrayIndexOutOfBoundsException {
        try {
            this.drawQuad(n, n2, (float)array[c].width, (float)array[c].height, (float)array[c].storedX, (float)array[c].storedY, (float)array[c].width, (float)array[c].height);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public boolean isAntiAlias() {
        return this.antiAlias;
    }
    
    public CFont(final Font font, final boolean antiAlias, final boolean fractionalMetrics) {
        this.charData = new CharData[1110];
        this.fontHeight = -1;
        this.charOffset = 0;
        this.font = font;
        this.antiAlias = antiAlias;
        this.fractionalMetrics = fractionalMetrics;
        this.tex = this.setupTexture(font, antiAlias, fractionalMetrics, this.charData);
    }
    
    protected BufferedImage generateFontImage(final Font font, final boolean b, final boolean b2, final CharData[] array) {
        this.getClass();
        final int n = 512;
        final BufferedImage bufferedImage = new BufferedImage(n, n, 2);
        final Graphics2D graphics2D = (Graphics2D)bufferedImage.getGraphics();
        graphics2D.setFont(font);
        graphics2D.setColor(new Color(255, 255, 255, 0));
        graphics2D.fillRect(0, 0, n, n);
        graphics2D.setColor(Color.WHITE);
        graphics2D.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, b2 ? RenderingHints.VALUE_FRACTIONALMETRICS_ON : RenderingHints.VALUE_FRACTIONALMETRICS_OFF);
        graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, b ? RenderingHints.VALUE_TEXT_ANTIALIAS_ON : RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, b ? RenderingHints.VALUE_ANTIALIAS_ON : RenderingHints.VALUE_ANTIALIAS_OFF);
        final FontMetrics fontMetrics = graphics2D.getFontMetrics();
        int height = 0;
        int storedX = 0;
        int storedY = 1;
        int i = 0;
        while (i < array.length) {
            final char c = (char)i;
            if (c > '\u040e' || c < '\u0100') {
                final CharData charData = new CharData();
                final Rectangle2D stringBounds = fontMetrics.getStringBounds(String.valueOf(c), graphics2D);
                charData.width = stringBounds.getBounds().width + 8;
                charData.height = stringBounds.getBounds().height;
                if (storedX + charData.width >= n) {
                    storedX = 0;
                    storedY += height;
                    height = 0;
                }
                if (charData.height > height) {
                    height = charData.height;
                }
                charData.storedX = storedX;
                charData.storedY = storedY;
                if (charData.height > this.fontHeight) {
                    this.fontHeight = charData.height;
                }
                array[i] = charData;
                graphics2D.drawString(String.valueOf(c), storedX + 2, storedY + fontMetrics.getAscent());
                storedX += charData.width;
            }
            else {
                array[i] = null;
            }
            final boolean b3 = true;
            final boolean b4 = true;
            ++i;
            if (b3 != b4) {
                return null;
            }
        }
        return bufferedImage;
    }
    
    protected class CharData
    {
        public int width;
        public int storedY;
        final CFont this$0;
        public int height;
        public int storedX;
        
        protected CharData(final CFont this$0) {
            this.this$0 = this$0;
        }
    }
}
