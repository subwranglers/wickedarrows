package com.subwranglers.wickedarrows;

import com.subwranglers.wickedarrows.block.BlockArrowWorkbench;
import com.subwranglers.wickedarrows.block.BlockInvokedIce;
import com.subwranglers.wickedarrows.block.BlockTorchArrow;
import com.subwranglers.wickedarrows.entity.*;
import com.subwranglers.wickedarrows.inventory.WickedGuiHandler;
import com.subwranglers.wickedarrows.item.*;
import com.subwranglers.wickedarrows.potion.*;
import com.subwranglers.wickedarrows.tileentity.TileEntityArrowWorkbench;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;

import static com.subwranglers.wickedarrows.info.Names.*;

public class ServerProxy {

    private static int id = 1;

    public void preInit() {
        // Arrows
        registerShotArrow();
        registerIceArrow();
        registerTorchArrow();
        registerLightburnArrow();
        registerSharpArrow();
        registerHungerArrow();
        registerMerlinArrow();
        registerVoidSnareArrow();
        registerSeekingArrow();
        registerRicochetArrow();

        // Blocks
        registerArrowWorkbench();

        // Other
        registerVoidVacuum();
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

    /*

        Arrows

     */

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
        ForgeRegistries.POTIONS.register(PotionIce.INSTANCE);
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
        ForgeRegistries.POTIONS.register(PotionBleed.INSTANCE);
    }

    private static void registerHungerArrow() {
        ForgeRegistries.ITEMS.register(ItemHungerArrow.INSTANCE);
        registerEntity(EntityHungerArrow.class, HUNGER_ARROW);
        ForgeRegistries.POTIONS.register(PotionBait.INSTANCE);
    }

    private static void registerMerlinArrow() {
        ForgeRegistries.ITEMS.register(ItemMerlinArrow.INSTANCE);
        registerEntity(EntityMerlinArrow.class, MERLIN_ARROW);
        ForgeRegistries.POTIONS.register(PotionBrittleBones.INSTANCE);
    }

    private static void registerVoidSnareArrow() {
        ForgeRegistries.ITEMS.register(ItemVoidSnareArrow.INSTANCE);
        registerEntity(EntityVoidSnareArrow.class, VOID_SNARE_ARROW);
        ForgeRegistries.POTIONS.register(PotionMobCaptured.INSTANCE);
    }

    private static void registerSeekingArrow() {
        ForgeRegistries.ITEMS.register(ItemSeekingArrow.INSTANCE);
        registerEntity(EntitySeekingArrow.class, SEEKING_ARROW);
    }

    private static void registerRicochetArrow() {
        ForgeRegistries.ITEMS.register(ItemRicochetArrow.INSTANCE);
        registerEntity(EntityRicochetArrow.class, RICOCHET_ARROW);
    }

    /*

        Blocks

     */

    private static void registerArrowWorkbench() {
        ForgeRegistries.BLOCKS.register(BlockArrowWorkbench.INSTANCE);
        GameRegistry.registerTileEntity(TileEntityArrowWorkbench.class, new ResourceLocation(WickedArrows.MOD_ID, ARROW_WORKBENCH));
        ForgeRegistries.ITEMS.register(BlockArrowWorkbench.INSTANCE_ITEM);

    }

    /*

        Other

     */

    private static void registerVoidVacuum() {
        registerEntity(EntityVoidVacuum.class, VOID_VACUUM);
    }
}
