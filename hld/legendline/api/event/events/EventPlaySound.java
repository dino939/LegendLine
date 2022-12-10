package ru.hld.legendline.api.event.events;

import ru.hld.legendline.api.event.*;
import net.minecraft.util.math.*;
import net.minecraft.util.*;
import net.minecraft.entity.player.*;

public class EventPlaySound extends Event
{
    BlockPos pos;
    SoundEvent soundIn;
    SoundCategory category;
    float pitch;
    EntityPlayer player;
    float volume;
    
    public SoundEvent getSoundIn() {
        return this.soundIn;
    }
    
    public SoundCategory getCategory() {
        return this.category;
    }
    
    public EventPlaySound(final EntityPlayer player, final BlockPos pos, final SoundEvent soundIn, final SoundCategory category, final float volume, final float pitch) {
        this.player = player;
        this.pos = pos;
        this.soundIn = soundIn;
        this.category = category;
        this.volume = volume;
        this.pitch = pitch;
    }
    
    public BlockPos getPos() {
        return this.pos;
    }
    
    public float getPitch() {
        return this.pitch;
    }
    
    public float getVolume() {
        return this.volume;
    }
    
    public EntityPlayer getPlayer() {
        return this.player;
    }
}
