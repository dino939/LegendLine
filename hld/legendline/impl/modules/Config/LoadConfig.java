package ru.hld.legendline.impl.modules.Config;

import ru.hld.legendline.api.event.events.*;
import ru.hld.legendline.api.event.*;
import ru.hld.legendline.api.shit.*;
import ru.hld.legendline.api.module.*;

public class LoadConfig extends Module
{
    @EventTarget
    public void onUpdate(final EventUpdate eventUpdate) {
    }
    
    @Override
    public void onEnable() {
        this.toggle();
        cfg.load();
    }
    
    public LoadConfig() {
        super("LoadConfig", "LoadConfig", Category.Config);
    }
}
