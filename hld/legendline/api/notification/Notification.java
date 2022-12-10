package ru.hld.legendline.api.notification;

import ru.hld.legendline.api.font.*;
import org.lwjgl.opengl.*;
import ru.hld.legendline.antiNative.*;
import ru.hld.legendline.*;
import ru.hld.legendline.impl.modules.Render.*;
import java.awt.*;
import ru.hld.legendline.api.utils.*;

public class Notification
{
    float anim;
    float animMotion;
    TimerUtils timer;
    String name;
    String message;
    
    public float getHeight() {
        return 12.0f;
    }
    
    public Notification(final String name, final String message) {
        this.anim = 0.0f;
        this.animMotion = 1.2f;
        this.message = message;
        this.name = name;
        this.timer = new TimerUtils();
        this.anim = 0.8f;
    }
    
    public boolean isTimeOut() {
        return this.timer.hasReached(1000L);
    }
    
    public float getWidth() {
        return (float)(FontUtils.fr.getStringWidth(this.name) + 100);
    }
    
    public void render(final int n, final int n2) {
        if (this.anim > 1.18f) {
            this.animMotion = 1.0f;
        }
        if (this.timer.hasReached(900L)) {
            this.animMotion = 0.7f;
        }
        this.anim = MathUtils.lerp(this.anim, this.animMotion, 0.1f);
        GL11.glPushMatrix();
        RenderUtils.customScaledObject2D((float)n, (float)n2, this.getWidth(), this.getHeight(), this.anim);
        RenderUtils.drawShadowRect(n, n2, n + this.getWidth(), n2 + this.getHeight(), 4, Client.getColor());
        final CFontRenderer mntsb_25 = Fonts.mntsb_25;
        final CFontRenderer iconswex_40 = Fonts.iconswex_40;
        mntsb_25.drawCenteredString(this.name, n + this.getWidth() / 2.0f + 10.0f, n2, -1);
        if (Notifications.colored.getVal()) {
            iconswex_40.drawCenteredString(this.message, n + 9, n2 + this.getHeight() - mntsb_25.getHeight() + 2.0f, (this.message == "I") ? Client.getColor2() : Client.getColor2());
        }
        else {
            iconswex_40.drawCenteredString(this.message, n + 9, n2 + this.getHeight() - mntsb_25.getHeight() + 2.0f, (this.message == "I") ? new Color(255, 0, 0, 255).getRGB() : new Color(0, 255, 0, 255).getRGB());
        }
        GL11.glPopMatrix();
    }
}
