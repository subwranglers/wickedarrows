package com.subwranglers.wickedarrows.potion;

import com.subwranglers.wickedarrows.WickedArrows;
import com.subwranglers.wickedarrows.nbt.NBTEndlessVoid;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import util.MCConst;

import static com.subwranglers.wickedarrows.info.Names.*;

public class PotionMobCaptured extends Potion {

    private static final int COLOR = 0x005e0080;

    /**
     * Starting value to subtract from for how long any given mob will survive in the <i>Endless Void.</i>
     */
    public static final int MAX_DURATION = MCConst.TICKS_PER_SECOND * 600;

    /**
     * Minimum possible duration a mob can be held in the <i>Endless Void.</i>
     */
    public static final int MIN_DURATION = MCConst.TICKS_PER_SECOND * 60;

    /**
     * How much time should be subtracted from {@link #MAX_DURATION}. Multiply by the capture'd mob's current health.
     */
    public static final int INTERVAL = MCConst.TICKS_PER_SECOND * 15;

    public static final PotionMobCaptured INSTANCE = new PotionMobCaptured();

    protected PotionMobCaptured() {
        super(false, COLOR);
        setRegistryName(WickedArrows.MOD_ID, MOB_CAPTURED_POTION);
    }

    public static void apply(Entity player, EntityLivingBase mobCaptured) {
        if (!(player instanceof EntityPlayer))
            return;

        int duration = (int) NBTEndlessVoid.getCapturedVoidHealth(player) * INTERVAL;

        INSTANCE.setPotionName(mobCaptured.getName());
        ((EntityPlayer) player).addPotionEffect(new PotionEffect(INSTANCE, duration - 1) {

                    @Override
                    public boolean onUpdate(EntityLivingBase entityIn) {
                        int ticksRemaining = getDuration();
                        if (ticksRemaining % INTERVAL == 0) {

                            // Damage the mob
                            if (NBTEndlessVoid.damageCapturedVoidHealth(entityIn) <= 0.f) {
                                // Mob was lost to the Endless Void. Consume the mob and remove this potion effect.
                                NBTEndlessVoid.consumeMob(entityIn);
                                entityIn.removePotionEffect(INSTANCE);
                            }
                        } else if (getDuration() <= 1)
                            NBTEndlessVoid.consumeMob(entityIn);
                        return super.onUpdate(entityIn);
                    }
                });
    }
}
