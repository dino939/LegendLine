package ru.hld.legendline.impl.modules.Render;

import ru.hld.legendline.api.setting.settings.*;
import ru.hld.legendline.*;
import net.minecraft.client.gui.*;
import ru.hld.legendline.api.module.*;
import ru.hld.legendline.api.setting.*;

public class ClickGui extends Module
{
    public static FloatSetting textgreen2;
    public static FloatSetting GreenFirst;
    public static FloatSetting RedGui;
    public static FloatSetting textblue2;
    public static FloatSetting textgreen;
    public static FloatSetting RedSecond;
    public static FloatSetting textred2;
    public static BooleanSetting shadows;
    public static FloatSetting GreenGui;
    public static FloatSetting RedFirst;
    public static FloatSetting textred;
    public static FloatSetting BlueGui;
    public static FloatSetting BlueSecond;
    public static FloatSetting GreenSecond;
    public static FloatSetting textblue;
    public static FloatSetting BlueFirst;
    public static FloatSetting AlphA;
    
    @Override
    public void onEnable() {
        super.onEnable();
        this.mc.displayGuiScreen(Client.gui);
        this.toggle();
    }
    
    public ClickGui() {
        super("ClickGui", "Main menu", Category.Render);
        this.setKey(54);
        Client.settingManager.add(ClickGui.shadows = new BooleanSetting("Shadow", "", this, true));
        Client.settingManager.add(ClickGui.RedFirst = new FloatSetting("RedFirst", "red color", this, 0.0f, 255.0f, 255.0f, 1.0f));
        Client.settingManager.add(ClickGui.GreenFirst = new FloatSetting("GreenFirst", "green color", this, 0.0f, 255.0f, 255.0f, 1.0f));
        Client.settingManager.add(ClickGui.BlueFirst = new FloatSetting("BlueFirst", "blue color", this, 0.0f, 255.0f, 255.0f, 1.0f));
        Client.settingManager.add(ClickGui.RedSecond = new FloatSetting("RedSecond", "red color", this, 0.0f, 255.0f, 255.0f, 1.0f));
        Client.settingManager.add(ClickGui.GreenSecond = new FloatSetting("GreenSecond", "green color", this, 0.0f, 255.0f, 255.0f, 1.0f));
        Client.settingManager.add(ClickGui.BlueSecond = new FloatSetting("BlueSecond", "blue color", this, 0.0f, 255.0f, 255.0f, 1.0f));
        Client.settingManager.add(ClickGui.RedGui = new FloatSetting("RedGui", "red color", this, 0.0f, 255.0f, 255.0f, 1.0f));
        Client.settingManager.add(ClickGui.GreenGui = new FloatSetting("GreenGui", "green color", this, 0.0f, 255.0f, 255.0f, 1.0f));
        Client.settingManager.add(ClickGui.BlueGui = new FloatSetting("BlueGui", "blue color", this, 0.0f, 255.0f, 255.0f, 1.0f));
        Client.settingManager.add(ClickGui.AlphA = new FloatSetting("Alpha", "Alpha color", this, 0.0f, 255.0f, 255.0f, 1.0f));
        Client.settingManager.add(ClickGui.textred = new FloatSetting("TextRedFirst", "red color", this, 0.0f, 255.0f, 255.0f, 1.0f));
        Client.settingManager.add(ClickGui.textgreen = new FloatSetting("TextGreenFirst", "red color", this, 0.0f, 255.0f, 255.0f, 1.0f));
        Client.settingManager.add(ClickGui.textblue = new FloatSetting("TextBlueFirst", "blue color", this, 0.0f, 255.0f, 255.0f, 1.0f));
        Client.settingManager.add(ClickGui.textred2 = new FloatSetting("TextRedSecond", "red color", this, 0.0f, 255.0f, 255.0f, 1.0f));
        Client.settingManager.add(ClickGui.textgreen2 = new FloatSetting("TextGreenSecond", "green color", this, 0.0f, 255.0f, 255.0f, 1.0f));
        Client.settingManager.add(ClickGui.textblue2 = new FloatSetting("TextBlueSecond", "blue color", this, 0.0f, 255.0f, 255.0f, 1.0f));
    }
}
