package ru.hld.legendline.impl.modules.movment;

import ru.hld.legendline.api.setting.settings.*;
import ru.hld.legendline.api.event.events.*;
import net.minecraft.client.gui.*;
import org.lwjgl.input.*;
import ru.hld.legendline.api.event.*;
import ru.hld.legendline.api.module.*;
import ru.hld.legendline.*;
import ru.hld.legendline.api.setting.*;

public class GuiWalk extends Module
{
    BooleanSetting sneak;
    
    @EventTarget
    public void onUpdate(final EventUpdate eventUpdate) {
        if (this.mc.currentScreen != null && !(this.mc.currentScreen instanceof GuiChat)) {
            this.mc.gameSettings.keyBindJump.pressed = Keyboard.isKeyDown(this.mc.gameSettings.keyBindJump.getKeyCode());
            this.mc.gameSettings.keyBindForward.pressed = Keyboard.isKeyDown(this.mc.gameSettings.keyBindForward.getKeyCode());
            this.mc.gameSettings.keyBindBack.pressed = Keyboard.isKeyDown(this.mc.gameSettings.keyBindBack.getKeyCode());
            this.mc.gameSettings.keyBindLeft.pressed = Keyboard.isKeyDown(this.mc.gameSettings.keyBindLeft.getKeyCode());
            this.mc.gameSettings.keyBindRight.pressed = Keyboard.isKeyDown(this.mc.gameSettings.keyBindRight.getKeyCode());
            if (this.sneak.getVal()) {
                this.mc.gameSettings.keyBindSneak.pressed = Keyboard.isKeyDown(this.mc.gameSettings.keyBindSneak.getKeyCode());
            }
            this.mc.gameSettings.keyBindSprint.pressed = Keyboard.isKeyDown(this.mc.gameSettings.keyBindSprint.getKeyCode());
        }
    }
    
    public GuiWalk() {
        super("GuiWalk", "you can walk in clickgui", Category.Movement);
        Client.settingManager.add(this.sneak = new BooleanSetting("Sneak", "you can sneak in gui", this, false));
    }
    
    @Override
    public void onDisable() {
        super.onDisable();
    }
}
