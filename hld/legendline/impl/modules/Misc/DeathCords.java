package ru.hld.legendline.impl.modules.Misc;

import ru.hld.legendline.api.module.*;
import ru.hld.legendline.api.event.events.*;
import net.minecraft.util.text.*;
import ru.hld.legendline.api.event.*;

public class DeathCords extends Module
{
    public int s;
    public int y;
    public int x;
    public int z;
    
    @Override
    public void onEnable() {
        super.onEnable();
        this.s = 0;
    }
    
    public DeathCords() {
        super("DeathCords", "show your death cords", Category.Misc);
    }
    
    @EventTarget
    public void onUpdate(final EventUpdate eventUpdate) {
        if (this.mc.player.isPlayerSleeping()) {
            if (this.s < 2) {
                this.x = (int)this.mc.player.lastTickPosX;
                this.y = (int)this.mc.player.lastTickPosY;
                this.z = (int)this.mc.player.lastTickPosZ;
                this.mc.player.addChatMessage(new TextComponentString(String.valueOf(new StringBuilder().append("knocked cords: x: ").append(this.x).append("  y: ").append(this.y).append("  z: ").append(this.z))));
                ++this.s;
            }
        }
        else {
            this.s = 0;
        }
    }
}
