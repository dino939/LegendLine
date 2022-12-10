package ru.hld.legendline.api.setting;

import java.util.function.*;
import ru.hld.legendline.api.module.*;

public class Setting
{
    String name;
    Supplier visible;
    String descriptions;
    int color;
    Module module;
    
    public Setting(final String name, final int color, final String descriptions, final Module module, final Supplier visible) {
        this.name = name;
        this.descriptions = descriptions;
        this.visible = visible;
        this.module = module;
        this.color = color;
    }
    
    public Supplier getVisible() {
        return this.visible;
    }
    
    public Module getModule() {
        return this.module;
    }
    
    public Setting(final String name, final String descriptions, final Module module, final Supplier visible) {
        this.name = name;
        this.descriptions = descriptions;
        this.visible = visible;
        this.module = module;
    }
    
    public String getDescriptions() {
        return this.descriptions;
    }
    
    private static Boolean lambda$new$0() {
        return true;
    }
    
    public Setting(final String name, final String descriptions, final Module module) {
        this.name = name;
        this.descriptions = descriptions;
        this.visible = Setting::lambda$new$0;
        this.module = module;
    }
    
    public boolean isVisible() {
        return this.visible.get();
    }
    
    public String getName() {
        return this.name;
    }
}
