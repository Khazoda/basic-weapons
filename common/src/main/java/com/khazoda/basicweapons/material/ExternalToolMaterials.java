package com.khazoda.basicweapons.material;


import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;

public class ExternalToolMaterials {

  public static final Tier BRONZE = new Tier() {
    @Override
    public int getUses() {
      return 350;
    }

    @Override
    public float getSpeed() {
      return 7.0F;
    }

    @Override
    public float getAttackDamageBonus() {
      return 2.5F;
    }

    @Override
    public int getEnchantmentValue() {
      return 13;
    }

    @Override
    public Ingredient getRepairIngredient() {
      return Ingredient.of(TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("bronze", "bronze_ingots")));
    }

    @Override
    public TagKey<Block> getIncorrectBlocksForDrops() {
      return BlockTags.NEEDS_IRON_TOOL; // Same as iron tier
    }
  };
}
