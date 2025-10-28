package com.khazoda.basicweapons.material;


import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.ToolMaterial;

public class ConditionalToolMaterials {
  public static final ToolMaterial BRONZE =
      new ToolMaterial(BlockTags.INCORRECT_FOR_IRON_TOOL, 350, 7.0F, 2.5F, 15,
          TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "ingots/bronze")));
}
