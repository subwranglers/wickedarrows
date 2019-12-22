package com.subwranglers.wickedarrows.client.model;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.ModelBox;
import net.minecraft.entity.Entity;

public class IceArrowModel extends EntityModel {
    private final RendererModel arrow;

    public IceArrowModel() {
        textureWidth = 32;
        textureHeight = 32;

        arrow = new RendererModel(this);
        arrow.setRotationPoint(0.0F, 24.0F, 0.0F);
        setRotationAngle(arrow, -1.5708F, -1.5708F, 0.0F);
        arrow.cubeList.add(new ModelBox(arrow, 0, 0, -2.0F, 3.0F, -26.0F, 4, 4, 4, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 4, 10, 1.5F, -7.0F, -24.0F, 1, 3, 0, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 4, 9, 0.5F, -6.0F, -24.0F, 1, 3, 0, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 3, 8, -2.5F, -7.0F, -24.0F, 1, 3, 0, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 4, 8, -1.5F, -6.0F, -24.0F, 1, 3, 0, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 4, 10, 0.0F, -6.0F, -25.5F, 0, 3, 1, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 3, 10, 0.0F, -7.0F, -26.5F, 0, 3, 1, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 4, 7, 0.0F, -7.0F, -22.5F, 0, 3, 1, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 3, 7, 0.0F, -6.0F, -23.5F, 0, 3, 1, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 0, 8, -0.5F, -7.0F, -24.0F, 1, 10, 0, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 0, 8, 0.0F, -7.0F, -24.5F, 0, 10, 1, 0.0F, false));
    }

    @Override
    public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        arrow.render(scale);
    }

    public void setRotationAngle(RendererModel modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}