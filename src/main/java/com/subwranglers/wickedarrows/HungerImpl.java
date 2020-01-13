package com.subwranglers.wickedarrows;

import com.subwranglers.util.coordinates.AabbUtil;
import com.subwranglers.wickedarrows.potion.BaitEffect;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.SpiderEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Predicate;

public class HungerImpl {

    public static final double TARGET_RADIUS = 60.d;

    /**
     * Gets a list of entities in the provided {@link AxisAlignedBB}, with a priority search for
     * {@link PlayerEntity players}. If no players can be found, returns a list of any other {@link LivingEntity}s
     * found.
     *
     * @param world
     * @param pos
     * @return
     */
    public static List<LivingEntity> getAfflictedInAABB(IWorld world,
                                                        BlockPos pos,
                                                        @Nullable AxisAlignedBB aabb,
                                                        @Nullable Predicate<LivingEntity> filter) {
        if (aabb == null)
            aabb = AabbUtil.getRadiusAabb(pos, TARGET_RADIUS);

        List<LivingEntity> afflicted = world.getEntitiesWithinAABB(PlayerEntity.class, aabb,
                player -> player != null && player.isPotionActive(BaitEffect.INSTANCE));

        if (afflicted.isEmpty())
            // No afflicted players nearby, so just find any afflicted mobs.
            afflicted = world.getEntitiesWithinAABB(LivingEntity.class, aabb,
                    entity -> entity != null
                            && entity.isPotionActive(BaitEffect.INSTANCE)
                            && (filter == null || filter.test(entity)));

        return afflicted;
    }

    /**
     * <p>Gets a List of {@link MobEntity}s that are near the provided {@link LivingEntity}, only only if:</p>
     * <ol>
     * <li>They're a Zombie or Spider</li>
     * <li>The entity is not themselves</li>
     * <li>If they aren't already targeting an Entity afflicted by {@link BaitEffect}</li>
     * </ol>
     *
     * @param entityHit
     * @return
     */
    public static List<MobEntity> getReadyZombiesSpidersNear(LivingEntity entityHit) {
        return entityHit.getEntityWorld().getEntitiesWithinAABB(
                MonsterEntity.class,
                AabbUtil.getRadiusAabb(entityHit.getPosition(), TARGET_RADIUS),
                mob -> (mob instanceof ZombieEntity || mob instanceof SpiderEntity)
                        && !mob.getUniqueID().equals(entityHit.getUniqueID())
                        && shouldChangeCurrentTarget(mob));
    }

    public static boolean shouldChangeCurrentTarget(MobEntity mob) {
        LivingEntity target = mob.getAttackTarget();
        return target == null || !target.isAlive() || !target.isPotionActive(BaitEffect.INSTANCE);
    }
}
