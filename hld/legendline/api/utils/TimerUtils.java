package ru.hld.legendline.api.utils;

public class TimerUtils
{
    long mc;
    
    public void reset() {
        this.mc = System.currentTimeMillis();
    }
    
    public long getMc() {
        return System.currentTimeMillis() - this.mc;
    }
    
    public boolean hasReached(final long n) {
        return System.currentTimeMillis() - this.mc > n;
    }
    
    public TimerUtils() {
        this.mc = System.currentTimeMillis();
    }
}
