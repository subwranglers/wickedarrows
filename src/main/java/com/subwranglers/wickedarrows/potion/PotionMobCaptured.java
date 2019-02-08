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

    public static final int INTERVAL = MCConst.TICKS_PER_SECOND * 5;

    public static final PotionMobCaptured INSTANCE = new PotionMobCaptured();

    protected PotionMobCaptured() {
        super(false, COLOR);
        setRegistryName(WickedArrows.MOD_ID, MOB_CAPTURED_POTION);
    }

    public static void apply(Entity player, EntityLivingBase mobCaptured) {
        if (!(player instanceof EntityPlayer))
            return;

        // -1 so we don't immediately apply damage when the effect is applied
        int duration = (int) NBTEndlessVoid.getCapturedVoidHealth(player) * INTERVAL - 1;

        INSTANCE.setPotionName(mobCaptured.getName());
        ((EntityPlayer) player).addPotionEffect(new PotionEffect(INSTANCE, duration) {

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
