package com.subwranglers.wickedarrows.registry;

import com.subwranglers.wickedarrows.entity.EntityIceArrow;
import com.subwranglers.wickedarrows.render.RenderIceArrow;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class EntitiesClient {

    public static void preInit() {
        registerRenderers();
    }

    public static void init() {

    }

    public static void postInit() {

    }

    private static void registerRenderers() {
        RenderingRegistry.registerEntityRenderingHandler(EntityIceArrow.class, RenderIceArrow::new);
    }
}
