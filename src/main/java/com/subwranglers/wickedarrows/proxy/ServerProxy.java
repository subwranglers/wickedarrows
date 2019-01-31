package com.subwranglers.wickedarrows.proxy;

import com.subwranglers.wickedarrows.WickedArrows;
import com.subwranglers.wickedarrows.block.BlockInvokedIce;
import com.subwranglers.wickedarrows.block.BlockTorchArrow;
import com.subwranglers.wickedarrows.entity.*;
import com.subwranglers.wickedarrows.item.*;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import static com.subwranglers.wickedarrows.info.Names.*;

public class ServerProxy {

    private static int id = 1;

    public void preInit() {
        registerShotArrow();
        registerIceArrow();
        registerTorchArrow();
        registerLightburnArrow();
        registerSharpArrow();
    }

    public void init() {

    }

    public void postInit() {

    }

    private static void registerEntity(Class<? extends Entity> entity,
                                       String name,
                                       int trackingRange,
                                       int updateFrequency,
                                       boolean sendsVelocityUpdates) {
        ModelResourceLocation loc = new ModelResourceLocation(name(name, QUALIFY));
        EntityRegistry.registerModEntity(
                loc,
                entity,
                name(name, ENTITY),
                id++,
                WickedArrows.instance,
                trackingRange,
                updateFrequency,
                sendsVelocityUpdates
        );
    }

    private static void registerEntity(Class<? extends Entity> entity, String name) {
        registerEntity(entity, name, 128, 3, true);
    }

    public static void registerShotArrow() {
        ForgeRegistries.ITEMS.register(ItemShotArr2w.INSTANCE);
        ForgeRegistries.ITEMS.register(ItemShotArr3w.INSTANCE);
        ForgeRegistries.ITEMS.register(ItemShotArr4w.INSTANCE);
        ForgeRegistries.ITEMS.register(ItemShotArr5w.INSTANCE);
        ForgeRegistries.ITEMS.register(ItemShotArr6w.INSTANCE);
        ForgeRegistries.ITEMS.register(ItemShotArr7w.INSTANCE);
        ForgeRegistries.ITEMS.register(ItemShotArr8w.INSTANCE);
        ForgeRegistries.ITEMS.register(ItemShotArr9w.INSTANCE);

        registerEntity(EntityShotArrow.class, SHOT_ARR2W);
    }

    public static void registerIceArrow() {
        ForgeRegistries.BLOCKS.register(BlockInvokedIce.INSTANCE);
        ForgeRegistries.ITEMS.register(ItemIceArrow.INSTANCE);
        registerEntity(EntityIceArrow.class, ICE_ARROW);
    }

    public static void registerTorchArrow() {
        ForgeRegistries.BLOCKS.register(BlockTorchArrow.INSTANCE);
        ForgeRegistries.ITEMS.register(ItemTorchArrow.INSTANCE);
        registerEntity(EntityTorchArrow.class, TORCH_ARROW);
    }

    private static void registerLightburnArrow() {
        ForgeRegistries.ITEMS.register(ItemLightburnArrow.INSTANCE);
        registerEntity(EntityLightburnArrow.class, LIGHTBURN_ARROW);
    }

    private static void registerSharpArrow() {
        ForgeRegistries.ITEMS.register(ItemSharpArrow.INSTANCE);
        registerEntity(EntitySharpArrow.class, SHARP_ARROW);
    }
}
