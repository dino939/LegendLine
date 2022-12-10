package ru.hld.legendline.api.module;

import java.util.*;
import ru.hld.legendline.impl.modules.Player.*;
import ru.hld.legendline.impl.modules.Misc.*;
import ru.hld.legendline.impl.modules.movment.*;
import ru.hld.legendline.impl.modules.Render.*;
import ru.hld.legendline.impl.modules.Combat.*;
import ru.hld.legendline.impl.modules.Config.*;

public class ModuleManager
{
    public ArrayList modules;
    
    public Module getModule(final String s) {
        for (final Module module : this.modules) {
            if (module.getName().equalsIgnoreCase(s)) {
                return module;
            }
        }
        return null;
    }
    
    public ArrayList getModules(final Category category) {
        final ArrayList<Module> list = new ArrayList<Module>();
        for (final Module module : this.modules) {
            if (module.getCategory() == category) {
                list.add(module);
            }
        }
        return list;
    }
    
    public ModuleManager() {
        (this.modules = new ArrayList()).add(new ClickGui());
        this.modules.add(new ClientHud());
        this.modules.add(new AntiBot());
        this.modules.add(new WaterMark());
        this.modules.add(new ModuleList());
        this.modules.add(new AutoSprint());
        this.modules.add(new NameTags());
        this.modules.add(new ESP());
        this.modules.add(new AirShoot());
        this.modules.add(new DeathCords());
        this.modules.add(new Tracers());
        this.modules.add(new AimBot());
        this.modules.add(new TargetHud());
        this.modules.add(new MCF());
        this.modules.add(new WallHack());
        this.modules.add(new NoHurtCam());
        this.modules.add(new Disabler());
        this.modules.add(new FullBright());
        this.modules.add(new GuiWalk());
        this.modules.add(new NoSlowDown());
        this.modules.add(new StorageESP());
        this.modules.add(new CameraClip());
        this.modules.add(new ItemPhysics());
        this.modules.add(new Freecam());
        this.modules.add(new Notifications());
        this.modules.add(new Strafe());
        this.modules.add(new Jesus());
        this.modules.add(new DamageColor());
        this.modules.add(new Velocity());
        this.modules.add(new SaveConfig());
        this.modules.add(new LoadConfig());
    }
}
