package com.subwranglers.wickedarrows.entity;

import com.subwranglers.wickedarrows.base.EntityWArrow;
import com.subwranglers.wickedarrows.block.BlockTorchArrow;
import com.subwranglers.wickedarrows.item.ItemTorchArrow;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityTorchArrow extends EntityWArrow {

    public EntityTorchArrow(World worldIn) {
        super(worldIn);
    }

    public EntityTorchArrow(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
    }

    public EntityTorchArrow(World worldIn, EntityLivingBase shooter) {
        super(worldIn, shooter);
    }

    @Override
    public boolean isBurning() {
        return false;
    }

    @Override
    protected void onBlockHit(RayTraceResult trace) {
        if (isDead)
            return;

        // Destroy this entity. The BlockTorchArrow drops 1 ItemTorchArrow on harvest.
        setDead();

        boolean canPlace = world.mayPlace(
                BlockTorchArrow.INSTANCE,
                trace.getBlockPos().offset(trace.sideHit),
                true,
                trace.sideHit,
                shootingEntity
        );
        BlockPos fromSideHit = trace.getBlockPos().offset(trace.sideHit);

        if (canPlace)
            world.setBlockState(fromSideHit, BlockTorchArrow.applyToBlockFace(trace.sideHit));
        else
            Block.spawnAsEntity(world, fromSideHit, new ItemStack(ItemTorchArrow.INSTANCE));
    }

    @Override
    protected void arrowHit(EntityLivingBase living) {
        living.setFire(3);
    }
}
