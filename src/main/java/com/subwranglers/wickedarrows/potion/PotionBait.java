package com.subwranglers.wickedarrows.potion;

import com.subwranglers.wickedarrows.WickedArrows;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import util.MCConst;

import static com.subwranglers.wickedarrows.info.Names.BAIT_POTION;

public class PotionBait extends Potion {

    private static final int COLOR = 0x440000;

    public static final int DURATION = MCConst.TICKS_PER_SECOND * 60;
    public static final PotionBait INSTANCE = new PotionBait();

    protected PotionBait() {
        super(true, COLOR);
        setRegistryName(WickedArrows.MOD_ID, BAIT_POTION);
    }

    public static void apply(EntityLivingBase living) {
        living.addPotionEffect(new PotionEffect(INSTANCE, DURATION));
    }
}
