package com.khazoda.basicweapons.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import static com.khazoda.basicweapons.Constants.ID;

public class TagRegistry {
  public static final TagKey<Item> BASIC_WEAPON = TagKey.create(Registries.ITEM, ID("basic_weapon"));
  public static final TagKey<Item> MIGHT_BLACKLISTED = TagKey.create(Registries.ITEM, ID("might_blacklisted"));
  public static final TagKey<Item> SHARPNESS_BLACKLISTED = TagKey.create(Registries.ITEM, ID("sharpness_blacklisted"));
  public static final TagKey<Item> SWEEPING_BLACKLISTED = TagKey.create(Registries.ITEM, ID("sweeping_blacklisted"));
}