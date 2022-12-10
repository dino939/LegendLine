package ru.hld.legendline.antiNative;

import net.minecraft.client.*;
import org.lwjgl.opengl.*;
import java.awt.*;
import ru.hld.legendline.api.utils.*;
import net.minecraft.util.math.*;
import net.minecraft.client.gui.*;
import net.minecraft.item.*;
import net.minecraft.client.renderer.vertex.*;
import net.minecraft.client.renderer.*;

public class RenderUtils
{
    public static Minecraft mc;
    
    public static void setupColor(final int n, final float n2) {
        GL11.glColor4f((n >> 16 & 0xFF) / 255.0f, (n >> 8 & 0xFF) / 255.0f, (n & 0xFF) / 255.0f, n2 / 255.0f);
    }
    
    public static void drawSuper(final double n, final double n2, final double n3, final double n4, final int n5, final int n6) {
        glColor(n6);
        drawRect(n, n2, n3, n4, n6);
        drawGradientRect(n, n2 - n5, n3, n2, false, true, n6, n6);
        drawGradientRect(n, n4, n3, n4 + n5, false, false, n6, n6);
        drawSector3(n3, n4, 0, 90, n5, n6);
        drawSector3(n3, n2, 90, 180, n5, n6);
        drawSector3(n, n2, 180, 270, n5, n6);
        drawSector3(n, n4, 270, 360, n5, n6);
        drawGradientRect(n - n5, n2, n, n4, true, true, n6, n6);
        drawGradientRect(n3, n2, n3 + n5, n4, true, false, n6, n6);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
    }
    
    public static void drawUpShadow(final float n, final float n2, final float n3, final float n4) {
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.shadeModel(7425);
        GL11.glBegin(7);
        glColor(new Color(0, 0, 0, 100).getRGB());
        GL11.glVertex2d((double)n, (double)n2);
        glColor(new Color(0, 0, 0, 0).getRGB());
        GL11.glVertex2d((double)n, (double)n4);
        GL11.glVertex2d((double)n3, (double)n4);
        glColor(new Color(0, 0, 0, 100).getRGB());
        GL11.glVertex2d((double)n3, (double)n2);
        GL11.glEnd();
        GlStateManager.disableBlend();
        GlStateManager.enableTexture2D();
        GlStateManager.enableAlpha();
        GlStateManager.shadeModel(7424);
    }
    
    public static void drawShadowRect(final double n, final double n2, final double n3, final double n4, final int n5, final int n6) {
        glColor(n6);
        drawRect(n, n2, n3, n4, n6);
        drawGradientRect(n, n2 - n5, n3, n2, false, true, n6, ColorUtils.swapAlpha(n6, 0.0f));
        drawGradientRect(n, n4, n3, n4 + n5, false, false, n6, ColorUtils.swapAlpha(n6, 0.0f));
        drawSector2(n3, n4, 0, 90, n5, n6);
        drawSector2(n3, n2, 90, 180, n5, n6);
        drawSector2(n, n2, 180, 270, n5, n6);
        drawSector2(n, n4, 270, 360, n5, n6);
        drawGradientRect(n - n5, n2, n, n4, true, true, n6, ColorUtils.swapAlpha(n6, 0.0f));
        drawGradientRect(n3, n2, n3 + n5, n4, true, false, n6, ColorUtils.swapAlpha(n6, 0.0f));
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
    }
    
