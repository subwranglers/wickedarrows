package com.subwranglers.wickedarrows.block;

import com.subwranglers.wickedarrows.WickedArrows;
import com.subwranglers.wickedarrows.client.sound.IceCrackleSoundEvent;
import com.subwranglers.wickedarrows.info.Names;
import com.subwranglers.wickedarrows.potion.IceEffect;
import net.minecraft.block.BlockState;
import net.minecraft.block.FrostedIceBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

public class InvokedIceBlock extends FrostedIceBlock {

    public static final int LIGHT_LEVEL = 15;

    public static final InvokedIceBlock INSTANCE = new InvokedIceBlock();

    protected InvokedIceBlock() {
        super(Properties.create(Material.ICE)
                .slipperiness(0.98F)
                .tickRandomly() //?
                .hardnessAndResistance(1.2f)
                .sound(SoundType.GLASS)
                .lightValue(LIGHT_LEVEL));

        setRegistryName(WickedArrows.MOD_ID, Names.INVOKED_ICE);
    }

    @Override
    public void harvestBlock(World worldIn, PlayerEntity player, BlockPos pos, BlockState state, @Nullable TileEntity te, ItemStack stack) {
        // No-Op. Block should give nothing, because it was created "magically."
    }

    @Override
    public void tick(BlockState state, World worldIn, BlockPos pos, Random random) {
        if ((random.nextInt(3) == 0 || this.shouldMelt(worldIn, pos, 4)) && worldIn.getLight(pos) > 11 - state.get(AGE) - state.getOpacity(worldIn, pos) && this.slightlyMelt(state, worldIn, pos)) {
            try (BlockPos.PooledMutableBlockPos blockpos$pooledmutableblockpos = BlockPos.PooledMutableBlockPos.retain()) {
                for(Direction direction : Direction.values()) {
                    blockpos$pooledmutableblockpos.setPos(pos).move(direction);
                    BlockState blockstate = worldIn.getBlockState(blockpos$pooledmutableblockpos);
                    if (blockstate.getBlock() == this && !this.slightlyMelt(blockstate, worldIn, blockpos$pooledmutableblockpos)) {
                        worldIn.getPendingBlockTicks().scheduleTick(blockpos$pooledmutableblockpos, this, MathHelper.nextInt(random, 20, 40));
                    }
                }
            }

        } else {
            worldIn.getPendingBlockTicks().scheduleTick(pos, this, MathHelper.nextInt(random, 20, 40));
        }
    }

    private boolean slightlyMelt(BlockState state, World worldIn, BlockPos pos) {
        int i = state.get(AGE);
        if (i < 3) {
            worldIn.setBlockState(pos, state.with(AGE, Integer.valueOf(i + 1)), 2);

            IceCrackleSoundEvent.play(worldIn, pos);
//            ParticleIceCrackle.spawn(worldIn, pos);
            Random rand = new Random();
            worldIn.getPendingBlockTicks().scheduleTick(
                    pos,
                    this,
                    MathHelper.nextInt(rand, IceEffect.DURATION, IceEffect.DURATION * 2)
            );

            return false;
        } else {
            this.turnIntoWater(state, worldIn, pos);
            return true;
        }
    }

    private boolean shouldMelt(IBlockReader worldIn, BlockPos pos, int neighborsRequired) {
        int i = 0;

        try (BlockPos.PooledMutableBlockPos blockpos$pooledmutableblockpos = BlockPos.PooledMutableBlockPos.retain()) {
            for(Direction direction : Direction.values()) {
                blockpos$pooledmutableblockpos.setPos(pos).move(direction);
                if (worldIn.getBlockState(blockpos$pooledmutableblockpos).getBlock() == this) {
                    ++i;
                    if (i >= neighborsRequired) {
                        return false;
                    }
                }
            }

            return true;
        }
    }

    @Override
    protected void turnIntoWater(BlockState state, World worldIn, BlockPos pos) {
        worldIn.removeBlock(pos, false);
    }

}
