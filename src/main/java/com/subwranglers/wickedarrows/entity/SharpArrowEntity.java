package com.subwranglers.wickedarrows.entity;

import com.subwranglers.wickedarrows.instances.EntityTypes;
import com.subwranglers.wickedarrows.item.SharpArrowItem;
import com.subwranglers.wickedarrows.potion.BleedEffect;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootParameterSets;
import net.minecraft.world.storage.loot.LootParameters;
import net.minecraft.world.storage.loot.LootTable;

import java.util.Collection;
import java.util.List;

public class SharpArrowEntity extends WickedArrowEntity {

    private int firedVelocity;

    public SharpArrowEntity(EntityType<? extends SharpArrowEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public SharpArrowEntity(World worldIn, double x, double y, double z) {
        super(EntityTypes.SHARP_ARROW, worldIn, x, y, z);
    }

    public SharpArrowEntity(World worldIn, LivingEntity shooter) {
        super(EntityTypes.SHARP_ARROW, worldIn, shooter);
    }

    {
        pickupStatus = PickupStatus.CREATIVE_ONLY;
        setKnockbackStrength(-1);
    }

    @Override
    protected ItemStack getArrowStack() {
        return new ItemStack(SharpArrowItem.INSTANCE);
    }

    @Override
    public void shoot(double x, double y, double z, float velocity, float inaccuracy) {
        super.shoot(x, y, z, velocity, inaccuracy);
        firedVelocity = velocity < 1 ? 1 : (int) velocity;
    }

    @Override
    protected void onBlockHit(BlockRayTraceResult trace) {
        BlockPos hit = trace.getPos();

        if (world.getBlockState(hit).getBlock() == Blocks.TNT) {
            world.removeBlock(hit, false);
            Block.spawnAsEntity(world, hit, new ItemStack(Blocks.TNT));
            this.remove();
        }
    }

    @Override
    protected void arrowHit(LivingEntity living) {
        BleedEffect.apply(living, firedVelocity); // firedVelocity = 1, 2, or 3

        if (!world.isRemote)
            tryDropEntityLoot(living);
    }

    // LivingEntity.dropLoot is inaccessible, so we replicate the logic as closely as possible here
    private void tryDropEntityLoot(LivingEntity living) {
        ResourceLocation resourcelocation = living.getLootTableResourceLocation();
        LootTable loot = world.getServer().getLootTableManager().getLootTableFromLocation(resourcelocation);

        LootContext.Builder lootContextBuilder = this.getLootContextBuilderFor(living);

        // TODO: 06/02/19 instead of generateLootForPools(), use some other non-random method
        List<ItemStack> drops = loot.generate(lootContextBuilder.build(LootParameterSets.ENTITY));

        if (drops.size() > 0) {
            ItemStack dropStack = drops.get(MathHelper.nextInt(world.rand, 0, drops.size() - 1));

            System.out.println(String.format("Dropping %d items", dropStack.getCount()));

            dropStack.setCount(1);
            living.entityDropItem(dropStack);
        }
    }

    // This is basically just a copy of the logic from LivingEntity.getLootContextBuilder
    // I don't know how the loot system works, so some of this might be unnecessary
    private LootContext.Builder getLootContextBuilderFor(LivingEntity living){
        LootContext.Builder builder = new LootContext.Builder((ServerWorld)this.world)
                .withRandom(this.rand)
                .withParameter(LootParameters.THIS_ENTITY, this)
                .withParameter(LootParameters.POSITION, new BlockPos(this));

        DamageSource damageSource = living.getLastDamageSource();
        if(damageSource != null) {
            builder
                .withParameter(LootParameters.DAMAGE_SOURCE, damageSource)
                .withNullableParameter(LootParameters.KILLER_ENTITY, damageSource.getTrueSource())
                .withNullableParameter(LootParameters.DIRECT_KILLER_ENTITY, damageSource.getImmediateSource());
        }

        Entity attacking = living.getAttackingEntity();
        if(attacking instanceof PlayerEntity){
            PlayerEntity attackingPlayer = (PlayerEntity) attacking;
            builder
                .withParameter(LootParameters.LAST_DAMAGE_PLAYER, attackingPlayer)
                .withLuck(attackingPlayer.getLuck());
        }
        return builder;
    }
}