    public static void drawRobotgui(final float n, final float n2, final int n3, final int n4, final float n5) {
        final float n6 = (n3 >> 24 & 0xFF) / 255.0f;
        final float n7 = (n3 >> 16 & 0xFF) / 255.0f;
        final float n8 = (n3 >> 8 & 0xFF) / 255.0f;
        final float n9 = (n3 & 0xFF) / 255.0f;
        final float n10 = (n4 >> 24 & 0xFF) / 255.0f;
        final float n11 = (n4 >> 16 & 0xFF) / 255.0f;
        final float n12 = (n4 >> 8 & 0xFF) / 255.0f;
        final float n13 = (n4 & 0xFF) / 255.0f;
        final boolean glIsEnabled = GL11.glIsEnabled(3042);
        final boolean glIsEnabled2 = GL11.glIsEnabled(2848);
        final boolean glIsEnabled3 = GL11.glIsEnabled(3553);
        if (!glIsEnabled) {
            GL11.glEnable(3042);
        }
        if (!glIsEnabled2) {
            GL11.glEnable(2848);
        }
        if (glIsEnabled3) {
            GL11.glDisable(3553);
        }
        GL11.glEnable(2848);
        GL11.glBlendFunc(770, 771);
        GL11.glColor4f(n7, n8, n9, n6);
        GL11.glLineWidth(2.5f);
        GL11.glBegin(3);
        int i = -140;
        while (i <= -60) {
            GL11.glVertex2d(n + Math.sin(i * 3.141592653589793 / 180.0) * n5, n2 + Math.cos(i * 3.141592653589793 / 180.0) * n5);
            GL11.glVertex2d(n - 0.5 + Math.sin(i * 3.141592653589793 / 180.0) * n5, n2 - 10.0 + Math.cos(i * 3.141592653589793 / 180.0) * n5);
            final int n14 = 0;
            final int n15 = -1;
            ++i;
            if (n14 <= n15) {
                return;
            }
        }
        GL11.glEnd();
        GL11.glBegin(3);
        int j = 100;
        while (j <= 180) {
            GL11.glVertex2d(n + Math.sin(j * 3.141592653589793 / 180.0) * n5, n2 + Math.cos(j * 3.141592653589793 / 180.0) * n5);
            GL11.glVertex2d(n - 0.5 + Math.sin(j * 3.141592653589793 / 180.0) * n5, n2 - 10.0 + Math.cos(j * 3.141592653589793 / 180.0) * n5);
            final int n16 = 4;
            final int n17 = 2;
            ++j;
            if (n16 <= n17) {
                return;
            }
        }
        GL11.glEnd();
        GL11.glBegin(3);
        int k = -20;
        while (k <= 60) {
            GL11.glVertex2d(n + Math.sin(k * 3.141592653589793 / 180.0) * n5, n2 + Math.cos(k * 3.141592653589793 / 180.0) * n5);
            GL11.glVertex2d(n - 0.5 + Math.sin(k * 3.141592653589793 / 180.0) * n5, n2 - 10.0 + Math.cos(k * 3.141592653589793 / 180.0) * n5);
            final int n18 = 3;
            final int n19 = 0;
            ++k;
            if (n18 < n19) {
                return;
            }
        }
        GL11.glEnd();
        GL11.glDisable(2848);
        if (glIsEnabled3) {
            GL11.glEnable(3553);
        }
        if (!glIsEnabled2) {
            GL11.glDisable(2848);
        }
        if (!glIsEnabled) {
            GL11.glDisable(3042);
        }
    }
    
    public static void glColor(final int n) {
        GlStateManager.color((n >> 16 & 0xFF) / 255.0f, (n >> 8 & 0xFF) / 255.0f, (n & 0xFF) / 255.0f, (n >> 24 & 0xFF) / 255.0f);
    }
    
    static {
        RenderUtils.mc = Minecraft.getMinecraft();
    }
    
    public static void putVertex3d(final Vec3d vec3d) {
        GL11.glVertex3d(vec3d.xCoord, vec3d.yCoord, vec3d.zCoord);
    }
    
    public static void drawRect(final double n, final double n2, final double n3, final double n4, final int n5) {
        final float n6 = (n5 >> 24 & 0xFF) / 255.0f;
        final float n7 = (n5 >> 16 & 0xFF) / 255.0f;
        final float n8 = (n5 >> 8 & 0xFF) / 255.0f;
        final float n9 = (n5 & 0xFF) / 255.0f;
        GL11.glPushMatrix();
        GL11.glPushAttrib(1048575);
        GL11.glEnable(3042);
        GL11.glDisable(2884);
        GL11.glDisable(3553);
        GL11.glColor4f(n7, n8, n9, n6);
        GL11.glBegin(7);
        GL11.glVertex2d(n, n2);
        GL11.glVertex2d(n3, n2);
        GL11.glVertex2d(n3, n4);
        GL11.glVertex2d(n, n4);
        GL11.glEnd();
        GL11.glEnable(3553);
        GL11.glEnable(2884);
        GL11.glDisable(3042);
        GL11.glPopAttrib();
        GL11.glPopMatrix();
    }
    
    public static void customScaledObject2D(final float n, final float n2, final float n3, final float n4, final float n5) {
        GL11.glTranslated((double)(n3 / 2.0f), (double)(n4 / 2.0f), 1.0);
        GL11.glTranslated((double)(-n * n5 + n + n3 / 2.0f * -n5), (double)(-n2 * n5 + n2 + n4 / 2.0f * -n5), 1.0);
        GL11.glScaled((double)n5, (double)n5, 0.0);
    }
    
