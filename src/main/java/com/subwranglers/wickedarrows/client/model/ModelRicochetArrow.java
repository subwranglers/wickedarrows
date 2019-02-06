package com.subwranglers.wickedarrows.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelRicochetArrow extends ModelBase {

    private final ModelRenderer arrow;

    public ModelRicochetArrow() {
        textureWidth = 48;
        textureHeight = 48;

        arrow = new ModelRenderer(this);
        arrow.setRotationPoint(0.0F, 24.0F, 0.0F);
        setRotationAngle(arrow, -1.5708F, -1.5708F, 0.0F);
        arrow.cubeList.add(new ModelBox(arrow, 4, 0, -1.0F, -10.0F, -25.0F, 2, 16, 0, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 0, 0, 0.0F, -10.0F, -26.0F, 0, 16, 2, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 24, 33, -1.0F, 3.0F, -26.0F, 2, 1, 2, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 24, 37, -1.0F, 1.0F, -27.0F, 2, 2, 1, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 0, 41, -1.0F, 1.0F, -24.0F, 2, 2, 1, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 0, 37, 1.0F, 1.0F, -26.0F, 1, 2, 2, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 6, 37, -2.0F, 1.0F, -26.0F, 1, 2, 2, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 20, 18, -2.0F, -4.0F, -28.0F, 4, 5, 1, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 0, 27, -2.0F, -4.0F, -23.0F, 4, 5, 1, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 0, 18, 2.0F, -4.0F, -27.0F, 1, 5, 4, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 10, 18, -3.0F, -4.0F, -27.0F, 1, 5, 4, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 20, 44, 1.0F, 1.0F, -24.0F, 1, 1, 1, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 16, 44, 1.0F, 1.0F, -27.0F, 1, 1, 1, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 12, 44, -2.0F, 1.0F, -27.0F, 1, 1, 1, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 24, 44, -2.0F, 1.0F, -24.0F, 1, 1, 1, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 8, 33, -2.0F, -6.0F, -27.0F, 3, 2, 1, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 18, 27, 1.0F, -6.0F, -27.0F, 1, 2, 3, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 16, 33, -1.0F, -6.0F, -24.0F, 3, 2, 1, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 10, 27, -2.0F, -6.0F, -26.0F, 1, 2, 3, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 0, 33, -1.0F, -8.0F, -26.0F, 2, 2, 2, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 6, 41, 2.0F, -5.0F, -26.0F, 1, 1, 2, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 12, 37, -3.0F, -5.0F, -26.0F, 1, 1, 2, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 0, 44, -1.0F, -5.0F, -23.0F, 2, 1, 1, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 6, 44, -1.0F, -5.0F, -28.0F, 2, 1, 1, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 18, 37, -3.0F, 1.0F, -26.0F, 1, 1, 2, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 18, 41, -1.0F, 1.0F, -23.0F, 2, 1, 1, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 12, 41, 2.0F, 1.0F, -26.0F, 1, 1, 2, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 24, 41, -1.0F, 1.0F, -28.0F, 2, 1, 1, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 8, 0, -2.0F, -10.0F, -25.0F, 4, 2, 0, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 8, 0, 0.0F, -10.0F, -27.0F, 0, 2, 4, 0.0F, false));
        arrow.cubeList.add(new ModelBox(arrow, 12, 0, -2.0F, -10.0F, -27.0F, 4, 0, 4, 0.0F, false));
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
