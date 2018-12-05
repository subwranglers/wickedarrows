package com.subwranglers.wickedarrows.sound;

import com.subwranglers.wickedarrows.WickedArrows;
import com.subwranglers.wickedarrows.info.Names;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class IceExplosionSoundEvent extends SoundEvent {

    public static final IceExplosionSoundEvent INSTANCE = new IceExplosionSoundEvent();

    public IceExplosionSoundEvent() {
        super(new ResourceLocation(WickedArrows.MOD_ID, Names.ICE_EXPLOSION));
        setRegistryName(WickedArrows.MOD_ID, Names.ICE_EXPLOSION);
    }

    public static void play(EntityPlayer player, World world, BlockPos pos) {
        world.playSound(player, pos, INSTANCE, SoundCategory.AMBIENT, 0.5f, 1.0f);
    }
}
