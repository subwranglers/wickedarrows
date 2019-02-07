package com.subwranglers.wickedarrows.entity;

import com.subwranglers.wickedarrows.base.EntityWArrow;
import com.subwranglers.wickedarrows.item.ItemVoidSnareArrow;
import com.subwranglers.wickedarrows.nbt.NBTEndlessVoid;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
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
    protected ItemStack getArrowStack() {
        return new ItemStack(ItemVoidSnareArrow.INSTANCE);
    }

    @Override
    protected void onHit(RayTraceResult trace) {
        super.onHit(trace);

        if (NBTEndlessVoid.hasPlayerCapturedMob(shootingEntity))
            NBTEndlessVoid.releaseCapturedEntity(shootingEntity, trace);

        else if (shootingEntity != null && !world.isRemote)
            // Spawn a void vacuum where the arrow landed
            spawnVoidVacuum();

        if (!isDead)
            setDead();
    }

    protected void spawnVoidVacuum() {
        EntityVoidVacuum vacuum = new EntityVoidVacuum(world);
        vacuum.setOwner((EntityPlayer) shootingEntity);
        vacuum.setPosition(posX, posY, posZ);
        vacuum.setRadius(EntityVoidVacuum.DEFAULT_RADIUS);
        vacuum.setStrength(EntityVoidVacuum.DEFAULT_STRENGTH);
        world.spawnEntity(vacuum);
    }
}
