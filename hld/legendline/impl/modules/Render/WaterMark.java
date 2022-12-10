package ru.hld.legendline.impl.modules.Render;

import ru.hld.legendline.api.setting.settings.*;
import ru.hld.legendline.api.module.*;
import ru.hld.legendline.*;
import ru.hld.legendline.api.setting.*;
import ru.hld.legendline.api.event.events.*;
import net.minecraft.client.*;
import net.minecraft.client.network.*;
import java.util.*;
import ru.hld.legendline.antiNative.*;
import ru.hld.legendline.api.utils.*;
import ru.hld.legendline.api.event.*;

public class WaterMark extends Module
{
    int col;
    float animXMot;
    TimerUtils timer;
    BooleanSetting shadow;
    int pos;
    ModeSetting mode;
    float animYMot;
    float animX;
    float animY;
    ModeSetting modec;
    
    public WaterMark() {
        super("WaterMark", "show name of client on screen", Category.Render);
        this.animX = 0.0f;
        this.animY = 1.0f;
        this.animXMot = 1.4f;
        this.animYMot = 1.4f;
        this.pos = 0;
        this.col = 0;
        this.timer = new TimerUtils();
        final ArrayList<String> list = new ArrayList<String>();
        final ArrayList<String> list2 = new ArrayList<String>();
        this.setPosX(5);
        this.setPosX(5);
        list.add("LegendLine");
        list.add("Onetap");
        list2.add("Client");
        list2.add("Gui");
        list2.add("Astolfo");
        Client.settingManager.add(this.mode = new ModeSetting("Mode", "modes of Watermark", this, list, "LegendLine"));
        Client.settingManager.add(this.shadow = new BooleanSetting("Shadow", "show shadow", this, false));
    }
    
    @EventTarget
    public void onRender2D(final EventRender2D eventRender2D) {
        final int posX = this.getPosX();
        final int posY = this.getPosY();
        final float clamp = MathUtils.clamp((float)Math.sqrt(this.mc.player.motionX * this.mc.player.motionX + this.mc.player.motionY * this.mc.player.motionY + this.mc.player.motionZ * this.mc.player.motionZ), 0.0f, 0.3f);
        this.animX = MathUtils.lerp(this.animX, this.animXMot, clamp);
        this.animY = MathUtils.lerp(this.animY, this.animYMot, clamp);
        if (this.animX > 1.3f) {
            this.animXMot = 0.7f;
        }
        else if (this.animX < 0.8f) {
            this.animXMot = 1.4f;
        }
        if (this.animY > 1.3f) {
            this.animYMot = 0.7f;
        }
        else if (this.animY < 0.8f) {
            this.animYMot = 1.4f;
        }
        final CFontRenderer mntsb_16 = Fonts.mntsb_16;
        String s = String.valueOf(new StringBuilder().append(Calendar.getInstance().getTime().getHours()).append(":").append(Calendar.getInstance().getTime().getMinutes()).append(":").append(Calendar.getInstance().getTime().getSeconds()));
        String s2 = "";
        String s3 = "";
        String s4 = "";
        this.pos = Client.getColor2();
        this.col = Client.getColor();
        final String val = this.mode.getVal();
        switch (val) {
            case "Onetap": {
                String s5;
                if (Calendar.getInstance().getTime().getHours() <= 15 && Calendar.getInstance().getTime().getHours() >= 0) {
                    s5 = "";
                }
                else {
                    s5 = "";
                }
                if (Calendar.getInstance().getTime().getHours() <= 9) {
                    s2 = "0";
                }
                if (Calendar.getInstance().getTime().getMinutes() <= 9) {
                    s3 = "0";
                }
                if (Calendar.getInstance().getTime().getSeconds() <= 9) {
                    s4 = "0";
                }
                if (Calendar.getInstance().getTime().getHours() <= 9 || Calendar.getInstance().getTime().getMinutes() <= 9 || Calendar.getInstance().getTime().getSeconds() <= 9) {
                    s = String.valueOf(new StringBuilder().append(s2).append(Calendar.getInstance().getTime().getHours()).append(":").append(s3).append(Calendar.getInstance().getTime().getMinutes()).append(":").append(s4).append(Calendar.getInstance().getTime().getSeconds()).append(" ").append(s5));
                }
                final String value = String.valueOf(new StringBuilder().append("LEGENDLINE | v2.0 | ").append(Minecraft.getDebugFPS()).append("fps | ").append(s));
                final int stringWidth = mntsb_16.getStringWidth(value);
                int i = 0;
                while (i < stringWidth + 8) {
                    RenderUtils.drawRect((float)(posX + 4 + i), posY + 5.5f, posX + 5.0f + i, (float)(posY + 7), ColorUtils.astolfoColorsCool(1, 80 + i / 2));
                    final int n2 = 4;
                    final int n3 = 3;
                    ++i;
                    if (n2 < n3) {
                        return;
                    }
                }
                RenderUtils.drawRect((float)(posX + 4), (float)(posY + 7), (float)(posX + 12 + stringWidth), (float)(posY + 21), ColorUtils.getColor(0, 0, 0, 190));
                mntsb_16.drawStringWithShadow(value, posX + 8, posY + 12, Client.textcolor());
                break;
            }
            case "LegendLine": {
                String s6;
                if (Calendar.getInstance().getTime().getHours() <= 15 && Calendar.getInstance().getTime().getHours() >= 0) {
                    s6 = "";
                }
                else {
                    s6 = "";
                }
                if (Calendar.getInstance().getTime().getHours() <= 9) {
                    s2 = "0";
                }
                if (Calendar.getInstance().getTime().getMinutes() <= 9) {
                    s3 = "0";
                }
                if (Calendar.getInstance().getTime().getSeconds() <= 9) {
                    s4 = "0";
                }
                if (Calendar.getInstance().getTime().getHours() <= 9 || Calendar.getInstance().getTime().getMinutes() <= 9 || Calendar.getInstance().getTime().getSeconds() <= 9) {
                    s = String.valueOf(new StringBuilder().append(s2).append(Calendar.getInstance().getTime().getHours()).append(":").append(s3).append(Calendar.getInstance().getTime().getMinutes()).append(":").append(s4).append(Calendar.getInstance().getTime().getSeconds()).append(" ").append(s6));
                }
                final String value2 = String.valueOf(new StringBuilder().append("LEGENDLINE | v2.0 | ").append(Minecraft.getDebugFPS()).append("fps | ").append(s));
                final int stringWidth2 = mntsb_16.getStringWidth(value2);
                if (this.shadow.getVal()) {
                    RenderUtils.drawShadowRect(posX + 4, posY + 7, posX + 22 + stringWidth2, posY + 21, 6, this.col);
                    RenderUtil.drawHead(Objects.requireNonNull(this.mc.getConnection()).getPlayerInfo(this.mc.player.getUniqueID()).getLocationSkin(), posX + 3, posY + 7, 14, 14);
                }
                else {
                    RenderUtils.drawGradientRect(posX + 2, posY + 6, posX + 22 + stringWidth2, posY + 22, true, this.col, this.pos);
                    RenderUtil.drawHead(Objects.requireNonNull(this.mc.getConnection()).getPlayerInfo(this.mc.player.getUniqueID()).getLocationSkin(), posX + 3, posY + 7, 14, 14);
                }
                mntsb_16.drawStringWithShadow(value2, posX + 19, posY + 12, Client.textcolor());
                break;
            }
        }
    }
}
