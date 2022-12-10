package ru.hld.legendline.impl.modules.movment;

import ru.hld.legendline.api.setting.settings.*;
import ru.hld.legendline.api.event.events.*;
import net.minecraft.util.text.*;
import net.minecraft.util.math.*;
import net.minecraft.init.*;
import ru.hld.legendline.api.utils.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import ru.hld.legendline.api.event.*;
import ru.hld.legendline.api.module.*;
import java.util.*;
import ru.hld.legendline.*;
import ru.hld.legendline.api.setting.*;

public class Jesus extends Module
{
    public int o;
    ModeSetting mode;
    FloatSetting jumps;
    FloatSetting speed;
    
    @EventTarget
    public void onUpdate(final EventUpdate eventUpdate) {
        this.setDisplayname(String.valueOf(new StringBuilder().append(TextFormatting.WHITE).append(this.mode.getVal()).append(" ").append(TextFormatting.RESET).append(this.getName()).append(" ")));
        final String val = this.mode.getVal();
        switch (val) {
            case "MiniJump": {
                if (this.mc.player.isSneaking()) {
                    return;
                }
                if (this.mc.world.getBlockState(new BlockPos(this.mc.player.posX, this.mc.player.posY + 0.03, this.mc.player.posZ)).getBlock() == Blocks.WATER) {
                    this.mc.player.motionY = 0.15000000596046448;
                    MoveUtils.setSpeed(this.speed.getVal() / 100.0f);
                    break;
                }
            }
            case "LongJump": {
                if (this.mc.player.isInWater()) {
                    if (this.mc.player.isSneaking()) {
                        return;
                    }
                    if (this.o < this.jumps.getVal() * 3.0f) {
                        this.mc.getConnection().sendPacket(new CPacketPlayer(true));
                        this.mc.player.motionY = 0.15000000596046448;
                        this.mc.player.jumpMovementFactor = 0.5f;
                        ++this.o;
                        break;
                    }
                    break;
                }
                else {
                    if (this.mc.world.getBlockState(new BlockPos(this.mc.player.posX, this.mc.player.posY + 0.03, this.mc.player.posZ)).getBlock() == Blocks.WATER && this.o > this.jumps.getVal() * 3.0f - 1.0f) {
                        this.mc.player.motionY = 0.15000000596046448;
                        MoveUtils.setSpeed(this.speed.getVal() / 100.0f);
                        break;
                    }
                    break;
                }
                break;
            }
        }
    }
    
    @Override
    public void onEnable() {
        super.onEnable();
        this.o = 0;
    }
    
    public Jesus() {
        super("Jesus", "you can run in water", Category.Movement);
        final ArrayList<String> list = new ArrayList<String>();
        list.add("MiniJump");
        list.add("LongJump");
        Client.settingManager.add(this.mode = new ModeSetting("Mode", "modes of Jesus", this, list, "MiniJump"));
        Client.settingManager.add(this.jumps = new FloatSetting("Jumps", "count of jumps", this, 1.0f, 10.0f, 3.0f, 1.0f));
        Client.settingManager.add(this.speed = new FloatSetting("Speed", "Speed of mini jumps", this, 1.0f, 25.0f, 13.0f, 1.0f));
    }
}
