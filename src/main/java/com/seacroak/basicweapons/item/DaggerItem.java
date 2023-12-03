package com.seacroak.basicweapons.item;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;

public class DaggerItem extends BasicWeaponSweeplessItem {
  public DaggerItem(ToolMaterial tier, int damage, float attackSpeed, Settings properties) {
    super(tier, damage, attackSpeed, properties);
  }

  @Override
  public float getMiningSpeedMultiplier(ItemStack stack, BlockState state) {
    if (state.isOf(Blocks.COBWEB)) {
      return 15.0F;
    } else {
      Material material = state.getMaterial();
      return material != Material.PLANT && material != Material.REPLACEABLE_PLANT && material != Material.STONE && material != Material.LEAVES && material != Material.ORGANIC_PRODUCT ? 1.0F : 1.5F;
    }
  }

  @Override
  public boolean isSuitableFor(BlockState state) {
    return state.isOf(Blocks.COBWEB);
  }

}
