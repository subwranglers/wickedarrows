package com.subwranglers.wickedarrows.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.network.IPacket;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraftforge.fml.network.NetworkHooks;

public abstract class WickedArrowEntity extends AbstractArrowEntity implements IEntityAdditionalSpawnData {

    protected WickedArrowEntity(EntityType<? extends WickedArrowEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public WickedArrowEntity(EntityType<? extends WickedArrowEntity> entity, World worldIn, double x, double y, double z) {

        super(entity, x, y, z, worldIn);
    }

    public WickedArrowEntity(EntityType<? extends WickedArrowEntity> entity, World worldIn, LivingEntity shooter) {
        super(entity, shooter, worldIn);
    }

    @Override
    protected void onHit(RayTraceResult trace) {
        if (trace.getType() == RayTraceResult.Type.BLOCK)
            onBlockHit((BlockRayTraceResult) trace);

        super.onHit(trace);
    }

    protected void onBlockHit(BlockRayTraceResult trace) {
        // No-op, inheriting classes will implement
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    public void writeSpawnData(PacketBuffer buffer) {
        Entity shooter = this.getShooter();
        buffer.writeInt(shooter == null ? 0 : shooter.getEntityId());
    }

    @Override
    public void readSpawnData(PacketBuffer additionalData) {
        int shooterID = additionalData.readInt();
        Entity shooter = this.world.getEntityByID(shooterID);
        if (shooter != null)
            this.setShooter(shooter);
    }
}
