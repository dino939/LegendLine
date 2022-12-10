package ru.hld.legendline.impl.modules.Config;

import ru.hld.legendline.api.module.*;
import net.minecraft.client.gui.*;
import ru.hld.legendline.*;
import java.util.*;
import ru.hld.legendline.api.event.events.*;
import ru.hld.legendline.api.event.*;

public class Panic extends Module
{
    public Panic() {
        super("Panic", "Ghosted client", Category.Config);
    }
    
    @Override
    public void onEnable() {
        this.toggle();
        this.mc.displayGuiScreen(null);
        final Iterator<Module> iterator = Client.moduleManager.getModules(Category.Render).iterator();
        while (iterator.hasNext()) {
            iterator.next().setEnabled(this.isEnabled());
        }
        final Iterator<Module> iterator2 = Client.moduleManager.getModules(Category.Combat).iterator();
        while (iterator2.hasNext()) {
            iterator2.next().setEnabled(this.isEnabled());
        }
        final Iterator<Module> iterator3 = Client.moduleManager.getModules(Category.Config).iterator();
        while (iterator3.hasNext()) {
            iterator3.next().setEnabled(this.isEnabled());
        }
        final Iterator<Module> iterator4 = Client.moduleManager.getModules(Category.Misc).iterator();
        while (iterator4.hasNext()) {
            iterator4.next().setEnabled(this.isEnabled());
        }
        final Iterator<Module> iterator5 = Client.moduleManager.getModules(Category.Movement).iterator();
        while (iterator5.hasNext()) {
            iterator5.next().setEnabled(this.isEnabled());
        }
        final Iterator<Module> iterator6 = Client.moduleManager.getModules(Category.Player).iterator();
        while (iterator6.hasNext()) {
            iterator6.next().setEnabled(this.isEnabled());
        }
        this.mc.player.rotationYaw = 0.0f;
        this.mc.player.rotationPitch = 0.0f;
    }
    
    @EventTarget
    public void onUpdate(final EventUpdate eventUpdate) {
    }
}
