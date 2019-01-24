package com.subwranglers.wickedarrows.client.render;

import com.subwranglers.wickedarrows.WickedArrows;
import com.subwranglers.wickedarrows.entity.EntityIceArrow;
import com.subwranglers.wickedarrows.client.model.ModelIceArrow;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nullable;

public class RenderIceArrow extends RenderWArrow<EntityIceArrow> implements IRenderFactory<EntityIceArrow> {

    public static final ResourceLocation TEXTURE = new ResourceLocation(WickedArrows.MOD_ID,
            "textures/entity/projectiles/ice_arrow.png");

    public RenderIceArrow(RenderManager renderManagerIn) {
        super(renderManagerIn);
        setModel(new ModelIceArrow());
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
