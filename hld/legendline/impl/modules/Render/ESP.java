package ru.hld.legendline.impl.modules.Render;

import ru.hld.legendline.api.setting.settings.*;
import ru.hld.legendline.api.module.*;
import ru.hld.legendline.*;
import ru.hld.legendline.api.setting.*;
import ru.hld.legendline.api.event.events.*;
import net.minecraft.util.text.*;
import net.minecraft.entity.*;
import net.minecraft.entity.player.*;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.monster.*;
import org.lwjgl.opengl.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.entity.*;
import ru.hld.legendline.antiNative.*;
import ru.hld.legendline.api.shit.*;
import ru.hld.legendline.api.utils.*;
import java.util.*;
import ru.hld.legendline.api.event.*;

public class ESP extends Module
{
    BooleanSetting friends;
    float healphAnim;
    ModeSetting mode;
    BooleanSetting animals;
    BooleanSetting self;
    BooleanSetting mobs;
    BooleanSetting players;
    
    public ESP() {
        super("ESP", "draw box on player", Category.Render);
        this.healphAnim = 0.0f;
        final ArrayList<String> list = new ArrayList<String>();
        list.add("Borderless");
        list.add("Line");
        list.add("DeadCode");
        list.add("NightMare");
        Client.settingManager.add(this.players = new BooleanSetting("Players", "you can see players throw walls", this, true));
        Client.settingManager.add(this.mobs = new BooleanSetting("Mobs", "you can see mobs throw walls", this, true));
        Client.settingManager.add(this.animals = new BooleanSetting("Animals", "you can see animals throw walls", this, true));
        Client.settingManager.add(this.mode = new ModeSetting("Mode", "modes of ESP", this, list, "Borderless"));
        Client.settingManager.add(this.friends = new BooleanSetting("Friends", "you can see friends throw walls", this, true));
        Client.settingManager.add(this.self = new BooleanSetting("Slf", "you can see self throw walls", this, true));
    }
    
