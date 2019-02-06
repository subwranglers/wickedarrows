package com.subwranglers.wickedarrows.entity;

import com.subwranglers.wickedarrows.base.EntityWArrow;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

public class EntityVoidSnareArrow extends EntityWArrow {

    public EntityVoidSnareArrow(World worldIn) {
        super(worldIn);
        setup();
    }

    public EntityVoidSnareArrow(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
        setup();
    }

    public EntityVoidSnareArrow(World worldIn, EntityLivingBase shooter) {
        super(worldIn, shooter);
        setup();
    }

    private void setup () {
        pickupStatus = PickupStatus.CREATIVE_ONLY;
    }
}
