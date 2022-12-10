package ru.hld.legendline.api.hudeditor;

import net.minecraft.client.gui.*;
import java.util.*;
import java.io.*;
import ru.hld.legendline.*;
import ru.hld.legendline.api.module.*;

public class HudEditor extends GuiScreen
{
    ArrayList hudComponents;
    
    @Override
    protected void mouseClicked(final int n, final int n2, final int n3) throws IOException {
        super.mouseClicked(n, n2, n3);
        final Iterator<HudComponent> iterator = this.hudComponents.iterator();
        while (iterator.hasNext()) {
            iterator.next().mouseClicked(n, n2, n3);
        }
    }
    
    @Override
    public void drawScreen(final int n, final int n2, final float n3) {
        super.drawScreen(n, n2, n3);
        final Iterator<HudComponent> iterator = this.hudComponents.iterator();
        while (iterator.hasNext()) {
            iterator.next().drawScreen(n, n2, n3);
        }
    }
    
    public HudEditor() {
        this.hudComponents = new ArrayList();
        for (final Module module : Client.moduleManager.modules) {
            if (module.isHud()) {
                this.hudComponents.add(new HudComponent(module));
            }
        }
    }
}
