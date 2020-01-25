package com.subwranglers.wickedarrows.event;

import com.subwranglers.wickedarrows.WickedArrows;
import com.subwranglers.wickedarrows.potion.BrittleBonesEffect;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = WickedArrows.MOD_ID)
public class EventBrittleBones {

    @SubscribeEvent
    public static void brittleBonesFallDamage(LivingFallEvent event) {
        if (event.getEntityLiving().isPotionActive(BrittleBonesEffect.INSTANCE)){
            // Apply double damage to entity if it's afflicted by the brittle bones effect
            event.setDistance(event.getDistance() * 2);
        }
    }
}
