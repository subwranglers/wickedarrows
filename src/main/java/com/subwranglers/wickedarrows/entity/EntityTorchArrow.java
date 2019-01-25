package com.subwranglers.wickedarrows.entity;

import com.subwranglers.wickedarrows.base.EntityWArrow;
import com.subwranglers.wickedarrows.block.BlockTorchArrow;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
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
        IBlockState torchArrowBlock = BlockTorchArrow.INSTANCE.getBlockState().getBaseState();

        torchArrowBlock = torchArrowBlock.withProperty(BlockTorchArrow.HIT_FACE, trace.sideHit);

        world.setBlockState(trace.getBlockPos().offset(trace.sideHit), torchArrowBlock);

        // Destroy this entity. The torch arrow gets "converted" into a block which gives reduced materials on harvest.
        setDead();
    }

    @Override
    protected void arrowHit(EntityLivingBase living) {
        living.setFire(3);
    }
}
