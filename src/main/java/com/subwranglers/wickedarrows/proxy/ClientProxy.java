package com.subwranglers.wickedarrows.proxy;

import com.subwranglers.wickedarrows.block.BlockInvokedIce;
import com.subwranglers.wickedarrows.entity.EntityIceArrow;
import com.subwranglers.wickedarrows.item.ItemIceArrow;
import com.subwranglers.wickedarrows.item.ItemTorchArrow;
import com.subwranglers.wickedarrows.render.RenderIceArrow;
import com.subwranglers.wickedarrows.sound.IceCrackleSoundEvent;
import com.subwranglers.wickedarrows.sound.IceExplosionSoundEvent;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import static com.subwranglers.wickedarrows.info.Names.*;

public class ClientProxy extends ServerProxy {

    public void preInit() {
        super.preInit();
        loadBlocks();
        loadModels();
        registerRenderers();
        registerSounds();
    }

    public void init() {
        super.init();
    }

    public void postInit() {
        super.postInit();
    }

    private static void loadBlocks() {
        ModelResourceLocation location = new ModelResourceLocation(name(INVOKED_ICE, QUALIFY));
        ModelLoader.setCustomModelResourceLocation(BlockInvokedIce.INSTANCE_ITEM, 0, location);
    }

    private static void loadModels() {
        // Ice Arrow
        ModelLoader.setCustomModelResourceLocation(
                ItemIceArrow.INSTANCE,
                0,
                new ModelResourceLocation(name(ICE_ARROW, QUALIFY))
        );

        // Torch Arrow
        ModelLoader.setCustomModelResourceLocation(
                ItemTorchArrow.INSTANCE,
                0,
                new ModelResourceLocation(name(TORCH_ARROW, QUALIFY))
        );
    }

    private static void registerRenderers() {
        RenderingRegistry.registerEntityRenderingHandler(EntityIceArrow.class, RenderIceArrow::new);
    }

    private static void registerSounds() {
        ForgeRegistries.SOUND_EVENTS.register(IceCrackleSoundEvent.INSTANCE);
        ForgeRegistries.SOUND_EVENTS.register(IceExplosionSoundEvent.INSTANCE);
    }
}
