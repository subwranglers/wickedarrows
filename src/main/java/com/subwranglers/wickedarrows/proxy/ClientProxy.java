package com.subwranglers.wickedarrows.proxy;

import com.subwranglers.wickedarrows.block.BlockInvokedIce;
import com.subwranglers.wickedarrows.entity.EntityIceArrow;
import com.subwranglers.wickedarrows.entity.EntityShotArrow;
import com.subwranglers.wickedarrows.entity.EntityTorchArrow;
import com.subwranglers.wickedarrows.item.*;
import com.subwranglers.wickedarrows.render.RenderIceArrow;
import com.subwranglers.wickedarrows.render.RenderShotArrow;
import com.subwranglers.wickedarrows.render.RenderTorchArrow;
import com.subwranglers.wickedarrows.sound.IceCrackleSoundEvent;
import com.subwranglers.wickedarrows.sound.IceExplosionSoundEvent;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.entity.RenderTippedArrow;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import static com.subwranglers.wickedarrows.info.Names.*;

public class ClientProxy extends ServerProxy {

    public void preInit() {
        super.preInit();

        loadShotArrow();
        loadIceArrow();
        loadTorchArrow();
    }

    public void init() {
        super.init();
    }

    public void postInit() {
        super.postInit();
    }

    private static void loadShotArrow() {
        ModelLoader.setCustomModelResourceLocation(
                ItemShotArr2w.INSTANCE,
                0,
                new ModelResourceLocation(name(SHOT_ARR2W, QUALIFY))
        );

        ModelLoader.setCustomModelResourceLocation(
                ItemShotArr3w.INSTANCE,
                0,
                new ModelResourceLocation(name(SHOT_ARR3W, QUALIFY))
        );

        ModelLoader.setCustomModelResourceLocation(
                ItemShotArr4w.INSTANCE,
                0,
                new ModelResourceLocation(name(SHOT_ARR4W, QUALIFY))
        );

        ModelLoader.setCustomModelResourceLocation(
                ItemShotArr5w.INSTANCE,
                0,
                new ModelResourceLocation(name(SHOT_ARR5W, QUALIFY))
        );

        ModelLoader.setCustomModelResourceLocation(
                ItemShotArr6w.INSTANCE,
                0,
                new ModelResourceLocation(name(SHOT_ARR6W, QUALIFY))
        );

        ModelLoader.setCustomModelResourceLocation(
                ItemShotArr7w.INSTANCE,
                0,
                new ModelResourceLocation(name(SHOT_ARR7W, QUALIFY))
        );

        ModelLoader.setCustomModelResourceLocation(
                ItemShotArr8w.INSTANCE,
                0,
                new ModelResourceLocation(name(SHOT_ARR8W, QUALIFY))
        );

        ModelLoader.setCustomModelResourceLocation(
                ItemShotArr9w.INSTANCE,
                0,
                new ModelResourceLocation(name(SHOT_ARR9W, QUALIFY))
        );

        RenderingRegistry.registerEntityRenderingHandler(EntityShotArrow.class, RenderShotArrow::new);
    }

    private static void loadIceArrow() {
        // Invoked Ice Rendering
        ModelResourceLocation location = new ModelResourceLocation(name(INVOKED_ICE, QUALIFY));
        ModelLoader.setCustomModelResourceLocation(BlockInvokedIce.INSTANCE_ITEM, 0, location);

        // Ice Arrow Item Rendering
        ModelLoader.setCustomModelResourceLocation(
                ItemIceArrow.INSTANCE,
                0,
                new ModelResourceLocation(name(ICE_ARROW, QUALIFY))
        );

        // Entity Rendering
        RenderingRegistry.registerEntityRenderingHandler(EntityIceArrow.class, RenderIceArrow::new);

        // Sounds
        ForgeRegistries.SOUND_EVENTS.register(IceCrackleSoundEvent.INSTANCE);
        ForgeRegistries.SOUND_EVENTS.register(IceExplosionSoundEvent.INSTANCE);
    }

    private static void loadTorchArrow() {
        // Torch Arrow Item Rendering
        ModelLoader.setCustomModelResourceLocation(
                ItemTorchArrow.INSTANCE,
                0,
                new ModelResourceLocation(name(TORCH_ARROW, QUALIFY))
        );

        // Entity Rendering
        RenderingRegistry.registerEntityRenderingHandler(EntityTorchArrow.class, RenderTorchArrow::new);
    }
}
