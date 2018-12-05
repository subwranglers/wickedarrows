package com.subwranglers.wickedarrows.proxy;

import com.subwranglers.wickedarrows.WickedArrows;
import com.subwranglers.wickedarrows.block.BlockInvokedIce;
import com.subwranglers.wickedarrows.entity.EntityIceArrow;
import com.subwranglers.wickedarrows.entity.EntityTorchArrow;
import com.subwranglers.wickedarrows.item.ItemIceArrow;
import com.subwranglers.wickedarrows.item.ItemTorchArrow;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import static com.subwranglers.wickedarrows.info.Names.*;

public class ServerProxy {

    private static int id = 1;

    public void preInit() {
        registerBlocks();
        registerEntities();
        registerItems();
    }

    public void init() {

    }

    public void postInit() {

    }

    private static void registerBlocks() {
        ForgeRegistries.BLOCKS.register(BlockInvokedIce.INSTANCE);
    }

    private static void registerEntities() {
        registerEntity(EntityIceArrow.class, ICE_ARROW, 128, 3, true);
        registerEntity(EntityTorchArrow.class, TORCH_ARROW, 128, 3, true);
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

    public static void registerItems() {
        ForgeRegistries.ITEMS.register(ItemIceArrow.INSTANCE);
        ForgeRegistries.ITEMS.register(ItemTorchArrow.INSTANCE);
    }
}
