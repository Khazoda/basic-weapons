package com.seacroak.basicweapons.registry;

import com.seacroak.basicweapons.data.WEAPON_TYPE;
import com.seacroak.basicweapons.data.WeaponStats;
import com.seacroak.basicweapons.item.*;
import com.seacroak.basicweapons.material.BWToolMaterials;
import com.seacroak.basicweapons.util.Reggie;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;

import java.util.LinkedList;
import java.util.List;

import static com.seacroak.basicweapons.registry.MainRegistry.bronze_mod_loaded;

public class WeaponRegistry {
  // This field is for use ONLY with loot table code in MainRegistry. Reference the static fields for the items themselves.
  public static List<ItemInfo> itemsWithInfo = new LinkedList<>();

  // Daggers
  public static final Item WOODEN_DAGGER = registerWeapon("wooden_dagger", ToolMaterial.WOOD, WEAPON_TYPE.DAGGER, WeaponStats.DAGGER, DaggerItem::new);
  public static final Item STONE_DAGGER = registerWeapon("stone_dagger", ToolMaterial.STONE, WEAPON_TYPE.DAGGER, WeaponStats.DAGGER, DaggerItem::new);
  public static final Item IRON_DAGGER = registerWeapon("iron_dagger", ToolMaterial.IRON, WEAPON_TYPE.DAGGER, WeaponStats.DAGGER, DaggerItem::new);
  public static final Item GOLDEN_DAGGER = registerWeapon("golden_dagger", ToolMaterial.GOLD, WEAPON_TYPE.DAGGER, WeaponStats.DAGGER.withDamage(-1).withSpeed(1), DaggerItem::new);
  public static final Item DIAMOND_DAGGER = registerWeapon("diamond_dagger", ToolMaterial.DIAMOND, WEAPON_TYPE.DAGGER, WeaponStats.DAGGER, DaggerItem::new);
  public static final Item NETHERITE_DAGGER = registerWeapon("netherite_dagger", ToolMaterial.NETHERITE, WEAPON_TYPE.DAGGER, WeaponStats.DAGGER, DaggerItem::new);
  // Hammers
  public static final Item WOODEN_HAMMER = registerWeapon("wooden_hammer", ToolMaterial.WOOD, WEAPON_TYPE.HAMMER, WeaponStats.HAMMER.withDamage(-6).withSpeed(0.4f), HammerItem::new);
  public static final Item STONE_HAMMER = registerWeapon("stone_hammer", ToolMaterial.STONE, WEAPON_TYPE.HAMMER, WeaponStats.HAMMER.withDamage(-3).withSpeed(0.2f), HammerItem::new);
  public static final Item IRON_HAMMER = registerWeapon("iron_hammer", ToolMaterial.IRON, WEAPON_TYPE.HAMMER, WeaponStats.HAMMER, HammerItem::new);
  public static final Item GOLDEN_HAMMER = registerWeapon("golden_hammer", ToolMaterial.GOLD, WEAPON_TYPE.HAMMER, WeaponStats.HAMMER.withDamage(-6).withSpeed(0.6f), HammerItem::new);
  public static final Item DIAMOND_HAMMER = registerWeapon("diamond_hammer", ToolMaterial.DIAMOND, WEAPON_TYPE.HAMMER, WeaponStats.HAMMER.withDamage(-1).withSpeed(0.1f), HammerItem::new);
  public static final Item NETHERITE_HAMMER = registerWeapon("netherite_hammer", ToolMaterial.NETHERITE, WEAPON_TYPE.HAMMER, WeaponStats.HAMMER.withDamage(-1).withSpeed(0.2f), HammerItem::new);
  // Clubs
  public static final Item WOODEN_CLUB = registerWeapon("wooden_club", ToolMaterial.WOOD, WEAPON_TYPE.CLUB, WeaponStats.CLUB, ClubItem::new);
  public static final Item STONE_CLUB = registerWeapon("stone_club", ToolMaterial.STONE, WEAPON_TYPE.CLUB, WeaponStats.CLUB, ClubItem::new);
  public static final Item IRON_CLUB = registerWeapon("iron_club", ToolMaterial.IRON, WEAPON_TYPE.CLUB, WeaponStats.CLUB, ClubItem::new);
  public static final Item GOLDEN_CLUB = registerWeapon("golden_club", ToolMaterial.GOLD, WEAPON_TYPE.CLUB, WeaponStats.CLUB, ClubItem::new);
  public static final Item DIAMOND_CLUB = registerWeapon("diamond_club", ToolMaterial.DIAMOND, WEAPON_TYPE.CLUB, WeaponStats.CLUB, ClubItem::new);
  public static final Item NETHERITE_CLUB = registerWeapon("netherite_club", ToolMaterial.NETHERITE, WEAPON_TYPE.CLUB, WeaponStats.CLUB, ClubItem::new);
  // Spears
  public static final Item WOODEN_SPEAR = registerWeapon("wooden_spear", ToolMaterial.WOOD, WEAPON_TYPE.SPEAR, WeaponStats.SPEAR, SpearItem::new);
  public static final Item STONE_SPEAR = registerWeapon("stone_spear", ToolMaterial.STONE, WEAPON_TYPE.SPEAR, WeaponStats.SPEAR, SpearItem::new);
  public static final Item IRON_SPEAR = registerWeapon("iron_spear", ToolMaterial.IRON, WEAPON_TYPE.SPEAR, WeaponStats.SPEAR, SpearItem::new);
  public static final Item GOLDEN_SPEAR = registerWeapon("golden_spear", ToolMaterial.GOLD, WEAPON_TYPE.SPEAR, WeaponStats.SPEAR, SpearItem::new);
  public static final Item DIAMOND_SPEAR = registerWeapon("diamond_spear", ToolMaterial.DIAMOND, WEAPON_TYPE.SPEAR, WeaponStats.SPEAR, SpearItem::new);
  public static final Item NETHERITE_SPEAR = registerWeapon("netherite_spear", ToolMaterial.NETHERITE, WEAPON_TYPE.SPEAR, WeaponStats.SPEAR, SpearItem::new);
  // Quarterstaves
  public static final Item WOODEN_QUARTERSTAFF = registerWeapon("wooden_quarterstaff", ToolMaterial.WOOD, WEAPON_TYPE.QUARTERSTAFF, WeaponStats.QUARTERSTAFF, QuarterstaffItem::new);
  public static final Item STONE_QUARTERSTAFF = registerWeapon("stone_quarterstaff", ToolMaterial.STONE, WEAPON_TYPE.QUARTERSTAFF, WeaponStats.QUARTERSTAFF, QuarterstaffItem::new);
  public static final Item IRON_QUARTERSTAFF = registerWeapon("iron_quarterstaff", ToolMaterial.IRON, WEAPON_TYPE.QUARTERSTAFF, WeaponStats.QUARTERSTAFF, QuarterstaffItem::new);
  public static final Item GOLDEN_QUARTERSTAFF = registerWeapon("golden_quarterstaff", ToolMaterial.GOLD, WEAPON_TYPE.QUARTERSTAFF, WeaponStats.QUARTERSTAFF, QuarterstaffItem::new);
  public static final Item DIAMOND_QUARTERSTAFF = registerWeapon("diamond_quarterstaff", ToolMaterial.DIAMOND, WEAPON_TYPE.QUARTERSTAFF, WeaponStats.QUARTERSTAFF, QuarterstaffItem::new);
  public static final Item NETHERITE_QUARTERSTAFF = registerWeapon("netherite_quarterstaff", ToolMaterial.NETHERITE, WEAPON_TYPE.QUARTERSTAFF, WeaponStats.QUARTERSTAFF, QuarterstaffItem::new);
  // Glaives
  public static final Item WOODEN_GLAIVE = registerWeapon("wooden_glaive", ToolMaterial.WOOD, WEAPON_TYPE.GLAIVE, WeaponStats.GLAIVE, GlaiveItem::new);
  public static final Item STONE_GLAIVE = registerWeapon("stone_glaive", ToolMaterial.STONE, WEAPON_TYPE.GLAIVE, WeaponStats.GLAIVE, GlaiveItem::new);
  public static final Item IRON_GLAIVE = registerWeapon("iron_glaive", ToolMaterial.IRON, WEAPON_TYPE.GLAIVE, WeaponStats.GLAIVE, GlaiveItem::new);
  public static final Item GOLDEN_GLAIVE = registerWeapon("golden_glaive", ToolMaterial.GOLD, WEAPON_TYPE.GLAIVE, WeaponStats.GLAIVE, GlaiveItem::new);
  public static final Item DIAMOND_GLAIVE = registerWeapon("diamond_glaive", ToolMaterial.DIAMOND, WEAPON_TYPE.GLAIVE, WeaponStats.GLAIVE, GlaiveItem::new);
  public static final Item NETHERITE_GLAIVE = registerWeapon("netherite_glaive", ToolMaterial.NETHERITE, WEAPON_TYPE.GLAIVE, WeaponStats.GLAIVE, GlaiveItem::new);
  // Bronze weapons
  public static final Item BRONZE_DAGGER = bronze_mod_loaded ? registerWeapon("bronze_dagger", BWToolMaterials.BRONZE, WEAPON_TYPE.DAGGER, WeaponStats.DAGGER, DaggerItem::new) : null;
  public static final Item BRONZE_HAMMER = bronze_mod_loaded ? registerWeapon("bronze_hammer", BWToolMaterials.BRONZE, WEAPON_TYPE.HAMMER, WeaponStats.HAMMER.withDamage(-0.5f).withSpeed(0.1f), HammerItem::new) : null;
  public static final Item BRONZE_CLUB = bronze_mod_loaded ? registerWeapon("bronze_club", BWToolMaterials.BRONZE, WEAPON_TYPE.CLUB, WeaponStats.CLUB, ClubItem::new) : null;
  public static final Item BRONZE_SPEAR = bronze_mod_loaded ? registerWeapon("bronze_spear", BWToolMaterials.BRONZE, WEAPON_TYPE.SPEAR, WeaponStats.SPEAR, SpearItem::new) : null;
  public static final Item BRONZE_QUARTERSTAFF = bronze_mod_loaded ? registerWeapon("bronze_quarterstaff", BWToolMaterials.BRONZE, WEAPON_TYPE.QUARTERSTAFF, WeaponStats.QUARTERSTAFF, QuarterstaffItem::new) : null;
  public static final Item BRONZE_GLAIVE = bronze_mod_loaded ? registerWeapon("bronze_glaive", BWToolMaterials.BRONZE, WEAPON_TYPE.GLAIVE, WeaponStats.GLAIVE, GlaiveItem::new) : null;

  private static Item registerWeapon(String name, ToolMaterial material, WEAPON_TYPE type, WeaponStats stats, WeaponFactory<? extends Item> factory) {
    Item item = Reggie.register(name, (settings) -> factory.create(material, stats.damage(), stats.speed(), stats.reach(), settings), buildDefaultSettings(material));
    itemsWithInfo.add(new ItemInfo(type, material, name, item));
    return item;
  }

  private static Item.Settings buildDefaultSettings(ToolMaterial material) {
    Item.Settings itemSettings = new Item.Settings();
    if (material == ToolMaterial.NETHERITE) itemSettings = itemSettings.fireproof();
    return itemSettings;
  }

  public static void init() {
  }

  @FunctionalInterface
  private interface WeaponFactory<T extends Item> {
    T create(ToolMaterial material, float damage, float speed, double reach, Item.Settings settings);
  }

  public record ItemInfo(WEAPON_TYPE weaponType, ToolMaterial weaponMaterial, String name, Item item) {
  }

}
