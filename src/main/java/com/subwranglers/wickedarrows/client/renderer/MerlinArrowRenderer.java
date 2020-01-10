package com.subwranglers.wickedarrows.client.renderer;

import com.subwranglers.wickedarrows.WickedArrows;
import com.subwranglers.wickedarrows.client.model.MerlinArrowModel;
import com.subwranglers.wickedarrows.entity.MerlinArrowEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class MerlinArrowRenderer<T extends MerlinArrowEntity> extends WickedArrowRenderer<T> {

    public static final ResourceLocation TEXTURE = new ResourceLocation(WickedArrows.MOD_ID,
            "textures/entity/projectiles/merlin_arrow.png");

    public MerlinArrowRenderer(EntityRendererManager renderManager) {
        super(renderManager, new MerlinArrowModel<>());
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(MerlinArrowEntity entity) {
        return TEXTURE;
    }
}
