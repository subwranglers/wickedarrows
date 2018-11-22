package com.subwranglers.wickedarrows.entities;

import com.subwranglers.wickedarrows.base.EntityWArrow;
import com.subwranglers.wickedarrows.blocks.BlockInvokedIce;
import com.subwranglers.wickedarrows.items.ItemIceArrow;
import com.subwranglers.wickedarrows.potion.PotionIce;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import util.Consumer2;
import util.S;
import util.coordinates.Circles;
import util.coordinates.Point3D;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

/**
 *
 */
public class EntityIceArrow extends EntityWArrow {

    private static Set<IBlockState> overridesBlocks = new HashSet<>();
    private static Map<IBlockState, Consumer2<World, BlockPos>> changesBlocks = new HashMap<>();

    static {
        overridesBlocks.add(S.getBs(Blocks.AIR));
        overridesBlocks.add(S.getBs(Blocks.TALLGRASS));
        overridesBlocks.add(S.getBs(Blocks.DEADBUSH));
        overridesBlocks.add(S.getBs(Blocks.FIRE));
        overridesBlocks.add(S.getBs(Blocks.SNOW));
        overridesBlocks.add(S.getBs(Blocks.SNOW_LAYER));
        overridesBlocks.add(S.getBs(Blocks.GRASS));
        overridesBlocks.add(S.getBs(Blocks.RED_FLOWER));
        overridesBlocks.add(S.getBs(Blocks.YELLOW_FLOWER));
        overridesBlocks.add(S.getBs(Blocks.FLOWING_WATER));
        overridesBlocks.add(S.getBs(Blocks.FLOWING_LAVA));

        IBlockState invokedIceMeta = BlockInvokedIce.getShouldTurnToWaterState(true);
        changesBlocks.put(Blocks.WATER.getDefaultState(), (world, pos) -> world.setBlockState(pos, invokedIceMeta));

        IBlockState obsidian = Blocks.OBSIDIAN.getDefaultState();
        changesBlocks.put(Blocks.LAVA.getDefaultState(), (world, pos) -> world.setBlockState(pos, obsidian));
        changesBlocks.put(Blocks.MAGMA.getDefaultState(), (world, pos) -> world.setBlockState(pos, obsidian));
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
    protected ItemStack getArrowStack() {
        return new ItemStack(ItemIceArrow.instance);
    }

    @Override
    protected void onHit(RayTraceResult raytraceResultIn) {
        long start = System.currentTimeMillis();

        super.onHit(raytraceResultIn);
        BlockPos blockHit = raytraceResultIn.getBlockPos();
        if (blockHit == null)
            // A Block wasn't hit -- return early.
            return;

        int blockX = blockHit.getX(), blockY = blockHit.getY(), blockZ = blockHit.getZ();
        IBlockState invokedIce = BlockInvokedIce.getShouldTurnToWaterState(false);

        World worldIn = getEntityWorld();

        // Spawn invokedIce blocks in a 3x3x3 pattern, only replacing air.
        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos(blockHit);
        for (int y = blockY - 1; y <= blockY + 1; y++) // Build from bottom
            for (int x = blockX - 1; x <= blockX + 1; x++)
                for (int z = blockZ - 1; z <= blockZ + 1; z++)
                    tryPutBlock(worldIn, pos.setPos(x, y, z), invokedIce);

        long duration = System.currentTimeMillis() - start;
        System.out.println("Took " + duration + "ms to spawn 3x3x3 ice");
    }

    @Override
    protected void arrowHit(EntityLivingBase livingHit) {
        long start = System.currentTimeMillis();

        super.arrowHit(livingHit);
        setDead();

        PotionIce.applyFrozenEffectToEntity(livingHit);

        // They're frozen -- remove all velocity
        livingHit.setVelocity(0, 0, 0);

        generateIceCage(
                livingHit.world,
                livingHit.getPosition(),
                Math.max((int) livingHit.width, (int) livingHit.height)
        );

        long duration = System.currentTimeMillis() - start;
        System.out.println("Took " + duration + "ms to cage entity with ice");
    }

    private void generateIceCage(World world, BlockPos pos, int radius) {
        int y = pos.getY();
        BlockPos.MutableBlockPos mut = new BlockPos.MutableBlockPos(pos);
        IBlockState invokedIce = BlockInvokedIce.getShouldTurnToWaterState(false);

        for (int i = y - radius; i <= y + radius; i++) {
            Consumer<Point3D> consumer = point3D -> {
                mut.setPos(point3D.getX(), point3D.getY(), point3D.getZ());
                tryPutBlock(world, mut, invokedIce);
            };

            Circles.getCircleCoords(pos.getX(), y, pos.getZ(), radius, consumer);
        }
//        // Build walls around the entity as tall as they are
//        double r = (int) livingHit.width + 2.0D;
//
//        for (int y = belowEnt; y <= aboveEnt; y++)
//            for (Point3D point : getAllPolarXZ(livingPos.getX(), livingPos.getZ(), r)) {
//                pos.setPos(point.getX(), y, point.getZ());
//                tryPutBlock(livingHit.world, pos, invokedIce);
//            }
//
//        // Cap the top and bottom of the cage with domes, relative to how wide the cage is
//        int y = 1;
//        r--; // Start loop with decremented radius -- we're building within the wall's radius now
//        while (r > 0.0D)  {
//            for (Point3D point : getAllPolarXZ(livingPos.getX(), livingPos.getZ(), r)) {
//
//                // Build above
//                pos.setPos(point.getX(), aboveEnt + y, point.getZ());
//                tryPutBlock(livingHit.world, pos, invokedIce);
//
//                // Build below
//                pos.setPos(point.getX(), belowEnt - y, point.getZ());
//                tryPutBlock(livingHit.world, pos, invokedIce);
//            }
//            y++; // next layer
//            r--; // smaller radius to close off eventually
//        }
    }

    private void tryPutBlock(World worldIn, BlockPos.MutableBlockPos pos, IBlockState invokedIce) {
        IBlockState testPos = worldIn.getBlockState(pos);
        if (overridesBlocks.contains(testPos))
            // Put ice block if we're supposed to override a specific block
            worldIn.setBlockState(pos, invokedIce);

        else if (changesBlocks.containsKey(testPos))
            // Change block to another type based on what it is
            changesBlocks.get(testPos).accept(worldIn, pos);
    }
}
