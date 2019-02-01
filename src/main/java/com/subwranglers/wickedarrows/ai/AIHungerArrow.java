package com.subwranglers.wickedarrows.ai;

import com.subwranglers.wickedarrows.entity.EntityHungerArrow;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIFindEntityNearest;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class AIHungerArrow {

    /**
     * How fast specified mobs move when moving toward the entity struck with an {@link EntityHungerArrow}.
     */
    public static final double ATTRACTION_SPEED = 1.2D;

    public static final float ATTRACTION_DISTANCE = 96.F;

    @SubscribeEvent()
    public static void moveToArrow(EntityJoinWorldEvent event) {
//        Entity e = event.getEntity();
//
//        if (e instanceof EntityZombie) {
//            EntityZombie zombie = (EntityZombie) e;
//
//            // Same Priority as attacking players
//            zombie.targetTasks.addTask(2, new EntityAIMoveTowardsTarget(
//                    zombie, ATTRACTION_SPEED, ATTRACTION_DISTANCE));
//        }
    }
}
