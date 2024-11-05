package com.seacroak.basicweapons.registry;

import com.seacroak.basicweapons.data.WEAPON_TYPE;
import com.seacroak.basicweapons.item.*;
import com.seacroak.basicweapons.material.BWToolMaterials;
import com.seacroak.basicweapons.util.ID;
import com.seacroak.basicweapons.util.Reggie;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

import java.util.LinkedList;
import java.util.List;

import static com.seacroak.basicweapons.registry.MainRegistry.bronze_mod_loaded;

public class WeaponRegistry {
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

  /* When referencing items, use the static fields in this class, not the itemsWithInfo list */
  public static List<ItemInfo> itemsWithInfo = new LinkedList<>();
  public static final Item WOODEN_DAGGER = registerWeapon("wooden_dagger", ToolMaterial.WOOD, WEAPON_TYPE.DAGGER, daggerDamage, daggerSpeed, daggerRange, DaggerItem::new);
  public static final Item STONE_DAGGER = registerWeapon("stone_dagger", ToolMaterial.STONE, WEAPON_TYPE.DAGGER, daggerDamage, daggerSpeed, daggerRange, DaggerItem::new);
  public static final Item IRON_DAGGER = registerWeapon("iron_dagger", ToolMaterial.IRON, WEAPON_TYPE.DAGGER, daggerDamage, daggerSpeed, daggerRange, DaggerItem::new);
  public static final Item GOLDEN_DAGGER = registerWeapon("golden_dagger", ToolMaterial.GOLD, WEAPON_TYPE.DAGGER, daggerDamage - 1, daggerSpeed + 1, daggerRange, DaggerItem::new);
  public static final Item DIAMOND_DAGGER = registerWeapon("diamond_dagger", ToolMaterial.DIAMOND, WEAPON_TYPE.DAGGER, daggerDamage, daggerSpeed, daggerRange, DaggerItem::new);
  public static final Item NETHERITE_DAGGER = registerWeapon("netherite_dagger", ToolMaterial.NETHERITE, WEAPON_TYPE.DAGGER, daggerDamage, daggerSpeed, daggerRange, DaggerItem::new);
  public static final Item WOODEN_HAMMER = registerWeapon("wooden_hammer", ToolMaterial.WOOD, WEAPON_TYPE.HAMMER, hammerDamage - 6, hammerSpeed + 0.4f, hammerRange, HammerItem::new);
  public static final Item STONE_HAMMER = registerWeapon("stone_hammer", ToolMaterial.STONE, WEAPON_TYPE.HAMMER, hammerDamage - 3, hammerSpeed + 0.2f, hammerRange, HammerItem::new);
  public static final Item IRON_HAMMER = registerWeapon("iron_hammer", ToolMaterial.IRON, WEAPON_TYPE.HAMMER, hammerDamage, hammerSpeed, hammerRange, HammerItem::new);
  public static final Item GOLDEN_HAMMER = registerWeapon("golden_hammer", ToolMaterial.GOLD, WEAPON_TYPE.HAMMER, hammerDamage - 6, hammerSpeed + 0.6f, hammerRange, HammerItem::new);
  public static final Item DIAMOND_HAMMER = registerWeapon("diamond_hammer", ToolMaterial.DIAMOND, WEAPON_TYPE.HAMMER, hammerDamage - 1, hammerSpeed + 0.1f, hammerRange, HammerItem::new);
  public static final Item NETHERITE_HAMMER = registerWeapon("netherite_hammer", ToolMaterial.NETHERITE, WEAPON_TYPE.HAMMER, hammerDamage - 1, hammerSpeed + 0.2f, hammerRange, HammerItem::new);
  public static final Item WOODEN_CLUB = registerWeapon("wooden_club", ToolMaterial.WOOD, WEAPON_TYPE.CLUB, clubDamage, clubSpeed, clubRange, ClubItem::new);
  public static final Item STONE_CLUB = registerWeapon("stone_club", ToolMaterial.STONE, WEAPON_TYPE.CLUB, clubDamage, clubSpeed, clubRange, ClubItem::new);
  public static final Item IRON_CLUB = registerWeapon("iron_club", ToolMaterial.IRON, WEAPON_TYPE.CLUB, clubDamage, clubSpeed, clubRange, ClubItem::new);
  public static final Item GOLDEN_CLUB = registerWeapon("golden_club", ToolMaterial.GOLD, WEAPON_TYPE.CLUB, clubDamage, clubSpeed, clubRange, ClubItem::new);
  public static final Item DIAMOND_CLUB = registerWeapon("diamond_club", ToolMaterial.DIAMOND, WEAPON_TYPE.CLUB, clubDamage, clubSpeed, clubRange, ClubItem::new);
  public static final Item NETHERITE_CLUB = registerWeapon("netherite_club", ToolMaterial.NETHERITE, WEAPON_TYPE.CLUB, clubDamage, clubSpeed, clubRange, ClubItem::new);
  public static final Item WOODEN_SPEAR = registerWeapon("wooden_spear", ToolMaterial.WOOD, WEAPON_TYPE.SPEAR, spearDamage, spearSpeed, spearRange, SpearItem::new);
  public static final Item STONE_SPEAR = registerWeapon("stone_spear", ToolMaterial.STONE, WEAPON_TYPE.SPEAR, spearDamage, spearSpeed, spearRange, SpearItem::new);
  public static final Item IRON_SPEAR = registerWeapon("iron_spear", ToolMaterial.IRON, WEAPON_TYPE.SPEAR, spearDamage, spearSpeed, spearRange, SpearItem::new);
  public static final Item GOLDEN_SPEAR = registerWeapon("golden_spear", ToolMaterial.GOLD, WEAPON_TYPE.SPEAR, spearDamage, spearSpeed, spearRange, SpearItem::new);
  public static final Item DIAMOND_SPEAR = registerWeapon("diamond_spear", ToolMaterial.DIAMOND, WEAPON_TYPE.SPEAR, spearDamage, spearSpeed, spearRange, SpearItem::new);
  public static final Item NETHERITE_SPEAR = registerWeapon("netherite_spear", ToolMaterial.NETHERITE, WEAPON_TYPE.SPEAR, spearDamage, spearSpeed, spearRange, SpearItem::new);
  public static final Item WOODEN_QUARTERSTAFF = registerWeapon("wooden_quarterstaff", ToolMaterial.WOOD, WEAPON_TYPE.QUARTERSTAFF, quarterstaffDamage, quarterstaffSpeed, quarterstaffRange, QuarterstaffItem::new);
  public static final Item STONE_QUARTERSTAFF = registerWeapon("stone_quarterstaff", ToolMaterial.STONE, WEAPON_TYPE.QUARTERSTAFF, quarterstaffDamage, quarterstaffSpeed, quarterstaffRange, QuarterstaffItem::new);
  public static final Item IRON_QUARTERSTAFF = registerWeapon("iron_quarterstaff", ToolMaterial.IRON, WEAPON_TYPE.QUARTERSTAFF, quarterstaffDamage, quarterstaffSpeed, quarterstaffRange, QuarterstaffItem::new);
  public static final Item GOLDEN_QUARTERSTAFF = registerWeapon("golden_quarterstaff", ToolMaterial.GOLD, WEAPON_TYPE.QUARTERSTAFF, quarterstaffDamage, quarterstaffSpeed, quarterstaffRange, QuarterstaffItem::new);
  public static final Item DIAMOND_QUARTERSTAFF = registerWeapon("diamond_quarterstaff", ToolMaterial.DIAMOND, WEAPON_TYPE.QUARTERSTAFF, quarterstaffDamage, quarterstaffSpeed, quarterstaffRange, QuarterstaffItem::new);
  public static final Item NETHERITE_QUARTERSTAFF = registerWeapon("netherite_quarterstaff", ToolMaterial.NETHERITE, WEAPON_TYPE.QUARTERSTAFF, quarterstaffDamage, quarterstaffSpeed, quarterstaffRange, QuarterstaffItem::new);
  public static final Item WOODEN_GLAIVE = registerWeapon("wooden_glaive", ToolMaterial.WOOD, WEAPON_TYPE.GLAIVE, glaiveDamage, glaiveSpeed, glaiveRange, GlaiveItem::new);
  public static final Item STONE_GLAIVE = registerWeapon("stone_glaive", ToolMaterial.STONE, WEAPON_TYPE.GLAIVE, glaiveDamage, glaiveSpeed, glaiveRange, GlaiveItem::new);
  public static final Item IRON_GLAIVE = registerWeapon("iron_glaive", ToolMaterial.IRON, WEAPON_TYPE.GLAIVE, glaiveDamage, glaiveSpeed, glaiveRange, GlaiveItem::new);
  public static final Item GOLDEN_GLAIVE = registerWeapon("golden_glaive", ToolMaterial.GOLD, WEAPON_TYPE.GLAIVE, glaiveDamage, glaiveSpeed, glaiveRange, GlaiveItem::new);
  public static final Item DIAMOND_GLAIVE = registerWeapon("diamond_glaive", ToolMaterial.DIAMOND, WEAPON_TYPE.GLAIVE, glaiveDamage, glaiveSpeed, glaiveRange, GlaiveItem::new);
  public static final Item NETHERITE_GLAIVE = registerWeapon("netherite_glaive", ToolMaterial.NETHERITE, WEAPON_TYPE.GLAIVE, glaiveDamage, glaiveSpeed, glaiveRange, GlaiveItem::new);
  public static final Item BRONZE_DAGGER = bronze_mod_loaded ? registerWeapon("bronze_dagger", BWToolMaterials.BRONZE, WEAPON_TYPE.DAGGER, daggerDamage, daggerSpeed, daggerRange, DaggerItem::new) : null;
  public static final Item BRONZE_HAMMER = bronze_mod_loaded ? registerWeapon("bronze_hammer", BWToolMaterials.BRONZE, WEAPON_TYPE.HAMMER, hammerDamage - 0.5f, hammerSpeed + 0.1f, hammerRange, HammerItem::new) : null;
  public static final Item BRONZE_CLUB = bronze_mod_loaded ? registerWeapon("bronze_club", BWToolMaterials.BRONZE, WEAPON_TYPE.CLUB, clubDamage, clubSpeed, clubRange, ClubItem::new) : null;
  public static final Item BRONZE_SPEAR = bronze_mod_loaded ? registerWeapon("bronze_spear", BWToolMaterials.BRONZE, WEAPON_TYPE.SPEAR, spearDamage, spearSpeed, spearRange, SpearItem::new) : null;
  public static final Item BRONZE_QUARTERSTAFF = bronze_mod_loaded ? registerWeapon("bronze_quarterstaff", BWToolMaterials.BRONZE, WEAPON_TYPE.QUARTERSTAFF, quarterstaffDamage, quarterstaffSpeed, quarterstaffRange, QuarterstaffItem::new) : null;
  public static final Item BRONZE_GLAIVE = bronze_mod_loaded ? registerWeapon("bronze_glaive", BWToolMaterials.BRONZE, WEAPON_TYPE.GLAIVE, glaiveDamage, glaiveSpeed, glaiveRange, GlaiveItem::new) : null;

