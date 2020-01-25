package com.subwranglers.wickedarrows.potion;

import com.subwranglers.wickedarrows.WickedArrows;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.util.DamageSource;

import static com.subwranglers.wickedarrows.info.Names.*;


public class BleedEffect extends Effect {

    /**
     * Minimum duration this potion effect lasts
     */
    public static final int DURATION_SECONDS = 90;

    /**
     * How much damage should be applied to the targeted entity (as an additive/subtractive value)
     */
    public static final float DAMAGE = 1.F;

    private static final String UUID = "d980ceda-25d7-11e9-ab14-d663bd873d93";

    public static final BleedEffect INSTANCE = new BleedEffect();

    protected BleedEffect() {
        super(EffectType.HARMFUL, 0xff0000);
        setRegistryName(WickedArrows.MOD_ID, BLEED_EFFECT);

        addAttributesModifier(SharedMonsterAttributes.MOVEMENT_SPEED, UUID, -0.1D, AttributeModifier.Operation.MULTIPLY_BASE);
    }

    public static void apply(LivingEntity entity, int amplifier) {
        // 0.55D is an arbitrary value that was used based on little amounts of testing during development. It's subject
        // to change in the future, when a larger effort will be put on balancing the strength of Sharp Arrows.
        int initialDuration = 20 * DURATION_SECONDS * (int) Math.pow(amplifier, 0.55D);

        // TODO: Creating a subclass of EffectInstance is going to cause problems with saving and loading, we need to find a better solution
        entity.addPotionEffect(new EffectInstance(INSTANCE, initialDuration) {

            /*
                Todd 2019-02-01:

                Since PotionEffect.isReady(int, int) doesn't give you access to the entity it's testing for I needed to
                use this custom performEffect(EntityLivingBase, int) method to achieve the desired effect for this
                potion. As far as I can tell this is fine, since this method is called by onUpdate(EntityLivingBase) and
                I'll be actually performing the effect using duration for the intervals in the method.
             */
            public void performEffect(LivingEntity entityIn, int duration) {
                if (entityIn.getHealth() > DAMAGE) {

                    // Smaller interval the more health the entity has
                    int health = (int) Math.ceil(entityIn.getHealth());
                    int interval = Math.max(20 - health, 1);
                    interval *= (20 / 4);

                    if (duration % interval == 0)
                        entityIn.attackEntityFrom(DamageSource.MAGIC, DAMAGE);
                }
            }

            @Override
            public void performEffect(LivingEntity entityIn) {
                // No-Op -- see overloaded method above
            }

            @Override
            public boolean tick(LivingEntity entityIn) {
                if (getDuration() > 0)
                    performEffect(entityIn, getDuration());
                return super.tick(entityIn);
            }
        });
    }
}
