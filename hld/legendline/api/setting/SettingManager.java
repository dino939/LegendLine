package ru.hld.legendline.api.setting;

import ru.hld.legendline.api.module.*;
import java.util.*;

public class SettingManager
{
    ArrayList settings;
    
    public ArrayList getSettings(final Module module) {
        final ArrayList<Setting> list = new ArrayList<Setting>();
        for (final Setting setting : this.settings) {
            if (setting.module == module) {
                list.add(setting);
            }
        }
        return list;
    }
    
    public SettingManager() {
        this.settings = new ArrayList();
    }
    
    public void add(final Setting setting) {
        this.settings.add(setting);
    }
}
