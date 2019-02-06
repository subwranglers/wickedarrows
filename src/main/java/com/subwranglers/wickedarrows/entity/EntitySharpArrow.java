package com.subwranglers.wickedarrows.entity;

import com.subwranglers.wickedarrows.base.EntityWArrow;
import com.subwranglers.wickedarrows.potion.PotionBleed;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootTable;

import java.util.List;

public class EntitySharpArrow extends EntityWArrow {

    private int firedVelocity;

    public EntitySharpArrow(World worldIn) {
        super(worldIn);
        setup();
    }

    public EntitySharpArrow(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
        setup();
    }

    public EntitySharpArrow(World worldIn, EntityLivingBase shooter) {
        super(worldIn, shooter);
        setup();
    }

    private void setup() {
        pickupStatus = PickupStatus.CREATIVE_ONLY;
        setKnockbackStrength(-1);
    }

    @Override
    public void shoot(double x, double y, double z, float velocity, float inaccuracy) {
        super.shoot(x, y, z, velocity, inaccuracy);
        firedVelocity = velocity < 1 ? 1 : (int) velocity;
    }

    @Override
    protected void onBlockHit(RayTraceResult trace) {
        BlockPos hit = trace.getBlockPos();

        if (world.getBlockState(hit).getBlock() == Blocks.TNT) {
            world.setBlockToAir(hit);
            Block.spawnAsEntity(world, hit, new ItemStack(Blocks.TNT));
            setDead();
        }
    }

    @Override
    protected void arrowHit(EntityLivingBase living) {
        PotionBleed.apply(living, firedVelocity);

        // 50% chance to drop a mob's loot on hit
        int chance = MathHelper.getInt(world.rand, 1, 2);
        if (world instanceof WorldServer && living instanceof EntityLiving && chance < 2)
            tryDropEntityLoot((EntityLiving) living);
    }

    public void tryDropEntityLoot(EntityLiving living) {
        String[] id = EntityList.getKey(living).toString().split(":");
        ResourceLocation mobLootResLoc = new ResourceLocation(id[0] + ":entities/" + id[1]);
        LootTable loot = world.getLootTableManager().getLootTableFromLocation(mobLootResLoc);

        LootContext.Builder builder = new LootContext.Builder((WorldServer) world);
        builder.withLootedEntity(living);

        List<ItemStack> drops = loot.generateLootForPools(world.rand, builder.build());

        if (drops.size() > 0) {
            ItemStack dropStack = drops.get(MathHelper.getInt(world.rand, 0, drops.size() - 1));

            System.out.println(String.format("Dropping %d items", dropStack.getCount()));

            dropStack.setCount(1);
            Block.spawnAsEntity(world, living.getPosition(), dropStack);
        }
    }

}
