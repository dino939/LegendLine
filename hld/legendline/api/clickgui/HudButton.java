package ru.hld.legendline.api.clickgui;

import net.minecraft.client.*;
import ru.hld.legendline.*;
import net.minecraft.client.gui.*;
import java.io.*;
import ru.hld.legendline.impl.modules.Render.*;
import java.awt.*;
import ru.hld.legendline.antiNative.*;
import ru.hld.legendline.api.utils.*;
import org.lwjgl.opengl.*;

public class HudButton extends Comp
{
    CFontRenderer icons;
    float anim;
    CFontRenderer fr;
    
    @Override
    public void mouseClicked(final int n, final int n2, final int n3, final int n4, final int n5) throws IOException {
        super.mouseClicked(n, n2, n3, n4, n5);
        if (this.ishover((float)n, (float)n2, (float)(n + 24 + this.fr.getStringWidth(" HudEditor")), (float)(n2 + 16), n3, n4) && n5 == 0) {
            Minecraft.getMinecraft().displayGuiScreen(Client.hudEditor);
        }
    }
    
    public HudButton() {
        this.fr = Fonts.mntsb_20;
        this.icons = Fonts.iconswex_30;
        this.anim = 0.0f;
    }
    
    @Override
    public void drawScreen(final int n, final int n2, final int n3, final int n4, final float n5) {
        super.drawScreen(n, n2, n3, n4, n5);
        if (ClickGui.shadows.getVal()) {
            RenderUtils.drawShadowRect(n, n2, n + 24 + this.fr.getStringWidth(" HudEditor"), n2 + 16, 3, new Color(30, 30, 30, 200).getRGB());
        }
        else {
            RenderUtils.drawSuper(n, n2, n + 24 + this.fr.getStringWidth(" HudEditor"), n2 + 16, 3, new Color(30, 30, 30, 200).getRGB());
        }
        if (this.ishover((float)n, (float)n2, (float)(n + 24), (float)(n2 + 16), n3, n4)) {
            this.anim = MathUtils.lerp(this.anim, 1.2f, 0.01f);
        }
        else {
            this.anim = MathUtils.lerp(this.anim, 1.0f, 0.1f);
        }
        GL11.glPushMatrix();
        RenderUtils.customScaledObject2D((float)n, (float)n2, 16.0f, 16.0f, this.anim);
        this.icons.drawCenteredString("g", n + 8, n2 + 8 - this.icons.getHeight() / 2, Client.getColor());
        GL11.glPopMatrix();
        this.fr.drawString("HudEditor", n + 16, n2 + 8 - this.fr.getHeight() / 2, -1);
    }
}
