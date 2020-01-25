package com.subwranglers.wickedarrows.client.model;

import com.subwranglers.wickedarrows.entity.VoidSnareArrowEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.ModelBox;

public class VoidSnareArrowModel<T extends VoidSnareArrowEntity> extends EntityModel<T> {

    private final RendererModel arrow;

    public VoidSnareArrowModel() {
        textureWidth = 32;
        textureHeight = 32;

        arrow = new RendererModel(this);
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
    public void render(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        arrow.render(scale);
    }

    public void setRotationAngle(RendererModel modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
