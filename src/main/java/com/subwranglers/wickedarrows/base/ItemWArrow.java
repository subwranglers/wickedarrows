package com.subwranglers.wickedarrows.base;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public abstract class ItemWArrow extends ItemArrow {

    @Override
    public EntityArrow createArrow(World worldIn, ItemStack stack, EntityLivingBase shooter) {
        throw new UnsupportedOperationException(
                "inheriting class didn't return the proper entity in createArrow(World, ItemStack, EntityLivingBase)");
    }

    @Override
    public boolean isInfinite(ItemStack stack, ItemStack bow, EntityPlayer player) {
        // No Wicked Arrow can be fired infinitely unless specifically enabled. They just aren't wicked enough for that.
        // Maybe in the future we could add a hyper-rare treasure book enchantment for wicked infinity.
        return false;
    }
}
