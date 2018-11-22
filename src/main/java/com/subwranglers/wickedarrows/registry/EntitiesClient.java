package com.subwranglers.wickedarrows.registry;

import com.subwranglers.wickedarrows.WickedArrows;
import com.subwranglers.wickedarrows.entities.EntityIceArrow;
import com.subwranglers.wickedarrows.entities.render.RenderIceArrow;
import com.subwranglers.wickedarrows.info.Names;
import com.subwranglers.wickedarrows.proxy.Inits;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class EntitiesClient {

    public static void preInit() {
        registerRenderers();
        registerModels();
    }

    public static void init() {

    }

    public static void postInit() {

    }

    private static void registerRenderers() {
        RenderingRegistry.registerEntityRenderingHandler(EntityIceArrow.class, RenderIceArrow::new);
    }

    private static void registerModels() {
        OBJLoader.INSTANCE.addDomain(WickedArrows.MOD_ID);
    }
}
