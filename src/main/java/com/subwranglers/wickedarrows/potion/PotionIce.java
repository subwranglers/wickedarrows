package com.subwranglers.wickedarrows.potion;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

import static com.subwranglers.wickedarrows.info.Names.*;

public class PotionIce extends Potion {

    public static final String UUID = "d563f0b9-eb7f-11e8-bafe-902b3498d180";
    public static final int POTION_COLOR = 0xc5d6f7;
    public static final int DURATION = 200;
    public static final int POTION_LEVEL = 2;

    public static final PotionIce INSTANCE = new PotionIce();

    private PotionIce() {
        super(true, POTION_COLOR);

        setRegistryName(ICE_POTION);
        setPotionName(name(ICE_POTION, QUALIFY));

        final int MODIFIER = 3;
        registerPotionAttributeModifier(SharedMonsterAttributes.ATTACK_SPEED, UUID, -MODIFIER, POTION_LEVEL);
        registerPotionAttributeModifier(SharedMonsterAttributes.FLYING_SPEED, UUID, -MODIFIER, POTION_LEVEL);
        registerPotionAttributeModifier(SharedMonsterAttributes.MOVEMENT_SPEED, UUID, -MODIFIER, POTION_LEVEL);
    }

    /**
     * "Freezes" an entity as much as possible by preventing it from moving, attacking, breaking blocks and giving it
     * fire resistance.
     *
     * @param living the entity to "freeze"
     */
    public static void applyFrozenEffectToEntity(EntityLivingBase living) {
        living.addPotionEffect(new PotionEffect(MobEffects.MINING_FATIGUE, DURATION, 5) {
            @Override
            public boolean doesShowParticles() {
                return false;
            }
        });
        living.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, DURATION) {
            @Override
            public boolean doesShowParticles() {
                return false;
            }
        });
        living.addPotionEffect(new PotionEffect(INSTANCE, DURATION));
    }
}
