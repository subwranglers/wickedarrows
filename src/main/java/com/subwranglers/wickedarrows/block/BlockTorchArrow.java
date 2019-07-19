package com.subwranglers.wickedarrows.block;

import com.subwranglers.wickedarrows.item.ItemTorchArrow;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.Random;

import static com.subwranglers.wickedarrows.info.Names.*;

public class BlockTorchArrow extends Block {

    public static final String KEY_HIT_FACE = "hit_face";
    public static final PropertyDirection HIT_FACE = PropertyDirection.create(KEY_HIT_FACE);

    public static final BlockTorchArrow INSTANCE = new BlockTorchArrow();
    public static final ItemBlock INSTANCE_ITEM = new ItemBlock(INSTANCE);

    protected boolean isDropped;

    public BlockTorchArrow() {
        super(Material.CIRCUITS);
        setDefaultState(blockState.getBaseState()
                .withProperty(HIT_FACE, EnumFacing.UP));
        setCreativeTab(CreativeTabs.DECORATIONS);

        String name = name(TORCH_ARROW, BLOCK);
        setRegistryName(name);

        setLightOpacity(-2);
        setLightLevel(2.f);

        setSoundType(SoundType.WOOD);

        setTickRandomly(true);
    }

    public static IBlockState applyToBlockFace(EnumFacing facing) {
        IBlockState torchArrowBlock = INSTANCE.getBlockState().getBaseState();
        return torchArrowBlock.withProperty(HIT_FACE, facing);
    }

    /*

        Options

     */

    @SuppressWarnings("deprecation")
    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @SuppressWarnings("deprecation")
    @Nullable
    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
        return NULL_AABB;
    }

    @SuppressWarnings("deprecation")
    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        // Passing in "relative" coordinates for the vector
        return adjustBoundingBox(state, new Vec3d(0.d, 0.d, 0.d));
    }

    @SuppressWarnings("deprecation")
    @Override
    public AxisAlignedBB getSelectedBoundingBox(IBlockState state, World worldIn, BlockPos pos) {
        // Passing in absolute coordinates for the vector because this method requires them.
        return adjustBoundingBox(state, new Vec3d(pos.getX(), pos.getY(), pos.getZ()));
    }

    private AxisAlignedBB adjustBoundingBox(IBlockState state, Vec3d pos) {
        EnumFacing dir = state.getValue(HIT_FACE);

        // Arbitrary values collected by trial-and-error
        double wide = 0.15d;
        double narrow = 0.35d;

        Vec3d start = pos;
        Vec3d end = pos.add(1.d, 1.d, 1.d);

        // Adjust the Vec3d's using wide and narrow values. Add to start and subtract from end to "contract" the
        // bounding box to fit the model of the BlockTorchArrow.
        switch (dir.getAxis()) {
            case X:
                start = start.add(0.d, narrow, wide);
                end = end.subtract(0.d, narrow, wide);
                break;
            case Y:
                start = start.add(wide, 0.d, narrow);
                end = end.subtract(wide, 0.d, narrow);
                break;
            case Z:
                start = start.add(wide, narrow, 0.d);
                end = end.subtract(wide, narrow, 0.d);
        }

        return new AxisAlignedBB(start.x, start.y, start.z, end.x, end.y, end.z);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        // TODO: 11/12/18 spawn flame particles
    }

    /*

        Block states

     */

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, HIT_FACE);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        EnumFacing dir = state.getValue(HIT_FACE);
        return dir.getIndex();
    }

    @SuppressWarnings("deprecation")
    @Override
    public IBlockState getStateFromMeta(int meta) {
        return getDefaultState().withProperty(HIT_FACE, EnumFacing.VALUES[meta]);
    }

    /*

        Implementation

     */

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return ItemTorchArrow.INSTANCE;
    }

    @Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
        for (EnumFacing facing : EnumFacing.VALUES) {
            IBlockState neighbour = worldIn.getBlockState(pos.offset(facing));
            if (neighbour.getBlock() == Blocks.TNT) {
                igniteTntNeighbours(worldIn, pos.offset(facing));

                if (canPlaceBlockAt(worldIn, pos.offset(state.getValue(HIT_FACE).getOpposite()))) {
                    dropBlockAsItem(worldIn, pos, getDefaultState(), 1);
                    worldIn.setBlockToAir(pos);
                }
            }
        }
    }

    private void igniteTntNeighbours(World worldIn, BlockPos pos) {
        if (!worldIn.isRemote) {
            worldIn.setBlockToAir(pos);
            EntityTNTPrimed entitytntprimed = new EntityTNTPrimed(worldIn, (double) ((float) pos.getX() + 0.5F), (double) pos.getY(), (double) ((float) pos.getZ() + 0.5F), null);
            worldIn.spawnEntity(entitytntprimed);
            worldIn.playSound(null, entitytntprimed.posX, entitytntprimed.posY, entitytntprimed.posZ, SoundEvents.ENTITY_TNT_PRIMED, SoundCategory.BLOCKS, 1.0F, 1.0F);
        }
    }
}
