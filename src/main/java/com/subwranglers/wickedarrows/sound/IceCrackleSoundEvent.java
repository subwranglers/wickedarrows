package com.subwranglers.wickedarrows.sound;

import com.subwranglers.wickedarrows.WickedArrows;
import com.subwranglers.wickedarrows.info.Names;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import java.util.Random;

public class IceCrackleSoundEvent extends SoundEvent {

    public static final IceCrackleSoundEvent INSTANCE = new IceCrackleSoundEvent();

    protected IceCrackleSoundEvent() {
        super(new ResourceLocation(WickedArrows.MOD_ID, Names.ICE_CRACKLING));
        setRegistryName(WickedArrows.MOD_ID, Names.ICE_CRACKLING);
    }

    public static void play(World world, BlockPos pos, Random rand) {
        if (MathHelper.getInt(rand, 1, 4) < 4)
            // 75% chance to play the sound
            world.playSound(null, pos, INSTANCE, SoundCategory.BLOCKS, 0.25f, 1.0f);
    }

}
