package com.subwranglers.wickedarrows.entity;

import com.subwranglers.wickedarrows.base.EntityWArrow;
import com.subwranglers.wickedarrows.item.ItemShotArr2w;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import java.util.Random;

public class EntityShotArrow extends EntityWArrow {
    public static final int MIN_ARROWS = 2;
    public static final int MAX_ARROWS = 9;

    private int numArrows = MIN_ARROWS;

    public EntityShotArrow(World worldIn) {
        super(worldIn);
    }

    public EntityShotArrow(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
    }

    public EntityShotArrow(World worldIn, EntityLivingBase shooter) {
        super(worldIn, shooter);
    }

    public EntityShotArrow setNumArrows(int numArrows) {
        this.numArrows = MathHelper.clamp(numArrows, MIN_ARROWS, MAX_ARROWS);
        return this;
    }

    @Override
    protected ItemStack getArrowStack() {
        return new ItemStack(ItemShotArr2w.INSTANCE);
    }

    private float velocityAdjustment(float velocity) {
        float ratio = 70.0f / 8.0f / 100; // 0.1125
        return velocity * ratio * numArrows;
    }

    private float inaccuracyAdjustment(float inaccuracy) {
        // Arrows have no chance of exploding until 4 or more in a bundle
        final int ARROWS_SAFE_MAX = 3;

        int increment = (20 / 5) * (numArrows - ARROWS_SAFE_MAX);
        int result = MathHelper.getInt(new Random(), 1, 100);

        System.out.println(String.format("%d/%d ? %b (result < chance boundary)", result, increment, result < increment));

        if (numArrows > ARROWS_SAFE_MAX &&  result <= increment)
            return 200.f;
        else
            return inaccuracy * numArrows * 1.75f;
    }

    @Override
    public void shoot(Entity shooter, float pitch, float yaw, float p_184547_4_, float velocity, float inaccuracy) {
        velocity = Math.abs(velocity - velocityAdjustment(velocity));
        inaccuracy = inaccuracyAdjustment(inaccuracy);

        System.out.println(String.format("V: %f, Arrows: %d", velocity, numArrows));

        super.shoot(shooter, pitch, yaw, p_184547_4_, velocity, inaccuracy);

        int arrows = numArrows - 1;
        while (arrows-- > 0) {
            World world = getEntityWorld();
            EntityTippedArrow arrow = new EntityTippedArrow(world, (EntityLivingBase) shooter);
            arrow.shoot(shooter, pitch + arrows, yaw + arrows, p_184547_4_, velocity, inaccuracy);
            arrow.setDamage(arrow.getDamage() * numArrows);

            world.spawnEntity(arrow);
        }
    }

//    @Override
//    public void shoot(double x, double y, double z, float velocity, float inaccuracy) {
//        velocity = velocityAdjustment(velocity);
//        int arrows = numArrows;
//        while (arrows-- > 0)
//            super.shoot(x, y, z, velocity, inaccuracy);
//    }
}
