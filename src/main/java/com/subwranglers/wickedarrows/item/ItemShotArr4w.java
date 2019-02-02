package com.subwranglers.wickedarrows.item;

import com.subwranglers.wickedarrows.WickedArrows;
import com.subwranglers.wickedarrows.base.ItemWArrow;
import com.subwranglers.wickedarrows.entity.EntityShotArrow;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import util.MCConst;

import static com.subwranglers.wickedarrows.info.Names.SHOT_ARR4W;

public class ItemShotArr4w extends ItemWArrow {

    public static final ItemShotArr4w INSTANCE = new ItemShotArr4w();

    public ItemShotArr4w() {
        setMaxStackSize(MCConst.DEF_MAX_STACK_SIZE);
        setCreativeTab(CreativeTabs.COMBAT);
        setRegistryName(WickedArrows.MOD_ID, SHOT_ARR4W);
    }

    @Override
    public EntityArrow createArrow(World worldIn, ItemStack stack, EntityLivingBase shooter) {
        return new EntityShotArrow(worldIn, shooter).setNumArrows(4);
    }
}
