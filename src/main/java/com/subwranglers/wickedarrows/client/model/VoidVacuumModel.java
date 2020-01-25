package com.subwranglers.wickedarrows.client.model;

import com.subwranglers.wickedarrows.entity.VoidVacuumEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.ModelBox;

public class VoidVacuumModel<T extends VoidVacuumEntity> extends EntityModel<T> {
    private final RendererModel voidrift;

    public VoidVacuumModel() {
        textureWidth = 80;
        textureHeight = 80;

        voidrift = new RendererModel(this);
        voidrift.setRotationPoint(0.0F, 24.0F, 0.0F);
        voidrift.cubeList.add(new ModelBox(voidrift, 0, 48, -8.0F, -40.0F, 0.0F, 16, 32, 0, 0.0F, false));
        voidrift.cubeList.add(new ModelBox(voidrift, 0, 0, 0.0F, -40.0F, -8.0F, 0, 32, 16, 0.0F, false));
    }

    @Override
    public void render(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        voidrift.render(scale);
    }
}
