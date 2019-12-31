package com.subwranglers.wickedarrows.client.model;

import com.subwranglers.wickedarrows.entity.LightburnArrowEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.ModelBox;

public class LightburnArrowModel<T extends LightburnArrowEntity> extends EntityModel<T> {
    private final RendererModel arrow;

    public LightburnArrowModel() {
        textureWidth = 48;
        textureHeight = 48;

        arrow = new RendererModel(this);
        arrow.setRotationPoint(0.0F, 24.0F, 0.0F);
        setRotationAngle(arrow, -1.5708F, -1.5708F, 0.0F);
        arrow.cubeList.add(new ModelBox(arrow, 0, 11, 0.0F, 2.0F, -25.5F, 0, 4, 3, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 0, 18, -1.5F, 1.0F, -24.0F, 3, 5, 0, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 0, 0, 0.0F, -9.0F, -24.5F, 0, 12, 1, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 0, 1, -0.5F, -9.0F, -24.0F, 1, 12, 0, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 3, 0, 0.0F, -9.0F, -25.5F, 0, 3, 1, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 3, 1, 0.5F, -9.0F, -24.0F, 1, 3, 0, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 2, 1, -1.5F, -9.0F, -24.0F, 1, 3, 0, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 2, 0, 0.0F, -9.0F, -23.5F, 0, 3, 1, 0.0F, false));
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