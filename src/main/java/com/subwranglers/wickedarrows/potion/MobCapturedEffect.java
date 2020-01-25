package com.subwranglers.wickedarrows.potion;

import com.subwranglers.wickedarrows.WickedArrows;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.DisplayEffectsScreen;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.EffectUtils;

import static com.subwranglers.wickedarrows.info.Names.MOB_CAPTURED_EFFECT;

public class MobCapturedEffect extends Effect {

    private static final int COLOR = 0x005e0080;

    public static final MobCapturedEffect INSTANCE = new MobCapturedEffect();
    private static String mobName = "";

    protected MobCapturedEffect() {
        super(EffectType.NEUTRAL, COLOR);
        setRegistryName(WickedArrows.MOD_ID, MOB_CAPTURED_EFFECT);
    }

    @Override
    public String getName() {
        return mobName;
    }

    public static void setName(String name) {
        mobName = name;
    }


// Instead of overwriting getName, which may have unintended side effects, it might be better to use the method below.
// The code in renderInventoryEffect is based on DisplayEffectsScreen.drawActivePotionEffectsNames, but we can use a private function to get the effect text.

//    @Override
//    public boolean shouldRenderInvText(EffectInstance effect) {
//        return false;
//    }
//
//    @Override
//    public void renderInventoryEffect(EffectInstance effect, DisplayEffectsScreen<?> gui, int x, int y, float z) {
//
//        String s = this.getMobName();
//
//        FontRenderer fontRenderer = Minecraft.getInstance().fontRenderer;
//        fontRenderer.drawStringWithShadow(s, (float)(x + 10 + 18), (float)(y + 6), 16777215);
//        String s1 = EffectUtils.getPotionDurationString(effect, 1.0F);
//        fontRenderer.drawStringWithShadow(s1, (float)(x + 10 + 18), (float)(y + 6 + 10), 8355711);
//    }
}
