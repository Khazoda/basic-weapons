package com.seacroak.basicweapons.item;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class DaggerItem extends BasicWeaponItem {
  public DaggerItem(Tier tier, int damage, float attackSpeed, Properties properties) {
    super(tier, damage, attackSpeed, properties);
  }

  public float getDestroySpeed(ItemStack itemStack, BlockState state) {
    if (state.is(Blocks.COBWEB)) {
      return 15.0F;
    } else {
      return state.is(BlockTags.SWORD_EFFICIENT) ? 1.5F : 1.0F;
    }
  }

  public boolean isCorrectToolForDrops(BlockState state) {
    return state.is(Blocks.COBWEB);
  }

}
