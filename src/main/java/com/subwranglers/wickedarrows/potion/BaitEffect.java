package com.subwranglers.wickedarrows.potion;

import com.subwranglers.wickedarrows.WickedArrows;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

import static com.subwranglers.wickedarrows.info.Names.BAIT_EFFECT;

public class BaitEffect extends Effect {

    private static final int COLOR = 0x440000;

    public static final BaitEffect INSTANCE = new BaitEffect();

    protected BaitEffect() {
        super(EffectType.HARMFUL, COLOR);
        setRegistryName(WickedArrows.MOD_ID, BAIT_EFFECT);
    }
}