    public static void outline(final double n, final double n2, final double n3, final double n4, final int n5, final int n6, final int n7) {
        drawGradientRect(n, n2 - n5, n3, n2, false, true, n6, n7);
        drawGradientRect(n, n4, n3, n4 + n5, false, false, n6, n7);
        drawSector2(n3, n4, 0, 90, n5, n6);
        drawSector2(n3, n2, 90, 180, n5, n6);
        drawSector2(n, n2, 180, 270, n5, n6);
        drawSector2(n, n4, 270, 360, n5, n6);
        drawGradientRect(n - n5, n2, n, n4, true, true, n6, n7);
        drawGradientRect(n3, n2, n3 + n5, n4, true, false, n6, n7);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
    }
    
    public static void blockEspFrame(final BlockPos blockPos, final double n, final double n2, final double n3) {
        final double n4 = blockPos.getX();
        Minecraft.getMinecraft().getRenderManager();
        final double n5 = n4 - RenderUtils.mc.getRenderManager().viewerPosX;
        final double n6 = blockPos.getY();
        Minecraft.getMinecraft().getRenderManager();
        final double n7 = n6 - RenderUtils.mc.getRenderManager().viewerPosY;
        final double n8 = blockPos.getZ();
        Minecraft.getMinecraft().getRenderManager();
        final double n9 = n8 - RenderUtils.mc.getRenderManager().viewerPosZ;
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(3042);
        GL11.glLineWidth(1.0f);
        GL11.glDisable(3553);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        GL11.glColor4d(n, n2, n3, 1.0);
        drawSelectionBoundingBox(new AxisAlignedBB(n5, n7, n9, n5 + 1.0, n7 + 1.0, n9 + 1.0));
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
    }
    
    public static void drawDownShadow(final float n, final float n2, final float n3, final float n4) {
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.shadeModel(7425);
        GL11.glBegin(7);
        glColor(new Color(0, 0, 0, 100).getRGB());
        GL11.glVertex2d((double)n3, (double)n4);
        glColor(new Color(0, 0, 0, 0).getRGB());
        GL11.glVertex2d((double)n3, (double)n2);
        GL11.glVertex2d((double)n, (double)n2);
        glColor(new Color(0, 0, 0, 100).getRGB());
        GL11.glVertex2d((double)n, (double)n4);
        GL11.glEnd();
        GlStateManager.disableBlend();
        GlStateManager.enableTexture2D();
        GlStateManager.enableAlpha();
        GlStateManager.shadeModel(7424);
    }
    
    public static void drawSector3(final double n, final double n2, final int n3, final int n4, final int n5, final int n6) {
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.shadeModel(7425);
        GL11.glBegin(6);
        glColor(n6);
        GL11.glVertex2d(n, n2);
        glColor(n6);
        for (int i = n3; i <= n4; ++i) {
            GL11.glVertex2d(n + Math.sin(i * 3.141592653589793 / 180.0) * n5, n2 + Math.cos(i * 3.141592653589793 / 180.0) * n5);
        }
        GL11.glEnd();
        GlStateManager.disableBlend();
        GlStateManager.enableTexture2D();
        GlStateManager.enableAlpha();
        GlStateManager.shadeModel(7424);
    }
    
    public static void scissorRect(final float n, final float n2, final float n3, final double n4) {
        final ScaledResolution scaledResolution = new ScaledResolution(Minecraft.getMinecraft());
        final int scaleFactor = scaledResolution.getScaleFactor();
        GL11.glScissor((int)(n * scaleFactor), (int)(((float)scaledResolution.getScaledHeight() - n4) * (float)scaleFactor), (int)((n3 - n) * scaleFactor), (int)((n4 - n2) * (float)scaleFactor));
    }
    
