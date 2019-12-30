package com.subwranglers.wickedarrows.client.renderer;

import com.subwranglers.wickedarrows.WickedArrows;
import com.subwranglers.wickedarrows.client.model.IceArrowModel;
import com.subwranglers.wickedarrows.entity.IceArrowEntity;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nullable;

public class IceArrowRenderer<T extends IceArrowEntity> extends WickedArrowRenderer<T> implements IRenderFactory<T> {

    public static final ResourceLocation TEXTURE = new ResourceLocation(WickedArrows.MOD_ID,
            "textures/entity/projectiles/ice_arrow.png");

    public IceArrowRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new IceArrowModel<>());
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(T entity) {
        return TEXTURE;
    }

    @Override
    public EntityRenderer<? super T> createRenderFor(EntityRendererManager manager) {
        return new IceArrowRenderer(manager);
    }
}
