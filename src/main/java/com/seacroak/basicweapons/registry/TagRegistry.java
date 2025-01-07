package com.seacroak.basicweapons.registry;

import com.seacroak.basicweapons.Constants;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class TagRegistry {
  public static final TagKey<Item> BASIC_WEAPONS = TagKey.of(RegistryKeys.ITEM, Identifier.of(Constants.BW_NAMESPACE, "enchantable/basic_weapon"));
  public static final TagKey<Item> HAS_SWEEPING = TagKey.of(RegistryKeys.ITEM, Identifier.of(Constants.BW_NAMESPACE, "enchantable/has_sweeping"));
  public static final TagKey<Item> NO_SWEEPING = TagKey.of(RegistryKeys.ITEM, Identifier.of(Constants.BW_NAMESPACE, "enchantable/no_sweeping"));
  public static final TagKey<Item> SHARP_WEAPONS = TagKey.of(RegistryKeys.ITEM, Identifier.of(Constants.BW_NAMESPACE, "enchantable/sharp_basic_weapon"));
  public static final TagKey<Item> BLUNT_WEAPONS = TagKey.of(RegistryKeys.ITEM, Identifier.of(Constants.BW_NAMESPACE, "enchantable/blunt_basic_weapon"));
}