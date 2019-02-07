package com.subwranglers.wickedarrows.entity;

import com.subwranglers.wickedarrows.base.EntityWArrow;
import com.subwranglers.wickedarrows.nbt.NBTEndlessVoid;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityVoidSnareArrow extends EntityWArrow {

    public EntityVoidSnareArrow(World worldIn) {
        super(worldIn);
    }

    public EntityVoidSnareArrow(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
    }

    public EntityVoidSnareArrow(World worldIn, EntityLivingBase shooter) {
        super(worldIn, shooter);
        pickupStatus = PickupStatus.CREATIVE_ONLY;
    }

    @Override
    protected void onBlockHit(RayTraceResult trace) {
        if (NBTEndlessVoid.hasPlayerCapturedMob(shootingEntity))
            NBTEndlessVoid.releaseCapturedEntity(shootingEntity, trace);
    }

    @Override
    protected void arrowHit(EntityLivingBase living) {
        try {
            if (NBTEndlessVoid.hasPlayerCapturedMob(shootingEntity))
                NBTEndlessVoid.releaseCapturedEntity(shootingEntity, living);
            else
                NBTEndlessVoid.captureMob(shootingEntity, living);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
