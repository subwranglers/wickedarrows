package com.subwranglers.wickedarrows.entity;

import com.subwranglers.util.coordinates.AabbUtil;
import com.subwranglers.util.coordinates.SphereCoordsBetween;
import com.subwranglers.util.coordinates.SphericalCoordinates;
import com.subwranglers.wickedarrows.instances.EntityTypes;
import com.subwranglers.wickedarrows.item.RicochetArrowItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import java.util.List;
import java.util.UUID;

public class RicochetArrowEntity extends WickedArrowEntity {

    public static final double HORIZON_PITCH_RANGE = 2.d;

    public static final double REBOUND_RANGE = 8.d;
    protected float firedVelocity;
    protected float p_184547_4_;

    protected UUID originalShooter;
    protected double prevY = Float.MIN_VALUE;

    public RicochetArrowEntity(EntityType<? extends RicochetArrowEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public RicochetArrowEntity(World worldIn, double x, double y, double z) {
        super(EntityTypes.RICOCHET_ARROW, worldIn, x, y, z);
    }

    public RicochetArrowEntity(World worldIn, LivingEntity shooter) {
        super(EntityTypes.RICOCHET_ARROW, worldIn, shooter);
        originalShooter = shooter.getUniqueID();
    }

    public RicochetArrowEntity(RicochetArrowEntity other) {
        super(EntityTypes.RICOCHET_ARROW, other.world, (LivingEntity) other.getShooter());
        firedVelocity = other.firedVelocity;
        p_184547_4_ = other.p_184547_4_;
        originalShooter = other.originalShooter;
        posX = other.posX;
        posY = other.posY;
        posZ = other.posZ;
    }

    {
        pickupStatus = PickupStatus.CREATIVE_ONLY;
    }

    @Override
    protected ItemStack getArrowStack() {
        return new ItemStack(RicochetArrowItem.INSTANCE);
    }

    @Override
    public void shoot(Entity shooter, float pitch, float yaw, float p_184547_4_, float velocity, float inaccuracy) {
        super.shoot(shooter, pitch, yaw, p_184547_4_, velocity, inaccuracy);
        firedVelocity = velocity;
        this.p_184547_4_ = p_184547_4_;
    }

    @Override
    protected void arrowHit(LivingEntity living) {
        AxisAlignedBB range = AabbUtil.getRadiusAabb(posX, posY, posZ, REBOUND_RANGE);
        List<LivingEntity> entities = world.getEntitiesWithinAABB(LivingEntity.class, range,
                entity -> entity != null
                        // Don't target the entity that was hit
                        && !entity.getUniqueID().equals(living.getUniqueID())

                        // Don't target the original shooting entity
                        && !entity.getUniqueID().equals(originalShooter)
        );

        if (entities.size() > 0)
            shootAtEntity(this, living, getTarget(entities));
        else if (this.getShooter() instanceof LivingEntity) {
            shootAtEntity(this, living, (LivingEntity) getShooter());
        }
    }

    protected LivingEntity getTarget(List<LivingEntity> entities) {
        if (entities.size() == 1)
            return entities.get(0);
        return entities.get(MathHelper.nextInt(world.rand, 0, entities.size() - 1));
    }

    public static void shootAtEntity(RicochetArrowEntity parent, LivingEntity from, LivingEntity target) {
        if (parent.isInvalidVelocity())
            return;

        RicochetArrowEntity rico = new RicochetArrowEntity(parent);

        // From eye-height, shoot the arrow at the target's midpoint
        SphereCoordsBetween tween = new SphereCoordsBetween(
                from.posX,
                from.posZ,
                from.posY + from.getEyeHeight(),
                target.posX,
                target.posZ,
                target.posY + target.getHeight() / 2.d);

        // Shoot at target with added velocity
        parent.firedVelocity *= 1.05f;
        rico.shoot(from, tween.getPitchF(), tween.getYawF(), rico.p_184547_4_, parent.firedVelocity, 0.f);

        parent.world.addEntity(rico);
    }

    @Override
    protected void onBlockHit(BlockRayTraceResult trace) {
        if (shootingEntity == null || isInvalidVelocity())
            return;

        // Reduce velocity by 2%
        firedVelocity *= 0.98f;
        RicochetArrowEntity rico = new RicochetArrowEntity(this);

        this.setMotion(0, 0, 0);

        float pitch;
        float yaw;
        if (trace.getFace().getAxis() == Direction.Axis.Y) {
            yaw = rotationYaw;
            pitch = rotationPitch;
        } else {
            yaw = SphericalCoordinates.getMirroredYaw(rotationYaw, trace.getFace());
            pitch = -rotationPitch;
        }

        if (Math.abs(prevY - posY) < 0.1d && isHorizonPitch())
            // We're scooting along the ground -- raise the pitch so we can bounce again
            pitch -= 30.f;

        rico.shoot(this, pitch, -yaw, p_184547_4_, firedVelocity, 0.f);
        rico.prevY = posY;

        world.addEntity(rico);

        this.remove();
    }

    public boolean isHorizonPitch() {
        return rotationPitch < HORIZON_PITCH_RANGE && rotationPitch > -HORIZON_PITCH_RANGE;
    }

    public boolean isInvalidVelocity() {
        return firedVelocity < 0.8f;
    }
}
