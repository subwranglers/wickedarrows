package com.subwranglers.wickedarrows.entity;

import com.subwranglers.wickedarrows.block.TorchArrowBlock;
import com.subwranglers.wickedarrows.instances.EntityTypes;
import com.subwranglers.wickedarrows.item.TorchArrowItem;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.DirectionalPlaceContext;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.world.World;

public class TorchArrowEntity extends WickedArrowEntity {

    public TorchArrowEntity(EntityType<? extends TorchArrowEntity> type, World worldIn) { super(type, worldIn); }

    public TorchArrowEntity(World worldIn, double x, double y, double z) {
        super(EntityTypes.TORCH_ARROW, worldIn, x, y, z);
    }

    public TorchArrowEntity(World worldIn, LivingEntity shooter) {
        super(EntityTypes.TORCH_ARROW, worldIn, shooter);
    }


    @Override
    protected ItemStack getArrowStack() {
        return new ItemStack(TorchArrowItem.INSTANCE);
    }

    @Override
    public boolean isBurning() {
        return false;
    }

    @Override
    protected void onBlockHit(BlockRayTraceResult trace) {
        if (!isAlive())
            return;

        // Destroy this entity. The BlockTorchArrow drops 1 ItemTorchArrow on harvest.
        this.remove();

        BlockPos fromSideHit = trace.getPos().offset(trace.getFace());

        BlockItemUseContext context = new DirectionalPlaceContext(
                world,
                fromSideHit,
                trace.getFace().getOpposite(),
                getArrowStack(),
                trace.getFace());

        if (context.canPlace())
            world.setBlockState(fromSideHit, TorchArrowBlock.applyToBlockFace(trace.getFace()));
        else
            TorchArrowBlock.spawnDrops(TorchArrowBlock.INSTANCE.getDefaultState(), world, fromSideHit);
    }

    @Override
    protected void onEntityHit(EntityRayTraceResult trace) {
        super.onEntityHit(trace);
        trace.getEntity().setFire(3);
    }
}
