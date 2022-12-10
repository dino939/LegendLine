package ru.hld.legendline.api.utils;

import net.minecraft.client.*;
import net.minecraft.util.*;
import net.minecraft.client.gui.*;
import org.lwjgl.opengl.*;
import ru.hld.legendline.antiNative.*;
import net.minecraft.client.shader.*;

public class BlurUtil
{
    private Framebuffer framebuffer;
    private int lastHeight;
    private int lastFactor;
    private ShaderGroup shaderGroup;
    protected static Minecraft mc;
    private final ResourceLocation resourceLocation;
    private int lastWidth;
    
    public void init() {
        try {
            (this.shaderGroup = new ShaderGroup(BlurUtil.mc.getTextureManager(), BlurUtil.mc.getResourceManager(), BlurUtil.mc.getFramebuffer(), this.resourceLocation)).createBindFramebuffers(BlurUtil.mc.displayWidth, BlurUtil.mc.displayHeight);
            this.framebuffer = this.shaderGroup.mainFramebuffer;
        }
        catch (Exception ex) {}
    }
    
    public void blur(final float n, final float n2, final float n3, final float n4, final int n5) {
        final ScaledResolution scaledResolution = new ScaledResolution(BlurUtil.mc);
        final int scaleFactor = scaledResolution.getScaleFactor();
        final int scaledWidth = scaledResolution.getScaledWidth();
        final int scaledHeight = scaledResolution.getScaledHeight();
        if (this.sizeHasChanged(scaleFactor, scaledWidth, scaledHeight) || this.framebuffer == null || this.shaderGroup == null) {
            this.init();
        }
        this.lastFactor = scaleFactor;
        this.lastWidth = scaledWidth;
        this.lastHeight = scaledHeight;
        GL11.glPushMatrix();
        GL11.glEnable(3089);
        RenderUtils.scissorRect(n, n2, n3, n4);
        this.framebuffer.bindFramebuffer(true);
        this.shaderGroup.loadShaderGroup(BlurUtil.mc.getRenderPartialTicks());
        int i = 0;
        while (i < 3) {
            ((Shader)this.shaderGroup.getListShaders().get(i)).getShaderManager().getShaderUniform("Radius").set((float)n5);
            final int n6 = -1;
            final int n7 = -1;
            ++i;
            if (n6 > n7) {
                return;
            }
        }
        BlurUtil.mc.getFramebuffer().bindFramebuffer(false);
        GL11.glDisable(3089);
        GL11.glPopMatrix();
    }
    
    private boolean sizeHasChanged(final int n, final int n2, final int n3) {
        return this.lastFactor != n || this.lastWidth != n2 || this.lastHeight != n3;
    }
    
    public BlurUtil() {
        this.resourceLocation = new ResourceLocation("blur.json");
    }
    
    static {
        BlurUtil.mc = Minecraft.getMinecraft();
    }
}
