package ru.hld.legendline.impl.modules.movment;

import ru.hld.legendline.api.event.events.*;
import net.minecraft.potion.*;
import ru.hld.legendline.api.utils.*;
import ru.hld.legendline.api.event.*;
import ru.hld.legendline.api.module.*;

public class NoSlowDown extends Module
{
    @EventTarget
    public void onUpdate(final EventUpdate eventUpdate) {
        if (this.mc.player.isPotionActive(Potion.getPotionById(2))) {
            if (this.mc.player.onGround) {
                MoveUtils.setSpeed(0.2199999988079071);
            }
            else {
                MoveUtils.setSpeed(0.25);
            }
        }
    }
    
    public NoSlowDown() {
        super("NoSlowDown", "you can aiming and go fast", Category.Movement);
    }
}
