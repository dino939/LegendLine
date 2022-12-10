package ru.hld.legendline.impl.modules.Misc;

import ru.hld.legendline.api.module.*;
import ru.hld.legendline.api.event.events.*;
import ru.hld.legendline.api.event.*;

public class Disabler extends Module
{
    public Disabler() {
        super("Disabler", "", Category.Misc);
    }
    
    @EventTarget
    public void onUpdate(final EventUpdate eventUpdate) {
        if (this.mc.player.isOnLadder()) {
            this.mc.player.motionY = 0.2;
        }
    }
}
