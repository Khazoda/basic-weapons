package com.seacroak.basicweapons;

import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public class Constants {
  /* Mod Information */
  public static final String BW_NAMESPACE = "basicweapons";
  public static final String BW_NAME = "Basic Weapons";
  public static final Logger BW_LOG = LoggerFactory.getLogger(BW_NAME);

  /* Bronze Mod */
  public static final String BRONZE_NAMESPACE = "bronze";
  public static final String BRONZE_NAME = "Bronze";

  /* Tag Code Accessors */
  public static final TagKey<Item> BRONZE_INGOTS = TagKey.of(RegistryKeys.ITEM, Identifier.of("c", "bronze_ingots"));

  /* Uncategorized */
  public static final UUID PLAYER_ENTITY_INTERACTION_RANGE_MODIFIER_ID = UUID.fromString("74a196e4-cd9e-4c93-8606-8e7f0afdc959");
}