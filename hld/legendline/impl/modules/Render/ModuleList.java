package ru.hld.legendline.impl.modules.Render;

import ru.hld.legendline.api.setting.settings.*;
import ru.hld.legendline.api.module.*;
import ru.hld.legendline.*;
import ru.hld.legendline.api.setting.*;
import ru.hld.legendline.api.event.events.*;
import org.lwjgl.opengl.*;
import ru.hld.legendline.antiNative.*;
import ru.hld.legendline.api.utils.*;
import java.util.*;
import ru.hld.legendline.api.event.*;
import net.minecraft.client.gui.*;

public class ModuleList extends Module
{
    public static BooleanSetting shadow;
    ArrayList modules;
    public static BooleanSetting rect;
    ModeSetting mode;
    
    public ModuleList() {
        super("ArrayList", "diplay all enabled modules on screen", Category.Render);
        this.modules = new ArrayList();
        final ArrayList<String> list = new ArrayList<String>();
        list.add("Astolfo");
        list.add("Custom");
        Client.settingManager.add(this.mode = new ModeSetting("Mode", "modes of ArrayList", this, list, "Custom"));
        Client.settingManager.add(ModuleList.rect = new BooleanSetting("Rect", "rect", this, true));
        Client.settingManager.add(ModuleList.shadow = new BooleanSetting("Shadow", "shadow", this, false));
    }
    
    @EventTarget
    public void onRender2D(final EventRender2D eventRender2D) {
        final CFontRenderer mntsb_18 = Fonts.mntsb_18;
        final int n = 0;
        final int n2 = Client.moduleManager.getModule("WaterMark").isEnabled() ? (n + 12) : (n + 0);
        final int n3 = (Client.moduleManager.getModule("hud").isEnabled() && ClientHud.cords.getVal()) ? (n2 + 12) : (n2 + 0);
        final int n4 = 140;
        final int n5 = n3;
        for (final SubModule subModule : this.modules) {
            if (subModule.getModule().isEnabled()) {
                subModule.setAnim(MathUtils.lerp(subModule.getAnim(), 1.0f, 0.1f));
                subModule.setY((int)MathUtils.lerp((float)subModule.getY(), (float)(mntsb_18.getHeight() + 12), 0.1f));
            }
            else {
                subModule.setAnim(MathUtils.lerp(subModule.getAnim(), 0.0f, 0.1f));
                subModule.setY((int)MathUtils.lerp((float)subModule.getY(), 0.0f, 0.1f));
            }
        }
        this.modules.sort(new Comparator(mntsb_18) {
            final ModuleList this$0;
            final CFontRenderer val$font;
            
            public int compare(final SubModule subModule, final SubModule subModule2) {
                return this.val$font.getStringWidth(subModule2.getModule().getDisplayname()) - this.val$font.getStringWidth(subModule.getModule().getDisplayname());
            }
            
            @Override
            public int compare(final Object o, final Object o2) {
                return this.compare((SubModule)o, (SubModule)o2);
            }
        });
        int n6 = 0;
        final Iterator<Module> iterator2 = (Iterator<Module>)Client.moduleManager.modules.iterator();
        while (iterator2.hasNext()) {
            if (iterator2.next().isEnabled()) {
                ++n6;
            }
        }
        int n7 = 0;
        for (final SubModule subModule2 : this.modules) {
            GL11.glPushMatrix();
            final float n8 = (float)(n4 - mntsb_18.getStringWidth(subModule2.getModule().getDisplayname()));
            final float n9 = (float)(n5 + n7);
            GL11.glTranslated((double)(-(float)(n4 - mntsb_18.getStringWidth(subModule2.getModule().getDisplayname())) + 10.0f), (double)10.0f, 1.0);
            GL11.glScaled((double)subModule2.getAnim(), 1.0, 0.0);
            final String val = this.mode.getVal();
            switch (val) {
                case "Astolfo": {
                    if (ModuleList.rect.getVal()) {
                        if (!ModuleList.shadow.getVal()) {
                            RenderUtils.drawGradientRect(n4 - mntsb_18.getStringWidth(subModule2.getModule().getDisplayname()) - 3, n5 + n7, n4 + 4, n5 + n7 + 11, true, Client.getColor(), Client.getColor2());
                        }
                        else {
                            RenderUtils.drawShadow(n4 - mntsb_18.getStringWidth(subModule2.getModule().getDisplayname()) + 1, n5 + n7 + 4, n4, n5 + n7 + 4, 9, Client.getColor());
                        }
                    }
                    mntsb_18.drawStringWithShadow(subModule2.getModule().getDisplayname(), n4 - mntsb_18.getStringWidth(subModule2.getModule().getDisplayname()), n5 + n7 + 2, ColorUtils.astolfoColorsCool(1, 80 + n7 / 3));
                    break;
                }
                case "Custom": {
                    if (ModuleList.rect.getVal()) {
                        if (!ModuleList.shadow.getVal()) {
                            RenderUtils.drawGradientRect(n4 - mntsb_18.getStringWidth(subModule2.getModule().getDisplayname()) - 3, n5 + n7, n4 + 4, n5 + n7 + 10, true, Client.getColor(), Client.getColor2());
                        }
                        else {
                            RenderUtils.drawShadow(n4 - mntsb_18.getStringWidth(subModule2.getModule().getDisplayname()) + 1, n5 + n7 + 4, n4, n5 + n7 + 4, 9, Client.getColor());
                        }
                    }
                    mntsb_18.drawStringWithShadow(subModule2.getModule().getDisplayname(), n4 - mntsb_18.getStringWidth(subModule2.getModule().getDisplayname()), n5 + n7 + 2, Client.textcolor());
                    break;
                }
            }
            GL11.glPopMatrix();
            n7 += subModule2.getY();
        }
    }
    
    @Override
    public void onEnable() {
        super.onEnable();
        final int n = GuiScreen.width - 3;
        this.modules.clear();
        final Iterator<Module> iterator = Client.moduleManager.modules.iterator();
        while (iterator.hasNext()) {
            this.modules.add(new SubModule(iterator.next()));
        }
    }
    
    static class SubModule
    {
        float anim;
        int y;
        Module module;
        
        public SubModule(final Module module) {
            this.y = 0;
            this.anim = 0.0f;
            this.module = module;
        }
        
        public float getAnim() {
            return this.anim;
        }
        
        public void setY(final int y) {
            this.y = y;
        }
        
        public Module getModule() {
            return this.module;
        }
        
        public int getY() {
            return this.y;
        }
        
        public void setAnim(final float anim) {
            this.anim = anim;
        }
    }
}
