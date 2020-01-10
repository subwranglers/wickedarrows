package com.subwranglers.wickedarrows.entity;

import com.subwranglers.wickedarrows.instances.EntityTypes;
import com.subwranglers.wickedarrows.potion.BrittleBonesEffect;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

public class MerlinArrowEntity extends WickedArrowEntity {

    public static final float VERTICAL_VELOCITY = 0.8F;

    protected float firedVelocity;

    public MerlinArrowEntity(EntityType<? extends MerlinArrowEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public MerlinArrowEntity(World worldIn, double x, double y, double z) {
        super(EntityTypes.MERLIN_ARROW, worldIn, x, y, z);
    }

    public MerlinArrowEntity(World worldIn, LivingEntity shooter) {
        super(EntityTypes.MERLIN_ARROW, worldIn, shooter);
    }

    {
        pickupStatus = PickupStatus.CREATIVE_ONLY;
        setDamage(getDamage() / 6);
    }

    @Override
    protected ItemStack getArrowStack() {
        return ItemStack.EMPTY;
    }

    @Override
    public void shoot(double x, double y, double z, float velocity, float inaccuracy) {
        setKnockbackStrength((int) Math.ceil(velocity + 1) * 3);
        firedVelocity = velocity;
        super.shoot(x, y, z, velocity, inaccuracy);
    }

    @Override
    protected void arrowHit(LivingEntity living) {
        living.addVelocity(0, VERTICAL_VELOCITY, 0);
        BrittleBonesEffect.apply(living, (int) Math.ceil(firedVelocity));
        remove();
        spawnMerlinDrops(world, living.getPosition());
    }

    @Override
    protected void onBlockHit(BlockRayTraceResult trace) {
        remove();
        spawnMerlinDrops(world, trace.getPos().offset(trace.getFace()));
    }

    private static void spawnMerlinDrops(World world, BlockPos pos) {
        Block.spawnAsEntity(world, pos, new ItemStack(Items.BUCKET));
        Block.spawnAsEntity(world, pos, new ItemStack(Items.SLIME_BALL, 6));
    }
}
