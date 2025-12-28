package com.khazoda.basicweapons.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;

import static com.khazoda.basicweapons.Constants.ID;

public class TagRegistry {
  public static final TagKey<Item> BASIC_WEAPON = TagKey.create(Registries.ITEM, ID("basic_weapon"));

  public static final TagKey<Item> CLUB = TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("c", "tools/clubs"));
  public static final TagKey<Item> DAGGER = TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("c", "tools/daggers"));
  public static final TagKey<Item> GLAIVE = TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("c", "tools/glaives"));
  public static final TagKey<Item> HAMMER = TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("c", "tools/hammers"));
  public static final TagKey<Item> PIKE = TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("c", "tools/pikes"));
  public static final TagKey<Item> QUARTERSTAFF = TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("c", "tools/quarterstaves"));

  public static final TagKey<Enchantment> MIGHT_ENCHANTABLE = TagKey.create(Registries.ENCHANTMENT, ID("enchantable/might"));
  public static final TagKey<Enchantment> SHARPNESS_ENCHANTABLE = TagKey.create(Registries.ENCHANTMENT, ID("enchantable/sharpness"));
  public static final TagKey<Enchantment> SWEEPING_EDGE_ENCHANTABLE = TagKey.create(Registries.ENCHANTMENT, ID("enchantable/sweeping_edge"));

  public static final TagKey<Item> BRONZE_INGOTS = TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("c", "ingots/bronze"));
  public static final TagKey<Item> TIN_INGOTS = TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("c", "ingots/tin"));

}