package com.subwranglers.wickedarrows.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelMerlinArrow extends ModelBase {
    private final ModelRenderer arrow;

    public ModelMerlinArrow() {
        textureWidth = 64;
        textureHeight = 64;

        arrow = new ModelRenderer(this);
        arrow.setRotationPoint(0.0F, 24.0F, 0.0F);
        setRotationAngle(arrow, -1.5708F, -1.5708F, 0.0F);
        arrow.cubeList.add(new ModelBox(arrow, 0, 0, -2.0F, 1.0F, -23.5F, 4, 1, 4, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 0, 38, -2.0F, -3.0F, -19.5F, 4, 4, 1, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 2, 5, 2.0F, -3.0F, -23.5F, 1, 4, 4, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 12, 22, -2.0F, -3.0F, -24.5F, 4, 4, 1, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 0, 22, -3.0F, -3.0F, -23.5F, 1, 4, 4, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 0, 5, 0.0F, -15.0F, -22.0F, 0, 16, 1, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 10, 22, -0.5F, -15.0F, -21.5F, 1, 16, 0, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 0, 44, -2.5F, -15.0F, -21.5F, 2, 4, 0, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 10, 38, 0.0F, -15.0F, -21.0F, 0, 4, 2, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 18, 38, 0.5F, -15.0F, -21.5F, 2, 4, 0, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 14, 38, 0.0F, -15.0F, -24.0F, 0, 4, 2, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 8, 46, -1.0F, -3.0F, -20.5F, 1, 1, 1, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 4, 44, -2.0F, -5.0F, -20.5F, 1, 1, 1, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 4, 46, 0.0F, -4.0F, -19.5F, 1, 1, 1, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 8, 44, -2.0F, -3.0F, -22.5F, 1, 1, 1, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 12, 48, -2.0F, -4.0F, -23.5F, 1, 1, 1, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 12, 44, -3.0F, -4.0F, -22.5F, 1, 1, 1, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 0, 48, -2.0F, -7.0F, -22.5F, 1, 1, 1, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 4, 48, -2.0F, -3.0F, -21.5F, 1, 1, 1, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 8, 48, -3.0F, -9.0F, -20.5F, 1, 1, 1, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 16, 0, -2.0F, -2.0F, -23.5F, 4, 1, 4, 0.0F, false));
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