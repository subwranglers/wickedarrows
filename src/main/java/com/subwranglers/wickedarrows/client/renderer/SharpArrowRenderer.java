package com.subwranglers.wickedarrows.client.renderer;

import com.subwranglers.wickedarrows.WickedArrows;
import com.subwranglers.wickedarrows.client.model.SharpArrowModel;
import com.subwranglers.wickedarrows.entity.SharpArrowEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class SharpArrowRenderer extends WickedArrowRenderer<SharpArrowEntity> {

    public static final ResourceLocation TEXTURE = new ResourceLocation(WickedArrows.MOD_ID,
            "textures/entity/projectiles/sharp_arrow.png");

    public SharpArrowRenderer(EntityRendererManager renderManager) {
        super(renderManager, new SharpArrowModel<>());
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(SharpArrowEntity entity) {
        return TEXTURE;
    }
}
