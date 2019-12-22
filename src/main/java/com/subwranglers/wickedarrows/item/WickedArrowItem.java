package com.subwranglers.wickedarrows.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public abstract class WickedArrowItem extends ArrowItem {

    public WickedArrowItem(Properties builder) {
        super(builder);
    }

    @Override
    public abstract AbstractArrowEntity createArrow(World worldIn, ItemStack stack, LivingEntity shooter);

    @Override
    public boolean isInfinite(ItemStack stack, ItemStack bow, PlayerEntity player) {
        // No Wicked Arrow can be fired infinitely unless specifically enabled. They just aren't wicked enough for that.
        // Maybe in the future we could add a hyper-rare treasure book enchantment for wicked infinity.
        return false;
    }
}
