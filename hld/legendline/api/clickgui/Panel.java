package ru.hld.legendline.api.clickgui;

import ru.hld.legendline.*;
import java.io.*;
import ru.hld.legendline.api.module.*;
import java.util.*;
import ru.hld.legendline.impl.modules.Render.*;
import ru.hld.legendline.antiNative.*;
import java.awt.*;
import ru.hld.legendline.api.utils.*;
import org.lwjgl.input.*;

public class Panel extends Comp
{
    ArrayList mods;
    int color;
    float scroll;
    CFontRenderer fr;
    int scrollTo;
    Category category;
    
    @Override
    public void mouseClicked(final int n, final int n2, final int n3, final int n4, final int n5) throws IOException {
        super.mouseClicked(n, n2, n3, n4, n5);
        if (this.ishover((float)n, (float)(n2 + 20), (float)(n + 100), (float)(n2 + 40), n3, n4)) {
            Client.gui.currentPanel = this;
        }
    }
    
    public Panel(final Category category) {
        this.fr = Fonts.mntsb_20;
        this.scroll = 0.0f;
        this.scrollTo = 0;
        this.color = -1;
        this.mods = new ArrayList();
        this.category = category;
        final Iterator<Module> iterator = Client.moduleManager.getModules(category).iterator();
        while (iterator.hasNext()) {
            this.mods.add(new Mod(iterator.next()));
        }
    }
    
    public void mouseClickedOpen(final int n, final int n2, final int n3, final int n4, final int n5) throws IOException {
        super.mouseClicked(n, n2, n3, n4, n5);
        int n6 = (int)(5.0f + this.scroll);
        for (final Mod mod : this.mods) {
            mod.mouseClicked(n + 105, n2 + n6, n3, n4, n5);
            n6 += (int)(mod.getHeight() + 2.0f);
        }
    }
    
    @Override
    public void drawScreen(final int n, final int n2, final int n3, final int n4, final float n5) {
        final CFontRenderer iconswex_36 = Fonts.iconswex_36;
        super.drawScreen(n, n2, n3, n4, n5);
        if (Client.gui.currentPanel == this) {
            if (ClickGui.shadows.getVal()) {
                RenderUtils.drawShadowRect(n, n2 + 23, n + 103, n2 + 37, 3, Client.getColor2());
            }
            else {
                RenderUtils.drawSuper(n, n2 + 23, n + 103, n2 + 37, 3, Client.getColor2());
            }
        }
        else if (ClickGui.shadows.getVal()) {
            RenderUtils.drawShadowRect(n, n2 + 21, n + 103, n2 + 39, 3, new Color(30, 30, 30, 200).getRGB());
        }
        else {
            RenderUtils.drawSuper(n, n2 + 23, n + 103, n2 + 37, 3, new Color(30, 30, 30, 200).getRGB());
        }
        final int ishover = this.ishover((float)n, (float)(n2 + 20), (float)(n + 100), (float)(n2 + 40), n3, n4) ? 1 : 0;
        if (this.category == Category.Movement) {
            iconswex_36.drawString("d", n + 2, n2 + 30 - this.fr.getHeight() / 2 - ishover, -1);
        }
        if (this.category == Category.Combat) {
            iconswex_36.drawString("e", n + 2, n2 + 30 - this.fr.getHeight() / 2 - ishover, -1);
        }
        if (this.category == Category.Player) {
            iconswex_36.drawString("b", n + 2, n2 + 30 - this.fr.getHeight() / 2 - ishover, -1);
        }
        if (this.category == Category.Render) {
            iconswex_36.drawString("f", n + 2, n2 + 30 - this.fr.getHeight() / 2 - ishover, -1);
        }
        if (this.category == Category.Misc) {
            iconswex_36.drawString("h", n + 3, n2 + 30 - this.fr.getHeight() / 2 - ishover, -1);
        }
        if (this.category == Category.Config) {
            iconswex_36.drawString("j", n + 3, n2 + 30 - this.fr.getHeight() / 2 - ishover, -1);
        }
        this.fr.drawCenteredString(this.category.name(), n + 50, n2 + 30 - this.fr.getHeight() / 2 - ishover, -1);
        if (Client.gui.currentPanel == this) {
            if (this.mods.size() > 0) {
                this.mods.get(0).anim = MathUtils.lerp(this.mods.get(0).anim, 1.0f, 0.3f);
                for (int i = 0; i < this.mods.size() - 1; ++i) {
                    if (((Mod)this.mods.get(i)).anim > 0.98f) {
                        ((Mod)this.mods.get(i + 1)).anim = MathUtils.lerp(((Mod)this.mods.get(i + 1)).anim, 1.0f, 0.3f);
                    }
                }
            }
        }
        else {
            final Iterator<Mod> iterator = this.mods.iterator();
            while (iterator.hasNext()) {
                iterator.next().reset();
            }
        }
    }
    
    public void drawMods(final int n, final int n2, final int n3, final int n4, final float n5) {
        if (this.ishover((float)n, (float)n2, (float)(Client.gui.WIGHT + n), (float)(Client.gui.HEIGHT + n2), n3, n4)) {
            final int dWheel = Mouse.getDWheel();
            if (dWheel > 0) {
                this.scrollTo += 8;
            }
            else if (dWheel < 0) {
                this.scrollTo -= 8;
            }
        }
        this.scroll = MathUtils.lerp(this.scroll, (float)this.scrollTo, 0.1f);
        int n6 = (int)(5.0f + this.scroll);
        for (final Mod mod : this.mods) {
            mod.drawScreen(n + 105, n2 + n6, n3, n4, n5);
            n6 += (int)(mod.getHeight() + 2.0f);
        }
    }
    
    @Override
    public void initGui() {
        super.initGui();
        this.color = -1;
    }
    
    public Category getCategory() {
        return this.category;
    }
}
