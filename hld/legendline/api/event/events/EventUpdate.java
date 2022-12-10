package ru.hld.legendline.api.event.events;

import ru.hld.legendline.api.event.*;

public class EventUpdate extends Event
{
    double posZ;
    double posY;
    float rotationYaw;
    boolean onGround;
    double posX;
    float rotationPitch;
    
    public double getPosY() {
        return this.posY;
    }
    
    public void setPosY(final double posY) {
        this.posY = posY;
    }
    
    public void setPosZ(final double posZ) {
        this.posZ = posZ;
    }
    
    public void setOnGround(final boolean onGround) {
        this.onGround = onGround;
    }
    
    public void setRotationPitch(final float rotationPitch) {
        this.rotationPitch = rotationPitch;
    }
    
    public double getPosX() {
        return this.posX;
    }
    
    public double getPosZ() {
        return this.posZ;
    }
    
    public float getRotationYaw() {
        return this.rotationYaw;
    }
    
    public void setPosX(final double posX) {
        this.posX = posX;
    }
    
    public void setRotationYaw(final float rotationYaw) {
        this.rotationYaw = rotationYaw;
    }
    
    public EventUpdate(final double posX, final double posY, final double posZ, final float rotationYaw, final float rotationPitch, final boolean onGround) {
        this.posX = posX;
        this.posY = posY;
        this.posZ = posZ;
        this.rotationYaw = rotationYaw;
        this.rotationPitch = rotationPitch;
        this.onGround = onGround;
    }
    
    public float getRotationPitch() {
        return this.rotationPitch;
    }
    
    public boolean isOnGround() {
        return this.onGround;
    }
}
