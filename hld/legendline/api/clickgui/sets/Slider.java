package ru.hld.legendline.api.clickgui.sets;

import ru.hld.legendline.api.clickgui.*;
import ru.hld.legendline.api.setting.settings.*;
import ru.hld.legendline.api.setting.*;
import ru.hld.legendline.*;
import org.lwjgl.opengl.*;
import ru.hld.legendline.api.utils.*;
import ru.hld.legendline.antiNative.*;
import ru.hld.legendline.impl.modules.Render.*;
import java.awt.*;
import org.lwjgl.input.*;
import java.io.*;

public class Slider extends Set
{
    float sliderAnim;
    CFontRenderer fr;
    boolean use;
    
    @Override
    public void reset() {
        super.reset();
        this.anim = 0.0f;
        this.sliderAnim = 0.0f;
    }
    
    public Slider(final FloatSetting floatSetting) {
        super(floatSetting);
        this.fr = Fonts.mntsb_20;
        this.use = false;
        this.sliderAnim = 0.0f;
    }
    
    @Override
    public void drawScreen(final int n, final int n2, final int n3, final int n4, final float n5) {
        super.drawScreen(n, n2, n3, n4, n5);
        final int n6 = Client.gui.WIGHT - 115;
        GL11.glPushMatrix();
        final FloatSetting floatSetting = (FloatSetting)this.getSetting();
        final int n7 = n6 - 5 - this.fr.getStringWidth(String.valueOf(new StringBuilder().append(floatSetting.getVal()).append(""))) - this.fr.getStringWidth(floatSetting.getName());
        if (this.use) {
            floatSetting.setVal((float)MathUtils.round((float)((n3 - (n + this.fr.getStringWidth(floatSetting.getName()) + 10)) * (double)(floatSetting.getMax() - floatSetting.getMin()) / n7 + floatSetting.getMin()), floatSetting.getIncrement()));
            if (floatSetting.getVal() > floatSetting.getMax()) {
                floatSetting.setVal(floatSetting.getMax());
            }
            else if (floatSetting.getVal() < floatSetting.getMin()) {
                floatSetting.setVal(floatSetting.getMin());
            }
        }
        this.sliderAnim = MathUtils.lerp(this.sliderAnim, floatSetting.getVal(), 0.05f);
        final double round = MathUtils.round(floatSetting.getVal(), floatSetting.getIncrement());
        final double n8 = n7 / (double)(floatSetting.getMax() - floatSetting.getMin());
        final double n9 = n8 * round - n8 * floatSetting.getMin();
        RenderUtils.customScaledObject2D((float)n, (float)n2, (float)n6, 20.0f, this.anim, 1.0f);
        if (!ClickGui.shadows.getVal()) {
            RenderUtils.drawRect((float)(n + 5), (float)n2, (float)(n + n6), n2 + this.getHeight(), new Color(30, 30, 30, 200).getRGB());
            RenderUtils.drawGradientRect(n + (this.fr.getStringWidth(this.getSetting().getName()) + 10), n2 + 9, n + (this.fr.getStringWidth(this.getSetting().getName()) + 10) + n9, n2 + 11, true, Client.getColor2(), Client.getColor());
        }
        else {
            RenderUtils.drawShadowRect(n + 5, n2, n + n6, n2 + this.getHeight(), 2, new Color(30, 30, 30, 200).getRGB());
            RenderUtils.drawShadowRect(n + (this.fr.getStringWidth(this.getSetting().getName()) + 10), n2 + 9, n + (this.fr.getStringWidth(this.getSetting().getName()) + 10) + n9, n2 + 11, 2, Client.getColor2());
        }
        this.fr.drawString(this.getSetting().getName(), n + 7, n2 + 10 - this.fr.getHeight() / 2, -1);
        this.fr.drawString(String.valueOf(new StringBuilder().append(floatSetting.getVal()).append("")), n + (this.fr.getStringWidth(this.getSetting().getName()) + 12) + n9, n2 + 10 - this.fr.getHeight() / 2, this.use ? Color.RED.getRGB() : -1);
        if (this.ishover((float)n, (float)n2, (float)(n + n6), (float)(n2 + 20), n3, n4)) {
            this.fr.drawString(floatSetting.getDescriptions(), n3 + 10, n4 - 2 - this.fr.getHeight() / 2, -1);
        }
        GL11.glPopMatrix();
        if (!Mouse.isButtonDown(0)) {
            this.use = false;
        }
    }
    
    @Override
    public float getHeight() {
        return 20.0f;
    }
    
    @Override
    public void mouseClicked(final int n, final int n2, final int n3, final int n4, final int n5) throws IOException {
        super.mouseClicked(n, n2, n3, n4, n5);
        if (this.ishover((float)n, (float)n2, (float)(n + (Client.gui.WIGHT - 115)), (float)(n2 + 20), n3, n4) && n5 == 0) {
            this.use = true;
        }
    }
}