    public static void draw2lines(final float n, final float n2, final int n3, final float n4) {
        final float n5 = (n3 >> 24 & 0xFF) / 255.0f;
        final float n6 = (n3 >> 16 & 0xFF) / 255.0f;
        final float n7 = (n3 >> 8 & 0xFF) / 255.0f;
        final float n8 = (n3 & 0xFF) / 255.0f;
        final boolean glIsEnabled = GL11.glIsEnabled(3042);
        final boolean glIsEnabled2 = GL11.glIsEnabled(2848);
        final boolean glIsEnabled3 = GL11.glIsEnabled(3553);
        if (!glIsEnabled) {
            GL11.glEnable(3042);
        }
        if (!glIsEnabled2) {
            GL11.glEnable(2848);
        }
        if (glIsEnabled3) {
            GL11.glDisable(3553);
        }
        GL11.glEnable(2848);
        GL11.glBlendFunc(770, 771);
        GL11.glColor4f(n6, n7, n8, n5);
        GL11.glLineWidth(2.5f);
        GL11.glBegin(9);
        GL11.glVertex2d((double)(n - n4 - 5.0f), (double)(n2 + 70.0f));
        GL11.glVertex2d((double)(n - n4), (double)(n2 + 80.0f));
        GL11.glVertex2d((double)(n - n4), (double)(n2 - 67.0f));
        GL11.glVertex2d((double)(n - n4 - 5.0f), (double)(n2 - 58.0f));
        GL11.glEnd();
        GL11.glBegin(9);
        GL11.glVertex2d((double)(n + n4), (double)(n2 + 80.0f));
        GL11.glVertex2d((double)(n + n4 + 5.0f), (double)(n2 + 70.0f));
        GL11.glVertex2d((double)(n + n4 + 5.0f), (double)(n2 - 58.0f));
        GL11.glVertex2d((double)(n + n4), (double)(n2 - 66.0f));
        GL11.glEnd();
        GL11.glDisable(2848);
        if (glIsEnabled3) {
            GL11.glEnable(3553);
        }
        if (!glIsEnabled2) {
            GL11.glDisable(2848);
        }
        if (!glIsEnabled) {
            GL11.glDisable(3042);
        }
    }
    
    public static void drawShadow(final double n, final double n2, final double n3, final double n4, final int n5, final int n6) {
        drawGradientRect(n, n2 - n5, n3, n2, false, true, n6, ColorUtils.swapAlpha(n6, 0.0f));
        drawGradientRect(n, n4, n3, n4 + n5, false, false, n6, ColorUtils.swapAlpha(n6, 0.0f));
        drawSector2(n3, n4, 0, 90, n5, n6);
        drawSector2(n3, n2, 90, 180, n5, n6);
        drawSector2(n, n2, 180, 270, n5, n6);
        drawSector2(n, n4, 270, 360, n5, n6);
        drawGradientRect(n - n5, n2, n, n4, true, true, n6, ColorUtils.swapAlpha(n6, 0.0f));
        drawGradientRect(n3, n2, n3 + n5, n4, true, false, n6, ColorUtils.swapAlpha(n6, 0.0f));
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
    }
    
    public static void customScaledObject2D(final float n, final float n2, final float n3, final float n4, final float n5, final float n6) {
        GL11.glTranslated((double)(n3 / 2.0f), (double)(n4 / 2.0f), 1.0);
        GL11.glTranslated((double)(-n * n5 + n + n3 / 2.0f * -n5), (double)(-n2 * n6 + n2 + n4 / 2.0f * -n6), 1.0);
        GL11.glScaled((double)n5, (double)n6, 0.0);
    }
    
    public static void renderItem(final ItemStack itemStack, final int n, final int n2) {
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.enableDepth();
        RenderHelper.enableGUIStandardItemLighting();
        RenderUtils.mc.getRenderItem().zLevel = -100.0f;
        RenderUtils.mc.getRenderItem().renderItemAndEffectIntoGUI(itemStack, n, n2);
        RenderUtils.mc.getRenderItem().renderItemOverlays(RenderUtils.mc.fontRendererObj, itemStack, n, n2);
        RenderUtils.mc.getRenderItem().zLevel = 0.0f;
        RenderHelper.disableStandardItemLighting();
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        GlStateManager.disableDepth();
    }
    
    public static void drawRect(final float n, final float n2, final float n3, final float n4, final int n5) {
        final float n6 = (n5 >> 24 & 0xFF) / 255.0f;
        final float n7 = (n5 >> 16 & 0xFF) / 255.0f;
        final float n8 = (n5 >> 8 & 0xFF) / 255.0f;
        final float n9 = (n5 & 0xFF) / 255.0f;
        GL11.glPushMatrix();
        GL11.glPushAttrib(1048575);
        GL11.glEnable(3042);
        GL11.glDisable(2884);
        GL11.glDisable(3553);
        GL11.glColor4f(n7, n8, n9, n6);
        GL11.glBegin(7);
        GL11.glVertex2d((double)n, (double)n2);
        GL11.glVertex2d((double)n3, (double)n2);
        GL11.glVertex2d((double)n3, (double)n4);
        GL11.glVertex2d((double)n, (double)n4);
        GL11.glEnd();
        GL11.glEnable(3553);
        GL11.glEnable(2884);
        GL11.glDisable(3042);
        GL11.glPopAttrib();
        GL11.glPopMatrix();
    }
    
