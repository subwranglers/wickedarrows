package com.subwranglers.wickedarrows.block;

import com.subwranglers.wickedarrows.info.Names;
import com.subwranglers.wickedarrows.potion.PotionIce;
import com.subwranglers.wickedarrows.client.sound.IceCrackleSoundEvent;
import net.minecraft.block.BlockFrostedIce;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

public class BlockInvokedIce extends BlockFrostedIce {

    public static final float LIGHT_LEVEL = 1.0f;

    public static final BlockInvokedIce INSTANCE = new BlockInvokedIce();
    public static final ItemBlock INSTANCE_ITEM = new ItemBlock(INSTANCE);

    protected BlockInvokedIce() {
        super();

        setRegistryName(Names.INVOKED_ICE);
        setLightLevel(LIGHT_LEVEL);
        setLightOpacity(5);
        setSoundType(SoundType.GLASS);
        setHardness(1.2f);
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    public static IBlockState getDefaultBlockState() {
        return INSTANCE.getDefaultState();
    }

    @Override
    public void harvestBlock(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, @Nullable TileEntity te, ItemStack stack) {
        // No-Op. Block should give nothing, because it was created "magically."
    }

    @Override
    protected void turnIntoWater(World worldIn, BlockPos pos) {
        // TODO: 23/11/18 if invoked ice replaced water, make it put a water source back
        worldIn.setBlockToAir(pos);
    }

    protected void slightlyMelt(World worldIn, BlockPos pos, IBlockState state, Random rand, boolean meltNeighbors) {
        int i = state.getValue(AGE);

        if (i < 3) {
            worldIn.setBlockState(pos, state.withProperty(AGE, i + 1), 2);

            IceCrackleSoundEvent.play(worldIn, pos, rand);

//            ParticleIceCrackle.spawn(worldIn, pos);

            worldIn.scheduleUpdate(pos, this, MathHelper.getInt(
                    rand,
                    PotionIce.DURATION,
                    PotionIce.DURATION * 2));
        } else
            turnIntoWater(worldIn, pos);
    }
}
