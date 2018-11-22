package com.subwranglers.wickedarrows.registry;

import com.subwranglers.wickedarrows.items.ItemIceArrow;
import com.subwranglers.wickedarrows.proxy.Inits;
import com.sun.org.apache.xml.internal.security.Init;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class ItemsCommon  {

    public static void preInit() {
        registerItems();
    }

    public static void init() {

    }

    public static void postInit() {

    }

    public static void registerItems() {
        ForgeRegistries.ITEMS.register(ItemIceArrow.instance);
    }
}
