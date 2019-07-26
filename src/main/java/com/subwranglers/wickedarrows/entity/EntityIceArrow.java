package com.subwranglers.wickedarrows.entity;

import com.subwranglers.wickedarrows.base.EntityWArrow;
import com.subwranglers.wickedarrows.block.BlockInvokedIce;
import com.subwranglers.wickedarrows.item.ItemIceArrow;
import com.subwranglers.wickedarrows.potion.PotionIce;
import com.subwranglers.wickedarrows.client.sound.IceExplosionSoundEvent;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import util.Consumer2;
import util.coordinates.BresenhamLine;
import util.coordinates.MidpointSphere;
import util.coordinates.SphericalCoordinates;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;


public class EntityIceArrow extends EntityWArrow {

    private static Set<IBlockState> overridesBlocks = new HashSet<>();
    private static Map<IBlockState, Consumer2<World, BlockPos>> changesBlocks = new HashMap<>();

    static {
        overridesBlocks.add(Blocks.AIR.getDefaultState());
        overridesBlocks.add(Blocks.SAPLING.getDefaultState());
        overridesBlocks.add(Blocks.DEADBUSH.getDefaultState());
        overridesBlocks.add(Blocks.RED_FLOWER.getDefaultState());
        overridesBlocks.add(Blocks.YELLOW_FLOWER.getDefaultState());
        overridesBlocks.add(Blocks.SNOW.getDefaultState());
        overridesBlocks.add(Blocks.SNOW_LAYER.getDefaultState());
        overridesBlocks.add(Blocks.FIRE.getDefaultState());
        overridesBlocks.add(Blocks.FLOWING_WATER.getDefaultState());
        overridesBlocks.add(Blocks.FLOWING_LAVA.getDefaultState());

        // TODO: 23/11/18 Add BlockFlower to overrides

        for (BlockTallGrass.EnumType type : BlockTallGrass.EnumType.values())
            overridesBlocks.add(Blocks.TALLGRASS.getDefaultState().withProperty(BlockTallGrass.TYPE, type));

        for (BlockDoublePlant.EnumPlantType type : BlockDoublePlant.EnumPlantType.values())
            overridesBlocks.add(Blocks.DOUBLE_PLANT.getDefaultState().withProperty(BlockDoublePlant.VARIANT, type));

        IBlockState invokedIceMeta = BlockInvokedIce.getDefaultBlockState();
        changesBlocks.put(Blocks.WATER.getBlockState().getBaseState(), (world, pos) -> world.setBlockState(pos, invokedIceMeta));

        IBlockState obsidian = Blocks.OBSIDIAN.getDefaultState();
        Consumer2<World, BlockPos> changeToObsidian = (world, pos) -> world.setBlockState(pos, obsidian);
        changesBlocks.put(Blocks.LAVA.getBlockState().getBaseState(), changeToObsidian);
        changesBlocks.put(Blocks.MAGMA.getDefaultState(), changeToObsidian);
    }

    public EntityIceArrow(World worldIn) {
        super(worldIn);
        setup();
    }

    public EntityIceArrow(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
        setup();
    }

    public EntityIceArrow(World worldIn, EntityLivingBase shooter) {
        super(worldIn, shooter);
        setup();
    }

    private void setup() {
        // reduce damage by 90%. Ice Arrows have many uses, so we need to keep them balanced
        setDamage(getDamage() * 0.1);
    }

    @Override
    public SoundCategory getSoundCategory() {
        return SoundCategory.BLOCKS;
    }

    @Override
    protected ItemStack getArrowStack() {
        return new ItemStack(ItemIceArrow.INSTANCE);
    }

