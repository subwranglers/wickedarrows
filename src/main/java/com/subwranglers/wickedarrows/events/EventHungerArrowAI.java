package com.subwranglers.wickedarrows.events;

import com.subwranglers.wickedarrows.HungerImpl;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraft.world.WorldEntitySpawner;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.List;

@Mod.EventBusSubscriber
public class EventHungerArrowAI {

    public static final int LIGHT_SPAWN_THRESHOLD = 10;

    @SubscribeEvent()
    public static void spawnZombieOrSpider(LivingSpawnEvent event) {
        final int LIMIT = 2000;
        World world = event.getWorld();

        if (MathHelper.getInt(world.rand, 1, LIMIT) < LIMIT)
            return;

        BlockPos pos = new BlockPos(event.getX(), event.getEntity().getEntityBoundingBox().minY, event.getZ());

        if (world.getLightFromNeighbors(pos) > LIGHT_SPAWN_THRESHOLD)
            return;

        List<EntityLivingBase> afflicted = HungerImpl.getAfflictedInAABB(world, pos, null, null);
        if (afflicted.size() <= 0)
            return;
        // There are afflicted entities nearby.

        EntityMob entity = MathHelper.getInt(world.rand, 1, 2) == 1 ?
                new EntityZombie(world) : new EntitySpider(world);

        entity.setPosition(pos.getX(), pos.getY(), pos.getZ());
        world.spawnEntity(entity);

        // Don't let a different mob spawn here
        event.setResult(Event.Result.DENY);
    }

    @SubscribeEvent()
    public static void attackAfflicted(LivingEvent event) {
        Entity e = event.getEntity();
        if (!(e instanceof EntityZombie || e instanceof EntitySpider))
            // Entity isn't a zombie or spider.
            return;

        EntityMob mob = (EntityMob) e;
        if (!HungerImpl.shouldIgnoreCurrentTarget(mob))
            // Shouldn't change the mob's target.
            return;

        World world = event.getEntity().getEntityWorld();

        List<EntityLivingBase> entities = HungerImpl.getAfflictedInAABB(world, e.getPosition(), null,
                entity -> !entity.equals(mob));

        if (entities.size() > 0)
            // Attack any afflicted target within range
            mob.setAttackTarget(entities.get(MathHelper.getInt(world.rand, 0, entities.size() - 1)));
    }

}
