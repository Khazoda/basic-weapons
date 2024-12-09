package com.seacroak.basicweapons.item;

import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.tag.BlockTags;

public class DaggerItem extends BasicWeaponItem {

  public DaggerItem(ToolMaterial tier, float attackDamage, float attackSpeed, double reach, Settings settings) {
    super(tier, BlockTags.SWORD_EFFICIENT, attackDamage, attackSpeed, reach, tier.applySwordSettings(settings, attackDamage, attackSpeed));
  }
}
