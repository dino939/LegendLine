package ru.hld.legendline.api.hudeditor;

import ru.hld.legendline.api.module.*;
import org.lwjgl.input.*;
import java.awt.*;
import ru.hld.legendline.antiNative.*;
import ru.hld.legendline.api.font.*;
import java.io.*;

public class HudComponent
{
    boolean draging;
    int dragX;
    int dragY;
    Module module;
    
    public void drawScreen(final int n, final int n2, final float n3) {
        if (this.draging) {
            if (!Mouse.isButtonDown(0)) {
                this.draging = false;
            }
            this.module.setPosX(n - this.dragX);
            this.module.setPosY(n2 - this.dragY);
        }
        RenderUtils.drawRect((float)this.module.getPosX(), (float)this.module.getPosY(), (float)(this.module.getPosX() + this.module.getSizeX()), (float)(this.module.getPosY() + this.module.getSizeY()), new Color(30, 30, 30, 80).getRGB());
        FontUtils.fr.drawCenteredString(this.module.getName(), this.module.getPosX() + this.module.getSizeX() / 2, this.module.getPosY() + 1, -1);
    }
    
    public HudComponent(final Module module) {
        this.draging = false;
        this.module = module;
    }
    
    public boolean ishover(final float n, final float n2, final float n3, final float n4, final int n5, final int n6) {
        return n5 >= n && n5 <= n3 && n6 >= n2 && n6 <= n4;
    }
    
    protected void mouseClicked(final int n, final int n2, final int n3) throws IOException {
        if (this.ishover((float)this.module.getPosX(), (float)this.module.getPosY(), (float)(this.module.getPosX() + this.module.getSizeX()), (float)(this.module.getPosY() + this.module.getSizeY()), n, n2) && n3 == 0) {
            this.draging = !this.draging;
            this.dragX = n - this.module.getPosX();
            this.dragY = n2 - this.module.getPosY();
        }
    }
}
