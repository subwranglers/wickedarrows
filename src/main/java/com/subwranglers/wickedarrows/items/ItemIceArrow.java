package com.subwranglers.wickedarrows.items;

import com.subwranglers.wickedarrows.WickedArrows;
import com.subwranglers.wickedarrows.base.ItemWArrow;
import com.subwranglers.wickedarrows.entities.EntityIceArrow;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import static com.subwranglers.wickedarrows.info.Names.*;

public class ItemIceArrow extends ItemWArrow {

    public static final int MAX_STACK_SIZE = 64;

    public static final ItemIceArrow instance = new ItemIceArrow();

    private ItemIceArrow() {
        setMaxStackSize(MAX_STACK_SIZE);
        setCreativeTab(CreativeTabs.COMBAT);
        setUnlocalizedName(ICE_ARROW);
        setRegistryName(WickedArrows.MOD_ID, ICE_ARROW);
    }

    @Override
    public EntityArrow createArrow(World worldIn, ItemStack stack, EntityLivingBase shooter) {
        return new EntityIceArrow(worldIn, shooter);
    }
}
