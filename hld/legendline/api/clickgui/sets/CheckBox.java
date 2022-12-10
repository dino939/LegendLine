package ru.hld.legendline.api.clickgui.sets;

import ru.hld.legendline.api.clickgui.*;
import ru.hld.legendline.*;
import ru.hld.legendline.api.setting.settings.*;
import java.io.*;
import ru.hld.legendline.api.setting.*;
import org.lwjgl.opengl.*;
import ru.hld.legendline.antiNative.*;
import ru.hld.legendline.impl.modules.Render.*;
import java.awt.*;
import ru.hld.legendline.api.utils.*;

public class CheckBox extends Set
{
    CFontRenderer fr;
    float anim2;
    
    @Override
    public void mouseClicked(final int n, final int n2, final int n3, final int n4, final int n5) throws IOException {
        super.mouseClicked(n, n2, n3, n4, n5);
        if (this.ishover((float)n, (float)n2, (float)(n + (Client.gui.WIGHT - 115)), (float)(n2 + 15), n3, n4) && n5 == 0) {
            ((BooleanSetting)this.getSetting()).setVal(!((BooleanSetting)this.getSetting()).getVal());
        }
    }
    
    @Override
    public void reset() {
        super.reset();
        this.anim = 0.0f;
    }
    
    public CheckBox(final BooleanSetting booleanSetting) {
        super(booleanSetting);
        this.fr = Fonts.mntsb_20;
        this.anim2 = 0.5f;
    }
    
    @Override
    public void drawScreen(final int n, final int n2, final int n3, final int n4, final float n5) {
        super.drawScreen(n, n2, n3, n4, n5);
        final int n6 = Client.gui.WIGHT - 115;
        GL11.glPushMatrix();
        RenderUtils.customScaledObject2D((float)n, (float)n2, (float)n6, 15.0f, this.anim, 1.0f);
        if (ClickGui.shadows.getVal()) {
            RenderUtils.drawShadowRect(n + 5, n2, n + n6, n2 + this.getHeight(), 2, new Color(30, 30, 30, 200).getRGB());
        }
        else {
            RenderUtils.drawRect((float)(n + 5), (float)n2, (float)(n + n6), n2 + this.getHeight(), new Color(30, 30, 30, 200).getRGB());
        }
        this.anim2 = MathUtils.lerp(this.anim2, ((BooleanSetting)this.getSetting()).getVal() ? 1.0f : 0.0f, 0.07f);
        if (ClickGui.shadows.getVal()) {
            RenderUtils.drawShadowRect(n + n6 - 20.5, n2 + this.getHeight() - 13.5, n + n6 - 19 + 17, n2 + this.getHeight() - 1.5, 2, Client.getGuiColor());
            RenderUtils.drawShadowRect(n + n6 - 19 + 10.0f * this.anim2, n2 + this.getHeight() - 13.0f, n + n6 - 13 + 10.0f * this.anim2, n2 + this.getHeight() - 2.0f, 2, ColorUtils.getColor(255 - (int)(this.anim2 * 255.0f), (int)(this.anim2 * 255.0f), 0));
        }
        else {
            RenderUtils.drawRect(n + n6 - 20.5, n2 + this.getHeight() - 13.5, n + n6 - 19 + 17, n2 + this.getHeight() - 1.5, Client.getColor());
            RenderUtils.drawRect(n + n6 - 19 + 10.0f * this.anim2, n2 + this.getHeight() - 13.0f, n + n6 - 13 + 10.0f * this.anim2, n2 + this.getHeight() - 2.0f, ColorUtils.getColor(255 - (int)(this.anim2 * 255.0f), (int)(this.anim2 * 255.0f), 0));
        }
        this.fr.drawString(this.getSetting().getName(), n + 7, n2 + 7 - this.fr.getHeight() / 2, -1);
        if (this.ishover((float)n, (float)n2, (float)(n + n6), (float)(n2 + 15), n3, n4)) {
            this.fr.drawString(this.getSetting().getDescriptions(), n3 + 10, n4 - 2 - this.fr.getHeight() / 2, -1);
        }
        GL11.glPopMatrix();
    }
    
    @Override
    public float getHeight() {
        return 15.0f;
    }
}
