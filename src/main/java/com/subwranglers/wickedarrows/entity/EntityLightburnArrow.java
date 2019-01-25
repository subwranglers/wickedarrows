package com.subwranglers.wickedarrows.entity;

import com.subwranglers.wickedarrows.base.EntityWArrow;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

public class EntityLightburnArrow extends EntityWArrow {

    public EntityLightburnArrow(World worldIn) {
        super(worldIn);
    }

    public EntityLightburnArrow(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
    }

    public EntityLightburnArrow(World worldIn, EntityLivingBase shooter) {
        super(worldIn, shooter);
    }


}
