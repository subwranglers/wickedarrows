package com.subwranglers.wickedarrows.registry;

import com.subwranglers.wickedarrows.block.BlockInvokedIce;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.model.ModelLoader;

import static com.subwranglers.wickedarrows.info.Names.*;

public class BlocksClient {

    public static void preInit() {
        ModelResourceLocation location = new ModelResourceLocation(name(INVOKED_ICE, QUALIFY));
        ModelLoader.setCustomModelResourceLocation(BlockInvokedIce.INSTANCE_ITEM, 0, location);
    }

    public static void init() {

    }

    public static void postInit() {

    }
}
