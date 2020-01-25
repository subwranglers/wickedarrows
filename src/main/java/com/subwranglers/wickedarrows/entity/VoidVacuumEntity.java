package com.subwranglers.wickedarrows.entity;

import com.subwranglers.wickedarrows.client.sound.SoundEventVoidVacuum;
import com.subwranglers.wickedarrows.instances.EntityTypes;
import com.subwranglers.wickedarrows.item.VoidSnareArrowItem;
import com.subwranglers.wickedarrows.voidspace.IVoidSpace;
import com.subwranglers.wickedarrows.voidspace.VoidSpaceCapability;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.function.Predicate;

public class VoidVacuumEntity extends Entity {

    public static final double DEFAULT_RADIUS = 10;
    public static final double DEFAULT_STRENGTH = 0.1;

    private static final double STRENGTH_MULTIPLIER = 0.7d;
    private static final String KEY_RADIUS = "radius";
    private static final String KEY_STRENGTH = "strength";
    // This shouldn't be changed, as the accompanying sound effect is 10 seconds long.
    private static final int LIFETIME_TICKS = 20 * 10;

    private PlayerEntity owner;
    private boolean mobCaptured;
    private double radius = DEFAULT_RADIUS;
    private double strength = DEFAULT_STRENGTH;
    private Predicate<Entity> predicate = entity -> {
        if (entity == null) return false;
        return entity.getPositionVec().subtractReverse(this.getPositionVec()).length() <= radius;
    };

    public VoidVacuumEntity(EntityType<? extends VoidVacuumEntity> type, World worldIn) {
        super(type, worldIn);
        noClip = true;
    }

    public VoidVacuumEntity(World worldIn, VoidSnareArrowEntity source) {
        this(EntityTypes.VOID_VACUUM, worldIn);
        this.setPosition(source.posX, source.posY, source.posZ);
        if(source.getShooter() instanceof PlayerEntity)
            this.owner = (PlayerEntity) source.getShooter();
    }

    @Override
    protected void registerData() {

    }

    @Override
    @ParametersAreNonnullByDefault
    public void applyEntityCollision(Entity entityIn) {
        super.applyEntityCollision(entityIn);

        if(world.isRemote || owner == null)
            return;

        // TODO: Change mobCaptured to a function that checks hasCapturedMob on the owner's voidspace handler
        if (!mobCaptured && entityIn instanceof CreatureEntity && getBoundingBox().intersects(entityIn.getBoundingBox())) {
            owner.getCapability(VoidSpaceCapability.VOID_SPACE_HANDLER).ifPresent(v -> {
                mobCaptured = v.captureMob((CreatureEntity) entityIn);
            });

            // Refund a Void Snare Arrow to the owner of this Void Vacuum since we captured a mob
            if(mobCaptured)
                 owner.addItemStackToInventory(new ItemStack(VoidSnareArrowItem.INSTANCE));

            // TODO: 06/02/19 Play successful sound effect and render a "voidey-like", wispy texture from the entity
            // captured to the player
        }
    }

    @Override
    public void onAddedToWorld() {
        super.onAddedToWorld();
        world.playSound(null, posX, posY, posZ, SoundEventVoidVacuum.INSTANCE, SoundCategory.AMBIENT, 0.5f, 1.f);
    }

    @Override
    public void tick() {
        super.tick();

        if (ticksExisted >= LIFETIME_TICKS) {
            this.remove();

            world.getEntitiesWithinAABB(Entity.class, getBoundingBox().grow(radius), predicate).forEach(entity ->
                    entity.addVelocity(
                            // This VoidVacuum is dead, send all entities away with an "explosion"
                            (entity.posX > posX ? strength : -strength) * STRENGTH_MULTIPLIER,
                            (entity.posY > posY ? strength : -strength) * STRENGTH_MULTIPLIER,
                            (entity.posZ > posZ ? strength : -strength) * STRENGTH_MULTIPLIER
                    ));

            // TODO: 06/02/19 Render slime explosion and sound FX
        } else if (ticksExisted > 20 / 2) {
            world.getEntitiesWithinAABB(Entity.class, getBoundingBox().grow(radius), predicate).forEach(entity -> {
                entity.addVelocity(
                        // Pull entities towards this VoidVacuum
                        entity.posX > posX ? -strength : strength,
                        entity.posY > posY ? -strength : strength,
                        entity.posZ > posZ ? -strength : strength
                );
                applyEntityCollision(entity);
            });
        }
    }


    /*
        Getters and Setters
     */

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public void setStrength(double strength) {
        this.strength = strength;
    }

    public PlayerEntity getOwner() {
        return owner;
    }

    public void setOwner(PlayerEntity owner) {
        this.owner = owner;
        this.mobCaptured = owner.getCapability(VoidSpaceCapability.VOID_SPACE_HANDLER)
                .map(IVoidSpace::hasCapturedMob).orElse(false);
    }


    /*
        NBT
     */

    @Override
    @ParametersAreNonnullByDefault
    protected void readAdditional(CompoundNBT compound) {
        radius = compound.getDouble(KEY_RADIUS);
        strength = compound.getDouble(KEY_STRENGTH);
        if (owner != null) {
            compound.putUniqueId("OwnerUUID", owner.getUniqueID());
        }

    }

    @Override
    @ParametersAreNonnullByDefault
    protected void writeAdditional(CompoundNBT compound) {
        compound.putDouble(KEY_RADIUS, radius);
        compound.putDouble(KEY_STRENGTH, strength);

        if(this.world instanceof ServerWorld){
            if (compound.hasUniqueId("OwnerUUID")) {
                Entity owner = ((ServerWorld)this.world).getEntityByUuid(compound.getUniqueId("OwnerUUID"));
                if(owner instanceof PlayerEntity)
                    this.owner = (PlayerEntity) owner;
            }
        }
    }

    @Nonnull
    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
