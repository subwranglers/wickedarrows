package com.subwranglers.wickedarrows.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelTorchArrow extends ModelBase {
    private final ModelRenderer arrow;
    private final ModelRenderer torch1;
    private final ModelRenderer torch2;

    public ModelTorchArrow() {
        textureWidth = 32;
        textureHeight = 32;

        arrow = new ModelRenderer(this);
        arrow.setRotationPoint(0.0F, 24.0F, 0.0F);
        setRotationAngle(arrow, -1.5708F, -1.5708F, 0.0F);
        arrow.cubeList.add(new ModelBox(arrow, 0, 1, -0.5F, -12.0F, -26.0F, 1, 13, 0, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 2, 0, 0.0F, -12.0F, -26.5F, 0, 13, 1, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 4, 10, 0.0F, 1.0F, -27.0F, 0, 2, 2, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 6, 12, 0.0F, 3.0F, -26.5F, 0, 1, 1, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 6, 11, -0.5F, 3.0F, -26.0F, 1, 1, 0, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 4, 10, -1.0F, 1.0F, -26.0F, 2, 2, 0, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 5, 0, 0.0F, -11.0F, -25.5F, 0, 3, 1, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 6, 0, 0.0F, -12.0F, -24.75F, 0, 3, 1, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 6, 0, 0.0F, -11.0F, -27.5F, 0, 3, 1, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 6, 0, 0.0F, -12.0F, -28.5F, 0, 3, 1, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 6, 1, 0.5F, -11.0F, -26.0F, 1, 3, 0, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 6, 1, 1.25F, -12.0F, -26.0F, 1, 3, 0, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 6, 1, -1.5F, -11.0F, -26.0F, 1, 3, 0, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 6, 1, -2.25F, -12.0F, -26.0F, 1, 3, 0, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 2, 23, -1.65F, -1.5F, -26.7F, 3, 1, 1, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 4, 24, -2.05F, -1.5F, -26.4F, 0, 1, 0, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 5, 23, -1.9F, -1.5F, -26.65F, 0, 1, 1, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 3, 23, 1.9F, -1.5F, -26.4F, 0, 1, 0, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 4, 23, 1.65F, -1.5F, -26.65F, 0, 1, 1, 0.0F, false));

        torch1 = new ModelRenderer(this);
        torch1.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(torch1, 0.0F, 0.0F, -0.1745F);
        arrow.addChild(torch1);
        torch1.cubeList.add(new ModelBox(torch1, 4, 14, -1.4446F, -7.0608F, -26.5F, 1, 7, 1, 0.0F, false));

        torch2 = new ModelRenderer(this);
        torch2.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(torch2, 0.0F, 0.0F, 0.1745F);
        arrow.addChild(torch2);
        torch2.cubeList.add(new ModelBox(torch2, 0, 14, 0.4446F, -7.0608F, -26.5F, 1, 7, 1, 0.0F, false));
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