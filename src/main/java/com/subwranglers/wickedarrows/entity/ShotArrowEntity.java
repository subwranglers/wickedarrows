package com.subwranglers.wickedarrows.entity;

import com.subwranglers.wickedarrows.instances.EntityTypes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.Map;

public class ShotArrowEntity extends WickedArrowEntity {
    public static final int MIN_ARROWS = 2;
    public static final int MAX_ARROWS = 9;

    private int arrowAmount = MIN_ARROWS;
    private Map<Entity, Double> cumulativeDamageMap = new HashMap<>();

    public ShotArrowEntity(EntityType<? extends ShotArrowEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public ShotArrowEntity(World worldIn, double x, double y, double z) {
        super(EntityTypes.SHOT_ARROW, worldIn, x, y, z);
    }

    public ShotArrowEntity(World worldIn, LivingEntity shooter) {
        super(EntityTypes.SHOT_ARROW, worldIn, shooter);
    }

    public ShotArrowEntity setArrowAmount(int arrowAmount) {
        this.arrowAmount = MathHelper.clamp(arrowAmount, MIN_ARROWS, MAX_ARROWS);
        return this;
    }

    @Override
    protected ItemStack getArrowStack() {
        return new ItemStack(Items.ARROW);
    }

    private float velocityAdjustment(float velocity) {
        float ratio = 50.0f / 8.0f / 100; // 0.0625
        return velocity * ratio * arrowAmount;
    }

    private float inaccuracyAdjustment(float inaccuracy) {
        // Arrows have no chance of exploding until 4 or more in a bundle
        final int ARROWS_SAFE_MAX = 3;

        int increment = (20 / 5) * (arrowAmount - ARROWS_SAFE_MAX);
        int result = MathHelper.nextInt(world.rand, 1, 100);

        if (arrowAmount > ARROWS_SAFE_MAX && result <= increment) {
            if (getShooter() instanceof LivingEntity){
                // Essentially notify the player that the firing has "failed"
                //((LivingEntity) getShooter()).renderBrokenItemStack(new ItemStack(Items.STRING));

                // Daniel 2020-01-11: Entity.renderBrokenItemStack is private now.
                // This isn't a perfect replacement, but I think it's okay.
                ((LivingEntity) getShooter()).sendBreakAnimation(Hand.MAIN_HAND);
                getShooter().playSound(SoundEvents.ENTITY_ITEM_BREAK, 0.8F, 0.8F + this.world.rand.nextFloat() * 0.4F);
            }
            return 200.f;
        } else
            return inaccuracy * arrowAmount * 1.75f;
    }

    @Override
    public void shoot(Entity shooter, float pitch, float yaw, float p_184547_4_, float velocity, float inaccuracy) {
        boolean isCritical = velocity == 3.0f;
        velocity = Math.abs(velocity - velocityAdjustment(velocity));
        inaccuracy = inaccuracyAdjustment(inaccuracy);

        super.shoot(shooter, pitch, yaw, p_184547_4_, velocity, inaccuracy);

        int arrows = arrowAmount - 1;
        while (arrows-- > 0) {
            ArrowEntity arrow = new ChildArrowEntity(world, (LivingEntity) shooter);
            arrow.shoot(shooter, pitch, yaw, p_184547_4_, velocity, inaccuracy);
            arrow.setIsCritical(isCritical);
            world.addEntity(arrow);
        }
    }

    @Override
    protected void onEntityHit(EntityRayTraceResult trace) {
        if(!world.isRemote){
            double damage = cumulativeDamageMap.merge(
                    trace.getEntity(),
                    this.getDamage(),
                    Double::sum);
            this.setDamage(damage);
        }
        super.onEntityHit(trace);
    }

    private class ChildArrowEntity extends ArrowEntity {

        ChildArrowEntity(World worldIn, LivingEntity shooter) {
            super(worldIn, shooter);
        }

        @Override
        protected void onEntityHit(EntityRayTraceResult trace) {
            if(!world.isRemote) {
                trace.getEntity();
                double damage = cumulativeDamageMap.merge(
                        trace.getEntity(),
                        this.getDamage(),
                        Double::sum);
                this.setDamage(damage);
            }
            super.onEntityHit(trace);
        }
    }
}
