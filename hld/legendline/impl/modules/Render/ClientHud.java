package ru.hld.legendline.impl.modules.Render;

import ru.hld.legendline.api.setting.settings.*;
import ru.hld.legendline.api.event.events.*;
import ru.hld.legendline.*;
import ru.hld.legendline.antiNative.*;
import ru.hld.legendline.api.utils.*;
import ru.hld.legendline.api.event.*;
import ru.hld.legendline.api.module.*;
import ru.hld.legendline.api.setting.*;

public class ClientHud extends Module
{
    BooleanSetting shadow;
    public static BooleanSetting cords;
    
    @EventTarget
    public void onRender2D(final EventRender2D eventRender2D) {
        final CFontRenderer mntsb_18 = Fonts.mntsb_18;
        final String value = String.valueOf(new StringBuilder().append("x: ").append((int)this.mc.player.posX).append(" y: ").append((int)this.mc.player.posY).append(" z: ").append((int)this.mc.player.posZ));
        final int n = 0;
        final int n2 = Client.moduleManager.getModule("WaterMark").isEnabled() ? (n + 12) : (n + 0);
        if (ClientHud.cords.getVal()) {
            if (!this.shadow.getVal()) {
                RenderUtils.drawGradientRect(this.getPosX() + 7, 10 + n2, this.getPosX() + mntsb_18.getStringWidth(value) + 12, 22 + n2, true, Client.getColor(), Client.getColor2());
            }
            else {
                RenderUtils.drawShadow(this.getPosX() + 11, 16 + n2, this.getPosX() + mntsb_18.getStringWidth(value) + 11, 16 + n2, 9, Client.getColor());
            }
            mntsb_18.drawStringWithShadow(value, 10.0, 13 + n2, Client.textcolor());
        }
    }
    
    public ClientHud() {
        super("Hud", "show player info on screen", Category.Render);
        Client.settingManager.add(ClientHud.cords = new BooleanSetting("Cords", "show your cords", this, true));
        Client.settingManager.add(this.shadow = new BooleanSetting("Shadow", "shadow", this, false));
    }
}
