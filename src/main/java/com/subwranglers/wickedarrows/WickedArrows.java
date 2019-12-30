package com.subwranglers.wickedarrows;

import com.subwranglers.wickedarrows.block.InvokedIceBlock;
import com.subwranglers.wickedarrows.block.TorchArrowBlock;
import com.subwranglers.wickedarrows.client.renderer.IceArrowRenderer;
import com.subwranglers.wickedarrows.client.renderer.TorchArrowRenderer;
import com.subwranglers.wickedarrows.client.sound.IceCrackleSoundEvent;
import com.subwranglers.wickedarrows.client.sound.IceExplosionSoundEvent;
import com.subwranglers.wickedarrows.entity.IceArrowEntity;
import com.subwranglers.wickedarrows.entity.TorchArrowEntity;
import com.subwranglers.wickedarrows.item.IceArrowItem;
import com.subwranglers.wickedarrows.item.TorchArrowItem;
import com.subwranglers.wickedarrows.potion.IceEffect;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.Item;
import net.minecraft.potion.Effect;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.subwranglers.wickedarrows.WickedArrows.MOD_ID;
import static com.subwranglers.wickedarrows.info.Names.*;
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

        // Entity Rendering
        RenderingRegistry.registerEntityRenderingHandler(IceArrowEntity.class, IceArrowRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(TorchArrowEntity.class, TorchArrowRenderer::new);
}

    @Mod.EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {

        @SubscribeEvent
        public static void onRegisterBlocks(final RegistryEvent.Register<Block> event) {
            event.getRegistry().register(InvokedIceBlock.INSTANCE);
            event.getRegistry().register(TorchArrowBlock.INSTANCE);
        }

        @SubscribeEvent
        public static void onRegisterItems(final RegistryEvent.Register<Item> event) {
            event.getRegistry().register(IceArrowItem.INSTANCE);
            event.getRegistry().register(TorchArrowItem.INSTANCE);
        }

        @SubscribeEvent
        public static void onRegisterEntityTypes(final RegistryEvent.Register<EntityType<?>> event){
            event.getRegistry().register(
                    EntityType.Builder.<IceArrowEntity>create(IceArrowEntity::new, EntityClassification.MISC)
                            .build(ICE_ARROW)
                            .setRegistryName(ICE_ARROW)
            );

            event.getRegistry().register(
                    EntityType.Builder.<TorchArrowEntity>create(TorchArrowEntity::new, EntityClassification.MISC)
                            .build(TORCH_ARROW)
                            .setRegistryName(TORCH_ARROW)
            );
        }

        @SubscribeEvent
        public static void onRegisterContainerTypes(final RegistryEvent.Register<ContainerType<?>> event) {
        }

        @SubscribeEvent
        public static void onRegisterSoundEvents(final RegistryEvent.Register<SoundEvent> event){
            event.getRegistry().registerAll(
                    IceCrackleSoundEvent.INSTANCE,
                    IceExplosionSoundEvent.INSTANCE
            );

        }

        @SubscribeEvent
        public static void onRegisterEffects(final RegistryEvent.Register<Effect> event) {
            event.getRegistry().register(IceEffect.INSTANCE);
        }
    }
}
