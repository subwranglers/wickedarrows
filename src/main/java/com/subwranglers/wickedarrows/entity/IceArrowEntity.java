package com.subwranglers.wickedarrows.entity;

import com.subwranglers.wickedarrows.client.sound.IceExplosionSoundEvent;
import com.subwranglers.util.coordinates.BresenhamLine;
import com.subwranglers.util.coordinates.MidpointSphere;
import com.subwranglers.util.coordinates.SphericalCoordinates;
import com.subwranglers.wickedarrows.block.InvokedIceBlock;
import com.subwranglers.wickedarrows.instances.EntityTypes;
import com.subwranglers.wickedarrows.item.IceArrowItem;
import com.subwranglers.wickedarrows.potion.IceEffect;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.state.IStateHolder;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.*;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;


public class IceArrowEntity extends WickedArrowEntity {

    private static Set<IStateHolder> overridesBlocks = new HashSet<>();
    private static Map<BlockState, BiConsumer<World, BlockPos>> changesBlocks = new HashMap<>();

    static {
        addOverride(Blocks.AIR);
        addOverride(Blocks.CAVE_AIR);

        addOverride(Blocks.SNOW);
        addOverride(Blocks.FIRE);

        addOverride(Blocks.GRASS);
        addOverride(Blocks.FERN);
        addOverride(Blocks.DEAD_BUSH);

        addOverride(Blocks.TALL_GRASS);
        addOverride(Blocks.LARGE_FERN);

        BlockTags.SAPLINGS
                .getAllElements()
                .forEach(IceArrowEntity::addOverride);

        BlockTags.SMALL_FLOWERS
                .getAllElements()
                .forEach(IceArrowEntity::addOverride);

        addOverride(Blocks.SUNFLOWER);
        addOverride(Blocks.LILAC);
        addOverride(Blocks.ROSE_BUSH);
        addOverride(Blocks.PEONY);

        overridesBlocks.add(Fluids.FLOWING_WATER.getDefaultState());
        overridesBlocks.add(Fluids.FLOWING_LAVA.getDefaultState());

        BlockState invokedIceMeta = InvokedIceBlock.INSTANCE.getDefaultState();
        changesBlocks.put(Blocks.WATER.getStateContainer().getBaseState(), (world, pos) -> world.setBlockState(pos, invokedIceMeta));

        BlockState obsidian = Blocks.OBSIDIAN.getDefaultState();
        BiConsumer<World, BlockPos> changeToObsidian = (world, pos) -> world.setBlockState(pos, obsidian);
        changesBlocks.put(Blocks.LAVA.getStateContainer().getBaseState(), changeToObsidian);
        changesBlocks.put(Blocks.MAGMA_BLOCK.getDefaultState(), changeToObsidian);
    }

    public IceArrowEntity(EntityType<? extends IceArrowEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public IceArrowEntity(World worldIn, double x, double y, double z) { super(EntityTypes.ICE_ARROW, worldIn, x, y, z); }

    public IceArrowEntity(World worldIn, LivingEntity shooter) {
        super(EntityTypes.ICE_ARROW, worldIn, shooter);
    }

    {
        // reduce damage by 90%. Ice Arrows have many uses, so we need to keep them balanced
        setDamage(getDamage() * 0.1);
    }

    private static void addOverride(Block block) {
        overridesBlocks.add(block.getDefaultState());
    }

    @Override
    public SoundCategory getSoundCategory() {
        return SoundCategory.BLOCKS;
    }

    @Override
    protected ItemStack getArrowStack() {
        return new ItemStack(IceArrowItem.INSTANCE);
    }

    @Override
    protected void onBlockHit(BlockRayTraceResult trace) {
        if (isAlive()) {
            playSound(IceExplosionSoundEvent.INSTANCE, 0.5F, 1.0F);
            generateIceSpike(trace);
            this.remove();
        }
    }

    @Override
    protected void arrowHit(LivingEntity livingHit) {
        // They're frozen -- remove all velocity
        livingHit.setMotion(0, 0, 0);

        IceEffect.applyFrozenEffectToEntity(livingHit);

        playSound(IceExplosionSoundEvent.INSTANCE, 0.5F, 1.0F);

        generateIceCage(
                livingHit.getPosition(),
                Math.max((int) livingHit.getHeight(), (int) livingHit.getWidth()) + 2
        );
        this.remove();
    }

    private void generateIceSpike(BlockRayTraceResult rayTraceResult) {
        BlockPos.MutableBlockPos mut = new BlockPos.MutableBlockPos(rayTraceResult.getPos());
        BlockState invokedIce = InvokedIceBlock.INSTANCE.getDefaultState();
        Vec3d spikeTip = getSpikeTipCoords(rayTraceResult);

        Consumer<Vec3i> consumer = vec -> {
            mut.setPos(vec.getX(), vec.getY(), vec.getZ());
            tryPutBlock(mut, invokedIce);
        };

        for (Direction side : Direction.values()) {
            BlockPos neighbour = mut.offset(side, 2);

            BresenhamLine.doPerLineCoord(
                    (int) spikeTip.x,
                    (int) spikeTip.y,
                    (int) spikeTip.z,
                    neighbour.getX(),
                    neighbour.getY(),
                    neighbour.getZ(),
                    consumer);
        }
    }

    private Vec3d getSpikeTipCoords(BlockRayTraceResult rayTrace) {
        boolean hitSides = rayTrace.getFace() != Direction.DOWN && rayTrace.getFace() != Direction.UP; // for brevity
        float pitch = hitSides ? rotationPitch : -rotationPitch;
        float yaw = hitSides ? SphericalCoordinates.getMirroredYaw(rotationYaw, rayTrace.getFace()) : rotationYaw;
        int velocityLength = 8;

        return SphericalCoordinates.getFromSphere(rayTrace.getHitVec(), velocityLength, yaw, pitch);
    }

    private void generateIceCage(BlockPos pos, int radius) {
        BlockPos.MutableBlockPos mut = new BlockPos.MutableBlockPos(pos);
        BlockState invokedIce = InvokedIceBlock.INSTANCE.getDefaultState();

        MidpointSphere.doPerSphereCoord(pos.getX(), pos.getY(), pos.getZ(), radius, vec -> {
            mut.setPos(vec.getX(), vec.getY(), vec.getZ());
            tryPutBlock(mut, invokedIce);
        });
    }

    private void tryPutBlock(BlockPos.MutableBlockPos pos, BlockState invokedIce) {
        BlockState block = world.getBlockState(pos);
        if (overridesBlocks.contains(block))
            // Just change the block to invoked ice
            world.setBlockState(pos, invokedIce);

        else if (changesBlocks.containsKey(block))
            // Change block to another based on what type they are
            changesBlocks.get(block).accept(world, pos);
    }
}
