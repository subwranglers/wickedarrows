package com.subwranglers.wickedarrows.item;

import com.subwranglers.wickedarrows.WickedArrows;
import com.subwranglers.wickedarrows.base.ItemWArrow;
import com.subwranglers.wickedarrows.entity.EntityRicochetArrow;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import util.MCConst;

import static com.subwranglers.wickedarrows.info.Names.RICOCHET_ARROW;

public class ItemRicochetArrow extends ItemWArrow {

    public static final ItemRicochetArrow INSTANCE = new ItemRicochetArrow();

    public ItemRicochetArrow() {
        setMaxStackSize(MCConst.DEF_MAX_STACK_SIZE);
        setCreativeTab(CreativeTabs.COMBAT);
        setRegistryName(WickedArrows.MOD_ID, RICOCHET_ARROW);
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        return getRegistryName().toString();
    }

    @Override
    public EntityArrow createArrow(World worldIn, ItemStack stack, EntityLivingBase shooter) {
        return new EntityRicochetArrow(worldIn, shooter);
    }
}
