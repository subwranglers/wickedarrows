package com.subwranglers.wickedarrows.entity;

import com.subwranglers.wickedarrows.base.EntityWArrow;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.init.Items;
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
        return new ItemStack(Items.ARROW);
    }

    private float velocityAdjustment(float velocity) {
        float ratio = 50.0f / 8.0f / 100; // 0.0625
        return velocity * ratio * numArrows;
    }

    private float inaccuracyAdjustment(float inaccuracy) {
        // Arrows have no chance of exploding until 4 or more in a bundle
        final int ARROWS_SAFE_MAX = 3;

        int increment = (20 / 5) * (numArrows - ARROWS_SAFE_MAX);
        int result = MathHelper.getInt(world.rand, 1, 100);

        if (numArrows > ARROWS_SAFE_MAX && result <= increment) {
            if (shootingEntity instanceof EntityLivingBase)
                // Essentially notify the player that the firing has "failed"
                ((EntityLivingBase) shootingEntity).renderBrokenItemStack(new ItemStack(Items.STRING));
            return 200.f;
        } else
            return inaccuracy * numArrows * 1.75f;
    }

    @Override
    public void shoot(Entity shooter, float pitch, float yaw, float p_184547_4_, float velocity, float inaccuracy) {
        velocity = Math.abs(velocity - velocityAdjustment(velocity));
        inaccuracy = inaccuracyAdjustment(inaccuracy);

        super.shoot(shooter, pitch, yaw, p_184547_4_, velocity, inaccuracy);

        int arrows = numArrows - 1;
        while (arrows-- > 0) {
            World world = getEntityWorld();
            EntityTippedArrow arrow = new EntityTippedArrow(world, (EntityLivingBase) shooter);
            arrow.shoot(shooter, pitch, yaw, p_184547_4_, velocity, inaccuracy);
            arrow.setDamage(arrow.getDamage() * numArrows);

            world.spawnEntity(arrow);
        }
    }
}
