package com.subwranglers.wickedarrows.entity;

import com.subwranglers.wickedarrows.base.EntityWArrow;
import com.subwranglers.wickedarrows.potion.PotionBleed;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class EntitySharpArrow extends EntityWArrow {

    private int firedVelocity;

    /**
     * Prevent the game from destroying this entity until it hits a block.
     */
    private boolean canDie = false;

    public EntitySharpArrow(World worldIn) {
        super(worldIn);
        setup();
    }

    public EntitySharpArrow(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
        setup();
    }

    public EntitySharpArrow(World worldIn, EntityLivingBase shooter) {
        super(worldIn, shooter);
        setup();
    }

    private void setup() {
        noClip = true;
        pickupStatus = PickupStatus.CREATIVE_ONLY;
        setKnockbackStrength(-1);
    }

    @Nullable
    @Override
    public AxisAlignedBB getCollisionBoundingBox() {
        return null;
    }

    @Override
    public void shoot(double x, double y, double z, float velocity, float inaccuracy) {
        super.shoot(x, y, z, velocity, inaccuracy);
        firedVelocity = velocity < 1 ? 1 : (int) velocity;
    }

    @Override
    protected void onBlockHit(RayTraceResult trace) {
        canDie = true;
        BlockPos hit = trace.getBlockPos();

        if (world.getBlockState(hit).getBlock() == Blocks.TNT) {
            world.setBlockToAir(hit);
            Block.spawnAsEntity(world, hit, new ItemStack(Blocks.TNT));
            setDead();
        }
    }

    @Override
    protected void arrowHit(EntityLivingBase living) {
        PotionBleed.apply(living, firedVelocity);
    }

    @Override
    public void setDead() {
        if (canDie)
            super.setDead();
    }
}
