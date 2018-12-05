package com.subwranglers.wickedarrows.registry;

import com.subwranglers.wickedarrows.item.ItemIceArrow;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.model.ModelLoader;

import static com.subwranglers.wickedarrows.info.Names.*;

public class ItemsClient {

    public static void preInit() {
        loadModels();
    }

    public static void init() {

    }

    public static void postInit() {

    }

    private static void loadModels() {
        // Ice Arrow
        ModelLoader.setCustomModelResourceLocation(
                ItemIceArrow.INSTANCE,
                0,
                new ModelResourceLocation(name(ICE_ARROW, QUALIFY))
        );
    }

}
