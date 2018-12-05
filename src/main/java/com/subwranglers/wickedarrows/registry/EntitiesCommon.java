package com.subwranglers.wickedarrows.registry;

import com.subwranglers.wickedarrows.WickedArrows;
import com.subwranglers.wickedarrows.entity.EntityIceArrow;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.common.registry.EntityRegistry;

import static com.subwranglers.wickedarrows.info.Names.*;

public class EntitiesCommon {

    private static int id = 1;

    public static void preInit() {
        registerEntity(EntityIceArrow.class, ICE_ARROW, 64, 3, true);
    }

    public static void init() {

    }

    public static void postInit() {

    }

    private static void registerEntity(Class<? extends Entity> entity,
                                String name,
                                int trackingRange,
                                int updateFrequency,
                                boolean sendsVelocityUpdates) {
        ModelResourceLocation loc = new ModelResourceLocation(name(name, QUALIFY));
//        ResourceLocation loc = new ResourceLocation(name(name, QUALIFY));
        EntityRegistry.registerModEntity(
                loc,
                entity,
                name(name, ENTITY),
                id++,
                WickedArrows.instance,
                trackingRange,
                updateFrequency,
                sendsVelocityUpdates
        );
    }
}
