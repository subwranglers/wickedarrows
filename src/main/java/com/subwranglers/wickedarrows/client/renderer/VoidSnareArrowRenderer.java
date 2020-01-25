package com.subwranglers.wickedarrows.client.renderer;

import com.subwranglers.wickedarrows.WickedArrows;
import com.subwranglers.wickedarrows.client.model.VoidSnareArrowModel;
import com.subwranglers.wickedarrows.entity.VoidSnareArrowEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class VoidSnareArrowRenderer<T extends VoidSnareArrowEntity> extends WickedArrowRenderer<T> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(WickedArrows.MOD_ID,
            "textures/entity/projectiles/void_snare_arrow.png");

    public VoidSnareArrowRenderer(EntityRendererManager renderManager) {
        super(renderManager, new VoidSnareArrowModel<>());
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(VoidSnareArrowEntity entity) {
        return TEXTURE;
    }
}
