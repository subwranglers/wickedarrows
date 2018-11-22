package com.subwranglers.wickedarrows.registry;

import com.subwranglers.wickedarrows.blocks.BlockInvokedIce;
import com.subwranglers.wickedarrows.info.Names;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import util.S;

public class BlocksClient {

    public static void preInit() {
        ModelResourceLocation location = new ModelResourceLocation(S.qualify(Names.INVOKED_ICE));
        ModelLoader.setCustomModelResourceLocation(BlockInvokedIce.instanceItem, 0, location);
    }

    public static void init() {

    }

    public static void postInit() {

    }
}
