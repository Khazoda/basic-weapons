package com.khazoda.basicweapons.materialpack;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;

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

  private Ingredient createRepairIngredient(String repair_ingredient) {
    if (repair_ingredient.startsWith("#")) {
      ResourceLocation identifier = ResourceLocation.bySeparator(repair_ingredient.substring(1), ':');
      return Ingredient.of(TagKey.create(Registries.ITEM, identifier));
    } else {
      ResourceLocation identifier = ResourceLocation.bySeparator(repair_ingredient, ':');
      return Ingredient.of(() -> BuiltInRegistries.ITEM.get(identifier));
    }
  }

  public Tier createTier() {
    return new TierWithReach();
  }

  public class TierWithReach implements Tier {
    @Override
    public int getUses() {
      return durability;
    }

    @Override
    public float getSpeed() {
      return 1.0f;
    }

    @Override
    public float getAttackDamageBonus() {
      return attack_damage_bonus;
    }

    public float getAttackSpeedBonus() {
      return attack_speed_bonus;
    }

    public float getReachBonus() {
      return reach_bonus;
    }

    @Override
    public int getEnchantmentValue() {
      return enchantability;
    }

    @Override
    public Ingredient getRepairIngredient() {
      return createRepairIngredient(repair_ingredient);
    }

    @Override
    public TagKey<Block> getIncorrectBlocksForDrops() {
      return BlockTags.INCORRECT_FOR_STONE_TOOL;
    }
  }
}