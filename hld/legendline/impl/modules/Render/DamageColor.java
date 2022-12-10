package ru.hld.legendline.impl.modules.Render;

import ru.hld.legendline.api.module.*;
import ru.hld.legendline.api.event.events.*;
import org.lwjgl.opengl.*;
import ru.hld.legendline.*;
import ru.hld.legendline.api.utils.*;
import ru.hld.legendline.antiNative.*;
import ru.hld.legendline.api.event.*;

public class DamageColor extends Module
{
    float gh2;
    float gh;
    
    public DamageColor() {
        super("DamageColor", "show you damage", Category.Render, 50, 20);
    }
    
    @EventTarget
    public void onRender2D(final EventRender2D eventRender2D) {
        final int posX = this.getPosX();
        final int posY = this.getPosY();
        GL11.glPushMatrix();
        this.gh = this.mc.player.getHealth();
        if (this.mc.player.hurtTime > 0) {
            this.fr.drawString(String.valueOf(new StringBuilder().append("-").append(this.gh2 - this.gh)), posX + 2, posY + 2, Client.getColor());
            RenderUtils.drawRect(0.0f, 0.0f, 1000.0f, 1000.0f, ColorUtils.getColor(255, 30, 40, this.mc.player.hurtTime * 8));
        }
        else {
            this.gh2 = this.gh;
        }
        GL11.glPopMatrix();
    }
}
