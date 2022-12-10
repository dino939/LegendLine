package ru.hld.legendline.impl.modules.Render;

import ru.hld.legendline.api.setting.settings.*;
import ru.hld.legendline.api.module.*;
import ru.hld.legendline.*;
import ru.hld.legendline.api.setting.*;
import ru.hld.legendline.api.event.events.*;
import ru.hld.legendline.impl.modules.Combat.*;
import net.minecraft.entity.player.*;
import org.lwjgl.opengl.*;
import java.awt.*;
import net.minecraft.client.gui.inventory.*;
import net.minecraft.entity.*;
import net.minecraft.item.*;
import net.minecraft.util.*;
import ru.hld.legendline.antiNative.*;
import net.minecraft.client.network.*;
import ru.hld.legendline.api.utils.*;
import java.util.*;
import ru.hld.legendline.api.event.*;

public class TargetHud extends Module
{
    float anim;
    int kal;
    ModeSetting mode;
    float healphAnim;
    ModeSetting color;
    
    @Override
    public void onEnable() {
        super.onEnable();
    }
    
    public TargetHud() {
        super("TargetHud", "show info about player on screen", Category.Render, 155, 35);
        this.anim = 0.8f;
        this.healphAnim = 0.0f;
        final ArrayList<String> list = new ArrayList<String>();
        final ArrayList<String> list2 = new ArrayList<String>();
        list.add("Circle");
        list.add("Line");
        list2.add("Astolfo");
        list2.add("Client");
        Client.settingManager.add(this.mode = new ModeSetting("Mode", "modes of TargetHud", this, list, "Circle"));
        Client.settingManager.add(this.color = new ModeSetting("Color", "Colors of TargetHud", this, list2, "Astolfo"));
    }
    
