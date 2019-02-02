package com.subwranglers.wickedarrows.entity;

import com.subwranglers.wickedarrows.base.EntityWArrow;
import com.subwranglers.wickedarrows.potion.PotionBrittleBones;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityMerlinArrow extends EntityWArrow {

    public static final float VERTICAL_VELOCITY = 0.9F;

    protected float firedVelocity;

    public EntityMerlinArrow(World worldIn) {
        super(worldIn);
        setup();
    }

    public EntityMerlinArrow(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
        setup();
    }

    public EntityMerlinArrow(World worldIn, EntityLivingBase shooter) {
        super(worldIn, shooter);
        setup();
    }

    private void setup() {
        pickupStatus = PickupStatus.CREATIVE_ONLY;
        setDamage(getDamage() / 2);
    }

    @Override
    public void shoot(double x, double y, double z, float velocity, float inaccuracy) {
        setKnockbackStrength((int) Math.ceil(velocity + 1) * 2);
        firedVelocity = velocity;
        super.shoot(x, y, z, velocity, inaccuracy);
    }

    @Override
    protected void arrowHit(EntityLivingBase living) {
        living.addVelocity(0, VERTICAL_VELOCITY, 0);
        PotionBrittleBones.apply(living, (int) Math.ceil(firedVelocity));
        setDead();
        spawnMerlinDrops(world, living.getPosition());
    }

    @Override
    protected void onBlockHit(RayTraceResult trace) {
        setDead();
        spawnMerlinDrops(world, trace.getBlockPos().offset(trace.sideHit));
    }

    private static void spawnMerlinDrops(World world, BlockPos pos) {
        Block.spawnAsEntity(world, pos, new ItemStack(Items.BUCKET));
        Block.spawnAsEntity(world, pos, new ItemStack(Items.SLIME_BALL, 6));
    }
}
