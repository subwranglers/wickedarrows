package com.subwranglers.wickedarrows.inventory;

import com.subwranglers.wickedarrows.tileentity.TileEntityArrowWorkbench;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;

public class ContainerArrowWorkbench extends Container {

    private int index = 0;

    public ContainerArrowWorkbench(InventoryPlayer playerInv, TileEntityArrowWorkbench workbench) {
        IItemHandler inventory = workbench.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
//        addSlotToContainer(new SlotItemHandler(inventory, 0, 80, 35) {
//            @Override
//            public void onSlotChanged() {
//                workbench.markDirty();
//            }
//        });

        // Player's inventory slots
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 9; j++)
                addSlotToContainer(new Slot(playerInv, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));

        for (int k = 0; k < 9; k++)
            addSlotToContainer(new Slot(playerInv, k, 8 + k * 18, 142));

        // Workbench crafting slots
        for (int lCol = 0; lCol < 4; lCol++)
            addSlotToContainer(new SlotItemHandler(inventory, index++, 28, 7 + lCol * 18));

        for (int mCol = 0; mCol < 2; mCol++)
            addSlotToContainer(new SlotItemHandler(inventory, index++, 52, 16 + mCol * 18));

        for (int rCol = 0; rCol < 4; rCol++)
            addSlotToContainer(new SlotItemHandler(inventory, index++, 76, 7 + rCol * 18));

        // Output Slot
//        addSlotToContainer(new SlotItemHandler(inventory, index, 141, 34));
    }

    private SlotItemHandler buildSlot(IItemHandler inventory, TileEntityArrowWorkbench workbench, int xPos, int yPos) {
        return new SlotItemHandler(inventory, index, xPos, yPos) {
            @Override
            public void onSlotChanged() {
                workbench.markDirty();
            }
        };
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return true;
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int index) {
        // This method's code is from: https://shadowfacts.net/tutorials/forge-modding-112/tile-entities-inventory-gui/

        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = inventorySlots.get(index);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            int containerSlots = inventorySlots.size() - player.inventory.mainInventory.size();

            if (index < containerSlots) {
                if (!this.mergeItemStack(itemstack1, containerSlots, inventorySlots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.mergeItemStack(itemstack1, 0, containerSlots, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.getCount() == 0) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(player, itemstack1);
        }

        return itemstack;
    }
}
