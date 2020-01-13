package com.subwranglers.wickedarrows.potion;

import com.subwranglers.wickedarrows.WickedArrows;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;

import static com.subwranglers.wickedarrows.info.Names.*;

public class BrittleBonesEffect extends Effect {

    private static final int COLOR = 0xbcbcbc;

    public static final int DURATION = 20 * 30;

    public static final BrittleBonesEffect INSTANCE = new BrittleBonesEffect();

    protected BrittleBonesEffect() {
        super(EffectType.HARMFUL, COLOR);
        setRegistryName(WickedArrows.MOD_ID, BRITTLE_BONES_EFFECT);
    }

    public static void apply(LivingEntity entity, int amplifier) {
        // The actual effects of this potion are implemented in EventBrittleBones
        entity.addPotionEffect(new EffectInstance(INSTANCE, Math.max(amplifier, 1) * DURATION));
    }
}
