package com.khazoda.basicweapons.item;

import com.khazoda.basicweapons.platform.ItemExtension;
import net.minecraft.core.component.DataComponents;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.component.Weapon;
import net.minecraft.world.level.block.Block;

public abstract class BasicWeaponSweeplessItem extends Item implements ItemExtension {

  /* For blunt weapons */
  public BasicWeaponSweeplessItem(ToolMaterial material, TagKey<Block> effectiveBlocks, float attackDamage, float attackSpeed, double extraReach, Properties properties) {
    super(properties.tool(material, effectiveBlocks, attackDamage, attackSpeed, 0.0f)
        .component(DataComponents.ATTRIBUTE_MODIFIERS, BasicWeaponItem.createAttributes(material, attackDamage, attackSpeed, extraReach))
        .component(DataComponents.WEAPON, new Weapon(1)));
  }

  /* For sharp weapons */
  public BasicWeaponSweeplessItem(ToolMaterial material, float attackDamage, float attackSpeed, double extraReach, Properties properties) {
    super(properties.sword(material, attackDamage, attackSpeed)
        .component(DataComponents.ATTRIBUTE_MODIFIERS, BasicWeaponItem.createAttributes(material, attackDamage, attackSpeed, extraReach)));
  }
}