package com.subwranglers.wickedarrows.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelVoidVacuum extends ModelBase {
    private final ModelRenderer voidrift;

    public ModelVoidVacuum() {
        textureWidth = 80;
        textureHeight = 80;

        voidrift = new ModelRenderer(this);
        voidrift.setRotationPoint(0.0F, 24.0F, 0.0F);
        voidrift.cubeList.add(new ModelBox(voidrift, 0, 48, -8.0F, -40.0F, 0.0F, 16, 32, 0, 0.0F, false));
        voidrift.cubeList.add(new ModelBox(voidrift, 0, 0, 0.0F, -40.0F, -8.0F, 0, 32, 16, 0.0F, false));
    }

    @Override
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        voidrift.render(scale);
    }
}
