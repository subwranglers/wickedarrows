package com.subwranglers.wickedarrows.client.render;

import com.subwranglers.wickedarrows.WickedArrows;
import com.subwranglers.wickedarrows.client.model.ModelRicochetArrow;
import com.subwranglers.wickedarrows.entity.EntityRicochetArrow;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class RenderRicochetArrow extends RenderWArrow<EntityRicochetArrow> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(WickedArrows.MOD_ID,
            "textures/entity/projectiles/ricochet_arrow.png");

    public RenderRicochetArrow(RenderManager renderManager) {
        super(renderManager);
        setModel(new ModelRicochetArrow());
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityRicochetArrow entity) {
        return TEXTURE;
    }
}
