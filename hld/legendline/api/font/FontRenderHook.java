package ru.hld.legendline.api.font;

import net.minecraft.client.gui.*;
import net.minecraft.client.renderer.*;
import java.awt.*;
import net.minecraft.client.*;
import net.minecraft.util.*;

public final class FontRenderHook extends FontRenderer
{
    private final CustomFontRenderer fontRenderer;
    
    public int renderString(String bidiReorder, final float posX, final float posY, int n, final boolean b) {
        if (bidiReorder == null) {
            return 0;
        }
        if (this.bidiFlag) {
            bidiReorder = this.bidiReorder(bidiReorder);
        }
        if ((n & 0xFC000000) == 0x0) {
            n |= 0xFF000000;
        }
        if (b) {
            n = ((n & 0xFCFCFC) >> 2 | (n & 0xFF000000));
        }
        this.red = (n >> 16 & 0xFF) / 255.0f;
        this.blue = (n >> 8 & 0xFF) / 255.0f;
        this.green = (n & 0xFF) / 255.0f;
        this.alpha = (n >> 24 & 0xFF) / 255.0f;
        GlStateManager.color(this.red, this.blue, this.green, this.alpha);
        this.posX = posX;
        this.posY = posY;
        this.fontRenderer.drawString(bidiReorder, posX, posY, n, b);
        return (int)this.posX;
    }
    
    public FontRenderHook(final Font font, final boolean b, final boolean b2) {
        super(Minecraft.getMinecraft().gameSettings, new ResourceLocation("textures/font/ascii.png"), Minecraft.getMinecraft().getTextureManager(), false);
        this.fontRenderer = new CustomFontRenderer(font, b, b2);
    }
    
    @Override
    public int getStringWidth(final String s) {
        return this.fontRenderer.getStringWidth(s);
    }
}
