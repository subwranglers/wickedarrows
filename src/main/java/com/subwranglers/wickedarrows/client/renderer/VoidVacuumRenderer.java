package com.subwranglers.wickedarrows.client.renderer;

import com.mojang.blaze3d.platform.GLX;
import com.mojang.blaze3d.platform.GlStateManager;
import com.subwranglers.wickedarrows.WickedArrows;
import com.subwranglers.wickedarrows.client.model.VoidVacuumModel;
import com.subwranglers.wickedarrows.entity.VoidVacuumEntity;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

import javax.annotation.Nullable;

public class VoidVacuumRenderer<T extends VoidVacuumEntity> extends EntityRenderer<T> {

    public static final int ANGLE_INTERVAL = 50;
    private static final ResourceLocation TEXTURE = new ResourceLocation(WickedArrows.MOD_ID,
            "textures/entity/void_vacuum.png");

    private int renderAngle = 0;
    private EntityModel<T> model = new VoidVacuumModel<>();

    public VoidVacuumRenderer(EntityRendererManager renderManager) {
        super(renderManager);
    }

    // I think we can use SpriteRenderer (either extend from it or copy the necessary code) to get a billboard effect instead of spinning
    @Override
    public void doRender(T entity, double x, double y, double z, float entityYaw, float partialTicks) {
        bindEntityTexture(entity);

        GlStateManager.pushMatrix();
        GlStateManager.disableLighting();

        GlStateManager.translatef((float) x, (float) y/* - 3.f*/, (float) z);

        GlStateManager.rotatef(nextAngle(), 0.f, 1.f, 0.f);

        // We want the Void Vacuum to glow
        GLX.glMultiTexCoord2f(GLX.GL_TEXTURE1, 240f, 240f);

        model.render(entity, 0, 0, partialTicks, entity.rotationYaw, entity.rotationPitch, 0.1f);

        GlStateManager.enableLighting();
        GlStateManager.popMatrix();

        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    private float nextAngle() {
        renderAngle = MathHelper.wrapDegrees(renderAngle + ANGLE_INTERVAL);
        return renderAngle;
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(VoidVacuumEntity entity) {
        return TEXTURE;
    }
}
