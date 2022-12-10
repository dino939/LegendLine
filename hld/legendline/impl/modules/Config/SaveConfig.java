package ru.hld.legendline.impl.modules.Config;

import ru.hld.legendline.api.event.events.*;
import ru.hld.legendline.api.event.*;
import ru.hld.legendline.api.shit.*;
import ru.hld.legendline.api.module.*;

public class SaveConfig extends Module
{
    @EventTarget
    public void onUpdate(final EventUpdate eventUpdate) {
    }
    
    @Override
    public void onEnable() {
        this.toggle();
        cfg.save();
    }
    
    public SaveConfig() {
        super("SaveConfig", "Save you settings", Category.Config);
    }
}
