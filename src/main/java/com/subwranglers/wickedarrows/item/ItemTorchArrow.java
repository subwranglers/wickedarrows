package com.subwranglers.wickedarrows.item;

import com.subwranglers.wickedarrows.WickedArrows;
import com.subwranglers.wickedarrows.base.ItemWArrow;
import com.subwranglers.wickedarrows.entity.EntityTorchArrow;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import util.MCConst;

import static com.subwranglers.wickedarrows.info.Names.TORCH_ARROW;

public class ItemTorchArrow extends ItemWArrow {

    public static final ItemTorchArrow INSTANCE = new ItemTorchArrow();

    public ItemTorchArrow() {
        setMaxStackSize(MCConst.DEF_MAX_STACK_SIZE);
        setCreativeTab(CreativeTabs.COMBAT);
        setRegistryName(WickedArrows.MOD_ID, TORCH_ARROW);
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        return getRegistryName().toString();
    }

    @Override
    public EntityArrow createArrow(World worldIn, ItemStack stack, EntityLivingBase shooter) {
        return new EntityTorchArrow(worldIn, shooter);
    }

    // TODO: 24/01/19 Todd let players place torch arrows like blocks in a later version
//    @Override
//    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
//        worldIn.setBlockState(pos.offset(facing), BlockTorchArrow.applyToBlockFace(facing));
//        player.swingArm(hand);
//        return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
//    }
}
