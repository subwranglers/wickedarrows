package com.subwranglers.wickedarrows.block;

import com.subwranglers.wickedarrows.WickedArrows;
import com.subwranglers.wickedarrows.base.BlockTileEntity;
import com.subwranglers.wickedarrows.inventory.WickedGuiHandler;
import com.subwranglers.wickedarrows.tileentity.TileEntityArrowWorkbench;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

import static com.subwranglers.wickedarrows.info.Names.ARROW_WORKBENCH;

public class BlockArrowWorkbench extends BlockTileEntity<TileEntityArrowWorkbench> {

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
        super(Material.WOOD, ARROW_WORKBENCH);
        setCreativeTab(CreativeTabs.DECORATIONS);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote) {
            playerIn.openGui(WickedArrows.instance, WickedGuiHandler.ARROW_WORKBENCH, worldIn, pos.getX(), pos.getY(), pos.getZ());
        }

        return true;
    }

    @Override
    public Class<TileEntityArrowWorkbench> getTileEntityClass() {
        return TileEntityArrowWorkbench.class;
    }

    @Nullable
    @Override
    public TileEntityArrowWorkbench createTileEntity(World world, IBlockState state) {
        return new TileEntityArrowWorkbench();
    }
}
