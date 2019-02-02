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

import static com.subwranglers.wickedarrows.info.Names.SHOT_ARR7W;

public class ItemShotArr7w extends ItemWArrow {

    public static final ItemShotArr7w INSTANCE = new ItemShotArr7w();

    public ItemShotArr7w() {
        setMaxStackSize(MCConst.DEF_MAX_STACK_SIZE);
        setCreativeTab(CreativeTabs.COMBAT);
        setRegistryName(WickedArrows.MOD_ID, SHOT_ARR7W);
    }

    @Override
    public EntityArrow createArrow(World worldIn, ItemStack stack, EntityLivingBase shooter) {
        return new EntityShotArrow(worldIn, shooter).setNumArrows(7);
    }
}
