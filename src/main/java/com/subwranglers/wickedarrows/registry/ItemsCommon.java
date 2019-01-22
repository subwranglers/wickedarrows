package com.subwranglers.wickedarrows.registry;

import com.subwranglers.wickedarrows.item.ItemIceArrow;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class ItemsCommon  {

    public static void preInit() {
    }

    public static void init() {

    }

    public static void postInit() {

    }

    public static void registerItems() {
        ForgeRegistries.ITEMS.register(ItemIceArrow.INSTANCE);
    }
}
