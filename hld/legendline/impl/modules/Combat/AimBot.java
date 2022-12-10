package ru.hld.legendline.impl.modules.Combat;

import ru.hld.legendline.api.setting.settings.*;
import net.minecraft.network.play.server.*;
import ru.hld.legendline.api.event.*;
import ru.hld.legendline.api.module.*;
import ru.hld.legendline.*;
import ru.hld.legendline.api.setting.*;
import java.util.function.*;
import net.minecraft.client.gui.*;
import ru.hld.legendline.antiNative.*;
import ru.hld.legendline.api.event.events.*;
import net.minecraft.util.text.*;
import net.minecraft.client.network.*;
import java.util.*;
import ru.hld.legendline.api.utils.*;
import net.minecraft.item.*;
import net.minecraft.init.*;
import net.minecraft.util.*;
import net.minecraft.entity.player.*;
import net.minecraft.entity.*;

public class AimBot extends Module
{
    public static boolean using;
    float diff2;
    TimerUtils timer;
    float diff;
    BooleanSetting selfPredict;
    public static FloatSetting Fov;
    float kal;
    BooleanSetting swap;
    BooleanSetting AutoShoot;
    BooleanSetting slient;
    public static BooleanSetting mods;
    TimerUtils autoPredictTimer;
    ModeSetting mode;
    FloatSetting minCps;
    FloatSetting maxCps;
    public static BooleanSetting walls;
    BooleanSetting autoPredict;
    FloatSetting predict;
    
    private static Object lambda$new$0() {
        return AimBot.mods.getVal();
    }
    
    private Object lambda$new$4() {
        return this.AutoShoot.getVal();
    }
    
    @EventTarget
    public void onUpPacketRecent(final EventPacketReceive eventPacketReceive) {
        if (eventPacketReceive.getPacket() instanceof SPacketChat) {
            final SPacketChat sPacketChat = (SPacketChat)eventPacketReceive.getPacket();
        }
    }
    
    private Object lambda$new$3() {
        return this.AutoShoot.getVal();
    }
    
    public AimBot() {
        super("AimBot", "auto aim on players", Category.Combat);
        this.diff = 0.0f;
        this.diff2 = 0.0f;
        final ArrayList<String> list = new ArrayList<String>();
        list.add("Rect");
        list.add("Circle");
        list.add("Robot");
        Client.settingManager.add(this.slient = new BooleanSetting("Silent", "aim with out client rotation", this, true));
        Client.settingManager.add(this.predict = new FloatSetting("Predict", "increment of the enemy's new position", this, 0.0f, 10.0f, 5.1f, 0.1f));
        Client.settingManager.add(AimBot.mods = new BooleanSetting("Fov", "Fov", this, true));
        Client.settingManager.add(AimBot.Fov = new FloatSetting("Fov Scale", "Fov", this, 0.0f, 5.0f, 1.5f, 0.1f, AimBot::lambda$new$0));
        Client.settingManager.add(this.mode = new ModeSetting("Fov Mode", "Fov", this, list, "Rect", AimBot::lambda$new$1));
        Client.settingManager.add(this.autoPredict = new BooleanSetting("AutoPredict", "auto set predict by ping", this, false));
        Client.settingManager.add(this.selfPredict = new BooleanSetting("SelfPredict", "youse metod predict for you", this, false));
        Client.settingManager.add(AimBot.walls = new BooleanSetting("Walls", "aiming through walls", this, false));
        Client.settingManager.add(this.AutoShoot = new BooleanSetting("AutoShoot", "auto shooting if you aimin on target", this, true));
        Client.settingManager.add(this.slient = new BooleanSetting("AutoSwap", "auto swap to gun", this, true, this::lambda$new$2));
        Client.settingManager.add(this.minCps = new FloatSetting("MinCps", "min Cps to AutoShoot", this, 0.0f, 20.0f, 16.0f, 1.0f, this::lambda$new$3));
        Client.settingManager.add(this.maxCps = new FloatSetting("MaxCps", "max Cps to AutoShoot", this, 0.0f, 20.0f, 16.0f, 1.0f, this::lambda$new$4));
        this.timer = new TimerUtils();
        this.autoPredictTimer = new TimerUtils();
    }
    
    private Object lambda$new$2() {
        return this.AutoShoot.getVal();
    }
    
    @EventTarget
    public void onRender2D(final EventRender2D eventRender2D) {
        this.kal = AimBot.Fov.getVal() * 90.0f;
        final ScaledResolution scaledResolution = new ScaledResolution(this.mc);
        if (AimBot.mods.getVal()) {
            final String val = this.mode.getVal();
            switch (val) {
                case "Circle": {
                    RenderUtil.drawCircle228((float)(scaledResolution.getScaledWidth() / 2), (float)(scaledResolution.getScaledHeight() / 2), this.kal, Client.getColor(), 360);
                    break;
                }
                case "Rect": {
                    RenderUtils.draw2lines((float)(scaledResolution.getScaledWidth() / 2), (float)(scaledResolution.getScaledHeight() / 2), Client.getColor(), this.kal);
                    break;
                }
                case "Robot": {
                    RenderUtils.drawRobotgui((float)(scaledResolution.getScaledWidth() / 2), (float)(scaledResolution.getScaledHeight() / 2), Client.getColor(), Client.getColor2(), this.kal);
                    break;
                }
            }
        }
    }
    
    private static Object lambda$new$1() {
        return AimBot.mods.getVal();
    }
    
