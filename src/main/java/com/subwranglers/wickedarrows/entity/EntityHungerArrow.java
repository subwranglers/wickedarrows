package com.subwranglers.wickedarrows.entity;

import com.subwranglers.wickedarrows.HungerImpl;
import com.subwranglers.wickedarrows.base.EntityWArrow;
import com.subwranglers.wickedarrows.potion.PotionBait;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.FoodStats;
import net.minecraft.world.World;
import util.MCConst;

public class EntityHungerArrow extends EntityWArrow {

    protected float exhaustionDamage;
    protected float firedVelocityFactor;

    public static final int DURATION_HUNGER_TICKS = MCConst.TICKS_PER_SECOND * 10;

    public EntityHungerArrow(World worldIn) {
        super(worldIn);
        setup();
    }

    public EntityHungerArrow(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
        setup();
    }

    public EntityHungerArrow(World worldIn, EntityLivingBase shooter) {
        super(worldIn, shooter);
        setup();
    }

    private void setup() {
        exhaustionDamage = (float) getDamage();

        // Remove most physical damage from this arrow -- it'll mainly be affecting hunger instead.
        setDamage(0.01);

        // Players shouldn't be allowed to pick up hunger arrows after they're fired.
        pickupStatus = PickupStatus.CREATIVE_ONLY;
    }

    @Override
    public void shoot(double x, double y, double z, float velocity, float inaccuracy) {
        firedVelocityFactor = velocity * 10;
        super.shoot(x, y, z, velocity, inaccuracy);
    }

    @Override
    protected void arrowHit(EntityLivingBase living) {
        PotionBait.apply(living);

        // Apply a decent amount of exhaustion to the target
        if (living instanceof EntityPlayer) {
            EntityPlayer player = ((EntityPlayer) living);
            FoodStats stats = player.getFoodStats();

            stats.setFoodSaturationLevel(0.f); // Remove any saturation
            stats.addExhaustion(exhaustionDamage * firedVelocityFactor);

            player.addPotionEffect(new PotionEffect(MobEffects.HUNGER, DURATION_HUNGER_TICKS));
        }

        HungerImpl.getReadyZombiesSpidersNear(living).forEach(mob -> mob.setAttackTarget(living));
    }
}
