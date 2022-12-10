package ru.hld.legendline.impl.modules.Render;

import ru.hld.legendline.api.setting.settings.*;
import ru.hld.legendline.api.module.*;
import ru.hld.legendline.*;
import ru.hld.legendline.api.setting.*;
import ru.hld.legendline.api.event.events.*;
import net.minecraft.entity.*;
import net.minecraft.entity.player.*;
import net.minecraft.util.text.*;
import org.lwjgl.opengl.*;
import net.minecraft.client.entity.*;
import ru.hld.legendline.antiNative.*;
import net.minecraft.item.*;
import net.minecraft.util.*;
import java.util.*;
import ru.hld.legendline.api.utils.*;
import net.minecraft.client.renderer.*;
import ru.hld.legendline.api.event.*;
import net.minecraft.enchantment.*;

public class NameTags extends Module
{
    public static ModeSetting mode;
    public static BooleanSetting shadow;
    
    public int getcenter(final String s) {
        return this.mc.fontRendererObj.getStringWidth(s) / 2;
    }
    
    public int getcenter(final int n) {
        return this.mc.fontRendererObj.getStringWidth(String.valueOf(n)) / 2;
    }
    
    public NameTags() {
        super("NameTags", "show player info", Category.Render);
        final ArrayList<String> list = new ArrayList<String>();
        list.add("Ricardo");
        Client.settingManager.add(NameTags.mode = new ModeSetting("Mode", "modes of NameTags", this, list, "Ricardo"));
        Client.settingManager.add(NameTags.shadow = new BooleanSetting("Shadow", "", this, true));
    }
    
