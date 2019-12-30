package com.subwranglers.wickedarrows.item;

import com.subwranglers.wickedarrows.WickedArrows;
import com.subwranglers.wickedarrows.entity.TorchArrowEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import static com.subwranglers.wickedarrows.info.Names.TORCH_ARROW;

public class TorchArrowItem extends WickedArrowItem {

    public static final TorchArrowItem INSTANCE = new TorchArrowItem();

    public TorchArrowItem() {
        super(new Item.Properties().group(ItemGroup.COMBAT));
        setRegistryName(WickedArrows.MOD_ID, TORCH_ARROW);
    }

    @Override
    public AbstractArrowEntity createArrow(World worldIn, ItemStack stack, LivingEntity shooter) {
        return new TorchArrowEntity(worldIn, shooter);
    }

    // TODO: 24/01/19 Todd let players place torch arrows like blocks in a later version
//    @Override
//    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
//        worldIn.setBlockState(pos.offset(facing), BlockTorchArrow.applyToBlockFace(facing));
//        player.swingArm(hand);
//        return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
//    }
}
