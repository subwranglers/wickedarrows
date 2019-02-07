package com.subwranglers.wickedarrows.entity;

import com.subwranglers.wickedarrows.base.EntityWArrow;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class EntityVoidSnareArrow extends EntityWArrow {

    public static final String KEY_ID = "id";
    public static final String KEY_VOID_SNARE = "VoidSnare_CreatureCaptured";

    public EntityVoidSnareArrow(World worldIn) {
        super(worldIn);
    }

    public EntityVoidSnareArrow(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
    }

    public EntityVoidSnareArrow(World worldIn, EntityLivingBase shooter) {
        super(worldIn, shooter);
        pickupStatus = PickupStatus.CREATIVE_ONLY;
    }

    @Override
    protected void onBlockHit(RayTraceResult trace) {
        if (hasPlayerCapturedMob(shootingEntity))
            releaseCapturedEntity(shootingEntity, trace);
    }

    @Override
    protected void arrowHit(EntityLivingBase living) {
        try {
            if (hasPlayerCapturedMob(shootingEntity)) {
                releaseCapturedEntity(shootingEntity, living);
            } else {
                // Add creature to the player's "void"
                NBTTagCompound creature = living.serializeNBT();
                shootingEntity.getEntityData().setTag(KEY_VOID_SNARE, creature);

                // Creature is being tracked, now remove them from the world
                world.removeEntity(living);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean hasPlayerCapturedMob(Entity player) {
        return player != null && player instanceof EntityPlayer && player.getEntityData().hasKey(KEY_VOID_SNARE);
    }

    public static void releaseCapturedEntity(Entity player, RayTraceResult result) {
        BlockPos pos = result.getBlockPos().offset(result.sideHit);
        releaseCapturedEntity(player, pos.getX(), pos.getY(), pos.getZ());
    }

    public static void releaseCapturedEntity(Entity player, EntityLivingBase living) {
        BlockPos pos = living.getPosition();
        releaseCapturedEntity(player, pos.getX(), pos.getY(), pos.getZ());
    }

    public static void releaseCapturedEntity(Entity player, int x, int y, int z) {
        if (!(player instanceof EntityPlayer) || !(player.world instanceof WorldServer))
            return;

        WorldServer world = (WorldServer) player.world;

        NBTTagCompound data = player.getEntityData();
        NBTTagCompound creature = data.getCompoundTag(KEY_VOID_SNARE);

        if (creature.hasKey("id")) {
            ResourceLocation resLoc = new ResourceLocation(creature.getString(KEY_ID));
            Entity entity = EntityList.createEntityByIDFromName(resLoc, world);

            if (entity != null) {
                // Restore the mob's original state
                entity.readFromNBT(creature);

                // Force mob to the position the arrow provided and remove all of its velocity
                entity.setPosition(x + 0.5d, y, z + 0.5d);
                entity.setVelocity(0.d, 0.d, 0.d);
                player.world.spawnEntity(entity);
            }
        }

        // Mob released! Remove it from NBT.
        // Keeping this statement outside of the if statements above: if an entity gets captured and that data gets
        // corrupted then the worst that happens is you spend 1 arrow and the corrupted data gets removed.
        data.removeTag(KEY_VOID_SNARE);
    }
}
