package ru.hld.legendline.impl.modules.Render;

import ru.hld.legendline.api.module.*;

public class FullBright extends Module
{
    float old;
    
    public FullBright() {
        super("FullBright", "enable max light", Category.Render);
    }
    
    @Override
    public void onDisable() {
        super.onDisable();
        this.mc.gameSettings.gammaSetting = this.old;
    }
    
    @Override
    public void onEnable() {
        super.onEnable();
        this.old = this.mc.gameSettings.gammaSetting;
        this.mc.gameSettings.gammaSetting = 1000.0f;
    }
}
