package ru.hld.legendline.impl.modules.Combat;

import ru.hld.legendline.api.event.events.*;
import net.minecraft.network.play.server.*;
import ru.hld.legendline.api.event.*;
import ru.hld.legendline.api.module.*;

public class Velocity extends Module
{
    @EventTarget
    public void onUpdate(final EventPacketReceive eventPacketReceive) {
        if ((eventPacketReceive.getPacket() instanceof SPacketEntityVelocity || eventPacketReceive.getPacket() instanceof SPacketExplosion) && ((SPacketEntityVelocity)eventPacketReceive.getPacket()).getEntityID() == this.mc.player.getEntityId()) {
            eventPacketReceive.setCancelled(true);
        }
    }
    
    public Velocity() {
        super("Velocity", "zalupa", Category.Combat);
    }
}
