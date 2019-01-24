package com.subwranglers.wickedarrows.render;

import com.subwranglers.wickedarrows.WickedArrows;
import com.subwranglers.wickedarrows.entity.EntityIceArrow;
import com.subwranglers.wickedarrows.model.ModelIceArrow;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nullable;

public class RenderIceArrow extends Render<EntityIceArrow> implements IRenderFactory<EntityIceArrow> {

    public static final ResourceLocation TEXTURE = new ResourceLocation(WickedArrows.MOD_ID,
            "textures/entity/projectiles/ice_arrow.png");

    protected ModelIceArrow modelIceArrow = new ModelIceArrow();

    public RenderIceArrow(RenderManager renderManagerIn) {
        super(renderManagerIn);
    }

    @Override
    public void doRender(EntityIceArrow entity, double x, double y, double z, float entityYaw, float partialTicks) {
        this.bindEntityTexture(entity);

        GlStateManager.pushMatrix();
        GlStateManager.disableLighting();

        GlStateManager.translate((float) x, (float) y, (float) z);

        GlStateManager.rotate(entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * partialTicks - 90.f, 0.f, 1.f, 0.f);
        GlStateManager.rotate(entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * partialTicks, 0.f, 0.f, 1.f);

        modelIceArrow.render(entity, 0, 0, partialTicks, entity.rotationYaw, entity.rotationPitch, .07f);

        GlStateManager.enableLighting();
        GlStateManager.popMatrix();

        super.doRender(entity, x, y, z, entityYaw, partialTicks);

//        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
//        GlStateManager.pushMatrix();
//        GlStateManager.disableLighting();
//        GlStateManager.translate((float) x, (float) y, (float) z);
//        GlStateManager.rotate(entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * partialTicks - 90.0F, 0.0F, 1.0F, 0.0F);
//        GlStateManager.rotate(entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * partialTicks, 0.0F, 0.0F, 1.0F);
//        GlStateManager.enableRescaleNormal();
//        float f9 = (float) entity.arrowShake - partialTicks;
//
//        if (f9 > 0.0F) {
//            float f10 = -MathHelper.sin(f9 * 3.0F) * f9;
//            GlStateManager.rotate(f10, 0.0F, 0.0F, 1.0F);
//        }
//
//        GlStateManager.rotate(45.0F, 1.0F, 0.0F, 0.0F);
//        GlStateManager.scale(0.05625F, 0.05625F, 0.05625F);
//        GlStateManager.translate(-4.0F, 0.0F, 0.0F);
//
//        if (this.renderOutlines) {
//            GlStateManager.enableColorMaterial();
//            GlStateManager.enableOutlineMode(this.getTeamColor(entity));
//        }
//
//        modelIceArrow.render(entity, 0, 0, partialTicks, entity.rotationYaw, entity.rotationPitch, 1.f);
//        if (this.renderOutlines) {
//            GlStateManager.disableOutlineMode();
//            GlStateManager.disableColorMaterial();
//        }
//
//        GlStateManager.disableRescaleNormal();
//        GlStateManager.enableLighting();
//        GlStateManager.popMatrix();
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityIceArrow entity) {
        return TEXTURE;
    }

    @Override
    public Render<? super EntityIceArrow> createRenderFor(RenderManager manager) {
        return new RenderIceArrow(manager);
    }
}
