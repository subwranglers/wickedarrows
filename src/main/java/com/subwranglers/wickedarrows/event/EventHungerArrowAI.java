package com.subwranglers.wickedarrows.event;

import com.subwranglers.wickedarrows.HungerImpl;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.monster.SpiderEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber
public class EventHungerArrowAI {

    public static final int LIGHT_SPAWN_THRESHOLD = 10;

    @SubscribeEvent()
    public static void spawnZombieOrSpider(LivingSpawnEvent.CheckSpawn event) {
        IWorld world = event.getWorld();

        BlockPos pos = new BlockPos(event.getX(), event.getY(), event.getZ());

        if (world.getLight(pos) > LIGHT_SPAWN_THRESHOLD)
            return;

        List<LivingEntity> afflicted = HungerImpl.getAfflictedInAABB(world, pos, null, null);
        if (afflicted.size() <= 0)
            return;

        // There are afflicted entities nearby.
        MobEntity entity = world.getRandom().nextBoolean() ?
                new ZombieEntity(world.getWorld()) : new SpiderEntity(EntityType.SPIDER, world.getWorld());

        entity.setPosition(pos.getX(), pos.getY(), pos.getZ());
        world.addEntity(entity);

        // Don't let a different mob spawn here
        event.setResult(Event.Result.DENY);
    }

    @SubscribeEvent()
    public static void attackAfflicted(LivingEvent.LivingUpdateEvent event) {
        Entity e = event.getEntity();
        if (!(e instanceof ZombieEntity || e instanceof SpiderEntity))
            // Entity isn't a zombie or spider.
            return;

        MobEntity mob = (MobEntity) e;
        if (!HungerImpl.shouldChangeCurrentTarget(mob))
            // Shouldn't change the mob's target.
            return;

        World world = event.getEntity().getEntityWorld();

        List<LivingEntity> entities = HungerImpl.getAfflictedInAABB(world, e.getPosition(), null,
                entity -> !entity.equals(mob));

        if (entities.size() > 0)
            // Attack any afflicted target within range
            mob.setAttackTarget(entities.get(MathHelper.nextInt(world.getRandom(), 0, entities.size() - 1)));
    }

}
