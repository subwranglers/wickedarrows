package com.subwranglers.wickedarrows.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelHungerArrow extends ModelBase {

    private final ModelRenderer arrow;

    public ModelHungerArrow() {
        textureWidth = 32;
        textureHeight = 32;

        arrow = new ModelRenderer(this);
        arrow.setRotationPoint(0.0F, 24.0F, 0.0F);
        setRotationAngle(arrow, -1.5708F, -1.5708F, 0.0F);
        arrow.cubeList.add(new ModelBox(arrow, 0, 13, -1.0F, 0.0F, -25.0F, 2, 2, 2, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 0, 21, -0.85F, 0.25F, -24.85F, 1, 2, 1, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 6, 18, -1.25F, 0.85F, -24.85F, 2, 1, 1, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 8, 26, -0.55F, 2.4F, -24.55F, 1, 0, 1, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 12, 18, -1.4F, 0.55F, -24.55F, 2, 1, 1, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 0, 18, -0.55F, 0.55F, -25.4F, 1, 1, 2, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 13, 13, -0.85F, 0.85F, -25.25F, 1, 1, 2, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 0, 0, -0.5F, -12.25F, -24.0F, 1, 12, 0, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 0, 0, 0.0F, -12.25F, -24.5F, 0, 12, 1, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 12, 21, -1.5F, -1.0F, -24.0F, 1, 1, 1, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 8, 13, 0.25F, -11.15F, -25.35F, 0, 3, 2, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 8, 9, -1.25F, -11.65F, -23.75F, 2, 3, 0, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 0, 24, -1.5F, -2.0F, -23.5F, 1, 1, 1, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 8, 21, -1.0F, -3.0F, -23.0F, 1, 1, 1, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 8, 21, -0.5F, -4.0F, -23.0F, 1, 1, 1, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 12, 24, 0.0F, -5.0F, -23.5F, 1, 1, 1, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 16, 24, 0.5F, -6.0F, -24.0F, 1, 1, 1, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 0, 26, 0.5F, -7.0F, -24.5F, 1, 1, 1, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 4, 26, 0.0F, -8.0F, -25.0F, 1, 1, 1, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 4, 24, -0.5F, -9.0F, -25.5F, 1, 1, 1, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 8, 3, -1.25F, -11.85F, -25.35F, 2, 0, 2, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 8, 9, -1.25F, -10.9F, -24.25F, 2, 3, 0, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 8, 13, -0.25F, -12.15F, -25.35F, 0, 3, 2, 0.0F, false));
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
