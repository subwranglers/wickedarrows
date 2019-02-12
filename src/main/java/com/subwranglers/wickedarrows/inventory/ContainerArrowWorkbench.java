package com.subwranglers.wickedarrows.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;

public class ContainerArrowWorkbench extends Container {

    public ContainerArrowWorkbench(InventoryPlayer playerInventory, EntityPlayer playerIn) {
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return true;
    }
}
