package ru.hld.legendline.api.utils;

public class PlayerHook
{
    boolean onGround;
    float pitch;
    float yaw;
    double x;
    double z;
    double y;
    
    public double getY() {
        return this.y;
    }
    
    public PlayerHook(final double x, final double y, final double z, final float yaw, final float pitch, final boolean onGround) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
        this.onGround = onGround;
    }
    
    public double getX() {
        return this.x;
    }
    
    public double getZ() {
        return this.z;
    }
    
    public void setX(final double x) {
        this.x = x;
    }
    
    public void setY(final double y) {
        this.y = y;
    }
    
    public void setYaw(final float yaw) {
        this.yaw = yaw;
    }
    
    public boolean isOnGround() {
        return this.onGround;
    }
    
    public float getPitch() {
        return this.pitch;
    }
    
    public float getYaw() {
        return this.yaw;
    }
    
    public void setOnGround(final boolean onGround) {
        this.onGround = onGround;
    }
    
    public void setZ(final double z) {
        this.z = z;
    }
    
    public void setPitch(final float pitch) {
        this.pitch = pitch;
    }
}
