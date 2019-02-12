package com.subwranglers.wickedarrows.tileentity;

import com.subwranglers.wickedarrows.inventory.ContainerArrowWorkbench;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IInteractionObject;

import static com.subwranglers.wickedarrows.info.Names.*;

public class TileEntityArrowWorkbench extends TileEntity implements IInteractionObject {

    @Override
    public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
        return new ContainerArrowWorkbench(playerInventory, playerIn);
    }

    @Override
    public String getGuiID() {
        return name(ARROW_WORKBENCH_GUI, QUALIFY);
    }

    @Override
    public String getName() {
        return getGuiID();
    }

    @Override
    public boolean hasCustomName() {
        return false;
    }
}
