package com.subwranglers.wickedarrows.entity;

import com.google.common.base.Predicate;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import util.MCConst;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

public class EntityVoidVacuum extends Entity {

    public static final double DEFAULT_RADIUS = 10;
    public static final double DEFAULT_STRENGTH = 1;

    private static final String KEY_RADIUS = "radius";
    private static final String KEY_STRENGTH = "strength";

    private static final int LIFETIME_TICKS = MCConst.TICKS_PER_SECOND;
//    private static final int LIFETIME_TICKS = MCConst.TICKS_PER_SECOND * 10;

    private double radius;
    private AxisAlignedBB aabbRadius;
    private Predicate<EntityCreature> predicate;

    private double strength;

    private EntityPlayer owner;

    public EntityVoidVacuum(World worldIn) {
        super(worldIn);
        setRadius(DEFAULT_RADIUS);
        setStrength(DEFAULT_STRENGTH);
    }

    @Override
    protected void entityInit() {

    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (ticksExisted >= LIFETIME_TICKS) {
            setDead();
            System.out.println("Vacuum removed from world");
        } else {
            List<EntityCreature> creatures =  world.getEntitiesWithinAABB(EntityCreature.class, aabbRadius, predicate);
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

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
        this.aabbRadius = new AxisAlignedBB(radius, radius, radius, -radius, -radius, -radius);
        this.predicate = creature -> {
            if (creature == null) return false;
            double dx = Math.pow(posX - creature.posX, 2);
            double dy = Math.pow(posY - creature.posY, 2);
            double dz = Math.pow(posZ - creature.posZ, 2);
            return Math.sqrt(dx + dy + dz) <= radius;
        };
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
    }
}
