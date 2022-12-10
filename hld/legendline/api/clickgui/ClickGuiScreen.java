package ru.hld.legendline.api.clickgui;

import net.minecraft.client.gui.*;
import ru.hld.legendline.*;
import org.lwjgl.input.*;
import org.lwjgl.opengl.*;
import ru.hld.legendline.impl.modules.Render.*;
import java.awt.*;
import net.minecraft.util.text.*;
import net.minecraft.client.network.*;
import ru.hld.legendline.antiNative.*;
import ru.hld.legendline.api.utils.*;
import java.util.*;
import java.io.*;
import ru.hld.legendline.api.module.*;

public class ClickGuiScreen extends GuiScreen
{
    int posY;
    ArrayList panels;
    int posX;
    float anim;
    float animMotion;
    int dragY;
    boolean draging;
    HudButton hudButton;
    public int HEIGHT;
    public int WIGHT;
    int dragX;
    public Panel currentPanel;
    boolean sizing;
    
    @Override
    public void drawScreen(final int n, final int n2, final float n3) {
        Client.blurUtil.blur((float)this.posX, (float)this.posY, (float)(this.posX + this.WIGHT), (float)(this.posY + this.HEIGHT), 5);
        final CFontRenderer mntsb_21 = Fonts.mntsb_21;
        super.drawScreen(n, n2, n3);
        if (this.draging) {
            if (!Mouse.isButtonDown(0)) {
                this.draging = false;
            }
            this.posX = n - this.dragX;
            this.posY = n2 - this.dragY;
        }
        this.anim = MathUtils.lerp(this.anim, this.animMotion, 0.3f);
        GL11.glPushMatrix();
        if (!ClickGui.shadows.getVal()) {
            RenderUtils.drawSuper(this.posX, this.posY, this.posX + this.WIGHT, this.posY + this.HEIGHT, 5, Client.getGuiColor());
            RenderUtils.drawSuper(this.posX + 2, this.posY + 6, this.posX + 105, this.posY + 34, 3, new Color(30, 30, 30, 200).getRGB());
        }
        else {
            RenderUtils.drawShadowRect(this.posX, this.posY, this.posX + this.WIGHT, this.posY + this.HEIGHT, 10, Client.getGuiColor());
            RenderUtils.drawShadowRect(this.posX + 2, this.posY + 6, this.posX + 105, this.posY + 34, 3, new Color(30, 30, 30, 200).getRGB());
        }
        final String s = "LEGENDLINE";
        final String value = String.valueOf(new StringBuilder().append(TextFormatting.WHITE).append(" PREM | v 2.0"));
        RenderUtil.drawHead(Objects.requireNonNull(this.mc.getConnection()).getPlayerInfo(this.mc.player.getUniqueID()).getLocationSkin(), this.posX + 3, this.posY + 7, 26, 26);
        mntsb_21.drawString(s, this.posX + 30.5, this.posY + 8, Client.gColor());
        mntsb_21.drawString(value, this.posX + 28, this.posY + 24, Client.gColor());
        int n4 = 0;
        final Iterator<Panel> iterator = (Iterator<Panel>)this.panels.iterator();
        while (iterator.hasNext()) {
            iterator.next().drawScreen(this.posX + 2, this.posY + 20 + n4, n, n2, n3);
            n4 += 23;
        }
        GL11.glEnable(3089);
        RenderUtils.scissorRect((float)(this.posX + 100), (float)(this.posY + 5), (float)(this.posX + this.WIGHT), this.posY - 5 + this.HEIGHT);
        if (this.currentPanel != null) {
            this.currentPanel.drawMods(this.posX, this.posY, n, n2, n3);
        }
        GL11.glDisable(3089);
        if (this.sizing) {
            if (!Mouse.isButtonDown(0)) {
                this.sizing = false;
            }
            if (n - this.posX > 300) {
                this.WIGHT = n - this.posX;
            }
            if (n2 - this.posY > 200) {
                this.HEIGHT = n2 - this.posY;
            }
        }
        RenderUtils.drawRect((float)(this.posX + this.WIGHT - 1), (float)(this.posY + this.HEIGHT - 10), (float)(this.posX + this.WIGHT), (float)(this.posY + this.HEIGHT), Client.getColor2());
        RenderUtils.drawRect((float)(this.posX + this.WIGHT - 10), (float)(this.posY + this.HEIGHT - 1), (float)(this.posX + this.WIGHT), (float)(this.posY + this.HEIGHT), Client.getColor());
        RenderUtils.drawRect((float)(this.posX + this.WIGHT - 3), (float)(this.posY + this.HEIGHT - 8), (float)(this.posX + this.WIGHT - 2), (float)(this.posY + this.HEIGHT - 2), Client.getColor2());
        RenderUtils.drawRect((float)(this.posX + this.WIGHT - 8), (float)(this.posY + this.HEIGHT - 3), (float)(this.posX + this.WIGHT - 2), (float)(this.posY + this.HEIGHT - 2), Client.getColor());
        RenderUtils.drawRect((float)(this.posX + this.WIGHT - 5), (float)(this.posY + this.HEIGHT - 6), (float)(this.posX + this.WIGHT - 4), (float)(this.posY + this.HEIGHT - 4), Client.getColor2());
        RenderUtils.drawRect((float)(this.posX + this.WIGHT - 6), (float)(this.posY + this.HEIGHT - 5), (float)(this.posX + this.WIGHT - 4), (float)(this.posY + this.HEIGHT - 4), Client.getColor());
        this.hudButton.drawScreen(this.posX + 3, this.posY + this.HEIGHT - 19, n, n2, n3);
        GL11.glPopMatrix();
    }
    
