package ru.hld.legendline.api.utils;

import net.minecraft.network.*;

public class SendPacket
{
    Packet packet;
    boolean cancelled;
    
    public void setCancelled(final boolean cancelled) {
        this.cancelled = cancelled;
    }
    
    public boolean isCancelled() {
        return this.cancelled;
    }
    
    public Packet getPacket() {
        return this.packet;
    }
    
    public void setPacket(final Packet packet) {
        this.packet = packet;
    }
    
    public SendPacket(final Packet packet) {
        this.cancelled = false;
        this.packet = packet;
    }
}
