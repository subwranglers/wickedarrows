package com.subwranglers.wickedarrows;

import com.subwranglers.wickedarrows.potion.PotionBait;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import util.coordinates.AabbUtil;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Predicate;

public class HungerImpl {

    public static final double TARGET_RADIUS = 60.d;

    /**
     * Gets a list of entities in the provided {@link AxisAlignedBB}, with a priority search for
     * {@link EntityPlayer players}. If no players can be found, returns a list of any other {@link EntityLivingBase}s
     * found.
     *
     * @param world
     * @param pos
     * @return
     */
    public static List<EntityLivingBase> getAfflictedInAABB(World world,
                                                            BlockPos pos,
                                                            @Nullable AxisAlignedBB aabb,
                                                            @Nullable Predicate<EntityLivingBase> extraAnd) {
        if (aabb == null)
            aabb = AabbUtil.getRadiusAabb(pos, TARGET_RADIUS);

        if (extraAnd == null)
            extraAnd = entity -> true;
        Predicate<EntityLivingBase> finalExtra = extraAnd;

        List<EntityLivingBase> afflicted = world.getEntitiesWithinAABB(EntityPlayer.class, aabb,
                player -> player != null && player.isPotionActive(PotionBait.INSTANCE));

        if (afflicted.size() == 0)
            // No afflicted players nearby, so just find any afflicted mobs.
            afflicted = world.getEntitiesWithinAABB(EntityLivingBase.class, aabb,
                    entity -> entity != null
                            && entity.isPotionActive(PotionBait.INSTANCE)
                            && finalExtra.test(entity));

        return afflicted;
    }

    /**
     * <p>Gets a List of {@link EntityMob}s that are near the provided {@link EntityLivingBase}, only only if:</p>
     * <ol>
     * <li>They're a Zombie or Spider</li>
     * <li>The entity is not themselves</li>
     * <li>If they aren't already targeting an Entity afflicted by {@link PotionBait}</li>
     * </ol>
     *
     * @param entityHit
     * @return
     */
    public static List<EntityMob> getReadyZombiesSpidersNear(EntityLivingBase entityHit) {
        return entityHit.getEntityWorld().getEntitiesWithinAABB(
                EntityMob.class,
                AabbUtil.getRadiusAabb(entityHit.getPosition(), TARGET_RADIUS),
                mob -> (mob instanceof EntityZombie || mob instanceof EntitySpider)
                        && !mob.getUniqueID().equals(entityHit.getUniqueID())
                        && shouldIgnoreCurrentTarget(mob));
    }

    public static boolean shouldIgnoreCurrentTarget(EntityMob mob) {
        EntityLivingBase target = mob.getAttackTarget();
        return target == null || target.isDead || !target.isPotionActive(PotionBait.INSTANCE);
    }
}
