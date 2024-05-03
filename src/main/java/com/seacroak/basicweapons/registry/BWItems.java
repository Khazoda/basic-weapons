package com.seacroak.basicweapons.registry;

import com.seacroak.basicweapons.item.*;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterials;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Supplier;

public class BWItems {
  public static final int daggerDamage = 1;
  public static final float daggerSpeed = -2.0f;
  public static final int hammerDamage = 7;
  public static final float hammerSpeed = -3.4f;
  public static final int clubDamage = 5;
  public static final float clubSpeed = -3.0f;
  public static final int spearDamage = 2;
  public static final float spearSpeed = -2.8f;
  public static final int quarterstaffDamage = 1;
  public static final float quarterstaffSpeed = -2.3f;

  /* When referencing items, use registeredItems from MainRegistry, not items.
  Indices are the same for both constants */
  public static List<ItemInfo> items = new LinkedList<>();

  public static void init() {
    /* Daggers */
    /* 0 */
    items.add(new ItemInfo("dagger", "wooden_dagger", () -> new DaggerItem(ToolMaterials.WOOD, new Item.Settings().attributeModifiers(DaggerItem.createAttributeModifiers(
        ToolMaterials.WOOD, daggerDamage, daggerSpeed)))));
    /* 1 */

    items.add(new ItemInfo("dagger", "stone_dagger", () -> new DaggerItem(ToolMaterials.STONE, new Item.Settings().attributeModifiers(DaggerItem.createAttributeModifiers(
        ToolMaterials.STONE, daggerDamage, daggerSpeed)))));
    /* 2 */
    items.add(new ItemInfo("dagger", "iron_dagger", () -> new DaggerItem(ToolMaterials.IRON, new Item.Settings().attributeModifiers(DaggerItem.createAttributeModifiers(
        ToolMaterials.IRON, daggerDamage, daggerSpeed)))));
    /* 3 */
    items.add(new ItemInfo("dagger", "golden_dagger", () -> new DaggerItem(ToolMaterials.GOLD, new Item.Settings().attributeModifiers(DaggerItem.createAttributeModifiers(
        ToolMaterials.GOLD, daggerDamage - 1, daggerSpeed + 1)))));
    /* 4 */
    items.add(new ItemInfo("dagger", "diamond_dagger", () -> new DaggerItem(ToolMaterials.DIAMOND, new Item.Settings().attributeModifiers(DaggerItem.createAttributeModifiers(
        ToolMaterials.DIAMOND, daggerDamage, daggerSpeed)))));
    /* 5 */
    items.add(new ItemInfo("dagger", "netherite_dagger", () -> new DaggerItem(ToolMaterials.NETHERITE, new Item.Settings().attributeModifiers(DaggerItem.createAttributeModifiers(
        ToolMaterials.NETHERITE, daggerDamage, daggerSpeed)).fireproof())));

    /* Hammers */
    /* 6 */
    items.add(new ItemInfo("hammer", "wooden_hammer", () -> new HammerItem(ToolMaterials.WOOD, new Item.Settings().attributeModifiers(HammerItem.createAttributeModifiers(
        ToolMaterials.WOOD, hammerDamage - 6, hammerSpeed + 0.4f)))));
    /* 7 */
    items.add(new ItemInfo("hammer", "stone_hammer", () -> new HammerItem(ToolMaterials.STONE, new Item.Settings().attributeModifiers(HammerItem.createAttributeModifiers(
        ToolMaterials.STONE, hammerDamage - 3, hammerSpeed + 0.2f)))));
    /* 8 */
    items.add(new ItemInfo("hammer", "iron_hammer", () -> new HammerItem(ToolMaterials.IRON, new Item.Settings().attributeModifiers(HammerItem.createAttributeModifiers(
        ToolMaterials.IRON, hammerDamage, hammerSpeed + 0.1f)))));
    /* 9 */
    items.add(new ItemInfo("hammer", "golden_hammer", () -> new HammerItem(ToolMaterials.GOLD, new Item.Settings().attributeModifiers(HammerItem.createAttributeModifiers(
        ToolMaterials.GOLD, hammerDamage - 3, hammerSpeed)))));
    /* 10 */
    items.add(new ItemInfo("hammer", "diamond_hammer", () -> new HammerItem(ToolMaterials.DIAMOND, new Item.Settings().attributeModifiers(HammerItem.createAttributeModifiers(
        ToolMaterials.DIAMOND, hammerDamage - 1, hammerSpeed)))));
    /* 11 */
    items.add(new ItemInfo("hammer", "netherite_hammer", () -> new HammerItem(ToolMaterials.NETHERITE, new Item.Settings().attributeModifiers(HammerItem.createAttributeModifiers(
        ToolMaterials.NETHERITE, hammerDamage - 1, hammerSpeed)).fireproof())));

    /* Clubs */
    /* 12 */
    items.add(new ItemInfo("club", "wooden_club", () -> new ClubItem(ToolMaterials.WOOD, new Item.Settings().attributeModifiers(ClubItem.createAttributeModifiers(
        ToolMaterials.WOOD, clubDamage, clubSpeed)))));
    /* 13 */
    items.add(new ItemInfo("club", "stone_club", () -> new ClubItem(ToolMaterials.STONE, new Item.Settings().attributeModifiers(ClubItem.createAttributeModifiers(
        ToolMaterials.STONE, clubDamage, clubSpeed)))));
    /* 14 */
    items.add(new ItemInfo("club", "iron_club", () -> new ClubItem(ToolMaterials.IRON, new Item.Settings().attributeModifiers(ClubItem.createAttributeModifiers(
        ToolMaterials.IRON, clubDamage, clubSpeed)))));
    /* 15 */
    items.add(new ItemInfo("club", "golden_club", () -> new ClubItem(ToolMaterials.GOLD, new Item.Settings().attributeModifiers(ClubItem.createAttributeModifiers(
        ToolMaterials.GOLD, clubDamage, clubSpeed)))));
    /* 16 */
    items.add(new ItemInfo("club", "diamond_club", () -> new ClubItem(ToolMaterials.DIAMOND, new Item.Settings().attributeModifiers(ClubItem.createAttributeModifiers(
        ToolMaterials.DIAMOND, clubDamage, clubSpeed)))));
    /* 17 */
    items.add(new ItemInfo("club", "netherite_club", () -> new ClubItem(ToolMaterials.NETHERITE, new Item.Settings().attributeModifiers(ClubItem.createAttributeModifiers(
        ToolMaterials.NETHERITE, clubDamage, clubSpeed)).fireproof())));

    /* Spears */
    /* 18 */
    items.add(new ItemInfo("spear", "wooden_spear", () -> new SpearItem(ToolMaterials.WOOD, new Item.Settings().attributeModifiers(SpearItem.createAttributeModifiers(
        ToolMaterials.WOOD, spearDamage, spearSpeed)))));
    /* 19 */
    items.add(new ItemInfo("spear", "stone_spear", () -> new SpearItem(ToolMaterials.STONE, new Item.Settings().attributeModifiers(SpearItem.createAttributeModifiers(
        ToolMaterials.STONE, spearDamage, spearSpeed)))));
    /* 20 */
    items.add(new ItemInfo("spear", "iron_spear", () -> new SpearItem(ToolMaterials.IRON, new Item.Settings().attributeModifiers(SpearItem.createAttributeModifiers(
        ToolMaterials.IRON, spearDamage, spearSpeed)))));
    /* 21 */
    items.add(new ItemInfo("spear", "golden_spear", () -> new SpearItem(ToolMaterials.GOLD, new Item.Settings().attributeModifiers(SpearItem.createAttributeModifiers(
        ToolMaterials.GOLD, spearDamage, spearSpeed)))));
    /* 22 */
    items.add(new ItemInfo("spear", "diamond_spear", () -> new SpearItem(ToolMaterials.DIAMOND, new Item.Settings().attributeModifiers(SpearItem.createAttributeModifiers(
        ToolMaterials.DIAMOND, spearDamage, spearSpeed)))));
    /* 23 */
    items.add(new ItemInfo("spear", "netherite_spear", () -> new SpearItem(ToolMaterials.NETHERITE, new Item.Settings().attributeModifiers(SpearItem.createAttributeModifiers(
        ToolMaterials.NETHERITE, spearDamage, spearSpeed)).fireproof())));

    /* Quarterstaves */
    /* 24 */
    items.add(new ItemInfo("quarterstaff", "wooden_quarterstaff", () -> new QuarterstaffItem(ToolMaterials.WOOD, new Item.Settings().attributeModifiers(QuarterstaffItem.createAttributeModifiers(
        ToolMaterials.WOOD, quarterstaffDamage, quarterstaffSpeed)))));
    /* 25 */
    items.add(new ItemInfo("quarterstaff", "stone_quarterstaff", () -> new QuarterstaffItem(ToolMaterials.STONE, new Item.Settings().attributeModifiers(QuarterstaffItem.createAttributeModifiers(
        ToolMaterials.STONE, quarterstaffDamage, quarterstaffSpeed)))));
    /* 26 */
    items.add(new ItemInfo("quarterstaff", "iron_quarterstaff", () -> new QuarterstaffItem(ToolMaterials.IRON, new Item.Settings().attributeModifiers(QuarterstaffItem.createAttributeModifiers(
        ToolMaterials.IRON, quarterstaffDamage, quarterstaffSpeed)))));
    /* 27 */
    items.add(new ItemInfo("quarterstaff", "golden_quarterstaff", () -> new QuarterstaffItem(ToolMaterials.GOLD, new Item.Settings().attributeModifiers(QuarterstaffItem.createAttributeModifiers(
        ToolMaterials.GOLD, quarterstaffDamage, quarterstaffSpeed)))));
    /* 28 */
    items.add(new ItemInfo("quarterstaff", "diamond_quarterstaff", () -> new QuarterstaffItem(ToolMaterials.DIAMOND, new Item.Settings().attributeModifiers(QuarterstaffItem.createAttributeModifiers(
        ToolMaterials.DIAMOND, quarterstaffDamage, quarterstaffSpeed)))));
    /* 29 */
    items.add(new ItemInfo("quarterstaff", "netherite_quarterstaff", () -> new QuarterstaffItem(ToolMaterials.NETHERITE, new Item.Settings().attributeModifiers(QuarterstaffItem.createAttributeModifiers(
        ToolMaterials.NETHERITE, quarterstaffDamage, quarterstaffSpeed)).fireproof())));

  }

  /*** This class holds information about all items.
   It is accessed by forge & fabric methods
   for automatic registration ***/
  public static class ItemInfo {
    public final String weaponType;
    public final String name;
    public final Supplier<Item> itemSupplier;

    public ItemInfo(String weaponType, String name, Supplier<Item> itemSupplier) {
      this.weaponType = weaponType;
      this.name = name;
      this.itemSupplier = itemSupplier;
    }
  }

}
