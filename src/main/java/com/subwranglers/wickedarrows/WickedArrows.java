package com.subwranglers.wickedarrows;

import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.subwranglers.wickedarrows.WickedArrows.MOD_ID;
import static net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@Mod(MOD_ID)
public class WickedArrows {

    public static final String MOD_ID = "wickedarrows";

    //public static IProxy proxy = DistExecutor.runForDist(() -> ClientProxy::new, () -> ServerProxy::new);

    public static final String PROXY_CLIENT = "com.subwranglers.wickedarrows.client.ClientProxy";
    public static final String PROXY_SERVER = "com.subwranglers.wickedarrows.ServerProxy";

    private static final Logger LOGGER = LogManager.getLogger();

    public WickedArrows() {

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::commonSetup);

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
//        proxy.preInit();
//        NetworkRegistry.INSTANCE.registerGuiHandler(this, new WickedGuiHandler());

//        proxy.init();

//        proxy.postInit();
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        // do something that can only be done on the client
        LOGGER.info("Got game settings {}", event.getMinecraftSupplier().get().gameSettings);
    }

    @Mod.EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {

        @SubscribeEvent
        public static void onItemsRegistry(final RegistryEvent.Register<Item> event) {
        }

        @SubscribeEvent
        public static void onContainerRegistry(final RegistryEvent.Register<ContainerType<?>> event) {
        }
    }
}
