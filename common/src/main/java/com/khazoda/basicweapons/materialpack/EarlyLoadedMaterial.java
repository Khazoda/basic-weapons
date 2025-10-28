package com.khazoda.basicweapons.materialpack;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;

import java.util.HashMap;
import java.util.Map;

/**
 * This class' fields should mirror the schema used for material pack material JSON files
 */
public class EarlyLoadedMaterial {
  private final String material_name;
  private final int durability;
  private final float attack_damage_bonus;
  private final float attack_speed_bonus;
  private final float reach_bonus;
  private final int enchantability;
  private final String repair_ingredient;

  public EarlyLoadedMaterial(String material_name, int durability, float attack_damage_bonus, float attack_speed_bonus, float reach_bonus, int enchantability, String repair_ingredient) {
    this.material_name = material_name;
    this.durability = durability;
    this.attack_damage_bonus = attack_damage_bonus;
    this.attack_speed_bonus = attack_speed_bonus;
    this.reach_bonus = reach_bonus;
    this.enchantability = enchantability;
    this.repair_ingredient = repair_ingredient;
  }

  private TagKey<Item> createRepairIngredientTagKey(String repairIngredient) {
    ResourceLocation identifier;
    if (repairIngredient.startsWith("#")) {
      identifier = ResourceLocation.bySeparator(repairIngredient.substring(1), ':');
    } else {
      identifier = ResourceLocation.bySeparator(repairIngredient, ':');
    }
    return TagKey.create(Registries.ITEM, identifier);
  }

  public ToolMaterial createToolMaterial() {
    return new ToolMaterial(
        BlockTags.INCORRECT_FOR_STONE_TOOL,
        durability,
        attack_speed_bonus,  // This becomes the mining speed in ToolMaterial
        attack_damage_bonus,
        enchantability,
        createRepairIngredientTagKey(repair_ingredient)
    );
  }

  public float getAttackSpeedBonus() {
    return attack_speed_bonus;
  }

  public float getReachBonus() {
    return reach_bonus;
  }
}