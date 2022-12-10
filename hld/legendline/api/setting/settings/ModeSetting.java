package ru.hld.legendline.api.setting.settings;

import ru.hld.legendline.api.setting.*;
import java.util.*;
import ru.hld.legendline.api.module.*;
import java.util.function.*;

public class ModeSetting extends Setting
{
    ArrayList modes;
    String val;
    
    public void setVal(final String val) {
        this.val = val;
    }
    
    public String getVal() {
        return this.val;
    }
    
    public ModeSetting(final String s, final String s2, final Module module, final ArrayList modes, final String val) {
        super(s, s2, module);
        this.modes = new ArrayList();
        this.modes = modes;
        this.val = val;
    }
    
    public ArrayList getModes() {
        return this.modes;
    }
    
    public ModeSetting(final String s, final String s2, final Module module, final ArrayList modes, final String val, final Supplier supplier) {
        super(s, s2, module, supplier);
        this.modes = new ArrayList();
        this.modes = modes;
        this.val = val;
    }
}