    @Override
    protected void mouseClicked(final int n, final int n2, final int n3) throws IOException {
        super.mouseClicked(n, n2, n3);
        if (this.ishover((float)this.posX, (float)this.posY, (float)(this.posX + 100), (float)(this.posY + 20), n, n2) && n3 == 0) {
            this.draging = true;
            this.dragX = n - this.posX;
            this.dragY = n2 - this.posY;
        }
        if (this.ishover((float)(this.posX + this.WIGHT - 10), (float)(this.posY + this.HEIGHT - 10), (float)(this.posX + this.WIGHT), (float)(this.posY + this.HEIGHT), n, n2) && n3 == 0) {
            this.sizing = true;
            this.dragX = n - this.posX;
            this.dragY = n2 - this.posY;
        }
        int n4 = 0;
        final Iterator<Panel> iterator = this.panels.iterator();
        while (iterator.hasNext()) {
            iterator.next().mouseClicked(this.posX + 2, this.posY + 20 + n4, n, n2, n3);
            n4 += 23;
        }
        if (this.currentPanel != null) {
            this.currentPanel.mouseClicked(this.posX, this.posY, n, n2, n3);
        }
        if (this.currentPanel != null) {
            this.currentPanel.mouseClickedOpen(this.posX, this.posY, n, n2, n3);
        }
        this.hudButton.mouseClicked(this.posX + 3, this.posY + this.HEIGHT - 19, n, n2, n3);
    }
    
    public ClickGuiScreen() {
        this.anim = 0.7f;
        this.animMotion = 1.1f;
        this.WIGHT = 400;
        this.HEIGHT = 300;
        this.draging = false;
        this.sizing = false;
        this.panels = new ArrayList();
        this.currentPanel = null;
        final Category[] values = Category.values();
        final int length = values.length;
        int i = 0;
        while (i < length) {
            this.panels.add(new Panel(values[i]));
            final int n = 0;
            final int n2 = 2;
            ++i;
            if (n >= n2) {
                throw null;
            }
        }
        this.hudButton = new HudButton();
    }
    
    @Override
    public void initGui() {
        super.initGui();
        this.anim = 0.7f;
        this.animMotion = 1.3f;
        final Iterator<Panel> iterator = this.panels.iterator();
        while (iterator.hasNext()) {
            iterator.next().initGui();
        }
    }
    
    public boolean ishover(final float n, final float n2, final float n3, final float n4, final int n5, final int n6) {
        return n5 >= n && n5 <= n3 && n6 >= n2 && n6 <= n4;
    }
}
