package com.subwranglers.wickedarrows.entity;

import com.subwranglers.wickedarrows.instances.EntityTypes;
import com.subwranglers.wickedarrows.item.HungerArrowItem;
import com.subwranglers.wickedarrows.potion.BaitEffect;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.FoodStats;
import net.minecraft.world.World;

public class HungerArrowEntity extends WickedArrowEntity {

    public static final int HUNGER_DURATION = 20 * 10;
    public static final int BAIT_DURATION = 20 * 60;

    protected float exhaustionDamage = 20.0f;

    public HungerArrowEntity(EntityType<? extends HungerArrowEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public HungerArrowEntity(World worldIn, double x, double y, double z) {
        super(EntityTypes.HUNGER_ARROW, worldIn, x, y, z);
    }

    public HungerArrowEntity(World worldIn, LivingEntity shooter) {
        super(EntityTypes.HUNGER_ARROW, worldIn, shooter);
    }

    {
        // Remove most physical damage from this arrow -- it'll mainly be affecting hunger instead.
        setDamage(0.01);

        // Players shouldn't be allowed to pick up hunger arrows after they're fired.
        pickupStatus = PickupStatus.CREATIVE_ONLY;
    }

    @Override
    protected ItemStack getArrowStack() {
        return new ItemStack(HungerArrowItem.INSTANCE);
    }

    @Override
    protected void arrowHit(LivingEntity living) {
        living.addPotionEffect(new EffectInstance(BaitEffect.INSTANCE, BAIT_DURATION));

        // Apply a decent amount of exhaustion to the target
        if (living instanceof PlayerEntity) {
            PlayerEntity player = ((PlayerEntity) living);
            FoodStats stats = player.getFoodStats();

            stats.setFoodSaturationLevel(0.f); // Remove any saturation
            stats.addExhaustion((float)(exhaustionDamage * getMotion().length()));
            player.addPotionEffect(new EffectInstance(Effects.HUNGER, HUNGER_DURATION));
        }
    }
}
