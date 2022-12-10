package ru.hld.legendline.api.setting.settings;

import ru.hld.legendline.api.setting.*;
import ru.hld.legendline.api.module.*;
import java.util.function.*;

public class FloatSetting extends Setting
{
    float increment;
    float val;
    float max;
    float min;
    
    public float getMin() {
        return this.min;
    }
    
    public FloatSetting(final String s, final String s2, final Module module, final float min, final float max, final float val, final float increment, final Supplier supplier) {
        super(s, s2, module, supplier);
        this.increment = increment;
        this.max = max;
        this.min = min;
        this.val = val;
    }
    
    public void setVal(final float val) {
        this.val = val;
    }
    
    public float getIncrement() {
        return this.increment;
    }
    
    public float getMax() {
        return this.max;
    }
    
    public float getVal() {
        return this.val;
    }
    
    public FloatSetting(final String s, final String s2, final Module module, final float min, final float max, final float val, final float increment) {
        super(s, s2, module);
        this.increment = increment;
        this.max = max;
        this.min = min;
        this.val = val;
    }
}
