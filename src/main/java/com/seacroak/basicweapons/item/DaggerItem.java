package com.seacroak.basicweapons.item;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.tag.BlockTags;

public class DaggerItem extends BasicWeaponSweeplessItem {
  public DaggerItem(ToolMaterial tier, int damage, float attackSpeed, Settings properties) {
    super(tier, damage, attackSpeed, properties);
  }

  @Override
  public float getMiningSpeedMultiplier(ItemStack stack, BlockState state) {
    if (state.isOf(Blocks.COBWEB)) {
      return 15.0F;
    } else {
      return state.isIn(BlockTags.SWORD_EFFICIENT) ? 1.5F : 1.0F;
    }
  }

  @Override
  public boolean isSuitableFor(BlockState state) {
    return state.isOf(Blocks.COBWEB);
  }

}
