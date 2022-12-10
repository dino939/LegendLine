package ru.hld.legendline.api.event;

public class PlayerHook
{
    double y;
    boolean onGround;
    float yaw;
    double x;
    double z;
    float pitch;
    
    public void setYaw(final float yaw) {
        this.yaw = yaw;
    }
    
    public double getX() {
        return this.x;
    }
    
    public float getPitch() {
        return this.pitch;
    }
    
    public void setX(final double x) {
        this.x = x;
    }
    
    public double getZ() {
        return this.z;
    }
    
    public float getYaw() {
        return this.yaw;
    }
    
    public boolean isOnGround() {
        return this.onGround;
    }
    
    public void setOnGround(final boolean onGround) {
        this.onGround = onGround;
    }
    
    public void setPitch(final float pitch) {
        this.pitch = pitch;
    }
    
    public PlayerHook(final double x, final double y, final double z, final float yaw, final float pitch, final boolean onGround) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
        this.onGround = onGround;
    }
    
    public void setY(final double y) {
        this.y = y;
    }
    
    public double getY() {
        return this.y;
    }
    
    public void setZ(final double z) {
        this.z = z;
    }
}
