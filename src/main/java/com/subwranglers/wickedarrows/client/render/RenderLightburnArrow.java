package com.subwranglers.wickedarrows.client.render;

import com.subwranglers.wickedarrows.WickedArrows;
import com.subwranglers.wickedarrows.client.model.ModelLightburnArrow;
import com.subwranglers.wickedarrows.entity.EntityLightburnArrow;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class RenderLightburnArrow extends RenderWArrow<EntityLightburnArrow> {

    public static final ResourceLocation TEXTURE = new ResourceLocation(WickedArrows.MOD_ID,
                "textures/entity/projectiles/lightburn_arrow.png");

    public RenderLightburnArrow(RenderManager renderManager) {
        super(renderManager);
        setModel(new ModelLightburnArrow());
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityLightburnArrow entity) {
        return TEXTURE;
    }
}
