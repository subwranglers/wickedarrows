package com.subwranglers.wickedarrows.client.renderer;

import com.subwranglers.wickedarrows.entity.ShotArrowEntity;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

import static net.minecraft.client.renderer.entity.TippedArrowRenderer.RES_ARROW;

public class ShotArrowRenderer extends ArrowRenderer<ShotArrowEntity> {

    public ShotArrowRenderer(EntityRendererManager manager) {
        super(manager);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(ShotArrowEntity entity) {
        return RES_ARROW;
    }
}
