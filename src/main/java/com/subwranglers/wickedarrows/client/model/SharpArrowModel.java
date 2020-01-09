package com.subwranglers.wickedarrows.client.model;

import com.subwranglers.wickedarrows.entity.SharpArrowEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.ModelBox;

public class SharpArrowModel<T extends SharpArrowEntity> extends EntityModel<T> {
    private final RendererModel arrow;

    public SharpArrowModel() {
        textureWidth = 48;
        textureHeight = 48;

        arrow = new RendererModel(this);
        arrow.setRotationPoint(0.0F, 24.0F, 0.0F);
        setRotationAngle(arrow, -1.5708F, -1.5708F, 0.0F);
        arrow.cubeList.add(new ModelBox(arrow, 0, 20, -2.0F, -12.0F, -24.5F, 4, 16, 0, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 0, 0, 0.0F, -12.0F, -26.5F, 0, 16, 4, 0.0F, false));
    }

    @Override
    public void render(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        arrow.render(scale);
    }

    public void setRotationAngle(RendererModel modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}