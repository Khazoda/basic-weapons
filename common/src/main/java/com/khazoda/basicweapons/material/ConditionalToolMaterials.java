package com.khazoda.basicweapons.material;


import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.ToolMaterial;

public class ConditionalToolMaterials {
  public static final ToolMaterial BRONZE =
      new ToolMaterial(BlockTags.INCORRECT_FOR_IRON_TOOL, 500, 6.0F, 2.0F, 14,
          TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "ingots/bronze")));
  public static final ToolMaterial TIN =
      new ToolMaterial(BlockTags.INCORRECT_FOR_COPPER_TOOL, 151, 6.0F, 1.0F, 13,
          TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "ingots/tin")));
}
