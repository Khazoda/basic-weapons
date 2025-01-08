package com.khazoda.basicweapons.item;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tier;

public class DaggerItem extends BasicWeaponSweeplessItem {
  public DaggerItem(Tier tier, float attackDamage, float attackSpeed, double reach, Item.Properties properties) {
    super(tier, BlockTags.SWORD_EFFICIENT, attackDamage, attackSpeed, reach, properties);
  }
}
