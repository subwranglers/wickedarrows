package com.subwranglers.wickedarrows.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelLightburnArrow extends ModelBase {
    private final ModelRenderer arrow;

    public ModelLightburnArrow() {
        textureWidth = 48;
        textureHeight = 48;

        arrow = new ModelRenderer(this);
        arrow.setRotationPoint(0.0F, 24.0F, 0.0F);
        setRotationAngle(arrow, -1.5708F, -1.5708F, 0.0F);
        arrow.cubeList.add(new ModelBox(arrow, 0, 16, -0.5F, 6.0F, -26.0F, 1, 1, 0, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 0, 16, 0.0F, 6.0F, -26.5F, 0, 1, 1, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 0, 10, 0.0F, 4.0F, -27.5F, 0, 1, 3, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 0, 14, -1.5F, 4.0F, -26.0F, 3, 1, 0, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 2, 13, 0.5F, 3.0F, -26.0F, 1, 1, 0, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 2, 13, 1.0F, 2.0F, -26.0F, 0, 1, 0, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 2, 13, 0.0F, 3.0F, -27.5F, 0, 1, 0, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 2, 13, 0.0F, 3.0F, -25.0F, 0, 1, 0, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 2, 13, -1.5F, 3.0F, -26.0F, 0, 1, 0, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 0, 0, 0.0F, -8.0F, -26.5F, 0, 12, 1, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 0, 1, -0.5F, -8.0F, -26.0F, 1, 12, 0, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 2, 0, 0.0F, -8.0F, -27.5F, 0, 2, 1, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 2, 0, 0.5F, -8.0F, -26.0F, 1, 2, 0, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 2, 2, -1.5F, -8.0F, -26.0F, 1, 2, 0, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 2, 1, 0.0F, -8.0F, -25.5F, 0, 2, 1, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 4, 0, 0.0F, -6.0F, -25.5F, 0, 1, 0, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 3, 0, 0.5F, -6.0F, -26.0F, 0, 1, 0, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 3, 0, 0.0F, -6.0F, -27.0F, 0, 1, 0, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 4, 1, -1.0F, -6.0F, -26.0F, 0, 1, 0, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 2, 14, -1.0F, 5.0F, -26.0F, 2, 1, 0, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 2, 12, 0.0F, 5.0F, -27.0F, 0, 1, 2, 0.0F, false));
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        arrow.render(f5);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}