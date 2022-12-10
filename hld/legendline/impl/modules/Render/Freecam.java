package ru.hld.legendline.impl.modules.Render;

import net.minecraft.client.entity.*;
import ru.hld.legendline.api.setting.settings.*;
import net.minecraft.world.*;
import ru.hld.legendline.api.module.*;
import ru.hld.legendline.*;
import ru.hld.legendline.api.setting.*;

public class Freecam extends Module
{
    private EntityOtherPlayerMP entity;
    private double oldZ;
    private double oldX;
    BooleanSetting autoDamageDisable;
    FloatSetting speed;
    private double oldY;
    private float old;
    BooleanSetting AntiAction;
    private GameType prevGameType;
    
    @Override
    public void onEnable() {
    }
    
    @Override
    public void onDisable() {
    }
    
    public Freecam() {
        super("FreeCam", "allow your fly like in gamemode 3", Category.Render);
        Client.settingManager.add(this.AntiAction = new BooleanSetting("AntiAction", "", this, true));
        Client.settingManager.add(this.autoDamageDisable = new BooleanSetting("autoDamageDisable", "auto disable on damage", this, true));
        Client.settingManager.add(this.speed = new FloatSetting("Speed", "speed of free cam", this, 0.1f, 1.0f, 0.5f, 0.1f));
    }
}
