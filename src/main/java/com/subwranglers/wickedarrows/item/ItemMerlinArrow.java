package com.subwranglers.wickedarrows.item;

import com.subwranglers.wickedarrows.WickedArrows;
import com.subwranglers.wickedarrows.base.ItemWArrow;
import com.subwranglers.wickedarrows.entity.EntityMerlinArrow;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import util.MCConst;

import static com.subwranglers.wickedarrows.info.Names.MERLIN_ARROW;

public class ItemMerlinArrow extends ItemWArrow {

    public static final ItemMerlinArrow INSTANCE = new ItemMerlinArrow();

    public ItemMerlinArrow() {
        setMaxStackSize(MCConst.DEF_MAX_STACK_SIZE);
        setCreativeTab(CreativeTabs.COMBAT);
        setRegistryName(WickedArrows.MOD_ID, MERLIN_ARROW);
    }

    @Override
    public EntityArrow createArrow(World worldIn, ItemStack stack, EntityLivingBase shooter) {
        return new EntityMerlinArrow(worldIn, shooter);
    }
}
