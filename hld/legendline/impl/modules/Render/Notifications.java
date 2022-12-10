package ru.hld.legendline.impl.modules.Render;

import ru.hld.legendline.api.setting.settings.*;
import ru.hld.legendline.api.module.*;
import ru.hld.legendline.*;
import ru.hld.legendline.api.setting.*;
import ru.hld.legendline.api.event.events.*;
import ru.hld.legendline.api.event.*;

public class Notifications extends Module
{
    public static BooleanSetting colored;
    
    public Notifications() {
        super("Notifications", "show info about toggeling modules", Category.Render);
        Client.settingManager.add(Notifications.colored = new BooleanSetting("Colored", "mega design in notifications", this, false));
    }
    
    @EventTarget
    public void onRender2D(final EventRender2D eventRender2D) {
        Client.notificationsManager.render();
    }
}
