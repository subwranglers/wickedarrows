package com.subwranglers.wickedarrows.client.render;

import com.subwranglers.wickedarrows.WickedArrows;
import com.subwranglers.wickedarrows.client.model.ModelMerlinArrow;
import com.subwranglers.wickedarrows.entity.EntityMerlinArrow;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class RenderMerlinArrow extends RenderWArrow<EntityMerlinArrow> {

    public static final ResourceLocation TEXTURE = new ResourceLocation(WickedArrows.MOD_ID,
            "textures/entity/projectiles/merlin_arrow.png");

    public RenderMerlinArrow(RenderManager renderManager) {
        super(renderManager);
        setModel(new ModelMerlinArrow());
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityMerlinArrow entity) {
        return TEXTURE;
    }
}
