package com.subwranglers.wickedarrows.potion;

import com.subwranglers.wickedarrows.WickedArrows;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import util.MCConst;

import static com.subwranglers.wickedarrows.info.Names.BRITTLE_BONES_POTION;
import static com.subwranglers.wickedarrows.info.Names.QUALIFY;
import static com.subwranglers.wickedarrows.info.Names.name;

public class PotionBrittleBones extends Potion {

    private static final int COLOR = 0xbcbcbc;

    public static final int DURATION = MCConst.TICKS_PER_SECOND * 30;

    public static final PotionBrittleBones INSTANCE = new PotionBrittleBones();

    protected PotionBrittleBones() {
        super(true, COLOR);
        setRegistryName(WickedArrows.MOD_ID, BRITTLE_BONES_POTION);
        setPotionName(name(BRITTLE_BONES_POTION, QUALIFY)); // TODO: 01/02/19 this needs to be a localized string
    }

    public static void apply(EntityLivingBase entity, int amplifier) {
        entity.addPotionEffect(new PotionEffect(INSTANCE, Math.max(amplifier, 1) * DURATION) {
            @Override
            public boolean onUpdate(EntityLivingBase entityIn) {
                if (getDuration() == 0)
                    System.out.println("Brittle Bones finished");
                return super.onUpdate(entityIn);
            }
        });
    }
}
