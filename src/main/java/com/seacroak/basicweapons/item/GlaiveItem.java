package com.seacroak.basicweapons.item;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ToolComponent;
import net.minecraft.item.ToolMaterial;

public class GlaiveItem extends BasicWeaponItem {
  public GlaiveItem(ToolMaterial tier, Settings settings) {
    super(tier, settings.component(DataComponentTypes.TOOL, createToolComponent()));
  }

  private static ToolComponent createToolComponent() {
    return null;
  }
}