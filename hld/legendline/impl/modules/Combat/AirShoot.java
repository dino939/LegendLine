package ru.hld.legendline.impl.modules.Combat;

import ru.hld.legendline.api.setting.settings.*;
import ru.hld.legendline.api.module.*;
import java.util.*;
import ru.hld.legendline.*;
import ru.hld.legendline.api.setting.*;
import ru.hld.legendline.api.event.events.*;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.init.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import ru.hld.legendline.api.event.*;

public class AirShoot extends Module
{
    int kol;
    FloatSetting slot;
    ModeSetting mode;
    FloatSetting slot2;
    int sosu;
    int s;
    
    public AirShoot() {
        super("AirShoot", "enable you shoot in aim", Category.Combat);
        final ArrayList<String> list = new ArrayList<String>();
        list.add("Swap");
        list.add("Packet");
        Client.settingManager.add(this.mode = new ModeSetting("Mode", "Mode of AirShoot", this, list, "Swap"));
    }
    
    @EventTarget
    public void onUpdate(final EventUpdate eventUpdate) {
        final String val = this.mode.getVal();
        switch (val) {
            case "Packet": {
                for (int i = 0; i < 45; ++i) {
                    if (Item.getIdFromItem(this.mc.player.inventoryContainer.getSlot(i).getStack().getItem()) == 278) {
                        this.sosu = i;
                    }
                }
                for (int j = 0; j < 45; ++j) {
                    if (Item.getIdFromItem(this.mc.player.inventoryContainer.getSlot(j).getStack().getItem()) != 278) {
                        this.s = j;
                    }
                }
                if (this.mc.gameSettings.keyBindAttack.isKeyDown() && this.mc.player.swingProgress >= 0.1f) {
                    this.mc.player.swingArm(EnumHand.MAIN_HAND);
                    break;
                }
                if (this.mc.player.getCooldownTracker().getCooldown(Items.DIAMOND_PICKAXE, this.mc.getRenderPartialTicks()) != 1.0f && this.sosu > 35 && this.sosu < 47) {
                    this.mc.getConnection().sendPacket(new CPacketHeldItemChange(this.sosu - 36));
                }
                if (this.mc.player.onGround && !this.mc.gameSettings.keyBindAttack.isKeyDown() && this.sosu > 35 && this.sosu < 47) {
                    this.mc.getConnection().sendPacket(new CPacketHeldItemChange(this.s - 36));
                    break;
                }
                break;
            }
            case "Swap": {
                int k = 0;
                while (k < 45) {
                    if (Item.getIdFromItem(this.mc.player.inventoryContainer.getSlot(k).getStack().getItem()) == 278) {
                        this.sosu = k;
                    }
                    final int n2 = -1;
                    final int n3 = 2;
                    ++k;
                    if (n2 >= n3) {
                        return;
                    }
                }
                int l = 0;
                while (l < 45) {
                    if (Item.getIdFromItem(this.mc.player.inventoryContainer.getSlot(l).getStack().getItem()) != 278) {
                        this.s = l;
                    }
                    final int n4 = 4;
                    final int n5 = -1;
                    ++l;
                    if (n4 == n5) {
                        return;
                    }
                }
                if (this.mc.gameSettings.keyBindAttack.isKeyDown() && this.mc.player.swingProgress >= 0.1f) {
                    this.mc.player.swingArm(EnumHand.MAIN_HAND);
                    break;
                }
                if (this.mc.player.getCooldownTracker().getCooldown(Items.DIAMOND_PICKAXE, this.mc.getRenderPartialTicks()) != 1.0f && this.sosu > 35 && this.sosu < 47) {
                    this.mc.player.inventory.currentItem = this.sosu - 36;
                }
                if (this.mc.player.onGround && !this.mc.gameSettings.keyBindAttack.isKeyDown() && this.sosu > 35 && this.sosu < 47) {
                    this.mc.player.inventory.currentItem = this.s - 36;
                    break;
                }
                break;
            }
        }
    }
}
