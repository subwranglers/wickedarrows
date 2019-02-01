package com.subwranglers.wickedarrows.potion;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import util.MCConst;

import static com.subwranglers.wickedarrows.info.Names.*;


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
     * How much damage should be applied to the targeted entity (as an additive/subtractive value)
     */
    public static final float DAMAGE = 2.F;

    protected PotionBleed() {
        super(true, 0xff0000);
        setRegistryName(BLEED_POTION);
        setPotionName(name(BLEED_POTION, QUALIFY));

        registerPotionAttributeModifier(SharedMonsterAttributes.MOVEMENT_SPEED, UUID, -0.1D, 1);
    }

    public static void apply(EntityLivingBase entity, int amplifier) {
        // 0.55D is an arbitrary value that was used based on little amounts of testing during development. It's subject
        // to change in the future, when a larger effort will be put on balancing the strength of Sharp Arrows.
        int initialDuration = MCConst.TICKS_PER_SECOND * DURATION_SECONDS * (int) Math.pow(amplifier, 0.55D);

        entity.addPotionEffect(new PotionEffect(INSTANCE, initialDuration) {

            /*
                Todd 2019-02-01:

                Since PotionEffect.isReady(int, int) doesn't give you access to the entity it's testing for I needed to
                use this custom performEffect(EntityLivingBase, int) method to achieve the desired effect for this
                potion. As far as I can tell this is fine, since this method is called by onUpdate(EntityLivingBase) and
                I'll be actually performing the effect using duration for the intervals in the method.
             */
            public void performEffect(EntityLivingBase entityIn, int duration) {


                if (entityIn.getHealth() > 20) {
                    // Entity has greater than 20 HP, so attack them every tick
                    entityIn.attackEntityFrom(DamageSource.MAGIC, DAMAGE);
                } else if (entityIn.getHealth() > DAMAGE) {

                    // Smaller interval the more health the entity has
                    int health = (int) Math.ceil(entityIn.getHealth());
                    int interval = Math.max(Math.abs(20 - health), 1);
                    interval *= (MCConst.TICKS_PER_SECOND / 4);

                    if (duration % interval == 0)
                        entityIn.attackEntityFrom(DamageSource.MAGIC, DAMAGE);
                }
            }

            @Override
            public void performEffect(EntityLivingBase entityIn) {
                // No-Op -- see overloaded method above
            }

            @Override
            public boolean onUpdate(EntityLivingBase entityIn) {
                if (getDuration() > 0)
                    performEffect(entityIn, getDuration());
                return super.onUpdate(entityIn);
            }
        });
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
