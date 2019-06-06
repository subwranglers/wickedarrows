package com.subwranglers.wickedarrows.inventory;

import com.subwranglers.wickedarrows.tileentity.TileEntityArrowWorkbench;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

import javax.annotation.Nullable;

public class WickedGuiHandler implements IGuiHandler {

    public static final int ARROW_WORKBENCH = 0;

    @Nullable
    @Override
    public Container getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == WickedGuiHandler.ARROW_WORKBENCH) {
            return new ContainerArrowWorkbench(player.inventory,
                    (TileEntityArrowWorkbench) world.getTileEntity(new BlockPos(x, y, z)));
        }
        return null;
    }

    @Nullable
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == WickedGuiHandler.ARROW_WORKBENCH) {
            return new GuiArrowWorkbench(getServerGuiElement(ID, player, world, x, y, z)/*, player.inventory*/);
        }
        return null;
    }
}
