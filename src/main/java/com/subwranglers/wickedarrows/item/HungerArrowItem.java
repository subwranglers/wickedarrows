package com.subwranglers.wickedarrows.item;

import com.subwranglers.wickedarrows.WickedArrows;
import com.subwranglers.wickedarrows.entity.HungerArrowEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import static com.subwranglers.wickedarrows.info.Names.HUNGER_ARROW;

public class HungerArrowItem extends WickedArrowItem {

    public static final HungerArrowItem INSTANCE = new HungerArrowItem();

    public HungerArrowItem() {
        super(new Item.Properties().group(ItemGroup.COMBAT));
        setRegistryName(WickedArrows.MOD_ID, HUNGER_ARROW);
    }

    @Override
    public AbstractArrowEntity createArrow(World worldIn, ItemStack stack, LivingEntity shooter) {
        return new HungerArrowEntity(worldIn, shooter);
    }
}
