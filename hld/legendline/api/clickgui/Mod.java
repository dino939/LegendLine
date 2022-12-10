package ru.hld.legendline.api.clickgui;

import ru.hld.legendline.api.module.*;
import ru.hld.legendline.*;
import ru.hld.legendline.api.setting.*;
import ru.hld.legendline.api.setting.settings.*;
import ru.hld.legendline.api.clickgui.sets.*;
import java.util.*;
import org.lwjgl.input.*;
import org.lwjgl.opengl.*;
import ru.hld.legendline.antiNative.*;
import ru.hld.legendline.impl.modules.Render.*;
import java.awt.*;
import ru.hld.legendline.api.utils.*;
import java.io.*;

public class Mod extends Comp
{
    ArrayList sets;
    boolean binding;
    float anim2;
    CFontRenderer fr;
    public float anim;
    boolean open;
    Module module;
    
    public Module getModule() {
        return this.module;
    }
    
    public Mod(final Module module) {
        this.fr = Fonts.mntsb_20;
        this.binding = false;
        this.sets = new ArrayList();
        this.open = false;
        this.anim = 0.0f;
        this.anim2 = 0.5f;
        this.module = module;
        for (final Setting setting : Client.settingManager.getSettings(module)) {
            if (setting instanceof BooleanSetting) {
                this.sets.add(new CheckBox((BooleanSetting)setting));
            }
            if (setting instanceof FloatSetting) {
                this.sets.add(new Slider((FloatSetting)setting));
            }
            if (setting instanceof ModeSetting) {
                this.sets.add(new Mode((ModeSetting)setting));
            }
        }
    }
    
    @Override
    public void reset() {
        super.reset();
        this.anim = 0.0f;
        final Iterator<Set> iterator = this.sets.iterator();
        while (iterator.hasNext()) {
            iterator.next().reset();
        }
    }
    
    @Override
    public void drawScreen(final int n, final int n2, final int n3, final int n4, final float n5) {
        super.drawScreen(n, n2, n3, n4, n5);
        if (this.binding && Keyboard.isKeyDown(Keyboard.getEventKey())) {
            if (Keyboard.getEventKey() == 211) {
                this.module.setKey(0);
            }
            else {
                this.module.setKey(Keyboard.getEventKey());
            }
            this.binding = false;
        }
        final int n6 = Client.gui.WIGHT - 110;
        GL11.glPushMatrix();
        RenderUtils.customScaledObject2D((float)n, (float)n2, (float)n6, 15.0f, this.anim, 1.0f);
        this.anim2 = MathUtils.lerp(this.anim2, this.module.isEnabled() ? 1.0f : 0.0f, 0.07f);
        if (ClickGui.shadows.getVal()) {
            RenderUtils.drawShadowRect(n + 5, n2, n + n6, n2 + 15, 2, new Color(30, 30, 30, 200).getRGB());
            RenderUtils.drawShadowRect(n + n6 - 20, n2 + 7 - this.fr.getHeight() / 2 - 1.5, n + n6 - 19 + 17, n2 + 7 - this.fr.getHeight() / 2 + 10.5, 1, Client.getGuiColor());
            RenderUtils.drawShadowRect(n + n6 - 19 + 10.0f * this.anim2, n2 + 7 - this.fr.getHeight() / 2 - 1, n + n6 - 13 + 10.0f * this.anim2, n2 + 7 - this.fr.getHeight() / 2 + 10, 2, ColorUtils.getColor(255 - (int)(this.anim2 * 255.0f), (int)(this.anim2 * 255.0f), 0));
        }
        else {
            RenderUtils.drawRect((float)(n + 5), (float)n2, (float)(n + n6), (float)(n2 + 15), new Color(30, 30, 30, 200).getRGB());
            RenderUtils.drawRect(n + n6 - 20, n2 + 7 - this.fr.getHeight() / 2 - 1.5, n + n6 - 19 + 17, n2 + 7 - this.fr.getHeight() / 2 + 10.5, Client.getGuiColor());
            RenderUtils.drawRect(n + n6 - 19 + 10.0f * this.anim2, (float)(n2 + 7 - this.fr.getHeight() / 2 - 1), n + n6 - 13 + 10.0f * this.anim2, (float)(n2 + 7 - this.fr.getHeight() / 2 + 10), ColorUtils.getColor(255 - (int)(this.anim2 * 255.0f), (int)(this.anim2 * 255.0f), 0));
        }
        this.fr.drawString(this.binding ? "Binding..." : this.module.getName(), n + 8, n2 + 7 - this.fr.getHeight() / 2 + 1, -1);
        GL11.glPopMatrix();
        if (this.open) {
            if (this.sets.size() > 0) {
                this.sets.get(0).anim = MathUtils.lerp(this.sets.get(0).anim, 1.0f, 0.3f);
                int i = 0;
                while (i < this.sets.size() - 1) {
                    if (((Set)this.sets.get(i)).anim > 0.98f) {
                        ((Set)this.sets.get(i + 1)).anim = MathUtils.lerp(((Set)this.sets.get(i + 1)).anim, 1.0f, 0.3f);
                    }
                    final int n7 = 4;
                    final int n8 = 4;
                    ++i;
                    if (n7 != n8) {
                        return;
                    }
                }
            }
            int n9 = 15;
            if (this.open) {
                for (final Set set : this.sets) {
                    if (set.getSetting().isVisible()) {
                        n9 += (int)set.getHeight();
                    }
                }
            }
            int n10 = 17;
            for (final Set set2 : this.sets) {
                if (set2.getSetting().isVisible()) {
                    set2.drawScreen(n + 2, n2 + n10, n3, n4, n5);
                    n10 += (int)(set2.getHeight() + 2.0f);
                }
                else {
                    set2.reset();
                }
            }
        }
        else {
            final Iterator<Set> iterator3 = (Iterator<Set>)this.sets.iterator();
            while (iterator3.hasNext()) {
                iterator3.next().reset();
            }
        }
        if (this.ishover((float)n, (float)n2, (float)(n + n6), (float)(n2 + 15), n3, n4)) {
            this.fr.drawString(this.module.getDescriptions(), n3 + 10, n4 - 2 - this.fr.getHeight() / 2, -1);
        }
    }
    
    @Override
    public void mouseClicked(final int n, final int n2, final int n3, final int n4, final int n5) throws IOException {
        super.mouseClicked(n, n2, n3, n4, n5);
        if (this.ishover((float)n, (float)n2, (float)(n + (Client.gui.WIGHT - 110)), (float)(n2 + 15), n3, n4)) {
            if (n5 == 0) {
                this.module.toggle();
            }
            if (n5 == 1 && this.sets.size() > 0) {
                this.open = !this.open;
            }
            if (n5 == 2) {
                this.binding = !this.binding;
            }
        }
        if (this.open) {
            int n6 = 17;
            for (final Set set : this.sets) {
                if (set.getSetting().isVisible()) {
                    set.mouseClicked(n + 2, n2 + n6, n3, n4, n5);
                    n6 += (int)(set.getHeight() + 2.0f);
                }
            }
        }
    }
    
    @Override
    public float getHeight() {
        int n = 15;
        if (this.open) {
            for (final Set set : this.sets) {
                if (set.getSetting().isVisible()) {
                    n += (int)(set.getHeight() + 3.0f);
                }
            }
        }
        return (float)n;
    }
}
