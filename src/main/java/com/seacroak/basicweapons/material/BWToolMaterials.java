package com.seacroak.basicweapons.material;

import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import java.util.function.Supplier;

public class BWToolMaterials {

  public static final ToolMaterial BRONZE = new FabricToolMaterial(
      2, // Mining level (same as iron)
      350, // Durability
      7.0F, // Mining speed
      2.5F, // Attack damage
      13, // Enchantability
      () -> Ingredient.ofItems(Registries.ITEM.get(new Identifier("bronze", "bronze_ingot")))
  );

  // You'll need this private class:
  private static class FabricToolMaterial implements ToolMaterial {
    private final int miningLevel;
    private final int durability;
    private final float miningSpeed;
    private final float attackDamage;
    private final int enchantability;
    private final Supplier<Ingredient> repairIngredient;

    FabricToolMaterial(int miningLevel, int durability, float miningSpeed, float attackDamage, int enchantability, Supplier<Ingredient> repairIngredient) {
      this.miningLevel = miningLevel;
      this.durability = durability;
      this.miningSpeed = miningSpeed;
      this.attackDamage = attackDamage;
      this.enchantability = enchantability;
      this.repairIngredient = repairIngredient;
    }

    @Override
    public int getDurability() {
      return this.durability;
    }

    @Override
    public float getMiningSpeedMultiplier() {
      return this.miningSpeed;
    }

    @Override
    public float getAttackDamage() {
      return this.attackDamage;
    }

    @Override
    public int getMiningLevel() {
      return this.miningLevel;
    }

    @Override
    public int getEnchantability() {
      return this.enchantability;
    }

    @Override
    public Ingredient getRepairIngredient() {
      return this.repairIngredient.get();
    }
  }
}