package ru.hld.legendline.impl.modules.Render;

import ru.hld.legendline.api.setting.settings.*;
import ru.hld.legendline.api.event.events.*;
import net.minecraft.client.renderer.*;
import net.minecraft.entity.*;
import net.minecraft.entity.player.*;
import ru.hld.legendline.api.shit.*;
import java.util.*;
import ru.hld.legendline.api.event.*;
import ru.hld.legendline.api.module.*;
import ru.hld.legendline.*;
import ru.hld.legendline.api.setting.*;

public class WallHack extends Module
{
    BooleanSetting friends;
    
    @EventTarget
    public void onRender3D(final EventRender3D eventRender3D) {
        GlStateManager.clear(256);
        RenderHelper.enableStandardItemLighting();
        for (final Entity entity : this.mc.world.loadedEntityList) {
            if (entity instanceof EntityPlayer && entity != this.mc.getRenderViewEntity()) {
                if (FriendManager.isFriend(entity.getName()) && this.friends.getVal()) {
                    this.render(entity, this.mc.getRenderPartialTicks());
                }
                else {
                    this.render(entity, this.mc.getRenderPartialTicks());
                }
            }
        }
    }
    
    void render(final Entity entity, final float n) {
        try {
            if (entity == null || entity == this.mc.player) {
                return;
            }
            if (entity == this.mc.getRenderViewEntity() && this.mc.gameSettings.thirdPersonView == 0) {
                return;
            }
            this.mc.entityRenderer.disableLightmap();
            this.mc.getRenderManager().renderEntityStatic(entity, n, false);
            this.mc.entityRenderer.enableLightmap();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public WallHack() {
        super("WallHack", "show player", Category.Render);
        Client.settingManager.add(this.friends = new BooleanSetting("Friends", "show friends", this, true));
    }
}
