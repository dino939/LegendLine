package ru.hld.legendline.impl.modules.Combat;

import ru.hld.legendline.api.setting.settings.*;
import ru.hld.legendline.api.event.events.*;
import ru.hld.legendline.api.event.*;
import ru.hld.legendline.api.module.*;
import ru.hld.legendline.*;
import ru.hld.legendline.api.setting.*;
import java.util.*;

public class TestModule extends Module
{
    BooleanSetting check;
    FloatSetting slider;
    ModeSetting mode;
    
    @EventTarget
    public void onRender2D(final EventRender2D eventRender2D) {
    }
    
    public TestModule() {
        super("TestModule", "test module for clickgui", Category.Combat);
        Client.settingManager.add(this.check = new BooleanSetting("Check", "test check box", this, true));
        Client.settingManager.add(this.slider = new FloatSetting("Slider", "test slider", this, 0.0f, 16.0f, 8.0f, 0.1f));
        final ArrayList<String> list = new ArrayList<String>();
        list.add("Test1");
        list.add("Test2");
        list.add("bavarca");
        Client.settingManager.add(this.mode = new ModeSetting("Mode", "test Mode setting", this, list, "Test1"));
        this.toggle();
        this.setKey(37);
    }
}
