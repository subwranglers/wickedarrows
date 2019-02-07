package com.subwranglers.wickedarrows.client.sound;

import com.subwranglers.wickedarrows.WickedArrows;
import com.subwranglers.wickedarrows.info.Names;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;


public class SoundEventVoidVacuum extends SoundEvent {

    public static final SoundEventVoidVacuum INSTANCE = new SoundEventVoidVacuum();

    public SoundEventVoidVacuum() {
        super(new ResourceLocation(WickedArrows.MOD_ID, Names.VOID_VACUUM_FX));
        setRegistryName(WickedArrows.MOD_ID, Names.VOID_VACUUM_FX);
    }
}
