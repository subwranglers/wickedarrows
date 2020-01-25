package com.subwranglers.wickedarrows.potion;

import com.subwranglers.wickedarrows.WickedArrows;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.potion.*;

import static com.subwranglers.wickedarrows.info.Names.*;

public class IceEffect extends Effect {

    public static final String UUID = "d563f0b9-eb7f-11e8-bafe-902b3498d180";
    public static final int POTION_COLOR = 0xc5d6f7;
    public static final int DURATION = 200;

    public static final IceEffect INSTANCE = new IceEffect();

    private IceEffect() {
        super(EffectType.HARMFUL, POTION_COLOR);

        setRegistryName(WickedArrows.MOD_ID, FROZEN_EFFECT);

        final int MODIFIER = 3;
        addAttributesModifier(SharedMonsterAttributes.ATTACK_SPEED, UUID, -MODIFIER, AttributeModifier.Operation.MULTIPLY_TOTAL);
        addAttributesModifier(SharedMonsterAttributes.FLYING_SPEED, UUID, -MODIFIER, AttributeModifier.Operation.MULTIPLY_TOTAL);
        addAttributesModifier(SharedMonsterAttributes.MOVEMENT_SPEED, UUID, -MODIFIER, AttributeModifier.Operation.MULTIPLY_TOTAL);
    }

    /**
     * "Freezes" an entity as much as possible by preventing it from moving, attacking, breaking blocks and giving it
     * fire resistance.
     *
     * @param living the entity to "freeze"
     */
    public static void applyFrozenEffectToEntity(LivingEntity living) {
        living.addPotionEffect(new EffectInstance(Effects.MINING_FATIGUE, DURATION, 5, false, false));
        living.addPotionEffect(new EffectInstance(Effects.FIRE_RESISTANCE, DURATION, 0, false, false));
        living.addPotionEffect(new EffectInstance(INSTANCE, DURATION));
    }
}
