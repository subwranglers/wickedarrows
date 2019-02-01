package com.subwranglers.wickedarrows.client.render;

import com.subwranglers.wickedarrows.WickedArrows;
import com.subwranglers.wickedarrows.client.model.ModelHungerArrow;
import com.subwranglers.wickedarrows.entity.EntityHungerArrow;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class RenderHungerArrow extends RenderWArrow<EntityHungerArrow> {

    public static final ResourceLocation TEXTURE = new ResourceLocation(WickedArrows.MOD_ID,
            "textures/entity/projectiles/hunger_arrow.png");

    public RenderHungerArrow(RenderManager renderManager) {
        super(renderManager);
        setModel(new ModelHungerArrow());
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityHungerArrow entity) {
        return TEXTURE;
    }
}
