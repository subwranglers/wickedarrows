package com.subwranglers.wickedarrows.client;

import com.subwranglers.wickedarrows.block.BlockInvokedIce;
import com.subwranglers.wickedarrows.block.BlockTorchArrow;
import com.subwranglers.wickedarrows.client.render.*;
import com.subwranglers.wickedarrows.entity.*;
import com.subwranglers.wickedarrows.item.*;
import com.subwranglers.wickedarrows.ServerProxy;
import com.subwranglers.wickedarrows.client.sound.IceCrackleSoundEvent;
import com.subwranglers.wickedarrows.client.sound.IceExplosionSoundEvent;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
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
        loadLightburnArrow();
        loadSharpArrow();
        loadHungerArrow();
        loadMerlinArrow();
        loadVoidSnareArrow();
        loadRicochetArrow();
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
        // Torch Arrow Block
        ModelResourceLocation location = new ModelResourceLocation(name(TORCH_ARROW, QUALIFY & BLOCK));
        ModelLoader.setCustomModelResourceLocation(BlockTorchArrow.INSTANCE_ITEM, 0, location);

        // Torch Arrow Item Rendering
        ModelLoader.setCustomModelResourceLocation(
                ItemTorchArrow.INSTANCE,
                0,
                new ModelResourceLocation(name(TORCH_ARROW, QUALIFY))
        );

        // Entity Rendering
        RenderingRegistry.registerEntityRenderingHandler(EntityTorchArrow.class, RenderTorchArrow::new);
    }

    private static void loadLightburnArrow() {
        // Item Rendering
        ModelLoader.setCustomModelResourceLocation(
                ItemLightburnArrow.INSTANCE,
                0,
                new ModelResourceLocation(name(LIGHTBURN_ARROW, QUALIFY))
        );

        // Entity Rendering
        RenderingRegistry.registerEntityRenderingHandler(EntityLightburnArrow.class, RenderLightburnArrow::new);
    }

    private static void loadSharpArrow() {
        // Item Rendering
        ModelLoader.setCustomModelResourceLocation(
                ItemSharpArrow.INSTANCE,
                0,
                new ModelResourceLocation(name(SHARP_ARROW, QUALIFY))
        );

        // Entity Rendering
        RenderingRegistry.registerEntityRenderingHandler(EntitySharpArrow.class, RenderSharpArrow::new);
    }

    private static void loadHungerArrow() {
        // Item Rendering
        ModelLoader.setCustomModelResourceLocation(
                ItemHungerArrow.INSTANCE,
                0,
                new ModelResourceLocation(name(HUNGER_ARROW, QUALIFY))
        );

        // Entity Rendering
        RenderingRegistry.registerEntityRenderingHandler(EntityHungerArrow.class, RenderHungerArrow::new);
    }

    private static void loadMerlinArrow() {
        // Item Rendering
        ModelLoader.setCustomModelResourceLocation(
                ItemMerlinArrow.INSTANCE,
                0,
                new ModelResourceLocation(name(MERLIN_ARROW, QUALIFY))
        );

        // Entity Rendering
        RenderingRegistry.registerEntityRenderingHandler(EntityMerlinArrow.class, RenderMerlinArrow::new);
    }

    private static void loadVoidSnareArrow() {
        // Item Rendering
        ModelLoader.setCustomModelResourceLocation(
                ItemVoidSnareArrow.INSTANCE,
                0,
                new ModelResourceLocation(name(VOID_SNARE_ARROW, QUALIFY))
        );

        // Entity Rendering
        RenderingRegistry.registerEntityRenderingHandler(EntityVoidSnareArrow.class, RenderVoidSnareArrow::new);
    }

    private static void loadRicochetArrow() {
        // Item Rendering
        ModelLoader.setCustomModelResourceLocation(
                ItemRicochetArrow.INSTANCE,
                0,
                new ModelResourceLocation(name(RICOCHET_ARROW, QUALIFY))
        );

        // Entity Rendering
        RenderingRegistry.registerEntityRenderingHandler(EntityRicochetArrow.class, RenderRicochetArrow::new);
    }
}
