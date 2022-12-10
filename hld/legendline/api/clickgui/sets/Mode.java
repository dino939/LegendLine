package ru.hld.legendline.api.clickgui.sets;

import ru.hld.legendline.api.clickgui.*;
import ru.hld.legendline.api.setting.settings.*;
import ru.hld.legendline.*;
import org.lwjgl.opengl.*;
import ru.hld.legendline.antiNative.*;
import ru.hld.legendline.impl.modules.Render.*;
import java.awt.*;
import java.util.*;
import java.io.*;
import ru.hld.legendline.api.setting.*;
import ru.hld.legendline.api.utils.*;

public class Mode extends Set
{
    CFontRenderer fr;
    boolean open;
    
    @Override
    public float getHeight() {
        int n = 15;
        final ModeSetting modeSetting = (ModeSetting)this.getSetting();
        if (this.open) {
            n += modeSetting.getModes().size() * 12;
            n -= 12;
        }
        return (float)n;
    }
    
    @Override
    public void drawScreen(final int n, final int n2, final int n3, final int n4, final float n5) {
        super.drawScreen(n, n2, n3, n4, n5);
        final int n6 = Client.gui.WIGHT - 115;
        final ModeSetting modeSetting = (ModeSetting)this.getSetting();
        GL11.glPushMatrix();
        RenderUtils.customScaledObject2D((float)n, (float)n2, (float)n6, 15.0f, this.anim, 1.0f);
        if (ClickGui.shadows.getVal()) {
            RenderUtils.drawShadowRect(n + 5, n2, n + n6, n2 + this.getHeight(), 2, new Color(30, 30, 30, 200).getRGB());
        }
        else {
            RenderUtils.drawRect((float)(n + 5), (float)n2, (float)(n + n6), n2 + this.getHeight(), new Color(30, 30, 30, 200).getRGB());
        }
        int stringWidth = 0;
        for (final String s : modeSetting.getModes()) {
            if (this.fr.getStringWidth(s) > stringWidth) {
                stringWidth = this.fr.getStringWidth(s);
            }
        }
        if (this.open) {
            int n7 = 15;
            for (final String s2 : modeSetting.getModes()) {
                if (!modeSetting.getVal().equalsIgnoreCase(s2)) {
                    this.fr.drawString(s2, n + n6 - this.fr.getStringWidth(s2) - 4, n2 + n7 + (6 - this.fr.getHeight() / 2), -1);
                    n7 += 12;
                }
            }
        }
        this.fr.drawString(this.getSetting().getName(), n + 7, n2 + 7 - this.fr.getHeight() / 2, -1);
        this.fr.drawString(modeSetting.getVal(), n - 4 + n6 - this.fr.getStringWidth(modeSetting.getVal()), n2 + 7 - this.fr.getHeight() / 2, Client.getColor());
        if (this.ishover((float)n, (float)n2, (float)(n + n6), (float)(n2 + 15), n3, n4)) {
            this.fr.drawString(modeSetting.getDescriptions(), n3 + 10, n4 - 2 - this.fr.getHeight() / 2, -1);
        }
        GL11.glPopMatrix();
    }
    
    @Override
    public void mouseClicked(final int n, final int n2, final int n3, final int n4, final int n5) throws IOException {
        super.mouseClicked(n, n2, n3, n4, n5);
        final int n6 = Client.gui.WIGHT - 115;
        final ModeSetting modeSetting = (ModeSetting)this.getSetting();
        if (this.ishover((float)n, (float)n2, (float)(n + n6), (float)(n2 + 15), n3, n4) && n5 == 1) {
            this.open = !this.open;
        }
        int stringWidth = 0;
        for (final String s : modeSetting.getModes()) {
            if (this.fr.getStringWidth(s) > stringWidth) {
                stringWidth = this.fr.getStringWidth(s);
            }
        }
        if (this.open) {
            int n7 = 15;
            for (final String val : modeSetting.getModes()) {
                if (!modeSetting.getVal().equalsIgnoreCase(val)) {
                    if (this.ishover((float)(n + n6 - stringWidth), (float)(n2 + n7), (float)(n + n6), (float)(n2 + n7 + 12), n3, n4) && n5 == 0) {
                        modeSetting.setVal(val);
                    }
                    n7 += 12;
                }
            }
        }
    }
    
    @Override
    public void reset() {
        super.reset();
        this.anim = 0.0f;
    }
    
    public Mode(final ModeSetting modeSetting) {
        super(modeSetting);
        this.fr = Fonts.mntsb_20;
        this.open = false;
    }
}
