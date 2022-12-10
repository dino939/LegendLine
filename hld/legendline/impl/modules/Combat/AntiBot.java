package ru.hld.legendline.impl.modules.Combat;

import ru.hld.legendline.api.event.events.*;
import net.minecraft.entity.*;
import net.minecraft.entity.player.*;
import java.util.*;
import ru.hld.legendline.api.event.*;
import ru.hld.legendline.api.module.*;

public class AntiBot extends Module
{
    @EventTarget
    public void onUpdate(final EventUpdate eventUpdate) {
        for (final Entity entity : this.mc.world.loadedEntityList) {
            if (entity instanceof EntityPlayer && entity.isInvisible() && entity != this.mc.player) {
                this.mc.world.removeEntity(entity);
            }
        }
    }
    
    public AntiBot() {
        super("AntiBot", "remove bots form anticheat", Category.Combat);
    }
}
