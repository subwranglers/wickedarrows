package com.subwranglers.wickedarrows.entities.render;

import com.subwranglers.wickedarrows.entities.EntityIceArrow;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import util.S;

import javax.annotation.Nullable;

public class RenderIceArrow extends RenderArrow<EntityIceArrow> implements IRenderFactory<EntityIceArrow> {

//    public static final ResourceLocation TEXTURES = new ResourceLocation(S.qualify("models/item/ice_arrow"));
    public static final ResourceLocation TEXTURES = new ModelResourceLocation(S.qualify("models/item/ice_arrow"));

    public RenderIceArrow(RenderManager renderManagerIn) {
        super(renderManagerIn);
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
