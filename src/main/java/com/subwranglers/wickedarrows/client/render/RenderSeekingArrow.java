package com.subwranglers.wickedarrows.client.render;

import com.subwranglers.wickedarrows.WickedArrows;
import com.subwranglers.wickedarrows.client.model.ModelSeekingArrow;
import com.subwranglers.wickedarrows.entity.EntitySeekingArrow;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class RenderSeekingArrow extends RenderWArrow<EntitySeekingArrow> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(WickedArrows.MOD_ID,
            "textures/entity/projectiles/seeking_arrow.png");

    public RenderSeekingArrow(RenderManager renderManager) {
        super(renderManager);
        setModel(new ModelSeekingArrow());
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntitySeekingArrow entity) {
        return TEXTURE;
    }
}
