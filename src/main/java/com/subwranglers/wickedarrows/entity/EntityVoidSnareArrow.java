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
    protected void onBlockHit(RayTraceResult trace) {
        if (NBTEndlessVoid.hasPlayerCapturedMob(shootingEntity))
            NBTEndlessVoid.releaseCapturedEntity(shootingEntity, trace);

        else if (shootingEntity != null && !world.isRemote)
            // Spawn a void vacuum where the arrow landed
            spawnVoidVacuum();

        setDead();
    }

    @Override
    protected void arrowHit(EntityLivingBase living) {
        if (NBTEndlessVoid.hasPlayerCapturedMob(shootingEntity))
            NBTEndlessVoid.releaseCapturedEntity(shootingEntity, living);
        else {
            NBTEndlessVoid.captureMob(shootingEntity, living);

            if (shootingEntity instanceof EntityPlayer) {
                // TODO: 06/02/19 Play successful sound effect and render a "voidey-like", wispy texture from the entity
                // captured to the player
                // Refund the arrow to the shooter
                ((EntityPlayer) shootingEntity).addItemStackToInventory(getArrowStack());

                spawnVoidVacuum();
            }
        }
    }

    protected void spawnVoidVacuum() {
        EntityVoidVacuum vacuum = new EntityVoidVacuum(world);
        vacuum.setOwner((EntityPlayer) shootingEntity);
        vacuum.setPosition(posX, posY, posZ);
        world.spawnEntity(vacuum);
    }
}
