package com.subwranglers.wickedarrows.entity;

import com.subwranglers.wickedarrows.base.EntityWArrow;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

public class EntitySeekingArrow extends EntityWArrow {

    public EntitySeekingArrow(World worldIn) {
        super(worldIn);
        setup();
    }

    public EntitySeekingArrow(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
        setup();
    }

    public EntitySeekingArrow(World worldIn, EntityLivingBase shooter) {
        super(worldIn, shooter);
        setup();
    }

    private void setup() {
        pickupStatus = PickupStatus.CREATIVE_ONLY;
    }
}
