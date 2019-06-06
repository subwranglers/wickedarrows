package com.subwranglers.wickedarrows.inventory;

import com.subwranglers.wickedarrows.WickedArrows;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class GuiArrowWorkbench extends GuiContainer {
    private static final ResourceLocation BG_TEXTURE = new ResourceLocation(WickedArrows.MOD_ID,
            "textures/gui/arrow_workbench_gui.png");

    public GuiArrowWorkbench(Container container) {
        super(container);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1, 1, 1, 1);
        mc.getTextureManager().bindTexture(BG_TEXTURE);
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
//        String name = I18n.format(BlockArrowWorkbench.INSTANCE.getLocalizedName() + ".name");
//        fontRenderer.drawString(name, xSize / 2 - fontRenderer.getStringWidth(name) / 2, 6, 0x404040);
//        fontRenderer.drawString(playerInv.getDisplayName().getUnformattedText(), 8, ySize - 94, 0x404040);
    }
}
