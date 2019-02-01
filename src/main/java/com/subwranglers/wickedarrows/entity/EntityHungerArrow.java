package com.subwranglers.wickedarrows.entity;

import com.subwranglers.wickedarrows.base.EntityWArrow;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

public class EntityHungerArrow extends EntityWArrow {

    public EntityHungerArrow(World worldIn) {
        super(worldIn);
    }

    public EntityHungerArrow(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
    }

    public EntityHungerArrow(World worldIn, EntityLivingBase shooter) {
        super(worldIn, shooter);
    }
}
