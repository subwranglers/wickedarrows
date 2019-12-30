package com.subwranglers.wickedarrows.client.renderer;

import com.subwranglers.wickedarrows.WickedArrows;
import com.subwranglers.wickedarrows.client.model.TorchArrowModel;
import com.subwranglers.wickedarrows.entity.TorchArrowEntity;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nullable;

public class TorchArrowRenderer extends WickedArrowRenderer<TorchArrowEntity> implements IRenderFactory<TorchArrowEntity> {

    public static final ResourceLocation TEXTURE = new ResourceLocation(WickedArrows.MOD_ID,
            "textures/entity/projectiles/torch_arrow.png");

    public TorchArrowRenderer(EntityRendererManager renderManager) {
        super(renderManager, new TorchArrowModel<>());
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(TorchArrowEntity entity) {
        return TEXTURE;
    }

    @Override
    public EntityRenderer<? super TorchArrowEntity> createRenderFor(EntityRendererManager manager) {
        return new TorchArrowRenderer(manager);
    }
}
