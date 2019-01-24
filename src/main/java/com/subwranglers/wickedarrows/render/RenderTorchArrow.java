package com.subwranglers.wickedarrows.render;

import com.subwranglers.wickedarrows.WickedArrows;
import com.subwranglers.wickedarrows.entity.EntityTorchArrow;
import com.subwranglers.wickedarrows.model.ModelTorchArrow;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nullable;

public class RenderTorchArrow extends RenderWArrow<EntityTorchArrow> implements IRenderFactory<EntityTorchArrow> {

    public static final ResourceLocation TEXTURE = new ResourceLocation(WickedArrows.MOD_ID,
            "textures/entity/projectiles/torch_arrow.png");

    public RenderTorchArrow(RenderManager renderManager) {
        super(renderManager);
        setModel(new ModelTorchArrow());
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityTorchArrow entity) {
        return TEXTURE;
    }

    @Override
    public Render<? super EntityTorchArrow> createRenderFor(RenderManager manager) {
        return new RenderTorchArrow(manager);
    }
}
