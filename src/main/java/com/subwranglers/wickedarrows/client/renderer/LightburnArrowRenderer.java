package com.subwranglers.wickedarrows.client.renderer;

import com.subwranglers.wickedarrows.WickedArrows;
import com.subwranglers.wickedarrows.client.model.LightburnArrowModel;
import com.subwranglers.wickedarrows.entity.LightburnArrowEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class LightburnArrowRenderer<T extends LightburnArrowEntity> extends WickedArrowRenderer<T> {

    public static final ResourceLocation TEXTURE = new ResourceLocation(WickedArrows.MOD_ID,
            "textures/entity/projectiles/lightburn_arrow.png");
    public static final ResourceLocation TRAIL_TEXTURE = new ResourceLocation(WickedArrows.MOD_ID,
            "textures/entity/redstone_beam.png");

    public LightburnArrowRenderer(EntityRendererManager renderManager) {
        super(renderManager, new LightburnArrowModel<>());
    }

//    TODO (Todd 2019-01-30): Learn how to properly render stuff in 3D to achieve the lightburn effects.
//
//    For now just don't render any lightburn effects. Only render the arrow itself. This can/will be revisited when
//    implementing rendering overall, after functionality is implemented.
//
//    @Override
//    public void doRender(EntityLightburnArrow entity, double x, double y, double z, float entityYaw, float partialTicks) {
//        super.doRender(entity, x, y, z, entityYaw, partialTicks);
//
//        // Don't push/pop the matrix or this won't actually render at the coordinates of the arrow and just turns into
//        // a crazy weird repeating mess that only ever shows up at the same coords
//
//        bindTexture(TRAIL_TEXTURE);
//
//        Tessellator tessellator = Tessellator.getInstance();
//        BufferBuilder buffer = tessellator.getBuffer();
//
//        buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
//
//        double width = 0.3;
//
//        buffer.pos(x + width, y, z + width).tex(0, 0).endVertex();
//        buffer.pos(x + width, y, z - width).tex(0, 1).endVertex();
//        buffer.pos(x - width, y, z + width).tex(1, 0).endVertex();
//        buffer.pos(x - width, y, z - width).tex(1, 1).endVertex();
//        buffer.pos(x - width, y, z - width).tex(0, 0).endVertex();
//        buffer.pos(x - width, y, z + width).tex(0, 1).endVertex();
//        buffer.pos(x + width, y, z - width).tex(1, 0).endVertex();
//        buffer.pos(x + width, y, z + width).tex(1, 1).endVertex();
//
//        tessellator.draw();
//    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(LightburnArrowEntity entity) {
        return TEXTURE;
    }
}
