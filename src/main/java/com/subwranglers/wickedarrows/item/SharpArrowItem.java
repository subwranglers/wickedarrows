package com.subwranglers.wickedarrows.item;

import com.subwranglers.wickedarrows.WickedArrows;
import com.subwranglers.wickedarrows.entity.SharpArrowEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import static com.subwranglers.wickedarrows.info.Names.SHARP_ARROW;

public class SharpArrowItem extends WickedArrowItem {

    public static final SharpArrowItem INSTANCE = new SharpArrowItem();

    public SharpArrowItem() {
        super(new Item.Properties().group(ItemGroup.COMBAT));
        setRegistryName(WickedArrows.MOD_ID, SHARP_ARROW);
    }

    @Override
    public AbstractArrowEntity createArrow(World worldIn, ItemStack stack, LivingEntity shooter) {
        return new SharpArrowEntity(worldIn, shooter);
    }
}
