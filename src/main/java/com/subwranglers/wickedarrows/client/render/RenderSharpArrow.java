package com.subwranglers.wickedarrows.client.render;

import com.subwranglers.wickedarrows.WickedArrows;
import com.subwranglers.wickedarrows.client.model.ModelSharpArrow;
import com.subwranglers.wickedarrows.entity.EntitySharpArrow;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class RenderSharpArrow extends RenderWArrow<EntitySharpArrow> {

    public static final ResourceLocation TEXTURE = new ResourceLocation(WickedArrows.MOD_ID,
            "textures/entity/projectiles/sharp_arrow.png");

    public RenderSharpArrow(RenderManager renderManager) {
        super(renderManager);
        setModel(new ModelSharpArrow());
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntitySharpArrow entity) {
        return TEXTURE;
    }
}
