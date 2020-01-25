package com.subwranglers.wickedarrows.instances;

import com.subwranglers.wickedarrows.WickedArrows;
import com.subwranglers.wickedarrows.entity.*;
import com.subwranglers.wickedarrows.info.Names;
import net.minecraft.entity.EntityType;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(WickedArrows.MOD_ID)
public class EntityTypes {

    @ObjectHolder(Names.ICE_ARROW)
    public static final EntityType<IceArrowEntity> ICE_ARROW = null;

    @ObjectHolder(Names.TORCH_ARROW)
    public static final EntityType<TorchArrowEntity> TORCH_ARROW = null;

    @ObjectHolder(Names.RICOCHET_ARROW)
    public static final EntityType<RicochetArrowEntity> RICOCHET_ARROW = null;

    @ObjectHolder(Names.LIGHTBURN_ARROW)
    public static final EntityType<LightburnArrowEntity> LIGHTBURN_ARROW = null;

    @ObjectHolder(Names.SHARP_ARROW)
    public static final EntityType<SharpArrowEntity> SHARP_ARROW = null;

    @ObjectHolder(Names.MERLIN_ARROW)
    public static final EntityType<MerlinArrowEntity> MERLIN_ARROW = null;

    @ObjectHolder(Names.SHOT_ARROW)
    public static final EntityType<ShotArrowEntity> SHOT_ARROW = null;

    @ObjectHolder(Names.HUNGER_ARROW)
    public static final EntityType<HungerArrowEntity> HUNGER_ARROW = null;

    @ObjectHolder(Names.VOID_SNARE_ARROW)
    public static final EntityType<VoidSnareArrowEntity> VOID_SNARE_ARROW = null;

    @ObjectHolder(Names.VOID_VACUUM)
    public static final EntityType<VoidVacuumEntity> VOID_VACUUM = null;

}
