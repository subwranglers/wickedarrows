package com.subwranglers.wickedarrows.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.Random;

import static com.subwranglers.wickedarrows.info.Names.*;
import static com.subwranglers.wickedarrows.info.Names.name;

public class TorchArrowBlock extends Block {

    public static final DirectionProperty HIT_FACE = BlockStateProperties.FACING;

    public static final TorchArrowBlock INSTANCE = new TorchArrowBlock();

    public TorchArrowBlock() {
        super(Properties.create(Material.MISCELLANEOUS)
                .doesNotBlockMovement()
                .hardnessAndResistance(0.0F)
                .lightValue(30)
                .sound(SoundType.WOOD)
                .tickRandomly()
        );

        setDefaultState(
            stateContainer.getBaseState().with(HIT_FACE, Direction.UP)
        );

        setRegistryName(name(TORCH_ARROW, BLOCK));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(HIT_FACE);
    }

    public static BlockState applyToBlockFace(Direction facing) {
        BlockState torchArrowBlock = INSTANCE.getDefaultState();
        return torchArrowBlock.with(HIT_FACE, facing);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        // TODO: 11/12/18 spawn flame particles
    }

    private boolean canAttachTo(IBlockReader worldIn, BlockPos pos, Direction face) {
        BlockState blockstate = worldIn.getBlockState(pos);

        // func_224755_d appears to be the BlockState version of Block.hasSolidSide.
        // The name will probably be changed to an unobfuscated one in a future version of forge.
        return blockstate.func_224755_d(worldIn, pos, face);
    }

    @Override
    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
        Direction hitFace = state.get(HIT_FACE);
        return canAttachTo(worldIn, pos.offset(hitFace.getOpposite()), hitFace);
    }

    @Nullable
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        BlockState blockstate = this.getDefaultState();
        IWorldReader world = context.getWorld();
        BlockPos blockpos = context.getPos();
        Direction[] lookingDirections = context.getNearestLookingDirections();

        for(Direction direction : lookingDirections) {
            blockstate = blockstate.with(HIT_FACE, direction.getOpposite());
            if (blockstate.isValidPosition(world, blockpos)) {
                return blockstate;
            }
        }

        return null;
    }

    @Override
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        return !stateIn.isValidPosition(worldIn, currentPos) ? Blocks.AIR.getDefaultState() : super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    @Override
    public void onBlockAdded(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
        for (Direction facing : Direction.values()) {
            BlockPos neighborPos = pos.offset(facing);
            BlockState neighborState = worldIn.getBlockState(neighborPos);
            Block neighborBlock = neighborState.getBlock();
            if (neighborBlock == Blocks.TNT) {
                neighborBlock.catchFire(neighborState, worldIn, neighborPos, null, null);
                worldIn.removeBlock(neighborPos, false);
            }
        }
    }

    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT;
    }

}