    public static void drawGradientRect(final double n, final double n2, final double n3, final double n4, final boolean b, final boolean b2, final int n5, final int n6) {
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.shadeModel(7425);
        GL11.glBegin(7);
        glColor(n5);
        if (b) {
            if (b2) {
                GL11.glVertex2d(n3, n4);
                GL11.glVertex2d(n3, n2);
                glColor(n6);
                GL11.glVertex2d(n, n2);
                GL11.glVertex2d(n, n4);
            }
            else {
                GL11.glVertex2d(n, n2);
                GL11.glVertex2d(n, n4);
                glColor(n6);
                GL11.glVertex2d(n3, n4);
                GL11.glVertex2d(n3, n2);
            }
        }
        else if (b2) {
            GL11.glVertex2d(n3, n4);
            glColor(n6);
            GL11.glVertex2d(n3, n2);
            GL11.glVertex2d(n, n2);
            glColor(n5);
            GL11.glVertex2d(n, n4);
        }
        else {
            GL11.glVertex2d(n, n2);
            glColor(n6);
            GL11.glVertex2d(n, n4);
            GL11.glVertex2d(n3, n4);
            glColor(n5);
            GL11.glVertex2d(n3, n2);
        }
        GL11.glEnd();
        GlStateManager.disableBlend();
        GlStateManager.enableTexture2D();
        GlStateManager.enableAlpha();
        GlStateManager.shadeModel(7424);
    }
    
    public static void drawGradientRect(final double n, final double n2, final double n3, final double n4, final boolean b, final int n5, final int n6) {
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.shadeModel(7425);
        glColor(n5);
        GL11.glBegin(7);
        if (b) {
            GL11.glVertex2d(n, n2);
            GL11.glVertex2d(n, n4);
            glColor(n6);
            GL11.glVertex2d(n3, n4);
            GL11.glVertex2d(n3, n2);
        }
        else {
            GL11.glVertex2d(n, n2);
            glColor(n6);
            GL11.glVertex2d(n, n4);
            GL11.glVertex2d(n3, n4);
            glColor(n5);
            GL11.glVertex2d(n3, n2);
        }
        GL11.glEnd();
        GlStateManager.disableBlend();
        GlStateManager.enableTexture2D();
        GlStateManager.enableAlpha();
        GlStateManager.shadeModel(7424);
    }
    
    public static void drawSelectionBoundingBox(final AxisAlignedBB axisAlignedBB) {
        final Tessellator instance = Tessellator.getInstance();
        final BufferBuilder buffer = instance.getBuffer();
        buffer.begin(3, DefaultVertexFormats.POSITION);
        buffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
        buffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
        buffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).endVertex();
        buffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).endVertex();
        buffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
        instance.draw();
        buffer.begin(3, DefaultVertexFormats.POSITION);
        buffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
        buffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
        buffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).endVertex();
        buffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).endVertex();
        buffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
        instance.draw();
        buffer.begin(1, DefaultVertexFormats.POSITION);
        buffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
        buffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
        buffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
        buffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
        buffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).endVertex();
        buffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).endVertex();
        buffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).endVertex();
        buffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).endVertex();
        instance.draw();
    }
    
    public static void drawSector2(final double n, final double n2, final int n3, final int n4, final int n5, final int n6) {
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.shadeModel(7425);
        GL11.glBegin(6);
        glColor(n6);
        GL11.glVertex2d(n, n2);
        glColor(ColorUtils.swapAlpha(n6, 0.0f));
        int i = n3;
        while (i <= n4) {
            GL11.glVertex2d(n + Math.sin(i * 3.141592653589793 / 180.0) * n5, n2 + Math.cos(i * 3.141592653589793 / 180.0) * n5);
            final Object o = null;
            ++i;
            if (o != null) {
                return;
            }
        }
        GL11.glEnd();
        GlStateManager.disableBlend();
        GlStateManager.enableTexture2D();
        GlStateManager.enableAlpha();
        GlStateManager.shadeModel(7424);
    }
    
    public static Vec3d getRenderPos(double n, double n2, double n3) {
        n -= RenderUtils.mc.getRenderManager().viewerPosX;
        n2 -= RenderUtils.mc.getRenderManager().viewerPosY;
        n3 -= RenderUtils.mc.getRenderManager().viewerPosZ;
        return new Vec3d(n, n2, n3);
    }
}
