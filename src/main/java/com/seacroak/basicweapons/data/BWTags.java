package com.seacroak.basicweapons.data;

import com.seacroak.basicweapons.Constants;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class BWTags {
  public static final TagKey<Item> BASIC_WEAPONS = TagKey.of(RegistryKeys.ITEM, Identifier.of("basicweapons", "enchantable/basic_weapon"));
  public static final TagKey<Item> NO_SWEEPING = TagKey.of(RegistryKeys.ITEM, Identifier.of("basicweapons", "enchantable/no_sweeping"));
  public static final TagKey<Item> SHARP_WEAPONS = TagKey.of(RegistryKeys.ITEM, Identifier.of(Constants.BW_NAMESPACE, "enchantable/sharp_basic_weapon"));
  public static final TagKey<Item> BLUNT_WEAPONS = TagKey.of(RegistryKeys.ITEM, Identifier.of(Constants.BW_NAMESPACE, "enchantable/blunt_basic_weapon"));
}