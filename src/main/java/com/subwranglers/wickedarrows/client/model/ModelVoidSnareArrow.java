package com.subwranglers.wickedarrows.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelVoidSnareArrow extends ModelBase {

    private final ModelRenderer arrow;

    public ModelVoidSnareArrow() {
        textureWidth = 32;
        textureHeight = 32;

        arrow = new ModelRenderer(this);
        arrow.setRotationPoint(0.0F, 24.0F, 0.0F);
        setRotationAngle(arrow, -1.5708F, -1.5708F, 0.0F);
        arrow.cubeList.add(new ModelBox(arrow, 0, 14, -1.0F, 3.0F, -24.5F, 2, 2, 2, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 0, 21, -0.85F, 3.25F, -24.35F, 1, 2, 1, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 6, 18, -1.25F, 3.85F, -24.35F, 2, 1, 1, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 8, 26, -0.55F, 5.4F, -24.05F, 1, 0, 1, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 12, 18, -1.4F, 3.55F, -24.3F, 2, 1, 1, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 0, 18, -0.8F, 3.55F, -24.9F, 1, 1, 2, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 13, 13, -0.85F, 3.85F, -24.75F, 1, 1, 2, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 0, 0, -1.25F, -9.25F, -23.5F, 2, 12, 0, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 9, 0, 0.0F, -9.25F, -24.75F, 0, 12, 2, 0.0F, false));
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