    @EventTarget
    public void onRender3D(final EventRender3D eventRender3D) {
        this.setDisplayname(String.valueOf(new StringBuilder().append(TextFormatting.WHITE).append(this.mode.getVal()).append(" ").append(TextFormatting.RESET).append(this.getName()).append(" ")));
        for (final Entity entity : this.mc.world.loadedEntityList) {
            final String val = this.mode.getVal();
            switch (val) {
                case "Line": {
                    if ((entity instanceof EntityPlayer && entity != this.mc.player && this.players.getVal()) || (entity instanceof EntityAnimal && this.animals.getVal()) || (entity instanceof EntityMob && this.mobs.getVal())) {
                        final double n2 = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * eventRender3D.getPartialTicks() - this.mc.getRenderManager().viewerPosX;
                        final double n3 = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * eventRender3D.getPartialTicks() - this.mc.getRenderManager().viewerPosY;
                        final double n4 = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * eventRender3D.getPartialTicks() - this.mc.getRenderManager().viewerPosZ;
                        GL11.glPushMatrix();
                        GL11.glDisable(2929);
                        GL11.glDisable(3553);
                        GL11.glTranslatef((float)n2, (float)n3, (float)n4);
                        GlStateManager.glNormal3f(0.0f, 1.0f, 0.0f);
                        GlStateManager.rotate(-this.mc.getRenderManager().playerViewY, 0.0f, 1.0f, 0.0f);
                        GlStateManager.rotate(this.mc.getRenderManager().playerViewX, 1.0f, 0.0f, 0.0f);
                        GL11.glLineWidth(4.7f);
                        GL11.glColor3f(0.0f, 0.0f, 0.0f);
                        GL11.glBegin(3);
                        GL11.glVertex3f(0.4f, 0.0f, 0.0f);
                        GL11.glVertex3f(0.4f, entity.height, 0.0f);
                        GL11.glEnd();
                        GL11.glColor3f(0.0f, 255.0f, 0.0f);
                        GL11.glLineWidth(0.8f);
                        GL11.glColor3f(255.0f, 255.0f, 255.0f);
                        GL11.glBegin(3);
                        GL11.glVertex3f(0.4f, 0.0f, 0.0f);
                        GL11.glVertex3f(0.4f, entity.height, 0.0f);
                        GL11.glColor3f(0.0f, 255.0f, 0.0f);
                        GL11.glEnd();
                        GL11.glBegin(3);
                        GL11.glVertex3f(0.4f, 0.0f, 0.0f);
                        GL11.glVertex3f(0.4f, ((EntityPlayerSP)entity).getHealth() / ((EntityPlayerSP)entity).getMaxHealth() * entity.height, 0.0f);
                        GL11.glEnd();
                        GL11.glEnable(3553);
                        GL11.glEnable(2929);
                        GL11.glColor3f(255.0f, 255.0f, 255.0f);
                        GL11.glPopMatrix();
                        continue;
                    }
                    continue;
                }
                case "Borderless": {
                    if ((entity instanceof EntityPlayer && entity != this.mc.player && this.players.getVal()) || (entity instanceof EntityAnimal && this.animals.getVal()) || (entity instanceof EntityMob && this.mobs.getVal())) {
                        final double n5 = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * eventRender3D.getPartialTicks() - this.mc.getRenderManager().viewerPosX;
                        final double n6 = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * eventRender3D.getPartialTicks() - this.mc.getRenderManager().viewerPosY;
                        final double n7 = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * eventRender3D.getPartialTicks() - this.mc.getRenderManager().viewerPosZ;
                        GL11.glPushMatrix();
                        GL11.glDisable(2929);
                        GL11.glDisable(3553);
                        GL11.glTranslatef((float)n5, (float)n6, (float)n7);
                        GlStateManager.glNormal3f(0.0f, 1.0f, 0.0f);
                        GlStateManager.rotate(-this.mc.getRenderManager().playerViewY, 0.0f, 1.0f, 0.0f);
                        GlStateManager.rotate(this.mc.getRenderManager().playerViewX, 1.0f, 0.0f, 0.0f);
                        GL11.glLineWidth(4.0f);
                        GL11.glColor3f(0.0f, 0.0f, 0.0f);
                        GL11.glBegin(3);
                        GL11.glVertex3f(entity.width, 0.0f, 0.0f);
                        GL11.glVertex3f(entity.width, entity.height, 0.0f);
                        GL11.glEnd();
                        GL11.glBegin(3);
                        GL11.glVertex3f(-entity.width, 0.0f, 0.0f);
                        GL11.glVertex3f(-entity.width, entity.height, 0.0f);
                        GL11.glEnd();
                        GL11.glBegin(3);
                        GL11.glVertex3f(-entity.width, 0.0f, 0.0f);
                        GL11.glVertex3f(entity.width, 0.0f, 0.0f);
                        GL11.glEnd();
                        GL11.glBegin(3);
                        GL11.glVertex3f(entity.width, entity.height, 0.0f);
                        GL11.glVertex3f(-entity.width, entity.height, 0.0f);
                        GL11.glEnd();
                        GL11.glLineWidth(6.0f);
                        GL11.glBegin(3);
                        GL11.glVertex3f(entity.width + 0.1f, -0.003f, 0.0f);
                        GL11.glVertex3f(entity.width + 0.1f, ((EntityPlayerSP)entity).getHealth() / ((EntityPlayerSP)entity).getMaxHealth() * entity.height + 0.003f, 0.0f);
                        GL11.glEnd();
                        RenderUtils.setupColor(Client.getColor(), 255.0f);
                        GL11.glLineWidth(2.0f);
                        GL11.glBegin(3);
                        GL11.glVertex3f(entity.width, 0.0f, 0.0f);
                        GL11.glVertex3f(entity.width, entity.height, 0.0f);
                        GL11.glEnd();
                        GL11.glBegin(3);
                        GL11.glVertex3f(-entity.width, 0.0f, 0.0f);
                        GL11.glVertex3f(-entity.width, entity.height, 0.0f);
                        GL11.glEnd();
                        GL11.glBegin(3);
                        GL11.glVertex3f(-entity.width, 0.0f, 0.0f);
                        GL11.glVertex3f(entity.width, 0.0f, 0.0f);
                        GL11.glEnd();
                        GL11.glBegin(3);
                        GL11.glVertex3f(entity.width, entity.height, 0.0f);
                        GL11.glVertex3f(-entity.width, entity.height, 0.0f);
                        GL11.glEnd();
                        GL11.glLineWidth(2.0f);
                        GL11.glBegin(3);
                        GL11.glVertex3f(entity.width + 0.1f, 0.0f, 0.0f);
                        GL11.glVertex3f(entity.width + 0.1f, ((EntityPlayerSP)entity).getHealth() / ((EntityPlayerSP)entity).getMaxHealth() * entity.height, 0.0f);
                        GL11.glEnd();
                        GL11.glEnable(3553);
                        GL11.glEnable(2929);
                        GL11.glColor3f(255.0f, 255.0f, 255.0f);
                        GL11.glPopMatrix();
                        continue;
                    }
                    continue;
                }
                case "DeadCode": {
                    if ((entity instanceof EntityPlayer && entity != this.mc.player && this.players.getVal()) || (entity instanceof EntityAnimal && this.animals.getVal()) || (entity instanceof EntityMob && this.mobs.getVal())) {
                        final double n8 = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * eventRender3D.getPartialTicks() - this.mc.getRenderManager().viewerPosX;
                        final double n9 = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * eventRender3D.getPartialTicks() - this.mc.getRenderManager().viewerPosY;
                        final double n10 = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * eventRender3D.getPartialTicks() - this.mc.getRenderManager().viewerPosZ;
                        GL11.glPushMatrix();
                        GL11.glDisable(2929);
                        GL11.glDisable(3553);
                        GL11.glTranslatef((float)n8, (float)n9, (float)n10);
                        GlStateManager.glNormal3f(0.0f, 1.0f, 0.0f);
                        GlStateManager.rotate(-this.mc.getRenderManager().playerViewY, 0.0f, 1.0f, 0.0f);
                        GlStateManager.rotate(this.mc.getRenderManager().playerViewX, 1.0f, 0.0f, 0.0f);
                        GL11.glLineWidth(5.0f);
                        GL11.glColor3f(0.0f, 0.0f, 0.0f);
                        GL11.glBegin(3);
                        GL11.glVertex3f(0.0f, entity.height - 1.0f, 0.0f);
                        GL11.glVertex3f(entity.width, entity.height - 0.4f, 0.0f);
                        GL11.glEnd();
                        GL11.glBegin(3);
                        GL11.glVertex3f(0.0f, entity.height + 0.2f, 0.0f);
                        GL11.glVertex3f(entity.width, entity.height - 0.4f, 0.0f);
                        GL11.glEnd();
                        GL11.glBegin(3);
                        GL11.glVertex3f(0.0f, entity.height + 0.2f, 0.0f);
                        GL11.glVertex3f(-entity.width, entity.height - 0.4f, 0.0f);
                        GL11.glEnd();
                        GL11.glBegin(3);
                        GL11.glVertex3f(0.0f, entity.height - 1.0f, 0.0f);
                        GL11.glVertex3f(-entity.width, entity.height - 0.4f, 0.0f);
                        GL11.glEnd();
                        RenderUtils.setupColor(Client.getColor(), 255.0f);
                        if (FriendManager.isFriend(entity.getName()) && this.friends.getVal()) {
                            RenderUtils.setupColor(ColorUtils.astolfoColorsCool(1, 80), 255.0f);
                        }
                        GL11.glLineWidth(2.0f);
                        GL11.glBegin(3);
                        GL11.glVertex3f(0.0f, entity.height - 1.0f, 0.0f);
                        GL11.glVertex3f(entity.width, entity.height - 0.4f, 0.0f);
                        GL11.glEnd();
                        GL11.glBegin(3);
                        GL11.glVertex3f(0.0f, entity.height + 0.2f, 0.0f);
                        GL11.glVertex3f(entity.width, entity.height - 0.4f, 0.0f);
                        GL11.glEnd();
                        GL11.glBegin(3);
                        GL11.glVertex3f(0.0f, entity.height + 0.2f, 0.0f);
                        GL11.glVertex3f(-entity.width, entity.height - 0.4f, 0.0f);
                        GL11.glEnd();
                        GL11.glBegin(3);
                        GL11.glVertex3f(0.0f, entity.height - 1.0f, 0.0f);
                        GL11.glVertex3f(-entity.width, entity.height - 0.4f, 0.0f);
                        GL11.glEnd();
                        GL11.glEnable(3553);
                        GL11.glEnable(2929);
                        GL11.glColor3f(255.0f, 255.0f, 255.0f);
                        GL11.glPopMatrix();
                        continue;
                    }
                    continue;
                }
                case "NightMare": {
                    if ((entity instanceof EntityPlayer && entity != this.mc.player && this.players.getVal()) || (entity instanceof EntityAnimal && this.animals.getVal()) || (entity instanceof EntityMob && this.mobs.getVal())) {
                        final double n11 = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * eventRender3D.getPartialTicks() - this.mc.getRenderManager().viewerPosX;
                        final double n12 = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * eventRender3D.getPartialTicks() - this.mc.getRenderManager().viewerPosY;
                        final double n13 = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * eventRender3D.getPartialTicks() - this.mc.getRenderManager().viewerPosZ;
                        GL11.glPushMatrix();
                        GL11.glDisable(2929);
                        GL11.glDisable(3553);
                        GL11.glTranslatef((float)n11, (float)n12, (float)n13);
                        GlStateManager.glNormal3f(0.0f, 1.0f, 0.0f);
                        GlStateManager.rotate(-this.mc.getRenderManager().playerViewY, 0.0f, 1.0f, 0.0f);
                        GlStateManager.rotate(this.mc.getRenderManager().playerViewX, 1.0f, 0.0f, 0.0f);
                        GL11.glLineWidth(10.0f);
                        GL11.glColor3f(0.0f, 0.0f, 0.0f);
                        GL11.glBegin(3);
                        GL11.glVertex3f(entity.width, entity.height - 0.5f, 0.0f);
                        GL11.glVertex3f(entity.width - 0.2f, entity.height - 0.7f, 0.0f);
                        GL11.glEnd();
                        GL11.glBegin(3);
                        GL11.glVertex3f(entity.width, entity.height - 0.5f, 0.0f);
                        GL11.glVertex3f(entity.width - 0.2f, entity.height - 0.3f, 0.0f);
                        GL11.glEnd();
                        GL11.glBegin(3);
                        GL11.glVertex3f(-entity.width, entity.height - 0.5f, 0.0f);
                        GL11.glVertex3f(-entity.width + 0.2f, entity.height - 0.7f, 0.0f);
                        GL11.glEnd();
                        GL11.glBegin(3);
                        GL11.glVertex3f(-entity.width, entity.height - 0.5f, 0.0f);
                        GL11.glVertex3f(-entity.width + 0.2f, entity.height - 0.3f, 0.0f);
                        GL11.glEnd();
                        GL11.glBegin(3);
                        GL11.glVertex3f(entity.width - 0.4f, entity.height - 0.9f, 0.0f);
                        GL11.glVertex3f(entity.width - 0.6f, entity.height - 1.1f, 0.0f);
                        GL11.glEnd();
                        GL11.glBegin(3);
                        GL11.glVertex3f(-entity.width + 0.4f, entity.height - 0.9f, 0.0f);
                        GL11.glVertex3f(-entity.width + 0.6f, entity.height - 1.1f, 0.0f);
                        GL11.glEnd();
                        GL11.glBegin(3);
                        GL11.glVertex3f(entity.width - 0.4f, entity.height - 0.1f, 0.0f);
                        GL11.glVertex3f(entity.width - 0.6f, entity.height + 0.1f, 0.0f);
                        GL11.glEnd();
                        GL11.glBegin(3);
                        GL11.glVertex3f(-entity.width + 0.4f, entity.height - 0.1f, 0.0f);
                        GL11.glVertex3f(-entity.width + 0.6f, entity.height + 0.1f, 0.0f);
                        GL11.glEnd();
                        RenderUtils.setupColor(Client.getColor(), 255.0f);
                        GL11.glLineWidth(4.0f);
                        GL11.glBegin(3);
                        GL11.glVertex3f(entity.width, entity.height - 0.5f, 0.0f);
                        GL11.glVertex3f(entity.width - 0.2f, entity.height - 0.7f, 0.0f);
                        GL11.glEnd();
                        GL11.glBegin(3);
                        GL11.glVertex3f(entity.width, entity.height - 0.5f, 0.0f);
                        GL11.glVertex3f(entity.width - 0.2f, entity.height - 0.3f, 0.0f);
                        GL11.glEnd();
                        GL11.glBegin(3);
                        GL11.glVertex3f(-entity.width, entity.height - 0.5f, 0.0f);
                        GL11.glVertex3f(-entity.width + 0.2f, entity.height - 0.7f, 0.0f);
                        GL11.glEnd();
                        GL11.glBegin(3);
                        GL11.glVertex3f(-entity.width, entity.height - 0.5f, 0.0f);
                        GL11.glVertex3f(-entity.width + 0.2f, entity.height - 0.3f, 0.0f);
                        GL11.glEnd();
                        GL11.glBegin(3);
                        GL11.glVertex3f(entity.width - 0.4f, entity.height - 0.9f, 0.0f);
                        GL11.glVertex3f(entity.width - 0.6f, entity.height - 1.1f, 0.0f);
                        GL11.glEnd();
                        GL11.glBegin(3);
                        GL11.glVertex3f(-entity.width + 0.4f, entity.height - 0.9f, 0.0f);
                        GL11.glVertex3f(-entity.width + 0.6f, entity.height - 1.1f, 0.0f);
                        GL11.glEnd();
                        GL11.glBegin(3);
                        GL11.glVertex3f(entity.width - 0.4f, entity.height - 0.1f, 0.0f);
                        GL11.glVertex3f(entity.width - 0.6f, entity.height + 0.1f, 0.0f);
                        GL11.glEnd();
                        GL11.glBegin(3);
                        GL11.glVertex3f(-entity.width + 0.4f, entity.height - 0.1f, 0.0f);
                        GL11.glVertex3f(-entity.width + 0.6f, entity.height + 0.1f, 0.0f);
                        GL11.glEnd();
                        GL11.glEnable(3553);
                        GL11.glEnable(2929);
                        GL11.glColor3f(255.0f, 255.0f, 255.0f);
                        GL11.glPopMatrix();
                        continue;
                    }
                    continue;
                }
            }
        }
    }
}
