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
        if (event.getEntity() instanceof EntityPlayer && !event.getWorld().isRemote) {
            EntityPlayer player = (EntityPlayer) event.getEntity();

            player.sendMessage(new TextComponentString("Welcome to the first dev-release of Wicked Arrows!\n"));
            player.sendMessage(new TextComponentString("Things are not nearly finished yet, but I figured it was time to let you play around with it so you could help me out with how the mod *feels* so far.\n"));
            player.sendMessage(new TextComponentString("It's too early for in-depth balancing, so don't worry about that yet. Just play around with the different arrow types and write down what does and doesn't work for you.\n"));
            player.sendMessage(new TextComponentString("You can find the wicked arrows in the combat tab in creative mode, or in the \"wickedarrows:\" namespace if you want to use commands (eg. /give <player> wickedarrows:ice_arrow 2)"));
        }
    }
}
