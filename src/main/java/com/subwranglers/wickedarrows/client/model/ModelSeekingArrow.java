package com.subwranglers.wickedarrows.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelSeekingArrow extends ModelBase {
    private final ModelRenderer arrow_top;
    private final ModelRenderer arrow_mid;
    private final ModelRenderer arrow_tail;

    public ModelSeekingArrow() {
        textureWidth = 32;
        textureHeight = 32;

        arrow_top = new ModelRenderer(this);
        arrow_top.setRotationPoint(0.0F, 24.0F, 0.0F);
        setRotationAngle(arrow_top, -1.5708F, -1.5708F, 0.0F);
        arrow_top.cubeList.add(new ModelBox(arrow_top, 4, 9, -1.0F, -2.0F, -24.0F, 2, 7, 0, 0.0F, false));
        arrow_top.cubeList.add(new ModelBox(arrow_top, 0, 0, 0.0F, -2.0F, -25.0F, 0, 7, 2, 0.0F, false));

        arrow_mid = new ModelRenderer(this);
        arrow_mid.setRotationPoint(0.0F, 24.0F, 0.0F);
        setRotationAngle(arrow_mid, -1.5708F, -1.5708F, 0.0F);
        arrow_mid.cubeList.add(new ModelBox(arrow_mid, 0, 16, 0.0F, -4.0F, -25.0F, 0, 2, 2, 0.0F, false));
        arrow_mid.cubeList.add(new ModelBox(arrow_mid, 4, 16, -1.0F, -4.0F, -24.0F, 2, 2, 0, 0.0F, false));

        arrow_tail = new ModelRenderer(this);
        arrow_tail.setRotationPoint(0.0F, 24.0F, 0.0F);
        setRotationAngle(arrow_tail, -1.5708F, -1.5708F, 0.0F);
        arrow_tail.cubeList.add(new ModelBox(arrow_tail, 4, 0, 0.0F, -11.0F, -25.0F, 0, 7, 2, 0.0F, false));
        arrow_tail.cubeList.add(new ModelBox(arrow_tail, 0, 9, -1.0F, -11.0F, -24.0F, 2, 7, 0, 0.0F, false));
        arrow_tail.cubeList.add(new ModelBox(arrow_tail, 0, 0, -1.0F, -11.0F, -25.0F, 2, 0, 2, 0.0F, false));
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        arrow_top.render(f5);
        arrow_mid.render(f5);
        arrow_tail.render(f5);
    }
    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
