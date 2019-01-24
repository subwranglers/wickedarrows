package com.subwranglers.wickedarrows.render;

import com.subwranglers.wickedarrows.base.EntityWArrow;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public abstract class RenderWArrow<T extends EntityWArrow> extends Render<T> {

    protected ModelBase model;

    protected RenderWArrow(RenderManager renderManager) {
        super(renderManager);
    }

    @Override
    public void doRender(T entity, double x, double y, double z, float entityYaw, float partialTicks) {
        this.bindEntityTexture(entity);

        GlStateManager.pushMatrix();
        GlStateManager.disableLighting();

        GlStateManager.translate((float) x, (float) y, (float) z);

        GlStateManager.rotate(entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * partialTicks - 90.f, 0.f, 1.f, 0.f);
        GlStateManager.rotate(entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * partialTicks, 0.f, 0.f, 1.f);

        model.render(entity, 0, 0, partialTicks, entity.rotationYaw, entity.rotationPitch, .07f);

        GlStateManager.enableLighting();
        GlStateManager.popMatrix();

        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    protected void setModel(ModelBase model) {
        this.model = model;
    }

    @Nullable
    @Override
    protected abstract ResourceLocation getEntityTexture(T entity);
}
