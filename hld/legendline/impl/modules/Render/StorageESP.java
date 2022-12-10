package ru.hld.legendline.impl.modules.Render;

import ru.hld.legendline.api.setting.settings.*;
import net.minecraft.entity.*;
import ru.hld.legendline.api.module.*;
import ru.hld.legendline.*;
import ru.hld.legendline.api.setting.*;
import net.minecraft.entity.item.*;
import java.util.*;
import ru.hld.legendline.api.event.*;
import ru.hld.legendline.api.event.events.*;
import net.minecraft.client.renderer.*;

public class StorageESP extends Module
{
    BooleanSetting wallHack;
    BooleanSetting glow;
    ArrayList armorStands;
    
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
    
    public StorageESP() {
        super("StorageESP", "show storages through walls", Category.Render);
        this.armorStands = new ArrayList();
        Client.settingManager.add(this.glow = new BooleanSetting("Glow", "make objects glow", this, false));
        Client.settingManager.add(this.wallHack = new BooleanSetting("WallHack", "make objects visabel through walls", this, true));
    }
    
    @EventTarget
    public void onUpdate(final EventUpdate eventUpdate) {
        for (final Entity entity : this.mc.world.loadedEntityList) {
            if (entity instanceof EntityArmorStand && this.glow.getVal() && !entity.isGlowing()) {
                entity.setGlowing(true);
            }
            if (entity instanceof EntityArmorStand && !this.glow.getVal() && entity.isGlowing()) {
                entity.setGlowing(false);
            }
        }
    }
    
    @EventTarget
    public void onRender3D(final EventRender3D eventRender3D) {
        GlStateManager.clear(256);
        RenderHelper.enableStandardItemLighting();
        if (this.wallHack.getVal()) {
            for (final Entity entity : this.mc.world.loadedEntityList) {
                if (entity instanceof EntityArmorStand && entity != this.mc.getRenderViewEntity()) {
                    this.render(entity, this.mc.getRenderPartialTicks());
                }
            }
        }
    }
}
