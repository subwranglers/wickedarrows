package com.subwranglers.wickedarrows.instances;

import com.subwranglers.wickedarrows.WickedArrows;
import com.subwranglers.wickedarrows.entity.IceArrowEntity;
import com.subwranglers.wickedarrows.info.Names;
import net.minecraft.entity.EntityType;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(WickedArrows.MOD_ID)
public class EntityTypes {

    @ObjectHolder(Names.ICE_ARROW)
    public static final EntityType<IceArrowEntity> ICE_ARROW = null;

    @ObjectHolder(Names.TORCH_ARROW)
    public static final EntityType<IceArrowEntity> TORCH_ARROW = null;

    @ObjectHolder(Names.RICOCHET_ARROW)
    public static final EntityType<IceArrowEntity> RICOCHET_ARROW = null;

    @ObjectHolder(Names.LIGHTBURN_ARROW)
    public static final EntityType<IceArrowEntity> LIGHTBURN_ARROW = null;
}
