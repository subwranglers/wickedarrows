package com.subwranglers.wickedarrows.potion;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import util.MCConst;

import static com.subwranglers.wickedarrows.info.Names.*;

/**
 * <p>Applies magical damage to a targeted entity in a diminishing fashion. Each damage pass takes the entity's current
 * health value, halves it, and applies that as damage -- as long as the entity has more than 2.0 points of health.</p>
 * <p>
 * <p>This effect is meant to be a long-lasting effect. It runs for a minimum of 90 seconds and an approx max of 2.7
 * minutes (with velocity provided from a fully-drawn vanilla bow)</p>
 */
public class PotionBleed extends Potion {

    private static final String UUID = "d980ceda-25d7-11e9-ab14-d663bd873d93";

//    TODO (Todd 2019-01-31): Implement proper potion icon rendering
//    public static final ResourceLocation ICON = new ResourceLocation(WickedArrows.MOD_ID,
//            "textures/gui/potion/bleeding_heart.png");

    public static final PotionBleed INSTANCE = new PotionBleed();

    /**
     * Minimum duration this potion effect lasts
     */
    public static final int DURATION_SECONDS = 90;

    /**
     * How often to apply damage to the targeted entity (in seconds)
     */
    public static final int DAMAGE_INTERVAL_SECONDS = 5;

    /**
     * How often to apply damage to the targeted entity (in ticks)
     */
    public static final int DAMAGE_INTERVAL_TICKS = DAMAGE_INTERVAL_SECONDS * MCConst.TICKS_PER_SECOND;

    /**
     * How much damage should be applied to the targeted entity (as a ratio of current health value)
     */
    public static final float DAMAGE = 0.5F;

    protected PotionBleed() {
        super(true, 0xff0000);
        setRegistryName(BLEED_POTION);
        setPotionName(name(BLEED_POTION, QUALIFY));

        registerPotionAttributeModifier(SharedMonsterAttributes.MOVEMENT_SPEED, UUID, -0.1D, 1);
    }

    public static void apply(EntityLivingBase entity, int amplifier) {
        // 0.55D is an arbitrary value that was used based on little amounts of testing during development. It's subject
        // to change in the future, when a larger effort will be put on balancing the strength of Sharp Arrows.
        int duration = MCConst.TICKS_PER_SECOND * DURATION_SECONDS * (int) Math.pow(amplifier, 0.55D);

        entity.addPotionEffect(new PotionEffect(INSTANCE, duration) {
            @Override
            public void performEffect(EntityLivingBase entityIn) {
                super.performEffect(entityIn);

                // Don't attack the entity unless they have more than 1 heart.
                if (entityIn.getHealth() > 2.F)
                    entityIn.attackEntityFrom(DamageSource.MAGIC, entityIn.getHealth() * DAMAGE);
            }

        });
    }

    @Override
    public boolean isReady(int duration, int amplifier) {
        return duration % DAMAGE_INTERVAL_TICKS == 0;
    }

//    TODO (Todd 2019-01-31): Implement proper potion icon rendering
//    @Override
//    @SideOnly(Side.CLIENT)
//    public void renderInventoryEffect(int x, int y, PotionEffect effect, Minecraft mc) {
//        mc.renderEngine.bindTexture(ICON);
//    }
//
//    @Override
//    @SideOnly(Side.CLIENT)
//    public void renderHUDEffect(int x, int y, PotionEffect effect, Minecraft mc, float alpha) {
//        mc.renderEngine.bindTexture(ICON);
//    }
}
