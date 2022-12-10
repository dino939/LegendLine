package ru.hld.legendline.api.utils;

import net.minecraft.client.*;
import net.minecraft.entity.*;
import net.minecraft.client.entity.*;
import net.minecraft.entity.player.*;
import ru.hld.legendline.api.shit.*;
import ru.hld.legendline.impl.modules.Combat.*;
import java.util.*;
import net.minecraft.util.math.*;

public class CombatUtils
{
    public static Minecraft mc;
    
    public static Entity getTergetCircle(final boolean b, final float n) {
        final ArrayList<EntityPlayerSP> list = new ArrayList<EntityPlayerSP>();
        for (final Entity entity : CombatUtils.mc.world.loadedEntityList) {
            if (entity instanceof EntityPlayer && entity != CombatUtils.mc.player && !FriendManager.isFriend(entity.getName()) && !entity.isInvisible() && entity.height > 1.0f && ((canSeeEntityAtFov2(entity, AimBot.Fov.getVal() * 100.0f / 5.0f) && canSeeEntityAtFov(entity, AimBot.Fov.getVal() * 100.0f / 5.0f) && ((EntityPlayerSP)entity).canEntityBeSeen(CombatUtils.mc.player) && !b) || b)) {
                list.add((EntityPlayerSP)entity);
            }
        }
        list.sort(new Comparator() {
            @Override
            public int compare(final Object o, final Object o2) {
                return this.compare((Entity)o, (Entity)o2);
            }
            
            public int compare(final Entity entity, final Entity entity2) {
                return (int)(CombatUtils.mc.player.getDistanceToEntity(entity) - CombatUtils.mc.player.getDistanceToEntity(entity2));
            }
        });
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }
    
    public static double angleDifference(final double n, final double n2) {
        float n3 = (float)(Math.abs(n - n2) % 360.0);
        if (n3 > 180.0f) {
            n3 = 360.0f - n3;
        }
        return n3;
    }
    
    public static Entity getTergetBox(final boolean b, final float n) {
        final ArrayList<EntityPlayerSP> list = new ArrayList<EntityPlayerSP>();
        for (final Entity entity : CombatUtils.mc.world.loadedEntityList) {
            if (entity instanceof EntityPlayer && entity != CombatUtils.mc.player && !FriendManager.isFriend(entity.getName()) && !entity.isInvisible() && entity.height > 1.0f && ((canSeeEntityAtFov(entity, AimBot.Fov.getVal() * 100.0f / 5.0f) && ((EntityPlayerSP)entity).canEntityBeSeen(CombatUtils.mc.player) && !b) || b)) {
                list.add((EntityPlayerSP)entity);
            }
        }
        list.sort(new Comparator() {
            @Override
            public int compare(final Object o, final Object o2) {
                return this.compare((Entity)o, (Entity)o2);
            }
            
            public int compare(final Entity entity, final Entity entity2) {
                return (int)(CombatUtils.mc.player.getDistanceToEntity(entity) - CombatUtils.mc.player.getDistanceToEntity(entity2));
            }
        });
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }
    
    static {
        CombatUtils.mc = Minecraft.getMinecraft();
    }
    
    public static Entity getTerget(final boolean b) {
        final ArrayList<EntityPlayerSP> list = new ArrayList<EntityPlayerSP>();
        for (final Entity entity : CombatUtils.mc.world.loadedEntityList) {
            if (entity instanceof EntityPlayer && entity != CombatUtils.mc.player && !FriendManager.isFriend(entity.getName()) && !entity.isInvisible() && entity.height > 1.0f && ((((EntityPlayerSP)entity).canEntityBeSeen(CombatUtils.mc.player) && !b) || b)) {
                list.add((EntityPlayerSP)entity);
            }
        }
        list.sort(new Comparator() {
            public int compare(final Entity entity, final Entity entity2) {
                return (int)(CombatUtils.mc.player.getDistanceToEntity(entity) - CombatUtils.mc.player.getDistanceToEntity(entity2));
            }
            
            @Override
            public int compare(final Object o, final Object o2) {
                return this.compare((Entity)o, (Entity)o2);
            }
        });
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }
    
    public static boolean canSeeEntityAtFov2(final Entity entity, final float n) {
        final double n2 = entity.posX - CombatUtils.mc.player.posX;
        final double n3 = entity.posZ - CombatUtils.mc.player.posZ;
        final double n4 = entity.posY - CombatUtils.mc.player.posY;
        final float n5 = (float)(Math.toDegrees(Math.atan2(n3, n2)) - 90.0);
        return pitchDifference((float)(Math.toDegrees(n4) * 45.0), CombatUtils.mc.player.rotationPitch) <= n;
    }
    
    public static double pitchDifference(final double n, final double n2) {
        float n3 = (float)(Math.abs(n - n2) % 90.0);
        if (n3 > 45.0f) {
            n3 = 45.0f - n3;
        }
        return n3;
    }
    
    public static boolean canSeeEntityAtFov(final Entity entity, final float n) {
        final double n2 = entity.posX - CombatUtils.mc.player.posX;
        final double n3 = entity.posZ - CombatUtils.mc.player.posZ;
        final double n4 = entity.posY - CombatUtils.mc.player.posY;
        final float n5 = (float)(Math.toDegrees(Math.atan2(n3, n2)) - 90.0);
        final float n6 = (float)n4;
        return angleDifference(n5, CombatUtils.mc.player.rotationYaw) <= n;
    }
    
    public static float[] getNeededRotations(final double n, final double n2, final double n3, final float n4, final float n5, final float n6) {
        final double n7 = n - n4;
        final double n8 = n3 - n6;
        return new float[] { (float)(Math.atan2(n8, n7) * 180.0 / 3.141592653589793 - 90.0), MathHelper.clamp((float)(-(Math.atan2(n2 - (n5 + CombatUtils.mc.player.getEyeHeight()), MathHelper.sqrt(n7 * n7 + n8 * n8)) * 180.0 / 3.141592653589793)), -90.0f, 90.0f) };
    }
}
