package com.subwranglers.wickedarrows.item;

import com.subwranglers.wickedarrows.WickedArrows;
import com.subwranglers.wickedarrows.base.ItemWArrow;
import com.subwranglers.wickedarrows.entity.EntityLightburnArrow;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import util.MCConst;

import static com.subwranglers.wickedarrows.info.Names.LIGHTBURN_ARROW;

public class ItemLightburnArrow extends ItemWArrow {

    public static final ItemLightburnArrow INSTANCE = new ItemLightburnArrow();

    public ItemLightburnArrow() {
        setMaxStackSize(MCConst.DEF_MAX_STACK_SIZE);
        setCreativeTab(CreativeTabs.COMBAT);
        setRegistryName(WickedArrows.MOD_ID, LIGHTBURN_ARROW);
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        return getRegistryName().toString();
    }

    @Override
    public EntityArrow createArrow(World worldIn, ItemStack stack, EntityLivingBase shooter) {
        return new EntityLightburnArrow(worldIn, shooter);
    }
}
