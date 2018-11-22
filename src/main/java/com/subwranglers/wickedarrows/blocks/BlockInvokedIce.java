package com.subwranglers.wickedarrows.blocks;

import com.subwranglers.wickedarrows.info.Names;
import net.minecraft.block.Block;
import net.minecraft.block.BlockIce;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockInvokedIce extends BlockIce {

    public static final float LIGHT_LEVEL = 1.0f;

    public static final BlockInvokedIce instance = new BlockInvokedIce();
    public static final ItemBlock instanceItem = new ItemBlock(instance);
    private static int forgeId = Integer.MIN_VALUE;

    public static final String KEY_PROP_TURN_INTO_WATER = "turnIntoWater";
    public static final PropertyBool TURN_INTO_WATER = PropertyBool.create(KEY_PROP_TURN_INTO_WATER);

    protected BlockInvokedIce() {
        super();

        setUnlocalizedName(Names.INVOKED_ICE);
        setRegistryName(Names.INVOKED_ICE);

        setLightLevel(LIGHT_LEVEL);
        setLightOpacity(5);
        setSoundType(SoundType.GLASS);
        setHardness(1.0f);


        setDefaultState(blockState.getBaseState().withProperty(TURN_INTO_WATER, false));
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, TURN_INTO_WATER);
    }

    public static IBlockState getShouldTurnToWaterState(boolean turnIntoWater) {
        return instance.getBlockState().getBaseState().withProperty(TURN_INTO_WATER, turnIntoWater);
    }

    @Override
    public void harvestBlock(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, @Nullable TileEntity te, ItemStack stack) {
        // No-Op. Block should give nothing, because it was created "magically."
    }

    @Override
    protected void turnIntoWater(World worldIn, BlockPos pos) {
        // We replaced a water source, so we should put it back
        if (worldIn.getBlockState(pos).getValue(TURN_INTO_WATER))
            worldIn.setBlockState(pos, Blocks.WATER.getDefaultState());
    }
}
