package com.subwranglers.wickedarrows.entity;

import com.subwranglers.util.coordinates.AabbUtil;
import com.subwranglers.wickedarrows.instances.EntityTypes;
import com.subwranglers.wickedarrows.item.VoidSnareArrowItem;
import com.subwranglers.wickedarrows.voidspace.IVoidSpace;
import com.subwranglers.wickedarrows.voidspace.VoidSpaceCapability;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;

public class VoidSnareArrowEntity extends WickedArrowEntity {

    public static final double ATTACK_RANGE = 20.d;

    public VoidSnareArrowEntity(EntityType<? extends VoidSnareArrowEntity> entityTypeIn, World worldIn) {
        super(entityTypeIn, worldIn);
    }

    public VoidSnareArrowEntity(World worldIn, double x, double y, double z) {
        super(EntityTypes.VOID_SNARE_ARROW, worldIn, x, y, z);
    }

    public VoidSnareArrowEntity(World worldIn, LivingEntity shooter) {
        super(EntityTypes.VOID_SNARE_ARROW, worldIn, shooter);
    }

    {
        pickupStatus = PickupStatus.CREATIVE_ONLY;
    }

    @Nonnull
    @Override
    protected ItemStack getArrowStack() {
        return new ItemStack(VoidSnareArrowItem.INSTANCE);
    }

    @Override
    protected void onHit(RayTraceResult trace) {
        super.onHit(trace);
        if(world.isRemote)
            return;

        LivingEntity shooter = (LivingEntity) getShooter();
        if(shooter == null)
            return;

        LazyOptional<IVoidSpace> voidSpace = shooter.getCapability(VoidSpaceCapability.VOID_SPACE_HANDLER);

        voidSpace.ifPresent(v -> {
            if(v.hasCapturedMob()){
                CreatureEntity creature = v.releaseCapturedMob();
                if(creature == null)
                    return;

                creature.setPosition(this.posX, this.posY, this.posZ);
                world.addEntity(creature); // TODO: figure out what happens here if you release an entity into a different dimension

                setAttackTarget(creature, trace);
            }
            else {
                // Spawn a void vacuum where the arrow landed
                world.addEntity(new VoidVacuumEntity(world, this));
            }
        });

        this.remove();
    }

    private void setAttackTarget(CreatureEntity attacker, RayTraceResult hitResult) {
        LivingEntity target = null;
        if(hitResult.getType() == RayTraceResult.Type.ENTITY){
            Entity hit = ((EntityRayTraceResult) hitResult).getEntity();
            if(hit instanceof LivingEntity)
                target = (LivingEntity) hit;
        }

        if (target == null){
            AxisAlignedBB range = AabbUtil.getRadiusAabb(new BlockPos(this), ATTACK_RANGE);
            target = world.getClosestEntityWithinAABB(
                    CreatureEntity.class,
                    (new EntityPredicate()).setDistance(ATTACK_RANGE),
                    attacker,
                    attacker.posX,
                    attacker.posY,
                    attacker.posZ,
                    range);
        }

        // Attack entity hit with released mob
        attacker.setAttackTarget(target);
    }
}
