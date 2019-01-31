package com.subwranglers.wickedarrows.entity;

import com.subwranglers.wickedarrows.base.EntityWArrow;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

public class EntitySharpArrow extends EntityWArrow {

    public EntitySharpArrow(World worldIn) {
        super(worldIn);
    }

    public EntitySharpArrow(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
    }

    public EntitySharpArrow(World worldIn, EntityLivingBase shooter) {
        super(worldIn, shooter);
    }
}
