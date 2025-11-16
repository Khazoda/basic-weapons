package com.khazoda.basicweapons.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;

import static com.khazoda.basicweapons.Constants.ID;

public class TagRegistry {
  public static final TagKey<Item> BASIC_WEAPON = TagKey.create(Registries.ITEM, ID("basic_weapon"));

  public static final TagKey<Enchantment> MIGHT_ENCHANTABLE = TagKey.create(Registries.ENCHANTMENT, ID("enchantable/might"));
  public static final TagKey<Enchantment> SHARPNESS_ENCHANTABLE = TagKey.create(Registries.ENCHANTMENT, ID("enchantable/sharpness"));
  public static final TagKey<Enchantment> SWEEPING_EDGE_ENCHANTABLE = TagKey.create(Registries.ENCHANTMENT, ID("enchantable/sweeping_edge"));

  public static final TagKey<Item> BRONZE_INGOTS = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "ingots/bronze"));
  public static final TagKey<Item> TIN_INGOTS = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "ingots/tin"));

}