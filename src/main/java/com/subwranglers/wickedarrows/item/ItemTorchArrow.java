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
        setUnlocalizedName(TORCH_ARROW);
        setRegistryName(WickedArrows.MOD_ID, TORCH_ARROW);
    }

    @Override
    public EntityArrow createArrow(World worldIn, ItemStack stack, EntityLivingBase shooter) {
        return new EntityTorchArrow(worldIn, shooter);
    }
}
