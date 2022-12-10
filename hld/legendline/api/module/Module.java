package ru.hld.legendline.api.module;

import net.minecraft.client.*;
import ru.hld.legendline.*;
import ru.hld.legendline.api.notification.*;
import ru.hld.legendline.api.utils.*;

public class Module
{
    String displayname;
    int sizeX;
    int key;
    Category category;
    String name;
    protected Minecraft mc;
    int posX;
    boolean enabled;
    int posY;
    int sizeY;
    public CFontRenderer fr;
    String descriptions;
    
    public void setKey(final int key) {
        this.key = key;
    }
    
    public void setSizeY(final int sizeY) {
        this.sizeY = sizeY;
    }
    
    public String getDisplayname() {
        return this.displayname;
    }
    
    public Category getCategory() {
        return this.category;
    }
    
    public void setDisplayname(final String displayname) {
        this.displayname = displayname;
    }
    
    public void toggle() {
        this.setEnabled(!this.isEnabled());
    }
    
    public void onEnable() {
        Client.eventManager.register(this);
        Client.notificationsManager.add(new Notification(this.name, "K"));
    }
    
    public Module(final String s, final String descriptions, final Category category, final int sizeX, final int sizeY) {
        this.key = 0;
        this.mc = Minecraft.getMinecraft();
        this.fr = Fonts.mntsb_20;
        this.category = category;
        this.name = s;
        this.descriptions = descriptions;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.displayname = s;
    }
    
    public void setPosX(final int posX) {
        this.posX = posX;
    }
    
    public void setDescriptions(final String descriptions) {
        this.descriptions = descriptions;
    }
    
    public int getPosY() {
        return this.posY;
    }
    
    public int getKey() {
        return this.key;
    }
    
    public boolean isEnabled() {
        return this.enabled;
    }
    
    public int getSizeX() {
        return this.sizeX;
    }
    
    public void setSizeX(final int sizeX) {
        this.sizeX = sizeX;
    }
    
    public void setPosY(final int posY) {
        this.posY = posY;
    }
    
    public void onDisable() {
        Client.eventManager.unregister(this);
        Client.notificationsManager.add(new Notification(this.name, "I"));
    }
    
    public String getName() {
        return this.name;
    }
    
    public Module(final String s, final String descriptions, final Category category) {
        this.key = 0;
        this.mc = Minecraft.getMinecraft();
        this.fr = Fonts.mntsb_20;
        this.category = category;
        this.name = s;
        this.descriptions = descriptions;
        this.displayname = s;
    }
    
    public void onUpdate(final PlayerHook playerHook) {
    }
    
    public int getPosX() {
        return this.posX;
    }
    
    public boolean isHud() {
        return this.sizeY != 0 || this.sizeX != 0;
    }
    
    public void setEnabled(final boolean enabled) {
        this.displayname = this.name;
        this.enabled = enabled;
        if (enabled) {
            this.onEnable();
        }
        else {
            this.onDisable();
        }
    }
    
    public String getDescriptions() {
        return this.descriptions;
    }
    
    public int getSizeY() {
        return this.sizeY;
    }
}
