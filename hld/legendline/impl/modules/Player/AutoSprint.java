package ru.hld.legendline.impl.modules.Player;

import ru.hld.legendline.api.setting.settings.*;
import ru.hld.legendline.api.module.*;
import java.util.*;
import ru.hld.legendline.*;
import ru.hld.legendline.api.setting.*;
import ru.hld.legendline.api.event.events.*;
import net.minecraft.util.text.*;
import net.minecraft.util.*;
import ru.hld.legendline.api.event.*;

public class AutoSprint extends Module
{
    ModeSetting mode;
    
    public AutoSprint() {
        super("AutoSprint", "autoPress CTRL", Category.Player);
        final ArrayList<String> list = new ArrayList<String>();
        list.add("Legit");
        list.add("setSprinting()");
        Client.settingManager.add(this.mode = new ModeSetting("Mode", "modes of Sprint", this, list, "Legit"));
    }
    
    @EventTarget
    public void onUpdate(final EventUpdate eventUpdate) {
        this.setDisplayname(String.valueOf(new StringBuilder().append(TextFormatting.WHITE).append(this.mode.getVal()).append(" ").append(TextFormatting.RESET).append(this.getName()).append(" ")));
        final String val = this.mode.getVal();
        switch (val) {
            case "Legit": {
                this.mc.gameSettings.keyBindSprint.pressed = true;
                break;
            }
            case "setSprinting()": {
                final MovementInput movementInput = this.mc.player.movementInput;
                if (MovementInput.field_192832_b > 0.0f) {
                    this.mc.player.setSprinting(true);
                    break;
                }
                break;
            }
        }
    }
}
