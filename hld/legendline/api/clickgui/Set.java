package ru.hld.legendline.api.clickgui;

import ru.hld.legendline.api.setting.*;
import java.io.*;

public class Set extends Comp
{
    Setting setting;
    public float anim;
    
    @Override
    public float getHeight() {
        return super.getHeight();
    }
    
    public Set(final Setting setting) {
        this.anim = 0.0f;
        this.setting = setting;
    }
    
    @Override
    public void drawScreen(final int n, final int n2, final int n3, final int n4, final float n5) {
        super.drawScreen(n, n2, n3, n4, n5);
    }
    
    public Setting getSetting() {
        return this.setting;
    }
    
    @Override
    public void mouseClicked(final int n, final int n2, final int n3, final int n4, final int n5) throws IOException {
        super.mouseClicked(n, n2, n3, n4, n5);
    }
    
    @Override
    public void reset() {
        super.reset();
    }
}
