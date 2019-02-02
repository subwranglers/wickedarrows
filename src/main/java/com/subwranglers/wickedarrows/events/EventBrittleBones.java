package com.subwranglers.wickedarrows.events;

import com.subwranglers.wickedarrows.potion.PotionBrittleBones;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class EventBrittleBones {

    @SubscribeEvent
    public static void brittleBonesFallDamage(LivingFallEvent event) {
        if (!event.getEntityLiving().isPotionActive(PotionBrittleBones.INSTANCE))
            return;

        // Apply double damage to entity if it's afflicted by the brittle bones effect
        event.setDistance(event.getDistance() * 2);
    }
}
