package com.subwranglers.wickedarrows.entity;

import com.subwranglers.wickedarrows.client.sound.SoundEventVoidVacuum;
import com.subwranglers.wickedarrows.item.ItemVoidSnareArrow;
import com.subwranglers.wickedarrows.nbt.NBTEndlessVoid;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import util.MCConst;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.function.Predicate;

public class EntityVoidVacuum extends Entity {

    public static final double DEFAULT_RADIUS = 10;
    public static final double DEFAULT_STRENGTH = 0.1;
    private static final double STRENGTH_MULTIPLIER = 0.7d;

    private static final String KEY_RADIUS = "radius";
    private static final String KEY_STRENGTH = "strength";

    // This shouldn't be changed, as the accompanying sound effect is 10 seconds long.
    private static final int LIFETIME_TICKS = MCConst.TICKS_PER_SECOND * 10;

    private EntityPlayer owner;

    private double radius = DEFAULT_RADIUS;
    private AxisAlignedBB aabbRadius;

    private AxisAlignedBB captureAabb;
    private boolean mobCaptured;

    public int renderAngle;

    private Predicate<Entity> predicate = entity -> {
        if (entity == null) return false;
        double dx = Math.pow(posX - entity.posX, 2);
        double dy = Math.pow(posY - entity.posY, 2);
        double dz = Math.pow(posZ - entity.posZ, 2);
        return Math.sqrt(dx + dy + dz) <= radius;
    };

    private double strength = DEFAULT_STRENGTH;

    public EntityVoidVacuum(World worldIn) {
        super(worldIn);
        noClip = true;
        updateAABB();
    }

    @Override
    protected void entityInit() {
    }

    @Override
    @ParametersAreNonnullByDefault
    public void applyEntityCollision(Entity entityIn) {
        super.applyEntityCollision(entityIn);
        AxisAlignedBB entityCollision = entityIn.getEntityBoundingBox();

        if (entityIn instanceof EntityLivingBase && captureAabb.intersects(entityCollision) && !mobCaptured) {
            NBTEndlessVoid.captureMob(owner, (EntityLivingBase) entityIn);
            mobCaptured = true;

            // Refund a Void Snare Arrow to the owner of this Void Vacuum since we captured a mob
            if (owner != null)
                owner.addItemStackToInventory(new ItemStack(ItemVoidSnareArrow.INSTANCE));

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
    public void onUpdate() {
        super.onUpdate();

        if (ticksExisted >= LIFETIME_TICKS) {

            if (!world.isRemote)
                setDead();

            world.getEntitiesWithinAABB(Entity.class, aabbRadius, predicate::test).forEach(entity ->
                    entity.addVelocity(
                            // This VoidVacuum is dead, send all entities away with an "explosion"
                            (entity.posX > posX ? strength : -strength) * STRENGTH_MULTIPLIER,
                            (entity.posY > posY ? strength : -strength) * STRENGTH_MULTIPLIER,
                            (entity.posZ > posZ ? strength : -strength) * STRENGTH_MULTIPLIER
                    ));

            // TODO: 06/02/19 Render slime explosion and sound FX
        } else if (ticksExisted > MCConst.TICKS_PER_SECOND / 2) {
            world.getEntitiesWithinAABB(Entity.class, aabbRadius, predicate::test).forEach(entity -> {
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

        NBT

     */

    @Override
    @ParametersAreNonnullByDefault
    protected void readEntityFromNBT(NBTTagCompound compound) {
        radius = compound.getDouble(KEY_RADIUS);
        strength = compound.getDouble(KEY_STRENGTH);
    }

    @Override
    @ParametersAreNonnullByDefault
    protected void writeEntityToNBT(NBTTagCompound compound) {
        compound.setDouble(KEY_RADIUS, radius);
        compound.setDouble(KEY_STRENGTH, strength);
    }

    /*

        Getters and Setters

     */

    @Override
    public void setPosition(double x, double y, double z) {
        super.setPosition(x, y, z);
        updateAABB();
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
        updateAABB();
    }

    public double getStrength() {
        return strength;
    }

    public void setStrength(double strength) {
        this.strength = strength;
    }

    public EntityPlayer getOwner() {
        return owner;
    }

    public void setOwner(EntityPlayer owner) {
        this.owner = owner;
        this.mobCaptured = NBTEndlessVoid.hasPlayerCapturedMob(owner);
    }

    private void updateAABB() {
        aabbRadius = new AxisAlignedBB(
                Math.floor(posX) + -radius,
                Math.floor(posY) + -radius,
                Math.floor(posZ) + -radius,
                Math.floor(posX) + radius,
                Math.floor(posY) + radius,
                Math.floor(posZ) + radius);

        captureAabb = new AxisAlignedBB(posX, posY, posZ, posX + 1, posY + 1, posZ + 1);
    }
}
