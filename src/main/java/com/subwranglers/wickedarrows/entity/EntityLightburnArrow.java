package com.subwranglers.wickedarrows.entity;

import com.subwranglers.wickedarrows.base.EntityWArrow;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class EntityLightburnArrow extends EntityWArrow {

    /**
     * Lightburn arrows fly at a constant speed.
     */
    public static final float ARROW_VELOCITY = 60.f;

    /**
     * Because lightburn arrows fly so fast ({@link #ARROW_VELOCITY}, default max velocity is 3.0f) their damage needs
     * to be exceptionally small to be balanced.
     */
    public static final double ARROW_DAMAGE = 0.01d;

    public EntityLightburnArrow(World worldIn) {
        super(worldIn);
        setup();
    }

    public EntityLightburnArrow(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
        setup();
    }

    public EntityLightburnArrow(World worldIn, EntityLivingBase shooter) {
        super(worldIn, shooter);
        setup();
    }

    private void setup() {
        setDamage(ARROW_DAMAGE);
    }

    @Override
    public void shoot(Entity shooter, float pitch, float yaw, float p_184547_4_, float velocity, float inaccuracy) {
        if (velocity >= 3.f) {
            // Lightburn flies at a constant speed.
            super.shoot(shooter, pitch, yaw, p_184547_4_, ARROW_VELOCITY, 0);
        } else {
            // To keep the arrow more balanced and unspammable, hit the player with a small explosion if they don't
            // fully draw the arrow before firing and don't project the arrow.
            Explosion ex = getEntityWorld().createExplosion(
                    shooter,
                    shooter.posX,
                    shooter.posY,
                    shooter.posZ,
                    1.f,
                    true
            );
            shooter.attackEntityFrom(DamageSource.causeExplosionDamage(ex), 4.f);

            // Don't let the player pick up a misfired arrow.
            setDead();
        }
    }

    @Override
    protected void arrowHit(EntityLivingBase living) {
    }
}
