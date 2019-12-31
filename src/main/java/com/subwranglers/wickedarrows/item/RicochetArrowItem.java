package com.subwranglers.wickedarrows.item;

import com.subwranglers.wickedarrows.WickedArrows;
import com.subwranglers.wickedarrows.entity.RicochetArrowEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import static com.subwranglers.wickedarrows.info.Names.RICOCHET_ARROW;

public class RicochetArrowItem extends WickedArrowItem {

    public static final RicochetArrowItem INSTANCE = new RicochetArrowItem();

    public RicochetArrowItem() {
        super(new Item.Properties().group(ItemGroup.COMBAT));
        setRegistryName(WickedArrows.MOD_ID, RICOCHET_ARROW);
    }

    @Override
    public AbstractArrowEntity createArrow(World worldIn, ItemStack stack, LivingEntity shooter) {
        return new RicochetArrowEntity(worldIn, shooter);
    }
}