    @EventTarget
    public void onRender2D(final EventRender2D eventRender2D) {
        final CFontRenderer mntsb_20 = Fonts.mntsb_20;
        EntityPlayer entityPlayer = null;
        if (!AimBot.mods.getVal()) {
            entityPlayer = (EntityPlayer)CombatUtils.getTerget(AimBot.walls.getVal());
        }
        else {
            final String val = this.mode.getVal();
            switch (val) {
                case "Rect": {
                    entityPlayer = (EntityPlayer)CombatUtils.getTergetBox(AimBot.walls.getVal(), AimBot.Fov.getVal() * 100.0f / 5.0f);
                    break;
                }
                case "Circle": {
                    entityPlayer = (EntityPlayer)CombatUtils.getTergetCircle(AimBot.walls.getVal(), AimBot.Fov.getVal() * 100.0f / 5.0f);
                    break;
                }
                case "Robot": {
                    entityPlayer = (EntityPlayer)CombatUtils.getTergetCircle(AimBot.walls.getVal(), AimBot.Fov.getVal() * 100.0f / 5.0f);
                    break;
                }
            }
        }
        final ArrayList<ItemStack> list = new ArrayList<ItemStack>();
        final int posX = this.getPosX();
        final int posY = this.getPosY();
        int n2 = 0;
        final String unformattedText = entityPlayer.getDisplayName().getUnformattedText();
        final String s = "huitopytutyuu5u5yuytj";
        final int n3 = mntsb_20.getStringWidth(unformattedText) + 10;
        mntsb_20.getStringWidth(s);
        final String val2 = this.color.getVal();
        switch (val2) {
            case "Astolfo": {
                n2 = ((entityPlayer.hurtTime < 1) ? ColorUtils.astolfoColorsCool(80, 80) : ColorUtils.getColor(255, 30, 40, this.mc.player.hurtTime * 8));
                break;
            }
            case "Client": {
                n2 = ((entityPlayer.hurtTime < 1) ? Client.getColor() : ColorUtils.getColor(255, 30, 40, this.mc.player.hurtTime * 8));
                break;
            }
        }
        final String val3 = this.mode.getVal();
        switch (val3) {
            case "Circle": {
                if (entityPlayer == null) {
                    this.anim = 0.5f;
                    return;
                }
                this.anim = MathUtils.lerp(this.anim, 1.0f, 0.3f);
                GL11.glPushMatrix();
                RenderUtils.customScaledObject2D((float)posX, (float)posY, (float)this.getSizeX(), (float)this.getSizeY(), this.anim, this.anim);
                RenderUtils.drawShadowRect(posX, posY, posX + this.getSizeX(), posY + this.getSizeY(), 3, new Color(30, 30, 30, 200).getRGB());
                RenderUtils.drawGradientRect(posX, posY, posX + this.getSizeX(), posY + this.getSizeY(), true, n2, n2);
                GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
                try {
                    GuiInventory.drawEntityOnScreen(posX + 10, posY + 32, 15, 100.0f, 0.0f, entityPlayer);
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
                if (!(entityPlayer.getHeldItem(EnumHand.MAIN_HAND).getItem() instanceof ItemAir)) {
                    list.add(entityPlayer.getHeldItem(EnumHand.MAIN_HAND));
                }
                for (final ItemStack itemStack : entityPlayer.getArmorInventoryList()) {
                    if (!(itemStack.getItem() instanceof ItemAir)) {
                        list.add(itemStack);
                    }
                }
                if (!(entityPlayer.getHeldItem(EnumHand.OFF_HAND).getItem() instanceof ItemAir)) {
                    list.add(entityPlayer.getHeldItem(EnumHand.OFF_HAND));
                }
                int n6 = 20;
                final Iterator<ItemStack> iterator2 = list.iterator();
                while (iterator2.hasNext()) {
                    RenderUtils.renderItem(iterator2.next(), posX + n6, posY + 16);
                    n6 += 16;
                }
                mntsb_20.drawString(unformattedText, posX + 23, posY + 2, -1);
                this.healphAnim = MathUtils.lerp(this.healphAnim, entityPlayer.getHealth(), 0.4f);
                RenderUtil.drawCircle228((float)(posX + 138), (float)(posY + 17), 12.0f, -1, (int)this.mc.player.getMaxHealth() * (int)entityPlayer.getHealth());
                mntsb_20.drawCenteredString(String.valueOf(new StringBuilder().append((int)entityPlayer.getHealth()).append("")), posX + 138, posY + 14, -1);
                GL11.glPopMatrix();
                break;
            }
            case "Line": {
                if (entityPlayer == null) {
                    this.anim = 0.5f;
                    return;
                }
                this.anim = MathUtils.lerp(this.anim, 1.0f, 0.1f);
                GL11.glPushMatrix();
                RenderUtils.customScaledObject2D((float)posX, (float)posY, (float)this.getSizeX(), (float)this.getSizeY(), this.anim, this.anim);
                if (n3 >= 120) {
                    RenderUtils.drawShadowRect(posX, posY, posX + 10 + n3 / 2 * 2.3, posY + this.getSizeY() + 5, 3, new Color(30, 30, 30, 100).getRGB());
                    RenderUtils.drawShadowRect(posX, posY + this.getSizeY(), posX + 10 + n3 / 2 * 2.3, posY + this.getSizeY() + 5, 3, ColorUtils.swapAlpha(n2, 255.0f));
                    RenderUtils.drawShadowRect(posX, posY + this.getSizeY(), posX + this.getSizeX(), posY + this.getSizeY() + 5, 3, new Color(100, 100, 100, 50).getRGB());
                    mntsb_20.drawCenteredString(String.valueOf(new StringBuilder().append((int)entityPlayer.getHealth()).append("")), posX + 3 + (n3 + 20) / entityPlayer.getMaxHealth() * this.healphAnim, posY + this.getSizeY() + 8, -1);
                }
                else {
                    RenderUtils.drawShadowRect(posX, posY, posX + this.getSizeX(), posY + this.getSizeY() + 5, 3, new Color(30, 30, 30, 100).getRGB());
                    RenderUtils.drawShadowRect(posX, posY + this.getSizeY(), posX + 6.4 + (this.getSizeX() - 6) / entityPlayer.getMaxHealth() * this.healphAnim, posY + this.getSizeY() + 5, 2, ColorUtils.swapAlpha(n2, 255.0f));
                    RenderUtils.drawShadowRect(posX, posY + this.getSizeY(), posX + this.getSizeX(), posY + this.getSizeY() + 5, 3, new Color(100, 100, 100, 50).getRGB());
                    mntsb_20.drawCenteredString(String.valueOf(new StringBuilder().append((int)entityPlayer.getHealth()).append("")), posX + 3 + (this.getSizeX() - 6) / entityPlayer.getMaxHealth() * this.healphAnim, posY + this.getSizeY() + 8, -1);
                }
                GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
                try {
                    RenderUtil.drawHead(Objects.requireNonNull(this.mc.getConnection()).getPlayerInfo(entityPlayer.getUniqueID()).getLocationSkin(), posX + 2, posY + 2, 28, 28);
                    if (this.mc.player.hurtTime > 0) {
                        RenderUtils.drawRect((float)(posX + 2), (float)(posY + 2), (float)(posX + 30), (float)(posY + 30), ColorUtils.getColor(255, 0, 0, this.mc.player.hurtTime * 20));
                    }
                }
                catch (Exception ex2) {
                    ex2.printStackTrace();
                }
                if (!(entityPlayer.getHeldItem(EnumHand.MAIN_HAND).getItem() instanceof ItemAir)) {
                    list.add(entityPlayer.getHeldItem(EnumHand.MAIN_HAND));
                }
                for (final ItemStack itemStack2 : entityPlayer.getArmorInventoryList()) {
                    if (!(itemStack2.getItem() instanceof ItemAir)) {
                        list.add(itemStack2);
                    }
                }
                if (!(entityPlayer.getHeldItem(EnumHand.OFF_HAND).getItem() instanceof ItemAir)) {
                    list.add(entityPlayer.getHeldItem(EnumHand.OFF_HAND));
                }
                int n7 = 20;
                final Iterator<ItemStack> iterator4 = list.iterator();
                while (iterator4.hasNext()) {
                    RenderUtils.renderItem(iterator4.next(), posX + n7 + 13, posY + 13);
                    n7 += 16;
                }
                mntsb_20.drawString(entityPlayer.getDisplayName().getUnformattedText(), posX + 33, posY + 2, -1);
                this.healphAnim = MathUtils.lerp(this.healphAnim, entityPlayer.getHealth(), 1.0f);
                GL11.glPopMatrix();
                break;
            }
        }
    }
}
