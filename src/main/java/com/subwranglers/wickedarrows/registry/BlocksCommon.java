package com.subwranglers.wickedarrows.registry;

import com.subwranglers.wickedarrows.blocks.BlockInvokedIce;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class BlocksCommon {

    public static void preInit() {
        ForgeRegistries.BLOCKS.register(BlockInvokedIce.INSTANCE);
    }

    public static void init() {

    }

    public static void postInit() {

    }
}