    @Override
    public void onUpdate(){
        super.onUpdate();
        if (!world.isRemote){
            Vec3d vec3d1 = new Vec3d(this.posX, this.posY, this.posZ);
            Vec3d vec3d2 = new Vec3d(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
            RayTraceResult raytraceresult = this.world.rayTraceBlocks(vec3d1, vec3d2, true);

            if(raytraceresult != null){
                IBlockState blockState = world.getBlockState(raytraceresult.getBlockPos());
                if(blockState == Blocks.WATER.getDefaultState())
                    this.onWaterHit(raytraceresult);
            }
        }
    }

    @Override
    protected void onHit(RayTraceResult trace) {
        super.onHit(trace);

        if (!world.isRemote && trace.typeOfHit == RayTraceResult.Type.BLOCK && !isDead) {
            playSound(IceExplosionSoundEvent.INSTANCE, 0.5F, 1.0F);
            generateIceSpike(trace);
            setDead();
        }
    }

    protected void onWaterHit(RayTraceResult trace){

        Vec3d position = new Vec3d(this.posX, this.posY, this.posZ);
        Vec3d velocity = new Vec3d(this.motionX, this.motionY, this.motionZ);
        Vec3d spikeTip = velocity.normalize().scale(8).add(position);

        generateIceSpike(trace, spikeTip);
        makeIcePlatform(trace.getBlockPos());
        setDead();
    }

    @Override
    protected void arrowHit(EntityLivingBase livingHit) {
        // They're frozen -- remove all velocity
        livingHit.motionX = 0.D;
        livingHit.motionY = 0.D;
        livingHit.motionZ = 0.D;

        PotionIce.applyFrozenEffectToEntity(livingHit);

        playSound(IceExplosionSoundEvent.INSTANCE, 0.5F, 1.0F);

        generateIceCage(
                livingHit.getPosition(),
                Math.max((int) livingHit.width, (int) livingHit.height) + 2
        );
        setDead();
    }

    private void makeIcePlatform(BlockPos pos){
        IBlockState invokedIce = BlockInvokedIce.getDefaultBlockState();
        BlockPos.MutableBlockPos mut = new BlockPos.MutableBlockPos(pos);

        tryPutBlock(mut, invokedIce);
        tryPutBlock(mut.move(EnumFacing.NORTH), invokedIce);
        tryPutBlock(mut.move(EnumFacing.EAST), invokedIce);
        tryPutBlock(mut.move(EnumFacing.SOUTH), invokedIce);
        tryPutBlock(mut.move(EnumFacing.SOUTH), invokedIce);
        tryPutBlock(mut.move(EnumFacing.WEST), invokedIce);
        tryPutBlock(mut.move(EnumFacing.WEST), invokedIce);
        tryPutBlock(mut.move(EnumFacing.NORTH), invokedIce);
        tryPutBlock(mut.move(EnumFacing.NORTH), invokedIce);
    }

    private void generateIceSpike(RayTraceResult rayTraceResult) {
        Vec3d spikeTip = getSpikeTipCoords(rayTraceResult);
        generateIceSpike(rayTraceResult, spikeTip);
    }

    private void generateIceSpike(RayTraceResult rayTraceResult, Vec3d spikeTip){
        BlockPos.MutableBlockPos mut = new BlockPos.MutableBlockPos(rayTraceResult.getBlockPos());
        IBlockState invokedIce = BlockInvokedIce.getDefaultBlockState();

        Consumer<Vec3i> consumer = vec -> {
            mut.setPos(vec.getX(), vec.getY(), vec.getZ());
            tryPutBlock(mut, invokedIce);
        };

        for (EnumFacing side : EnumFacing.values()) {
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

    private Vec3d getSpikeTipCoords(RayTraceResult rayTrace) {
        boolean hitSides = rayTrace.sideHit != EnumFacing.DOWN && rayTrace.sideHit != EnumFacing.UP; // for brevity
        float pitch = hitSides ? rotationPitch : -rotationPitch;
        float yaw = hitSides ? SphericalCoordinates.getMirroredYaw(rotationYaw, rayTrace.sideHit) : rotationYaw;
        int velocityLength = 8;

        return SphericalCoordinates.getFromSphere(rayTrace.hitVec, velocityLength, yaw, pitch);
    }

    private void generateIceCage(BlockPos pos, int radius) {
        BlockPos.MutableBlockPos mut = new BlockPos.MutableBlockPos(pos);
        IBlockState invokedIce = BlockInvokedIce.getDefaultBlockState();

        MidpointSphere.doPerSphereCoord(pos.getX(), pos.getY(), pos.getZ(), radius, vec -> {
            mut.setPos(vec.getX(), vec.getY(), vec.getZ());
            tryPutBlock(mut, invokedIce);
        });
    }

    private void tryPutBlock(BlockPos.MutableBlockPos pos, IBlockState invokedIce) {
        IBlockState testPos = world.getBlockState(pos);
        if (overridesBlocks.contains(testPos))
            // Just change the block to invoked ice
            world.setBlockState(pos, invokedIce);

        else if (changesBlocks.containsKey(testPos))
            // Change block to another based on what type they are
            changesBlocks.get(testPos).accept(world, pos);
    }
}
