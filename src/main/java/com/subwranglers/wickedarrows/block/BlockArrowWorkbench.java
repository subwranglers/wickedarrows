package com.subwranglers.wickedarrows.block;

import com.subwranglers.wickedarrows.WickedArrows;
import com.subwranglers.wickedarrows.tileentity.TileEntityArrowWorkbench;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

import static com.subwranglers.wickedarrows.info.Names.ARROW_WORKBENCH;

public class BlockArrowWorkbench extends Block {

    public static final BlockArrowWorkbench INSTANCE = new BlockArrowWorkbench();
    public static final ItemBlock INSTANCE_ITEM;

    static {
        INSTANCE_ITEM = new ItemBlock(INSTANCE) {
            @Override
            public String getItemStackDisplayName(ItemStack stack) {
                return INSTANCE.getRegistryName().toString();
            }
        };
        INSTANCE_ITEM.setRegistryName(WickedArrows.MOD_ID, ARROW_WORKBENCH);
    }

    public BlockArrowWorkbench() {
        super(Material.WOOD);
        setRegistryName(WickedArrows.MOD_ID, ARROW_WORKBENCH);
        setCreativeTab(CreativeTabs.DECORATIONS);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (worldIn.isRemote)
            return true;

        TileEntity tileentity = worldIn.getTileEntity(pos);

        if (tileentity instanceof TileEntityArrowWorkbench)
            playerIn.displayGui((TileEntityArrowWorkbench) tileentity);

        return true;
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return state.getBlock() == INSTANCE;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileEntityArrowWorkbench();
    }
}
