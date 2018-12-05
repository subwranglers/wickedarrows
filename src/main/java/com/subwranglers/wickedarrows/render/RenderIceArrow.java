package com.subwranglers.wickedarrows.render;

import com.subwranglers.wickedarrows.entity.EntityIceArrow;
import com.subwranglers.wickedarrows.model.ModelIceArrow;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nullable;

import static com.subwranglers.wickedarrows.info.Names.QUALIFY;
import static com.subwranglers.wickedarrows.info.Names.name;

public class RenderIceArrow extends Render<EntityIceArrow> implements IRenderFactory<EntityIceArrow> {

    private static final String ICE_ARROW_TEXTURE = "textures/entity/projectiles/ice_arrow.png";
    public static final ResourceLocation TEXTURES = new ResourceLocation(name(ICE_ARROW_TEXTURE, QUALIFY));
    protected ModelBase modelIceArrow = new ModelIceArrow();

    public RenderIceArrow(RenderManager renderManagerIn) {
        super(renderManagerIn);
    }

    @Override
    public void doRender(EntityIceArrow entity, double x, double y, double z, float entityYaw, float partialTicks) {
        this.bindEntityTexture(entity);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.pushMatrix();
        GlStateManager.disableLighting();
        GlStateManager.translate((float) x, (float) y, (float) z);
        GlStateManager.rotate(entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * partialTicks - 90.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * partialTicks, 0.0F, 0.0F, 1.0F);
//        Tessellator tessellator = Tessellator.getInstance();
//        BufferBuilder bufferbuilder = tessellator.getBuffer();

        GlStateManager.enableRescaleNormal();
        float f9 = (float) entity.arrowShake - partialTicks;

        if (f9 > 0.0F) {
            float f10 = -MathHelper.sin(f9 * 3.0F) * f9;
            GlStateManager.rotate(f10, 0.0F, 0.0F, 1.0F);
        }

        GlStateManager.rotate(45.0F, 1.0F, 0.0F, 0.0F);
        GlStateManager.scale(0.05625F, 0.05625F, 0.05625F);
        GlStateManager.translate(-4.0F, 0.0F, 0.0F);

        modelIceArrow.render(entity, 0, 0, 0, 0, 0, 1);

        GlStateManager.disableRescaleNormal();
        GlStateManager.enableLighting();
        GlStateManager.popMatrix();
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityIceArrow entity) {
        return TEXTURES;
    }

    @Override
    public Render<? super EntityIceArrow> createRenderFor(RenderManager manager) {
        return new RenderIceArrow(manager);
    }
}
