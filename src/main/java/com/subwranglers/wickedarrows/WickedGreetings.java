package com.subwranglers.wickedarrows;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class WickedGreetings {

    @SubscribeEvent
    public static void wickedArrowsGreeting(EntityJoinWorldEvent event) {
//        if (event.getEntity() instanceof EntityPlayer && !event.getWorld().isRemote) {
//            EntityPlayer player = (EntityPlayer) event.getEntity();
//        }
    }
}
