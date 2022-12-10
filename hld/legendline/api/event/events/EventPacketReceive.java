package ru.hld.legendline.api.event.events;

import ru.hld.legendline.api.event.*;
import net.minecraft.network.*;

public class EventPacketReceive extends Event
{
    Packet packet;
    
    public Packet getPacket() {
        return this.packet;
    }
    
    public EventPacketReceive(final Packet packet) {
        this.packet = packet;
    }
    
    public void setPacket(final Packet packet) {
        this.packet = packet;
    }
}
