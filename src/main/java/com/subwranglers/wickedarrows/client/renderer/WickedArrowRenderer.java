package com.subwranglers.wickedarrows.client.renderer;

import com.mojang.blaze3d.platform.GlStateManager;
import com.subwranglers.wickedarrows.entity.WickedArrowEntity;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public abstract class WickedArrowRenderer<T extends WickedArrowEntity> extends EntityRenderer<T> {

    protected EntityModel<T> model;

    protected WickedArrowRenderer(EntityRendererManager renderManager) {
        super(renderManager);
    }

    @Override
    public void doRender(T entity, double x, double y, double z, float entityYaw, float partialTicks) {
        this.bindEntityTexture(entity);

        GlStateManager.pushMatrix();
        GlStateManager.disableLighting();

        GlStateManager.translatef((float) x, (float) y, (float) z);

        GlStateManager.rotatef(entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * partialTicks - 90.f, 0.f, 1.f, 0.f);
        GlStateManager.rotatef(entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * partialTicks, 0.f, 0.f, 1.f);

        model.render(entity, 0, 0, partialTicks, entity.rotationYaw, entity.rotationPitch, .07f);

        GlStateManager.enableLighting();
        GlStateManager.popMatrix();

        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    protected void setModel(EntityModel<T> model) {
        this.model = model;
    }

    @Nullable
    @Override
    protected abstract ResourceLocation getEntityTexture(T entity);
}
