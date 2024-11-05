package com.seacroak.basicweapons.material;

import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class BWToolMaterials {

  public static final ToolMaterial BRONZE = new ToolMaterial(BlockTags.INCORRECT_FOR_IRON_TOOL, 350, 7.0F, 2.5F, 13,
      TagKey.of(RegistryKeys.ITEM, Identifier.of("bronze", "bronze.item.bronze_ingot")));

}
