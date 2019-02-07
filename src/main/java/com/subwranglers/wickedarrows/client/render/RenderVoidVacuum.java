package com.subwranglers.wickedarrows.client.render;

import com.subwranglers.wickedarrows.WickedArrows;
import com.subwranglers.wickedarrows.client.model.ModelVoidVacuum;
import com.subwranglers.wickedarrows.entity.EntityVoidVacuum;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

import javax.annotation.Nullable;

public class RenderVoidVacuum extends Render<EntityVoidVacuum> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(WickedArrows.MOD_ID,
            "textures/entity/void_vacuum.png");


    private ModelVoidVacuum model = new ModelVoidVacuum();

    public RenderVoidVacuum(RenderManager renderManager) {
        super(renderManager);
    }

    @Override
    public void doRender(EntityVoidVacuum entity, double x, double y, double z, float entityYaw, float partialTicks) {
        bindEntityTexture(entity);

        GlStateManager.pushMatrix();
        GlStateManager.disableLighting();

        GlStateManager.translate((float) x, (float) y - 3.f, (float) z);

        entity.renderAngle = MathHelper.wrapDegrees(entity.renderAngle + 50);
        GlStateManager.rotate(entity.renderAngle, 0.f, 1.f, 0.f);

        model.render(entity, 0, 0, partialTicks, entity.rotationYaw, entity.rotationPitch, 0.1f);

        GlStateManager.enableLighting();
        GlStateManager.popMatrix();

        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityVoidVacuum entity) {
        return TEXTURE;
    }
}
