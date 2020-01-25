package com.subwranglers.wickedarrows;

import com.subwranglers.wickedarrows.block.InvokedIceBlock;
import com.subwranglers.wickedarrows.block.TorchArrowBlock;
import com.subwranglers.wickedarrows.client.renderer.*;
import com.subwranglers.wickedarrows.client.sound.IceCrackleSoundEvent;
import com.subwranglers.wickedarrows.client.sound.IceExplosionSoundEvent;
import com.subwranglers.wickedarrows.entity.*;
import com.subwranglers.wickedarrows.info.Names;
import com.subwranglers.wickedarrows.item.*;
import com.subwranglers.wickedarrows.potion.*;
import com.subwranglers.wickedarrows.voidspace.IVoidSpace;
import com.subwranglers.wickedarrows.voidspace.VoidSpace;
import com.subwranglers.wickedarrows.voidspace.VoidSpaceCapability;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.Item;
import net.minecraft.potion.Effect;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

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
        VoidSpaceCapability.register();
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        // do something that can only be done on the client
        LOGGER.info("Got game settings {}", event.getMinecraftSupplier().get().gameSettings);

        // Entity Rendering
        RenderingRegistry.registerEntityRenderingHandler(HungerArrowEntity.class, HungerArrowRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(IceArrowEntity.class, IceArrowRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(LightburnArrowEntity.class, LightburnArrowRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(MerlinArrowEntity.class, MerlinArrowRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(RicochetArrowEntity.class, RicochetArrowRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(SharpArrowEntity.class, SharpArrowRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ShotArrowEntity.class, ShotArrowRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(TorchArrowEntity.class, TorchArrowRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(VoidSnareArrowEntity.class, VoidSnareArrowRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(VoidVacuumEntity.class, VoidVacuumRenderer::new);
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
            event.getRegistry().register(HungerArrowItem.INSTANCE);
            event.getRegistry().register(IceArrowItem.INSTANCE);
            event.getRegistry().register(LightburnArrowItem.INSTANCE);
            event.getRegistry().register(MerlinArrowItem.INSTANCE);
            event.getRegistry().register(RicochetArrowItem.INSTANCE);
            event.getRegistry().register(SharpArrowItem.INSTANCE);
            event.getRegistry().register(new ShotArrowItem(2));
            event.getRegistry().register(new ShotArrowItem(3));
            event.getRegistry().register(new ShotArrowItem(4));
            event.getRegistry().register(new ShotArrowItem(5));
            event.getRegistry().register(new ShotArrowItem(6));
            event.getRegistry().register(new ShotArrowItem(7));
            event.getRegistry().register(new ShotArrowItem(8));
            event.getRegistry().register(new ShotArrowItem(9));
            event.getRegistry().register(TorchArrowItem.INSTANCE);
            event.getRegistry().register(VoidSnareArrowItem.INSTANCE);
        }

        @SubscribeEvent
        public static void onRegisterEntityTypes(final RegistryEvent.Register<EntityType<?>> event){
            event.getRegistry().registerAll(
                RegistryEvents.<HungerArrowEntity>createEntityType(HUNGER_ARROW, HungerArrowEntity::new),
                RegistryEvents.<IceArrowEntity>createEntityType(ICE_ARROW, IceArrowEntity::new),
                RegistryEvents.<LightburnArrowEntity>createEntityType(LIGHTBURN_ARROW, LightburnArrowEntity::new),
                RegistryEvents.<MerlinArrowEntity>createEntityType(MERLIN_ARROW, MerlinArrowEntity::new),
                RegistryEvents.<RicochetArrowEntity>createEntityType(RICOCHET_ARROW, RicochetArrowEntity::new),
                RegistryEvents.<SharpArrowEntity>createEntityType(SHARP_ARROW, SharpArrowEntity::new),
                RegistryEvents.<ShotArrowEntity>createEntityType(SHOT_ARROW, ShotArrowEntity::new),
                RegistryEvents.<TorchArrowEntity>createEntityType(TORCH_ARROW, TorchArrowEntity::new),
                RegistryEvents.<VoidSnareArrowEntity>createEntityType(VOID_SNARE_ARROW, VoidSnareArrowEntity::new),
                RegistryEvents.createEntityType(VOID_VACUUM, EntityType.Builder
                        .<VoidVacuumEntity>create(VoidVacuumEntity::new, EntityClassification.MISC)
                        .size(1, 1)
                )
            );
        }

        @SubscribeEvent
        public static void onRegisterContainerTypes(final RegistryEvent.Register<ContainerType<?>> event) {
        }

        @SubscribeEvent
        public static void onRegisterSoundEvents(final RegistryEvent.Register<SoundEvent> event){
            event.getRegistry().register(IceCrackleSoundEvent.INSTANCE);
            event.getRegistry().register(IceExplosionSoundEvent.INSTANCE);
        }

        @SubscribeEvent
        public static void onRegisterEffects(final RegistryEvent.Register<Effect> event) {
            event.getRegistry().register(BaitEffect.INSTANCE);
            event.getRegistry().register(BleedEffect.INSTANCE);
            event.getRegistry().register(BrittleBonesEffect.INSTANCE);
            event.getRegistry().register(IceEffect.INSTANCE);
            event.getRegistry().register(MobCapturedEffect.INSTANCE);
        }

        private static <T extends Entity> EntityType<?> createEntityType(String registryName, EntityType.IFactory<T> factoryIn) {
            return EntityType.Builder.create(factoryIn, EntityClassification.MISC)
                    .build(registryName)
                    .setRegistryName(registryName);
        }

        private static <T extends Entity> EntityType<?> createEntityType(String key, EntityType.Builder<T> builder) {
            return  builder.build(key).setRegistryName(key);
        }
    }
}
