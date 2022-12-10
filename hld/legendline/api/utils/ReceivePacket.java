package ru.hld.legendline.api.utils;

import io.netty.channel.*;
import net.minecraft.network.*;

public class ReceivePacket
{
    ChannelHandlerContext p_channelRead01;
    boolean cancelled;
    Packet p_channelRead02;
    
    public ChannelHandlerContext getP_channelRead01() {
        return this.p_channelRead01;
    }
    
    public void setP_channelRead01(final ChannelHandlerContext p_channelRead01) {
        this.p_channelRead01 = p_channelRead01;
    }
    
    public void setP_channelRead02(final Packet p_channelRead02) {
        this.p_channelRead02 = p_channelRead02;
    }
    
    public ReceivePacket(final ChannelHandlerContext p_channelRead01, final Packet p_channelRead2) {
        this.p_channelRead01 = p_channelRead01;
        this.p_channelRead02 = p_channelRead2;
        this.cancelled = false;
    }
    
    public Packet getP_channelRead02() {
        return this.p_channelRead02;
    }
    
    public boolean isCancelled() {
        return this.cancelled;
    }
    
    public void setCancelled(final boolean cancelled) {
        this.cancelled = cancelled;
    }
}
