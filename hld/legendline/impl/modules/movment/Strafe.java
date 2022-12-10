package ru.hld.legendline.impl.modules.movment;

import ru.hld.legendline.api.setting.settings.*;
import ru.hld.legendline.api.module.*;
import ru.hld.legendline.*;
import ru.hld.legendline.api.setting.*;
import ru.hld.legendline.api.event.events.*;
import ru.hld.legendline.api.utils.*;
import ru.hld.legendline.api.event.*;

public class Strafe extends Module
{
    FloatSetting speed;
    
    public Strafe() {
        super("Strafe", "allow you always run", Category.Movement);
        Client.settingManager.add(this.speed = new FloatSetting("Speed", "Speed val", this, 1.0f, 35.0f, 23.0f, 1.0f));
    }
    
    @EventTarget
    public void onUpdate(final EventUpdate eventUpdate) {
        if (this.mc.player.isSneaking()) {
            return;
        }
        if (this.mc.player.onGround) {
            MoveUtils.setSpeed(this.speed.getVal() / 100.0f - 0.05);
        }
        else {
            MoveUtils.setSpeed(this.speed.getVal() / 100.0f);
        }
    }
}
