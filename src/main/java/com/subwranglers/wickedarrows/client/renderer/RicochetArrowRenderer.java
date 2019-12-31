package com.subwranglers.wickedarrows.client.renderer;

import com.subwranglers.wickedarrows.WickedArrows;
import com.subwranglers.wickedarrows.client.model.RicochetArrowModel;
import com.subwranglers.wickedarrows.entity.RicochetArrowEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class RicochetArrowRenderer<T extends RicochetArrowEntity> extends WickedArrowRenderer<T> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(WickedArrows.MOD_ID,
            "textures/entity/projectiles/ricochet_arrow.png");

    public RicochetArrowRenderer(EntityRendererManager renderManager) {
        super(renderManager, new RicochetArrowModel<>());
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(RicochetArrowEntity entity) {
        return TEXTURE;
    }
}
