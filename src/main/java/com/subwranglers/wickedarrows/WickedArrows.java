package com.subwranglers.wickedarrows;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import static com.subwranglers.wickedarrows.WickedArrows.MOD_ID;
import static net.minecraftforge.fml.common.Mod.*;

@Mod(modid = MOD_ID, useMetadata = true)
public class WickedArrows {

    public static final String MOD_ID = "wickedarrows";
    public static final String PROXY_CLIENT = "com.subwranglers.wickedarrows.client.ClientProxy";
    public static final String PROXY_SERVER = "com.subwranglers.wickedarrows.ServerProxy";

    @Instance(MOD_ID)
    public static WickedArrows instance;

    @SidedProxy(clientSide = PROXY_CLIENT, serverSide = PROXY_SERVER)
    public static ServerProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init();
    }

    @EventHandler
    public void postInit(FMLInitializationEvent event) {
        proxy.postInit();
    }
}
