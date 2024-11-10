package com.seacroak.basicweapons.item;

import net.minecraft.block.Blocks;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ToolComponent;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.registry.tag.BlockTags;

import java.util.List;

public class DaggerItem extends BasicWeaponItem {
  public DaggerItem(ToolMaterial tier, float attackDamage, float attackSpeed, double reach, Settings settings) {
    super(tier, BlockTags.SWORD_EFFICIENT, attackDamage, attackSpeed, reach,
        settings.component(DataComponentTypes.TOOL,
            new ToolComponent(List.of(ToolComponent.Rule.ofAlwaysDropping(RegistryEntryList.of(Blocks.COBWEB.getRegistryEntry()), 15.0F)), 1.0F, 2)
        )
    );
  }
}
