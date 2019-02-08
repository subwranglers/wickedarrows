package com.subwranglers.wickedarrows.entity;

import com.subwranglers.wickedarrows.base.EntityWArrow;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import util.coordinates.AabbUtil;
import util.coordinates.SphereCoordsBetween;

import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public class EntityRicochetArrow extends EntityWArrow {

    public static final double REBOUND_RANGE = 8.d;
    protected float firedVelocity;
    protected float p_184547_4_;
    protected UUID originalShooter;

    public EntityRicochetArrow(World worldIn) {
        super(worldIn);
        setup();
    }

    public EntityRicochetArrow(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
        setup();
    }

    public EntityRicochetArrow(World worldIn, EntityLivingBase shooter) {
        super(worldIn, shooter);
        setup();
        originalShooter = shooter.getUniqueID();
    }

    public EntityRicochetArrow(EntityRicochetArrow other) {
        super(other.world, (EntityLivingBase) other.shootingEntity);
        firedVelocity = other.firedVelocity;
        p_184547_4_ = other.p_184547_4_;
        originalShooter = other.originalShooter;
        posX = other.posX;
        posY = other.posY;
        posZ = other.posZ;
    }

    private void setup() {
        pickupStatus = PickupStatus.CREATIVE_ONLY;
        setDamage(0.000001f);
    }


    @Override
    public void shoot(Entity shooter, float pitch, float yaw, float p_184547_4_, float velocity, float inaccuracy) {
        super.shoot(shooter, pitch, yaw, p_184547_4_, velocity, inaccuracy);
        firedVelocity = velocity;
        this.p_184547_4_ = p_184547_4_;
    }

    @Override
    protected void arrowHit(EntityLivingBase living) {
        AxisAlignedBB range = AabbUtil.getRadiusAabb(posX, posY, posZ, REBOUND_RANGE);
        List<EntityLivingBase> entities = world.getEntitiesWithinAABB(EntityLivingBase.class, range,
                entity -> entity != null
                        // Don't target the entity that was hit
                        && !entity.getUniqueID().equals(living.getUniqueID())

                        // Don't target the original shooting entity
                        && !entity.getUniqueID().equals(originalShooter)
        );

        if (entities.size() < 1)
            return;

        shootAtEntity(this, living, getTarget(entities));
    }

    protected EntityLivingBase getTarget(List<EntityLivingBase> entities) {
        if (entities.size() == 1)
            return entities.get(0);
        return entities.get(MathHelper.getInt(world.rand, 0, entities.size() - 1));
    }

    public static void shootAtEntity(EntityRicochetArrow parent, EntityLivingBase from, EntityLivingBase target) {
        EntityRicochetArrow rico = new EntityRicochetArrow(parent);

        // From eye-height, shoot the arrow at the target's midpoint
        SphereCoordsBetween tween = new SphereCoordsBetween(
                from.posX,
                from.posZ,
                from.posY + from.getEyeHeight(),
                target.posX,
                target.posZ,
                target.posY + target.height / 2.d);

        rico.shoot(from, tween.getPitchF(), tween.getYawF(), rico.p_184547_4_, rico.firedVelocity, 0.f);

        parent.world.spawnEntity(rico);
    }
}
