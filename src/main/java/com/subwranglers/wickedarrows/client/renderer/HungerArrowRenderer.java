package com.subwranglers.wickedarrows.client.renderer;

import com.subwranglers.wickedarrows.WickedArrows;
import com.subwranglers.wickedarrows.client.model.ModelHungerArrow;
import com.subwranglers.wickedarrows.entity.HungerArrowEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class HungerArrowRenderer<T extends HungerArrowEntity> extends WickedArrowRenderer<T> {

    public static final ResourceLocation TEXTURE = new ResourceLocation(WickedArrows.MOD_ID,
            "textures/entity/projectiles/hunger_arrow.png");

    public HungerArrowRenderer(EntityRendererManager renderManager) {
        super(renderManager, new ModelHungerArrow<>());
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(HungerArrowEntity entity) {
        return TEXTURE;
    }
}