  private static Item addItem(WEAPON_TYPE type, ToolMaterial material, String name, Item item) {
    itemsWithInfo.add(new ItemInfo(type, material, name, item));
    return item;
  }

  private static Item registerWeapon(String name, ToolMaterial material, WEAPON_TYPE type, float damage, float speed, double range, WeaponFactory<? extends Item> factory) {
    return addItem(type, material, name, Reggie.register(name, (settings) -> {
      Item.Settings itemSettings = attachDefaultSettings(name, type, material, damage, speed, range);
      if (material == ToolMaterial.NETHERITE) itemSettings = itemSettings.fireproof();
      return factory.create(material, damage, speed, range, itemSettings);
    }));
  }

  private static Item.Settings attachDefaultSettings(String name, WEAPON_TYPE weaponType, ToolMaterial material, float damage, float speed, double range) {
    Item.Settings itemSettings = new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, ID.of(name)));
    switch (weaponType) {
      case DAGGER, HAMMER, CLUB, SPEAR ->
          itemSettings = itemSettings.attributeModifiers(BasicWeaponSweeplessItem.createAttributeModifiers(material, damage, speed, range));
      case QUARTERSTAFF, GLAIVE ->
          itemSettings = itemSettings.attributeModifiers(BasicWeaponItem.createAttributeModifiers(material, damage, speed, range));
    }
    return itemSettings;
  }

  public static void init() {

  }

  @FunctionalInterface
  private interface WeaponFactory<T extends Item> {
    T create(ToolMaterial material, float damage, float speed, double range, Item.Settings settings);
  }

  public static class ItemInfo {
    public final WEAPON_TYPE weaponType;
    public final ToolMaterial weaponMaterial;
    public final String name;
    public final Item item;

    public ItemInfo(WEAPON_TYPE weaponType, ToolMaterial weaponMaterial, String name, Item item) {
      this.weaponType = weaponType;
      this.weaponMaterial = weaponMaterial;
      this.name = name;
      this.item = item;
    }
  }
}
