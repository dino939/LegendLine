package ru.hld.legendline.impl.modules.Render;

import ru.hld.legendline.api.module.*;
import ru.hld.legendline.api.event.events.*;
import org.lwjgl.opengl.*;
import net.minecraft.entity.*;
import net.minecraft.entity.player.*;
import net.minecraft.util.math.*;
import ru.hld.legendline.api.shit.*;
import ru.hld.legendline.*;
import ru.hld.legendline.antiNative.*;
import java.awt.*;
import java.util.*;
import ru.hld.legendline.api.event.*;

public class Tracers extends Module
{
    static final boolean $assertionsDisabled;
    
    static {
        $assertionsDisabled = !Tracers.class.desiredAssertionStatus();
    }
    
    public Tracers() {
        super("Tracers", "render lines from your crosshair to players", Category.Render);
    }
    
    @EventTarget
    public void onRender3D(final EventRender3D eventRender3D) {
        this.mc.gameSettings.viewBobbing = false;
        this.mc.entityRenderer.setupCameraTransform(eventRender3D.getPartialTicks(), 2);
        GL11.glPushMatrix();
        GL11.glEnable(2848);
        GL11.glDisable(2929);
        GL11.glDisable(3553);
        GL11.glDisable(2896);
        GL11.glDepthMask(false);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glEnable(3042);
        GL11.glLineWidth(1.0E-6f);
        for (final Entity entity : this.mc.world.loadedEntityList) {
            if (entity != this.mc.player) {
                if (!(entity instanceof EntityPlayer)) {
                    continue;
                }
                assert this.mc.getRenderViewEntity() != null;
                this.mc.player.getDistanceToEntity(entity);
                final double n = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) - this.mc.getRenderManager().viewerPosX;
                final double n2 = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) - this.mc.getRenderManager().viewerPosY;
                final double n3 = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) - this.mc.getRenderManager().viewerPosZ;
                final Vec3d rotateYaw = new Vec3d(0.0, 0.0, 1.0).rotatePitch(-(float)Math.toRadians(this.mc.player.rotationPitch)).rotateYaw(-(float)Math.toRadians(this.mc.player.rotationYaw));
                GL11.glBegin(2);
                if (FriendManager.isFriend(entity.getName())) {
                    RenderUtils.glColor(Client.getColor());
                }
                else {
                    RenderUtils.glColor(Color.white.getRGB());
                }
                GL11.glVertex3d(rotateYaw.xCoord, this.mc.player.getEyeHeight() + rotateYaw.yCoord, rotateYaw.zCoord);
                GL11.glVertex3d(n, n2 + 1.1, n3);
                GL11.glEnd();
            }
        }
        GL11.glDisable(3042);
        GL11.glDepthMask(true);
        GL11.glDisable(2848);
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDisable(2848);
        GL11.glPopMatrix();
    }
}
