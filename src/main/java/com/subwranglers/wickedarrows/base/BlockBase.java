package com.subwranglers.wickedarrows.base;

import com.subwranglers.wickedarrows.WickedArrows;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockBase extends Block {

    public BlockBase(Material materialIn, String nakedName) {
        super(materialIn);

        setRegistryName(WickedArrows.MOD_ID, nakedName);
    }
}
