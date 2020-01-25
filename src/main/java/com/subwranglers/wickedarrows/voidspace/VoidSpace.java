package com.subwranglers.wickedarrows.voidspace;

import com.subwranglers.wickedarrows.potion.MobCapturedEffect;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;

public class VoidSpace implements IVoidSpace {

    private PlayerEntity owner = null;
    private CreatureEntity mob = null;
    private CompoundNBT entityInfo = null;
    private int captureDuration = 0;
    private long capturedStartTime = 0;

    public static int DAMAGE_INTERVAL = 20 * 5;
    public static final float MAX_VOID_HEALTH = 60.f;

    public VoidSpace setOwner(PlayerEntity player) {
        owner = player;
        return this;
    }

    @Override
    public boolean hasCapturedMob() {
        if(mob != null){
            if(mob.getEntityWorld().getGameTime() < capturedStartTime + captureDuration)
                return true;
            else
                mob = null; //mob has perished in the void
        }

        return false;
    }

    @Override
    public boolean captureMob(CreatureEntity entity) {
        if (entity == null || hasCapturedMob())
            return false;

        CompoundNBT compound = new CompoundNBT();
        if(entity.writeUnlessRemoved(compound))
            entityInfo = compound;
        else
            return false;

        capturedStartTime = entity.getEntityWorld().getGameTime();
        captureDuration = (int) getStartingVoidHealth(entity) * DAMAGE_INTERVAL;
        mob = entity;
        entity.remove();
        if(owner != null){
            MobCapturedEffect.setName(mob.getType().getTranslationKey());
            owner.addPotionEffect(new EffectInstance(MobCapturedEffect.INSTANCE, captureDuration));
        }

        return true;
    }

    @Override
    public CreatureEntity releaseCapturedMob() {
        if(hasCapturedMob()){
            CreatureEntity t = mob;
            mob = null;
            t.revive();
            if(owner != null)
                owner.removePotionEffect(MobCapturedEffect.INSTANCE);

            return t;
        }
        else return null;
    }

    private float getStartingVoidHealth(LivingEntity entity) {
        // Hardcoded numbers arbitrary -- they were found by fiddling around.
        return (100.f - Math.min(entity.getMaxHealth() * 3, MAX_VOID_HEALTH));
    }
}
