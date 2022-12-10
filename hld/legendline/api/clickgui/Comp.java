package ru.hld.legendline.api.clickgui;

import java.io.*;

public class Comp
{
    public boolean ishover(final float n, final float n2, final float n3, final float n4, final int n5, final int n6) {
        return n5 >= n && n5 <= n3 && n6 >= n2 && n6 <= n4;
    }
    
    public void initGui() {
    }
    
    public float getWidth() {
        return 100.0f;
    }
    
    public void drawScreen(final int n, final int n2, final int n3, final int n4, final float n5) {
    }
    
    public float getHeight() {
        return 10.0f;
    }
    
    public void reset() {
    }
    
    public void mouseClicked(final int n, final int n2, final int n3, final int n4, final int n5) throws IOException {
    }
}
