package com.subwranglers.wickedarrows.render;

import com.subwranglers.wickedarrows.entity.EntityShotArrow;
import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

import static net.minecraft.client.renderer.entity.RenderTippedArrow.RES_ARROW;

public class RenderShotArrow extends RenderArrow<EntityShotArrow> {

    public RenderShotArrow(RenderManager renderManagerIn) {
        super(renderManagerIn);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityShotArrow entity) {
        return RES_ARROW;
    }
}
