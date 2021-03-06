package com.subwranglers.wickedarrows.nbt;

import com.subwranglers.wickedarrows.Strings;
import com.subwranglers.wickedarrows.entity.EntityVoidVacuum;
import com.subwranglers.wickedarrows.potion.PotionMobCaptured;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.WorldServer;

public class NBTEndlessVoid {
    public static final String KEY_ID = "id";
    public static final String KEY_VOID_SNARE = "VoidSnare_CreatureCaptured";
    public static final String KEY_VOID_HEALTH = "VoidSnare_VoidHealth";

    public static final float MAX_VOID_HEALTH = 60.f;

    public static boolean hasPlayerCapturedMob(Entity player) {
        return player != null && player instanceof EntityPlayer && player.getEntityData().hasKey(KEY_VOID_SNARE);
    }

    public static float getCapturedVoidHealth(Entity player) {
        if (!(player instanceof EntityPlayer))
            return -1.f;

        return player.getEntityData()
                .getCompoundTag(KEY_VOID_SNARE)
                .getCompoundTag("ForgeData")
                .getFloat(KEY_VOID_HEALTH);
    }

    public static float damageCapturedVoidHealth(Entity player) {
        if (!(player instanceof EntityPlayer))
            return -1.f;

        NBTTagCompound creatureData = player.getEntityData().getCompoundTag(KEY_VOID_SNARE).getCompoundTag("ForgeData");
        float newHealth = creatureData.getFloat(KEY_VOID_HEALTH) - 1.f;
        creatureData.setFloat(KEY_VOID_HEALTH, newHealth);

        return newHealth;
    }

    public static float getStartingVoidHealth(EntityLivingBase living) {
        // Hardcoded numbers arbitrary -- they were found by fiddling around.
        return 100.f - Math.min(living.getMaxHealth() * 3, MAX_VOID_HEALTH);
    }

    public static void captureMob(Entity player, EntityLivingBase mob) {
        if (!(player instanceof EntityPlayer) || !(mob instanceof EntityCreature))
            return;

        // Give the creature a starting VoidHealth value if it doesn't already have it
        if (!mob.getEntityData().hasKey(KEY_VOID_HEALTH))
            mob.getEntityData().setFloat(KEY_VOID_HEALTH, getStartingVoidHealth(mob));

        // Add creature to the player's "void"
        NBTTagCompound creature = mob.serializeNBT();

        // Add captured creature to the player's data
        player.getEntityData().setTag(NBTEndlessVoid.KEY_VOID_SNARE, creature);

        // Creature is being tracked, now remove them from the world
        player.world.removeEntity(mob);

        PotionMobCaptured.apply(player, mob);
    }

    public static EntityCreature releaseCapturedEntity(Entity player, RayTraceResult result) {
        BlockPos pos = result.entityHit != null ?
                result.entityHit.getPosition() : result.getBlockPos().offset(result.sideHit);
        return releaseCapturedEntity(player, pos.getX(), pos.getY(), pos.getZ());
    }

    public static EntityCreature releaseCapturedEntity(Entity player, int x, int y, int z) {
        if (!(player instanceof EntityPlayer) || !(player.world instanceof WorldServer))
            return null;

        WorldServer world = (WorldServer) player.world;

        NBTTagCompound data = player.getEntityData();
        NBTTagCompound creature = data.getCompoundTag(KEY_VOID_SNARE);

        Entity entity = null;
        if (creature.hasKey(KEY_ID)) {
            ResourceLocation resLoc = new ResourceLocation(creature.getString(KEY_ID));
            entity = EntityList.createEntityByIDFromName(resLoc, world);

            if (entity != null) {
                // Restore the mob's original state
                entity.readFromNBT(creature);

                // Force mob to the position the arrow provided and remove all of its velocity
                entity.setPosition(x + 0.5d, y, z + 0.5d);
                entity.motionX = 0.d;
                entity.motionY = 0.d;
                entity.motionZ = 0.d;
                player.world.spawnEntity(entity);
            }
        }

        // Mob released! Remove it from NBT.
        // Keeping this statement outside of the if statements above: if an entity gets captured and that data gets
        // corrupted then the worst that happens is you spend 1 arrow and the corrupted data gets removed.
        data.removeTag(KEY_VOID_SNARE);

        ((EntityPlayer) player).removePotionEffect(PotionMobCaptured.INSTANCE);

        return (EntityCreature) entity;
    }

    /**
     * Removes a mob that a player has captured using a {@link EntityVoidVacuum}
     *
     * @param player
     */
    public static void consumeMob(Entity player) {
        if (!(player instanceof EntityPlayer))
            return;

        NBTTagCompound data = player.getEntityData();

        if (data.hasKey(KEY_VOID_SNARE)) {
            data.removeTag(KEY_VOID_SNARE);
            player.sendMessage(new TextComponentString(Strings.MOB_CONSUMED_BY_VOID));
        }
    }
}