    @EventTarget
    public void onUpdate(final EventUpdate eventUpdate) {
        this.setDisplayname(String.valueOf(new StringBuilder().append(TextFormatting.WHITE).append("").append(this.predict.getVal()).append(" ").append(TextFormatting.RESET).append(this.getName()).append("")));
        if (this.autoPredict.getVal()) {
            this.predict.setVal(MathUtils.clamp((int)MathUtils.clamp((float)Objects.requireNonNull(this.mc.getConnection()).getPlayerInfo(this.mc.player.getUniqueID()).getResponseTime(), 1.0f, 300.0f) * 6.5f / 100.0f, 0.0f, 10.0f));
        }
        Entity entity = CombatUtils.getTerget(AimBot.walls.getVal());
        if (!AimBot.mods.getVal()) {
            entity = CombatUtils.getTerget(AimBot.walls.getVal());
        }
        else {
            final String val = this.mode.getVal();
            switch (val) {
                case "Rect": {
                    entity = CombatUtils.getTergetBox(AimBot.walls.getVal(), AimBot.Fov.getVal() * 100.0f / 5.0f);
                    break;
                }
                case "Circle": {
                    entity = CombatUtils.getTergetCircle(AimBot.walls.getVal(), AimBot.Fov.getVal() * 100.0f / 5.0f);
                    break;
                }
                case "Robot": {
                    entity = CombatUtils.getTergetCircle(AimBot.walls.getVal(), AimBot.Fov.getVal() * 100.0f / 5.0f);
                    break;
                }
            }
        }
        if (entity == null) {
            AimBot.using = false;
            return;
        }
        AimBot.using = true;
        final double n2 = entity.posX - entity.lastTickPosX;
        final double n3 = entity.posY - entity.lastTickPosY;
        final double n4 = entity.posZ - entity.lastTickPosZ;
        final double n5 = entity.lastTickPosX + n2 * this.predict.getVal();
        final double n6 = entity.lastTickPosZ + n4 * this.predict.getVal();
        final double n7 = entity.posY + (entity.getEyeHeight() + 0.1f);
        final double n8 = this.mc.player.posX - this.mc.player.lastTickPosX;
        final double n9 = this.mc.player.posZ - this.mc.player.lastTickPosZ;
        double posX = this.mc.player.posX;
        double posZ = this.mc.player.posZ;
        if (this.selfPredict.getVal()) {
            posX = this.mc.player.lastTickPosX + n2 * (this.predict.getVal() / 4.0f);
            posZ = this.mc.player.lastTickPosZ + n4 * (this.predict.getVal() / 4.0f);
        }
        final float[] neededRotations = CombatUtils.getNeededRotations((float)n5, (float)n7, (float)n6, (float)posX, (float)this.mc.player.posY, (float)posZ);
        this.kal = AimBot.Fov.getVal() * 100.0f;
        final float kal = this.kal;
        if (this.slient.getVal()) {
            final float lastReportedYaw = this.mc.player.lastReportedYaw;
            final float lastReportedPitch = this.mc.player.lastReportedPitch;
            final float lerp = MathUtils.lerp(lastReportedYaw, neededRotations[0], 1.0f);
            final float lerp2 = MathUtils.lerp(lastReportedPitch, neededRotations[1], 1.0f);
            eventUpdate.setRotationYaw(lerp);
            eventUpdate.setRotationPitch(lerp2);
            this.mc.player.renderYawOffset = lerp;
            this.mc.player.rotationYawHead = lerp;
            this.mc.player.rotationPitchHead = lerp2;
            this.diff = Math.abs(lerp - neededRotations[0]);
            this.diff2 = Math.abs(lerp2 - neededRotations[1]);
        }
        else {
            this.mc.player.rotationYaw = MathUtils.lerp(this.mc.player.rotationYaw, neededRotations[0], 1.0f);
            this.mc.player.rotationPitch = MathUtils.lerp(this.mc.player.rotationPitch, neededRotations[1], 1.0f);
            this.diff = Math.abs(this.mc.player.rotationYaw - neededRotations[0]);
            this.diff2 = Math.abs(this.mc.player.rotationPitch - neededRotations[1]);
        }
        int n10 = 0;
        int i = 0;
        while (i < 45) {
            if (Item.getIdFromItem(this.mc.player.inventoryContainer.getSlot(i).getStack().getItem()) == 278) {
                n10 = i;
            }
            final int n11 = 4;
            final int n12 = 4;
            ++i;
            if (n11 != n12) {
                return;
            }
        }
        if (this.AutoShoot.getVal() && this.mc.player.getCooldownTracker().getCooldown(Items.DIAMOND_PICKAXE, this.mc.getRenderPartialTicks()) != 1.0f) {
            if (this.timer.hasReached((long)MathUtils.getRandomInRange(1000.0f / this.maxCps.getVal(), 1000.0f / this.minCps.getVal())) && this.diff < 1.0f && this.diff2 < 1.0f) {
                if (this.swap.getVal() && this.mc.player.getCooldownTracker().getCooldown(Items.DIAMOND_PICKAXE, this.mc.getRenderPartialTicks()) != 1.0f && n10 > 35 && n10 < 47) {
                    this.mc.player.inventory.currentItem = n10 - 36;
                }
                this.mc.player.swingArm(EnumHand.MAIN_HAND);
                if (this.mc.player.getDistanceToEntity(entity) <= 4.0f) {
                    if (this.swap.getVal() && this.mc.player.getCooldownTracker().getCooldown(Items.DIAMOND_PICKAXE, this.mc.getRenderPartialTicks()) != 1.0f && n10 > 35 && n10 < 47) {
                        this.mc.player.inventory.currentItem = n10 - 36;
                    }
                    this.mc.playerController.attackEntity(this.mc.player, entity);
                }
            }
            this.timer.reset();
        }
    }
    
    static {
        AimBot.using = false;
    }
}
