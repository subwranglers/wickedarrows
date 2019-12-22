package com.subwranglers.wickedarrows.item;

import com.subwranglers.wickedarrows.WickedArrows;
import com.subwranglers.wickedarrows.entity.IceArrowEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import static com.subwranglers.wickedarrows.info.Names.ICE_ARROW;

public class IceArrowItem extends WickedArrowItem {

    public static final IceArrowItem INSTANCE = new IceArrowItem();

    private IceArrowItem() {
        super(new Item.Properties().group(ItemGroup.COMBAT));
        setRegistryName(WickedArrows.MOD_ID, ICE_ARROW);
    }
 
    @Override
    public AbstractArrowEntity createArrow(World worldIn, ItemStack stack, LivingEntity shooter) {
        return new IceArrowEntity(worldIn, shooter);
    }
}