    @EventTarget
    public void onRender3D(final EventRender3D eventRender3D) {
        for (final Entity entity : this.mc.world.loadedEntityList) {
            final CFontRenderer mntsb_20 = Fonts.mntsb_20;
            if (entity != null && !entity.isDead && entity instanceof EntityPlayer && entity != this.mc.player) {
                String s = String.valueOf(TextFormatting.GREEN);
                final double n = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * eventRender3D.getPartialTicks() - this.mc.getRenderManager().viewerPosX;
                final double n2 = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * eventRender3D.getPartialTicks() - this.mc.getRenderManager().viewerPosY;
                final double n3 = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * eventRender3D.getPartialTicks() - this.mc.getRenderManager().viewerPosZ;
                GL11.glPushMatrix();
                GL11.glDisable(2929);
                GL11.glEnable(3042);
                GL11.glBlendFunc(770, 771);
                GL11.glNormal3f(0.0f, 1.0f, 0.0f);
                final float n4 = Math.min(Math.max(1.2f * (this.mc.player.getDistanceToEntity(entity) * 0.15f), 1.25f), 6.0f) * 0.015f;
                GL11.glTranslatef((float)n, (float)n2 + entity.height + 0.6f, (float)n3);
                GlStateManager.glNormal3f(0.0f, 1.0f, 0.0f);
                GlStateManager.rotate(-this.mc.getRenderManager().playerViewY, 0.0f, 1.0f, 0.0f);
                GlStateManager.rotate(this.mc.getRenderManager().playerViewX, 1.0f, 0.0f, 0.0f);
                GL11.glScalef(-n4, -n4, -n4);
                final int n5 = (int)(((EntityPlayerSP)entity).getHealth() / ((EntityPlayerSP)entity).getMaxHealth() * 100.0f);
                if (((EntityPlayerSP)entity).getHealth() <= ((EntityPlayerSP)entity).getMaxHealth() || ((EntityPlayerSP)entity).getHealth() >= ((EntityPlayerSP)entity).getMaxHealth()) {
                    s = String.valueOf(TextFormatting.GREEN);
                }
                if (((EntityPlayerSP)entity).getHealth() <= ((EntityPlayerSP)entity).getMaxHealth() * 65.0f / 100.0f) {
                    s = String.valueOf(TextFormatting.YELLOW);
                }
                if (((EntityPlayerSP)entity).getHealth() <= ((EntityPlayerSP)entity).getMaxHealth() * 40.0f / 100.0f) {
                    s = String.valueOf(TextFormatting.RED);
                }
                final String value = String.valueOf(new StringBuilder().append(entity.getDisplayName().getUnformattedText()).append(s).append(" & ").append((int)((EntityPlayerSP)entity).getHealth()));
                final float n6 = (float)(mntsb_20.getStringWidth(value) + 10);
                final int n7 = (int)(n6 - 4.0f);
                if (NameTags.shadow.getVal()) {
                    RenderUtils.drawShadowRect(-(n6 / 2.0f), 0.0, n6 / 2.0f, 15.0, 3, Client.getColor2());
                    RenderUtils.drawShadowRect(-(n6 / 2.0f) + 2.0f, 11.0, -(n6 / 2.0f) + 2.0f + n7 / ((EntityPlayerSP)entity).getMaxHealth() * ((EntityPlayerSP)entity).getHealth(), 13.0, 2, Client.getColor());
                }
                else {
                    RenderUtils.drawSuper(-(n6 / 2.0f), 0.0, n6 / 2.0f, 15.0, 2, Client.getColor());
                    RenderUtils.drawSuper(-(n6 / 2.0f) + 2.0f, 11.0, -(n6 / 2.0f) + 2.0f + n7 / ((EntityPlayerSP)entity).getMaxHealth() * ((EntityPlayerSP)entity).getHealth(), 13.0, 1, Client.getColor());
                }
                mntsb_20.drawCenteredString(value, 0.0, 5 - mntsb_20.getHeight() / 2, -1);
                final ArrayList<ItemStack> list = new ArrayList<ItemStack>();
                if (!(((EntityPlayerSP)entity).getHeldItem(EnumHand.MAIN_HAND).getItem() instanceof ItemAir)) {
                    list.add(((EntityPlayerSP)entity).getHeldItem(EnumHand.MAIN_HAND));
                }
                for (final ItemStack itemStack : entity.getArmorInventoryList()) {
                    if (!(itemStack.getItem() instanceof ItemAir)) {
                        list.add(itemStack);
                    }
                }
                if (!(((EntityPlayerSP)entity).getHeldItem(EnumHand.OFF_HAND).getItem() instanceof ItemAir)) {
                    list.add(((EntityPlayerSP)entity).getHeldItem(EnumHand.OFF_HAND));
                }
                int n8 = -(list.size() * 16 / 2);
                for (final ItemStack itemStack2 : list) {
                    final RenderItem renderItem = this.mc.getRenderItem();
                    GlStateManager.pushMatrix();
                    GlStateManager.enableBlend();
                    RenderHelper.enableStandardItemLighting();
                    renderItem.zLevel = -100.0f;
                    this.renderEnchant(itemStack2, (float)n8, -35);
                    renderItem.renderItemIntoGUI(itemStack2, n8, -20);
                    renderItem.zLevel = 0.0f;
                    RenderHelper.disableStandardItemLighting();
                    GlStateManager.enableAlpha();
                    GlStateManager.disableBlend();
                    GlStateManager.disableLighting();
                    GlStateManager.popMatrix();
                    GlStateManager.pushMatrix();
                    GlStateManager.disableLighting();
                    GlStateManager.disableDepth();
                    GlStateManager.popMatrix();
                    n8 += 16;
                }
                GL11.glEnable(2929);
                GL11.glColor3f(255.0f, 255.0f, 255.0f);
                GL11.glEnable(2929);
                GL11.glPopMatrix();
            }
        }
    }
    
    public void renderEnchant(final ItemStack itemStack, final float n, final int n2) {
        final CFontRenderer mntsb_20 = Fonts.mntsb_20;
        int n3 = n2 + 5;
        for (final Enchantment enchantment : EnchantmentHelper.getEnchantments(itemStack).keySet()) {
            mntsb_20.drawStringWithShadow(String.valueOf(new StringBuilder().append(String.valueOf(enchantment.getName().substring(12).charAt(0)).toUpperCase()).append(EnchantmentHelper.getEnchantmentLevel(enchantment, itemStack))), n, n3, 16777215);
            n3 -= 12;
        }
    }
}
