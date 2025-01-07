package com.khazoda.basicweapons.item;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tier;

public class GlaiveItem extends BasicWeaponItem {
  public GlaiveItem(Tier tier, float attackDamage, float attackSpeed, double reach, Item.Properties properties) {
    super(tier, BlockTags.AIR, attackDamage, attackSpeed, reach, properties);
  }
}