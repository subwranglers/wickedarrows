package com.subwranglers.wickedarrows.client.render;

import com.subwranglers.wickedarrows.WickedArrows;
import com.subwranglers.wickedarrows.client.model.ModelVoidSnareArrow;
import com.subwranglers.wickedarrows.entity.EntityVoidSnareArrow;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class RenderVoidSnareArrow extends RenderWArrow<EntityVoidSnareArrow> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(WickedArrows.MOD_ID,
            "textures/entity/projectiles/void_snare_arrow.png");

    public RenderVoidSnareArrow(RenderManager renderManager) {
        super(renderManager);
        setModel(new ModelVoidSnareArrow());
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityVoidSnareArrow entity) {
        return TEXTURE;
    }
}
