package com.seacroak.basicweapons.item;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ToolComponent;
import net.minecraft.item.ToolMaterial;

public class QuarterstaffItem extends BasicWeaponItem {
  public QuarterstaffItem(ToolMaterial tier, float attackDamage, float attackSpeed, Settings settings) {
    super(tier, attackDamage, attackSpeed, settings.component(DataComponentTypes.TOOL, createToolComponent()));
  }

  private static ToolComponent createToolComponent() {
    return null;
  }
}