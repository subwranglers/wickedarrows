package com.subwranglers.wickedarrows.entity;

import com.subwranglers.wickedarrows.base.EntityWArrow;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

public class EntityRicochetArrow extends EntityWArrow {

    public EntityRicochetArrow(World worldIn) {
        super(worldIn);
        setup();
    }

    public EntityRicochetArrow(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
        setup();
    }

    public EntityRicochetArrow(World worldIn, EntityLivingBase shooter) {
        super(worldIn, shooter);
        setup();
    }

    private void setup() {
        pickupStatus = PickupStatus.CREATIVE_ONLY;
    }
}
