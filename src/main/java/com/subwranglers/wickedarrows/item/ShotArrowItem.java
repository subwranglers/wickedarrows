package com.subwranglers.wickedarrows.item;

import com.subwranglers.wickedarrows.WickedArrows;
import com.subwranglers.wickedarrows.entity.ShotArrowEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import static com.subwranglers.wickedarrows.info.Names.SHOT_ARROW;

public class ShotArrowItem extends WickedArrowItem {

    public static final ShotArrowItem INSTANCE = new ShotArrowItem(2);
    private final int arrowAmount;

    public ShotArrowItem(int amount) {
        super(new Item.Properties().group(ItemGroup.COMBAT));
        arrowAmount = MathHelper.clamp(amount, 2, 9);
        setRegistryName(WickedArrows.MOD_ID, SHOT_ARROW + "_" + amount);
    }

    @Override
    public AbstractArrowEntity createArrow(World worldIn, ItemStack stack, LivingEntity shooter) {
        return new ShotArrowEntity(worldIn, shooter).setArrowAmount(arrowAmount);
    }
}
