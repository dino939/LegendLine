package ru.hld.legendline.impl.modules.Misc;

import ru.hld.legendline.api.module.*;
import ru.hld.legendline.api.event.events.*;
import org.lwjgl.input.*;
import net.minecraft.entity.*;
import ru.hld.legendline.api.shit.*;
import ru.hld.legendline.api.event.*;

public class MCF extends Module
{
    public boolean onFriend;
    
    public MCF() {
        super("MCF", "add or remove player over your srosshair on Midddle Click on mouse", Category.Misc);
        this.onFriend = true;
    }
    
    @EventTarget
    public void onUpdate(final EventUpdate eventUpdate) {
        if (Mouse.isButtonDown(2) && this.mc.pointedEntity != null && this.mc.pointedEntity instanceof EntityLivingBase && this.onFriend) {
            this.onFriend = false;
            if (FriendManager.isFriend(this.mc.objectMouseOver.entityHit.getName())) {
                FriendManager.remove(this.mc.objectMouseOver.entityHit.getName());
            }
            else {
                FriendManager.add(this.mc.objectMouseOver.entityHit.getName());
            }
        }
        if (!Mouse.isButtonDown(2)) {
            this.onFriend = true;
        }
    }
}
