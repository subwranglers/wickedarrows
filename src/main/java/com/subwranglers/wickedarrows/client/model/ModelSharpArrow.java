package com.subwranglers.wickedarrows.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelSharpArrow extends ModelBase {
    private final ModelRenderer arrow;

    public ModelSharpArrow() {
        textureWidth = 48;
        textureHeight = 48;

        arrow = new ModelRenderer(this);
        arrow.setRotationPoint(0.0F, 24.0F, 0.0F);
        setRotationAngle(arrow, -1.5708F, -1.5708F, 0.0F);
        arrow.cubeList.add(new ModelBox(arrow, 0, 20, -2.0F, -8.0F, -26.0F, 4, 16, 0, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 0, 0, 0.0F, -8.0F, -28.0F, 0, 16, 4, 0.0F, false));
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