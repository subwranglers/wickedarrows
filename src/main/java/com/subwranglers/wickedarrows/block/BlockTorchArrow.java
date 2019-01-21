package com.subwranglers.wickedarrows.block;

import com.subwranglers.wickedarrows.info.Names;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class BlockTorchArrow extends Block {

    public static final String KEY_ON_FACE = "on_face";
    public static final String KEY_ROTATION = "rotation";
    public static final PropertyDirection ON_FACE = PropertyDirection.create(KEY_ON_FACE);

    /**
     * The orientation of the block on whichever face it's attached to. By default, it has no orientation specified,
     * which interprets as a "straight-on" orientation.
     */
    public static final PropertyEnum<EnumRotation> ROTATION = PropertyEnum.create(KEY_ROTATION, EnumRotation.class);

    public static final BlockTorchArrow INSTANCE = new BlockTorchArrow();

    public BlockTorchArrow() {
        super(Material.CIRCUITS);
        setDefaultState(blockState.getBaseState()
                .withProperty(ON_FACE, EnumFacing.UP)
                .withProperty(ROTATION, EnumRotation.NONE)
        );
        setCreativeTab(CreativeTabs.DECORATIONS);

        setUnlocalizedName(Names.TORCH_ARROW);
        setRegistryName(Names.TORCH_ARROW);
        setTickRandomly(true);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        // TODO: 11/12/18 spawn flame particles
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, ON_FACE, ROTATION);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        EnumFacing dir = state.getValue(ON_FACE);
        EnumRotation rot = state.getValue(ROTATION);

        int out = dir.getIndex() << 4;
        out &= rot.index;

        return out;
    }

    /**
     * Enum values representing the orientation of the block. These are meant to be interpreted in 2D (looking directly
     * at one block face).
     */
    public enum EnumRotation implements IStringSerializable {
        N(0, "north"),
        NE(1, "northeast"),
        E(2, "east"),
        SE(3, "southeast"),
        S(4, "south"),
        SW(5, "southwest"),
        W(6, "west"),
        NW(7, "northwest"),
        NONE(8, "none");

        int index;
        String name;

        EnumRotation(int index, String name) {
            this.index = index;
            this.name = name;
        }

        public EnumRotation opposite(EnumRotation rotation) {
            EnumRotation[] rots = values();
            return rots[(rotation.index + 4) % rots.length - 1]; // -1 ignores "none"
        }

        @Override
        public String getName() {
            return name;
        }
    }
}
