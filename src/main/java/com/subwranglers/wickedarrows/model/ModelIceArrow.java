package com.subwranglers.wickedarrows.model;// Cubik Studio 2.9.480 Beta JAVA exporter
// Model generated using MrCrayfish's Model Creator (http://mrcrayfish.com/modelcreator/)

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelIceArrow extends ModelBase {

    //fields
    public ModelRenderer e1;
    public ModelRenderer e2;
    public ModelRenderer e3;
    public ModelRenderer e4;
    public ModelRenderer e5;
    public ModelRenderer e6;
    public ModelRenderer e7;
    public ModelRenderer e8;
    public ModelRenderer e9;

    public ModelIceArrow()
    {
        textureWidth = 64;
        textureHeight = 64;

        e1 = new ModelRenderer(this, 24, 5);
        e1.setRotationPoint(-8.5F, 8F, -8F);
        e1.addBox(0F, 0F, 0F, 1, 10, 1);
        e1.setTextureSize(64, 64);
        e1.mirror = false;
        setRotation(e1, 0F, 0F, 0F);
        e2 = new ModelRenderer(this, 0, 4);
        e2.setRotationPoint(-11F, 22F, -11F);
        e2.addBox(0F, 0F, 0F, 6, 6, 6);
        e2.setTextureSize(64, 64);
        e2.mirror = false;
        setRotation(e2, 0F, 0F, 0F);
        e3 = new ModelRenderer(this, 0, 0);
        e3.setRotationPoint(-9F, 16F, -8F);
        e3.addBox(0F, 4.768372E-07F, 0F, 2, 3, 1);
        e3.setTextureSize(64, 64);
        e3.mirror = false;
        setRotation(e3, 0F, 0F, 0F);
        e4 = new ModelRenderer(this, 6, 0);
        e4.setRotationPoint(-7F, 15F, -8F);
        e4.addBox(0F, 1.430511E-06F, 4.768372E-07F, 1, 3, 1);
        e4.setTextureSize(64, 64);
        e4.mirror = false;
        setRotation(e4, 0F, 0F, 0F);
        e5 = new ModelRenderer(this, 24, 0);
        e5.setRotationPoint(-8F, 16F, -9F);
        e5.addBox(0F, 1.430511E-06F, 0F, 1, 3, 2);
        e5.setTextureSize(64, 64);
        e5.mirror = false;
        setRotation(e5, 0F, 0F, 0F);
        e6 = new ModelRenderer(this, 10, 0);
        e6.setRotationPoint(-8F, 15F, -10F);
        e6.addBox(0F, 1.430511E-06F, 0F, 1, 3, 1);
        e6.setTextureSize(64, 64);
        e6.mirror = false;
        setRotation(e6, 0F, 0F, 0F);
        e7 = new ModelRenderer(this, 10, 0);
        e7.setRotationPoint(-8F, 15F, -7.000001F);
        e7.addBox(0F, 1.430511E-06F, 9.536743E-07F, 1, 3, 1);
        e7.setTextureSize(64, 64);
        e7.mirror = false;
        setRotation(e7, 0F, 0F, 0F);
        e8 = new ModelRenderer(this, 6, 0);
        e8.setRotationPoint(-10F, 15F, -8F);
        e8.addBox(-4.768372E-07F, 1.430511E-06F, 4.768372E-07F, 1, 3, 1);
        e8.setTextureSize(64, 64);
        e8.mirror = false;
        setRotation(e8, 0F, 0F, 0F);
        e9 = new ModelRenderer(this, 28, 5);
        e9.setRotationPoint(-8F, 8F, -8.5F);
        e9.addBox(0F, 0F, 0F, 1, 10, 1);
        e9.setTextureSize(64, 64);
        e9.mirror = false;
        setRotation(e9, 0F, 0F, 0F);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);

        e1.render(f5);
        e2.render(f5);
        e3.render(f5);
        e4.render(f5);
        e5.render(f5);
        e6.render(f5);
        e7.render(f5);
        e8.render(f5);
        e9.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
     
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
    {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    }
 
}