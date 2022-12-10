package ru.hld.legendline.api.setting.settings;

import ru.hld.legendline.api.setting.*;
import ru.hld.legendline.api.module.*;
import java.util.function.*;

public class BooleanSetting extends Setting
{
    boolean val;
    
    public BooleanSetting(final String s, final String s2, final Module module, final boolean val, final Supplier supplier) {
        super(s, s2, module, supplier);
        this.val = val;
    }
    
    public void setVal(final boolean val) {
        this.val = val;
    }
    
    public BooleanSetting(final String s, final String s2, final Module module, final boolean val) {
        super(s, s2, module);
        this.val = val;
    }
    
    public boolean getVal() {
        return this.val;
    }
}
