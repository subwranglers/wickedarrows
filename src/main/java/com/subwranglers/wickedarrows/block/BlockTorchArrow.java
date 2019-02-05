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
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
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
                if (canPlaceBlockAt(worldIn, pos.offset(state.getValue(HIT_FACE))))
                    dropAsItem(worldIn, pos);
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

    public static void dropAsItem(World world, BlockPos pos) {
        world.setBlockToAir(pos);
        Block.spawnAsEntity(world, pos, new ItemStack(ItemTorchArrow.INSTANCE));
    }
}
