package com.subwranglers.wickedarrows.entity;

import com.subwranglers.wickedarrows.base.EntityWArrow;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

public class EntityMerlinArrow extends EntityWArrow {

    public EntityMerlinArrow(World worldIn) {
        super(worldIn);
    }

    public EntityMerlinArrow(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
    }

    public EntityMerlinArrow(World worldIn, EntityLivingBase shooter) {
        super(worldIn, shooter);
    }
}
