package com.subwranglers.wickedarrows.instances;

import com.subwranglers.wickedarrows.WickedArrows;
import com.subwranglers.wickedarrows.entity.IceArrowEntity;
import net.minecraft.entity.EntityType;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(WickedArrows.MOD_ID)
public class EntityTypes {

    @ObjectHolder("ice_arrow")
    public static final EntityType<IceArrowEntity> ICE_ARROW = null;
}
