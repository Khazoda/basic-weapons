package com.khazoda.basicweapons.item;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tier;

public class SpearItem extends BasicWeaponItem {

  public SpearItem(Tier tier, float attackDamage, float attackSpeed, double reach, Item.Properties properties) {
    super(tier, BlockTags.SWORD_EFFICIENT, attackDamage, attackSpeed, reach, properties);
  }
}