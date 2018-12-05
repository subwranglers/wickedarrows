package com.subwranglers.wickedarrows.particle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ParticleIceCrackle extends Particle {

    protected ParticleIceCrackle(World worldIn, BlockPos pos) {
        super(worldIn, pos.getX(), pos.getY(), pos.getZ());
        setMaxAge(33333333); // same as InvokedIce's age
    }

    public static void spawn(World world, BlockPos pos) {
        Minecraft.getMinecraft().effectRenderer.addEffect(new ParticleIceCrackle(world, pos));
    }

    @Override
    public void renderParticle(BufferBuilder buffer, Entity entityIn, float partialTicks, float rotationX, float rotationZ, float rotationYZ, float rotationXY, float rotationXZ) {
        super.renderParticle(buffer, entityIn, partialTicks, rotationX, rotationZ, rotationYZ, rotationXY, rotationXZ);
    }
}
