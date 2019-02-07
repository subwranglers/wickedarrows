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

    public static final int DURATION_MULTIPLIER = MCConst.TICKS_PER_SECOND * 15;

    public static final PotionMobCaptured INSTANCE = new PotionMobCaptured();

    protected PotionMobCaptured() {
        super(false, COLOR);
        setRegistryName(WickedArrows.MOD_ID, MOB_CAPTURED_POTION);
        setPotionName(name(MOB_CAPTURED_POTION, QUALIFY));
    }

    public static void apply(Entity player, EntityLivingBase mobCaptured) {
        if (!(player instanceof EntityPlayer))
            return;

        ((EntityPlayer) player).addPotionEffect(
                new PotionEffect(INSTANCE, (int) mobCaptured.getMaxHealth() * DURATION_MULTIPLIER) {

                    @Override
                    public boolean onUpdate(EntityLivingBase entityIn) {
                        if (getDuration() <= 1)
                            NBTEndlessVoid.consumeMob(entityIn);
                        return super.onUpdate(entityIn);
                    }
                });
    }
}
