package com.seacroak.basicweapons.registry;

import com.seacroak.basicweapons.item.*;
import com.seacroak.basicweapons.util.ID;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Supplier;

import static com.seacroak.basicweapons.registry.MainRegistry.bronze_mod_loaded;

public class BWItems {
  public static final float daggerDamage = 1f;
  public static final float daggerSpeed = -1.6f;
  public static final double daggerRange = 0;
  public static final float hammerDamage = 7f;
  public static final float hammerSpeed = -3.4f;
  public static final double hammerRange = 0;
  public static final float clubDamage = 5f;
  public static final float clubSpeed = -3.0f;
  public static final double clubRange = 0;
  public static final float spearDamage = 2f;
  public static final float spearSpeed = -2.8f;
  public static final double spearRange = 2;
  public static final float quarterstaffDamage = 1f;
  public static final float quarterstaffSpeed = -2.3f;
  public static final double quarterstaffRange = 1.25;
  public static final float glaiveDamage = 5f;
  public static final float glaiveSpeed = -3.2f;
  public static final double glaiveRange = 1.25;

  /* When referencing items, use registeredItems from MainRegistry, not items.
  Indices are the same for both constants */
  public static List<UnregisteredItemInfo> items = new LinkedList<>();

  private static Item.Settings makeItemSettings(WEAPON_TYPE weaponType, String path, ToolMaterial material, float damage, float speed, double range) {
    Item.Settings itemSettings = new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, ID.of(path)));

    switch (weaponType) {
      case DAGGER -> {
        itemSettings = itemSettings.attributeModifiers(DaggerItem.createAttributeModifiers(material, damage, speed, range));
      }
      case QUARTERSTAFF -> {
        itemSettings = itemSettings.attributeModifiers(QuarterstaffItem.createAttributeModifiers(material, damage, speed, range));
        ;
      }
    }
    return itemSettings;
  }

  public static void init() {

    /* Daggers */
    items.add(new UnregisteredItemInfo(WEAPON_TYPE.DAGGER, WEAPON_MATERIAL.WOOD, "wooden_dagger", () -> new DaggerItem(ToolMaterial.WOOD, daggerDamage, daggerSpeed, daggerRange, makeItemSettings(WEAPON_TYPE.DAGGER, "wooden_dagger", ToolMaterial.WOOD, daggerDamage, daggerSpeed, daggerRange))));
    items.add(new UnregisteredItemInfo(WEAPON_TYPE.DAGGER, WEAPON_MATERIAL.STONE, "stone_dagger", () -> new DaggerItem(ToolMaterial.STONE, daggerDamage, daggerSpeed, daggerRange, makeItemSettings(WEAPON_TYPE.DAGGER, "stone_dagger", ToolMaterial.STONE, daggerDamage, daggerSpeed, daggerRange))));
    items.add(new UnregisteredItemInfo(WEAPON_TYPE.DAGGER, WEAPON_MATERIAL.IRON, "iron_dagger", () -> new DaggerItem(ToolMaterial.IRON, daggerDamage, daggerSpeed, daggerRange, makeItemSettings(WEAPON_TYPE.DAGGER, "iron_dagger", ToolMaterial.IRON, daggerDamage, daggerSpeed, daggerRange))));
    if (bronze_mod_loaded)
      items.add(new UnregisteredItemInfo(WEAPON_TYPE.DAGGER, WEAPON_MATERIAL.BRONZE, "bronze_dagger", () -> new DaggerItem(BWToolMaterial.BRONZE, daggerDamage, daggerSpeed, daggerRange, makeItemSettings(WEAPON_TYPE.DAGGER, "bronze_dagger", BWToolMaterial.BRONZE, daggerDamage, daggerSpeed, daggerRange))));
    items.add(new UnregisteredItemInfo(WEAPON_TYPE.DAGGER, WEAPON_MATERIAL.GOLD, "golden_dagger", () -> new DaggerItem(ToolMaterial.GOLD, daggerDamage - 1, daggerSpeed + 1, daggerRange, makeItemSettings(WEAPON_TYPE.DAGGER, "golden_dagger", ToolMaterial.GOLD, daggerDamage - 1, daggerSpeed + 1, daggerRange))));
    items.add(new UnregisteredItemInfo(WEAPON_TYPE.DAGGER, WEAPON_MATERIAL.DIAMOND, "diamond_dagger", () -> new DaggerItem(ToolMaterial.DIAMOND, daggerDamage, daggerSpeed, daggerRange, makeItemSettings(WEAPON_TYPE.DAGGER, "diamond_dagger", ToolMaterial.DIAMOND, daggerDamage, daggerSpeed, daggerRange))));
    items.add(new UnregisteredItemInfo(WEAPON_TYPE.DAGGER, WEAPON_MATERIAL.NETHERITE, "netherite_dagger", () -> new DaggerItem(ToolMaterial.NETHERITE, daggerDamage, daggerSpeed, daggerRange, makeItemSettings(WEAPON_TYPE.DAGGER, "netherite_dagger", ToolMaterial.NETHERITE, daggerDamage, daggerSpeed, daggerRange).fireproof())));

    /* Hammers */
    items.add(new UnregisteredItemInfo(WEAPON_TYPE.HAMMER, WEAPON_MATERIAL.WOOD, "wooden_hammer", () -> new HammerItem(ToolMaterial.WOOD, hammerDamage - 6, hammerSpeed + 0.4f, hammerRange, makeItemSettings(WEAPON_TYPE.HAMMER, "wooden_hammer", ToolMaterial.WOOD, hammerDamage - 6, hammerSpeed + 0.4f, hammerRange))));
    items.add(new UnregisteredItemInfo(WEAPON_TYPE.HAMMER, WEAPON_MATERIAL.STONE, "stone_hammer", () -> new HammerItem(ToolMaterial.STONE, hammerDamage - 3, hammerSpeed + 0.2f, hammerRange, makeItemSettings(WEAPON_TYPE.HAMMER, "stone_hammer", ToolMaterial.STONE, hammerDamage - 3, hammerSpeed + 0.2f, hammerRange))));
    items.add(new UnregisteredItemInfo(WEAPON_TYPE.HAMMER, WEAPON_MATERIAL.IRON, "iron_hammer", () -> new HammerItem(ToolMaterial.IRON, hammerDamage, hammerSpeed, hammerRange, makeItemSettings(WEAPON_TYPE.HAMMER, "iron_hammer", ToolMaterial.IRON, hammerDamage, hammerSpeed, hammerRange))));
    if (bronze_mod_loaded)
      items.add(new UnregisteredItemInfo(WEAPON_TYPE.HAMMER, WEAPON_MATERIAL.BRONZE, "bronze_hammer", () -> new HammerItem(BWToolMaterial.BRONZE, hammerDamage - 0.5f, hammerSpeed + 0.1f, hammerRange, makeItemSettings(WEAPON_TYPE.HAMMER, "bronze_hammer", BWToolMaterial.BRONZE, hammerDamage - 0.5f, hammerSpeed + 0.1f, hammerRange))));
    items.add(new UnregisteredItemInfo(WEAPON_TYPE.HAMMER, WEAPON_MATERIAL.GOLD, "golden_hammer", () -> new HammerItem(ToolMaterial.GOLD, hammerDamage - 6, hammerSpeed + 0.6f, hammerRange, makeItemSettings(WEAPON_TYPE.HAMMER, "golden_hammer", ToolMaterial.GOLD, hammerDamage - 6, hammerSpeed + 0.6f, hammerRange))));
    items.add(new UnregisteredItemInfo(WEAPON_TYPE.HAMMER, WEAPON_MATERIAL.DIAMOND, "diamond_hammer", () -> new HammerItem(ToolMaterial.DIAMOND, hammerDamage - 1, hammerSpeed + 0.1f, hammerRange, makeItemSettings(WEAPON_TYPE.HAMMER, "diamond_hammer", ToolMaterial.DIAMOND, hammerDamage - 1, hammerSpeed + 0.1f, hammerRange))));
    items.add(new UnregisteredItemInfo(WEAPON_TYPE.HAMMER, WEAPON_MATERIAL.NETHERITE, "netherite_hammer", () -> new HammerItem(ToolMaterial.NETHERITE, hammerDamage - 1, hammerSpeed + 0.2f, hammerRange, makeItemSettings(WEAPON_TYPE.HAMMER, "netherite_hammer", ToolMaterial.NETHERITE, hammerDamage - 1, hammerSpeed + 0.2f, hammerRange).fireproof())));

    /* Clubs */
    items.add(new UnregisteredItemInfo(WEAPON_TYPE.CLUB, WEAPON_MATERIAL.WOOD, "wooden_club", () -> new ClubItem(ToolMaterial.WOOD, clubDamage, clubSpeed, clubRange, makeItemSettings(WEAPON_TYPE.CLUB, "wooden_club", ToolMaterial.WOOD, clubDamage, clubSpeed, clubRange))));
    items.add(new UnregisteredItemInfo(WEAPON_TYPE.CLUB, WEAPON_MATERIAL.STONE, "stone_club", () -> new ClubItem(ToolMaterial.STONE, clubDamage, clubSpeed, clubRange, makeItemSettings(WEAPON_TYPE.CLUB, "stone_club", ToolMaterial.STONE, clubDamage, clubSpeed, clubRange))));
    items.add(new UnregisteredItemInfo(WEAPON_TYPE.CLUB, WEAPON_MATERIAL.IRON, "iron_club", () -> new ClubItem(ToolMaterial.IRON, clubDamage, clubSpeed, clubRange, makeItemSettings(WEAPON_TYPE.CLUB, "iron_club", ToolMaterial.IRON, clubDamage, clubSpeed, clubRange))));
    if (bronze_mod_loaded)
      items.add(new UnregisteredItemInfo(WEAPON_TYPE.CLUB, WEAPON_MATERIAL.BRONZE, "bronze_club", () -> new ClubItem(BWToolMaterial.BRONZE, clubDamage, clubSpeed, clubRange, makeItemSettings(WEAPON_TYPE.CLUB, "bronze_club", BWToolMaterial.BRONZE, clubDamage, clubSpeed, clubRange))));
    items.add(new UnregisteredItemInfo(WEAPON_TYPE.CLUB, WEAPON_MATERIAL.GOLD, "golden_club", () -> new ClubItem(ToolMaterial.GOLD, clubDamage, clubSpeed, clubRange, makeItemSettings(WEAPON_TYPE.CLUB, "golden_club", ToolMaterial.GOLD, clubDamage, clubSpeed, clubRange))));
    items.add(new UnregisteredItemInfo(WEAPON_TYPE.CLUB, WEAPON_MATERIAL.DIAMOND, "diamond_club", () -> new ClubItem(ToolMaterial.DIAMOND, clubDamage, clubSpeed, clubRange, makeItemSettings(WEAPON_TYPE.CLUB, "diamond_club", ToolMaterial.DIAMOND, clubDamage, clubSpeed, clubRange))));
    items.add(new UnregisteredItemInfo(WEAPON_TYPE.CLUB, WEAPON_MATERIAL.NETHERITE, "netherite_club", () -> new ClubItem(ToolMaterial.NETHERITE, clubDamage, clubSpeed, clubRange, makeItemSettings(WEAPON_TYPE.CLUB, "netherite_club", ToolMaterial.NETHERITE, clubDamage, clubSpeed, clubRange).fireproof())));

    /* Spears */
    items.add(new UnregisteredItemInfo(WEAPON_TYPE.SPEAR, WEAPON_MATERIAL.WOOD, "wooden_spear", () -> new SpearItem(ToolMaterial.WOOD, spearDamage, spearSpeed, makeItemSettings(WEAPON_TYPE.SPEAR, "wooden_spear", ToolMaterial.WOOD, spearDamage, spearSpeed, spearRange))));
    items.add(new UnregisteredItemInfo(WEAPON_TYPE.SPEAR, WEAPON_MATERIAL.STONE, "stone_spear", () -> new SpearItem(ToolMaterial.STONE, spearDamage, spearSpeed, makeItemSettings(WEAPON_TYPE.SPEAR, "stone_spear", ToolMaterial.STONE, spearDamage, spearSpeed, spearRange))));
    items.add(new UnregisteredItemInfo(WEAPON_TYPE.SPEAR, WEAPON_MATERIAL.IRON, "iron_spear", () -> new SpearItem(ToolMaterial.IRON, spearDamage, spearSpeed, makeItemSettings(WEAPON_TYPE.SPEAR, "iron_spear", ToolMaterial.IRON, spearDamage, spearSpeed, spearRange))));
    if (bronze_mod_loaded)
      items.add(new UnregisteredItemInfo(WEAPON_TYPE.SPEAR, WEAPON_MATERIAL.BRONZE, "bronze_spear", () -> new SpearItem(BWToolMaterial.BRONZE, spearDamage, spearSpeed, makeItemSettings(WEAPON_TYPE.SPEAR, "bronze_spear", BWToolMaterial.BRONZE, spearDamage, spearSpeed, spearRange))));
    items.add(new UnregisteredItemInfo(WEAPON_TYPE.SPEAR, WEAPON_MATERIAL.GOLD, "golden_spear", () -> new SpearItem(ToolMaterial.GOLD, spearDamage, spearSpeed, makeItemSettings(WEAPON_TYPE.SPEAR, "golden_spear", ToolMaterial.GOLD, spearDamage, spearSpeed, spearRange))));
    items.add(new UnregisteredItemInfo(WEAPON_TYPE.SPEAR, WEAPON_MATERIAL.DIAMOND, "diamond_spear", () -> new SpearItem(ToolMaterial.DIAMOND, spearDamage, spearSpeed, makeItemSettings(WEAPON_TYPE.SPEAR, "diamond_spear", ToolMaterial.DIAMOND, spearDamage, spearSpeed, spearRange))));
    items.add(new UnregisteredItemInfo(WEAPON_TYPE.SPEAR, WEAPON_MATERIAL.NETHERITE, "netherite_spear", () -> new SpearItem(ToolMaterial.NETHERITE, spearDamage, spearSpeed, makeItemSettings(WEAPON_TYPE.SPEAR, "netherite_spear", ToolMaterial.NETHERITE, spearDamage, spearSpeed, spearRange).fireproof())));

    /* Quarterstaves */
    items.add(new UnregisteredItemInfo(WEAPON_TYPE.QUARTERSTAFF, WEAPON_MATERIAL.WOOD, "wooden_quarterstaff", () -> new QuarterstaffItem(ToolMaterial.WOOD, quarterstaffDamage, quarterstaffSpeed, makeItemSettings(WEAPON_TYPE.QUARTERSTAFF, "wooden_quarterstaff", ToolMaterial.WOOD, quarterstaffDamage, quarterstaffSpeed, quarterstaffRange))));
    items.add(new UnregisteredItemInfo(WEAPON_TYPE.QUARTERSTAFF, WEAPON_MATERIAL.STONE, "stone_quarterstaff", () -> new QuarterstaffItem(ToolMaterial.STONE, quarterstaffDamage, quarterstaffSpeed, makeItemSettings(WEAPON_TYPE.QUARTERSTAFF, "stone_quarterstaff", ToolMaterial.STONE, quarterstaffDamage, quarterstaffSpeed, quarterstaffRange))));
    items.add(new UnregisteredItemInfo(WEAPON_TYPE.QUARTERSTAFF, WEAPON_MATERIAL.IRON, "iron_quarterstaff", () -> new QuarterstaffItem(ToolMaterial.IRON, quarterstaffDamage, quarterstaffSpeed, makeItemSettings(WEAPON_TYPE.QUARTERSTAFF, "iron_quarterstaff", ToolMaterial.IRON, quarterstaffDamage, quarterstaffSpeed, quarterstaffRange))));
    if (bronze_mod_loaded)
      items.add(new UnregisteredItemInfo(WEAPON_TYPE.QUARTERSTAFF, WEAPON_MATERIAL.BRONZE, "bronze_quarterstaff", () -> new QuarterstaffItem(BWToolMaterial.BRONZE, quarterstaffDamage, quarterstaffSpeed, makeItemSettings(WEAPON_TYPE.QUARTERSTAFF, "bronze_quarterstaff", BWToolMaterial.BRONZE, quarterstaffDamage, quarterstaffSpeed, quarterstaffRange))));
    items.add(new UnregisteredItemInfo(WEAPON_TYPE.QUARTERSTAFF, WEAPON_MATERIAL.GOLD, "golden_quarterstaff", () -> new QuarterstaffItem(ToolMaterial.GOLD, quarterstaffDamage, quarterstaffSpeed, makeItemSettings(WEAPON_TYPE.QUARTERSTAFF, "golden_quarterstaff", ToolMaterial.GOLD, quarterstaffDamage, quarterstaffSpeed, quarterstaffRange))));
    items.add(new UnregisteredItemInfo(WEAPON_TYPE.QUARTERSTAFF, WEAPON_MATERIAL.DIAMOND, "diamond_quarterstaff", () -> new QuarterstaffItem(ToolMaterial.DIAMOND, quarterstaffDamage, quarterstaffSpeed, makeItemSettings(WEAPON_TYPE.QUARTERSTAFF, "diamond_quarterstaff", ToolMaterial.DIAMOND, quarterstaffDamage, quarterstaffSpeed, quarterstaffRange))));
    items.add(new UnregisteredItemInfo(WEAPON_TYPE.QUARTERSTAFF, WEAPON_MATERIAL.NETHERITE, "netherite_quarterstaff", () -> new QuarterstaffItem(ToolMaterial.NETHERITE, quarterstaffDamage, quarterstaffSpeed, makeItemSettings(WEAPON_TYPE.QUARTERSTAFF, "netherite_quarterstaff", ToolMaterial.NETHERITE, quarterstaffDamage, quarterstaffSpeed, quarterstaffRange).fireproof())));

    /* Glaives */
    items.add(new UnregisteredItemInfo(WEAPON_TYPE.GLAIVE, WEAPON_MATERIAL.WOOD, "wooden_glaive", () -> new GlaiveItem(ToolMaterial.WOOD, glaiveDamage, glaiveSpeed, makeItemSettings(WEAPON_TYPE.GLAIVE, "wooden_glaive", ToolMaterial.WOOD, glaiveDamage, glaiveSpeed, glaiveRange))));
    items.add(new UnregisteredItemInfo(WEAPON_TYPE.GLAIVE, WEAPON_MATERIAL.STONE, "stone_glaive", () -> new GlaiveItem(ToolMaterial.STONE, glaiveDamage, glaiveSpeed, makeItemSettings(WEAPON_TYPE.GLAIVE, "stone_glaive", ToolMaterial.STONE, glaiveDamage, glaiveSpeed, glaiveRange))));
    items.add(new UnregisteredItemInfo(WEAPON_TYPE.GLAIVE, WEAPON_MATERIAL.IRON, "iron_glaive", () -> new GlaiveItem(ToolMaterial.IRON, glaiveDamage, glaiveSpeed, makeItemSettings(WEAPON_TYPE.GLAIVE, "iron_glaive", ToolMaterial.IRON, glaiveDamage, glaiveSpeed, glaiveRange))));
    if (bronze_mod_loaded)
      items.add(new UnregisteredItemInfo(WEAPON_TYPE.GLAIVE, WEAPON_MATERIAL.BRONZE, "bronze_glaive", () -> new GlaiveItem(BWToolMaterial.BRONZE, glaiveDamage, glaiveSpeed, makeItemSettings(WEAPON_TYPE.GLAIVE, "bronze_glaive", BWToolMaterial.BRONZE, glaiveDamage, glaiveSpeed, glaiveRange))));
    items.add(new UnregisteredItemInfo(WEAPON_TYPE.GLAIVE, WEAPON_MATERIAL.GOLD, "golden_glaive", () -> new GlaiveItem(ToolMaterial.GOLD, glaiveDamage, glaiveSpeed, makeItemSettings(WEAPON_TYPE.GLAIVE, "golden_glaive", ToolMaterial.GOLD, glaiveDamage, glaiveSpeed, glaiveRange))));
    items.add(new UnregisteredItemInfo(WEAPON_TYPE.GLAIVE, WEAPON_MATERIAL.DIAMOND, "diamond_glaive", () -> new GlaiveItem(ToolMaterial.DIAMOND, glaiveDamage, glaiveSpeed, makeItemSettings(WEAPON_TYPE.GLAIVE, "diamond_glaive", ToolMaterial.DIAMOND, glaiveDamage, glaiveSpeed, glaiveRange))));
    items.add(new UnregisteredItemInfo(WEAPON_TYPE.GLAIVE, WEAPON_MATERIAL.NETHERITE, "netherite_glaive", () -> new GlaiveItem(ToolMaterial.NETHERITE, glaiveDamage, glaiveSpeed, makeItemSettings(WEAPON_TYPE.GLAIVE, "netherite_glaive", ToolMaterial.NETHERITE, glaiveDamage, glaiveSpeed, glaiveRange).fireproof())));

  }

  public enum WEAPON_TYPE {
    DAGGER, CLUB, HAMMER, SPEAR, QUARTERSTAFF, GLAIVE
  }

  public enum WEAPON_MATERIAL {
    WOOD, STONE, IRON, BRONZE, GOLD, DIAMOND, NETHERITE
  }

  /*** This class holds information about all items.
   It is accessed by forge & fabric methods
   for automatic registration ***/
  public static class UnregisteredItemInfo {
    public final WEAPON_TYPE weaponType;
    public final WEAPON_MATERIAL weaponMaterial;
    public final String name;
    public final Supplier<Item> itemSupplier;

    public UnregisteredItemInfo(WEAPON_TYPE weaponType, WEAPON_MATERIAL weaponMaterial, String name, Supplier<Item> itemSupplier) {
      this.weaponType = weaponType;
      this.weaponMaterial = weaponMaterial;
      this.name = name;
      this.itemSupplier = itemSupplier;
    }
  }


}
