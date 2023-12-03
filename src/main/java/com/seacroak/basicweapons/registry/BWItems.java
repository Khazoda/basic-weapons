package com.seacroak.basicweapons.registry;

import com.seacroak.basicweapons.item.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
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
    items.add(new ItemInfo("dagger", "wooden_dagger", () -> new DaggerItem(ToolMaterials.WOOD, daggerDamage, daggerSpeed, new Item.Settings().group(ItemGroup.COMBAT))));
    /* 1 */
    items.add(new ItemInfo("dagger", "stone_dagger", () -> new DaggerItem(ToolMaterials.STONE, daggerDamage, daggerSpeed, new Item.Settings().group(ItemGroup.COMBAT))));
    /* 2 */
    items.add(new ItemInfo("dagger", "iron_dagger", () -> new DaggerItem(ToolMaterials.IRON, daggerDamage, daggerSpeed, new Item.Settings().group(ItemGroup.COMBAT))));
    /* 3 */
    items.add(new ItemInfo("dagger", "golden_dagger", () -> new DaggerItem(ToolMaterials.GOLD, daggerDamage - 1, daggerSpeed + 1, new Item.Settings().group(ItemGroup.COMBAT))));
    /* 4 */
    items.add(new ItemInfo("dagger", "diamond_dagger", () -> new DaggerItem(ToolMaterials.DIAMOND, daggerDamage, daggerSpeed, new Item.Settings().group(ItemGroup.COMBAT))));
    /* 5 */
    items.add(new ItemInfo("dagger", "netherite_dagger", () -> new DaggerItem(ToolMaterials.NETHERITE, daggerDamage, daggerSpeed, new Item.Settings().group(ItemGroup.COMBAT).fireproof())));
    /* Hammers */
    /* 6 */
    items.add(new ItemInfo("hammer", "wooden_hammer", () -> new HammerItem(ToolMaterials.WOOD, hammerDamage - 6, hammerSpeed + 0.4f, new Item.Settings().group(ItemGroup.COMBAT))));
    /* 7 */
    items.add(new ItemInfo("hammer", "stone_hammer", () -> new HammerItem(ToolMaterials.STONE, hammerDamage - 3, hammerSpeed + 0.2f, new Item.Settings().group(ItemGroup.COMBAT))));
    /* 8 */
    items.add(new ItemInfo("hammer", "iron_hammer", () -> new HammerItem(ToolMaterials.IRON, hammerDamage, hammerSpeed - 0.1f, new Item.Settings().group(ItemGroup.COMBAT))));
    /* 9 */
    items.add(new ItemInfo("hammer", "golden_hammer", () -> new HammerItem(ToolMaterials.GOLD, hammerDamage - 3, hammerSpeed, new Item.Settings().group(ItemGroup.COMBAT))));
    /* 10 */
    items.add(new ItemInfo("hammer", "diamond_hammer", () -> new HammerItem(ToolMaterials.DIAMOND, hammerDamage - 1, hammerSpeed, new Item.Settings().group(ItemGroup.COMBAT))));
    /* 11 */
    items.add(new ItemInfo("hammer", "netherite_hammer", () -> new HammerItem(ToolMaterials.NETHERITE, hammerDamage - 1, hammerSpeed, new Item.Settings().group(ItemGroup.COMBAT).fireproof())));
    /* Batons */
    /* 12 */
    items.add(new ItemInfo("club", "wooden_club", () -> new ClubItem(ToolMaterials.WOOD, clubDamage, clubSpeed, new Item.Settings().group(ItemGroup.COMBAT))));
    /* 13 */
    items.add(new ItemInfo("club", "stone_club", () -> new ClubItem(ToolMaterials.STONE, clubDamage, clubSpeed, new Item.Settings().group(ItemGroup.COMBAT))));
    /* 14 */
    items.add(new ItemInfo("club", "iron_club", () -> new ClubItem(ToolMaterials.IRON, clubDamage, clubSpeed, new Item.Settings().group(ItemGroup.COMBAT))));
    /* 15 */
    items.add(new ItemInfo("club", "golden_club", () -> new ClubItem(ToolMaterials.GOLD, clubDamage, clubSpeed, new Item.Settings().group(ItemGroup.COMBAT))));
    /* 16 */
    items.add(new ItemInfo("club", "diamond_club", () -> new ClubItem(ToolMaterials.DIAMOND, clubDamage, clubSpeed, new Item.Settings().group(ItemGroup.COMBAT))));
    /* 17 */
    items.add(new ItemInfo("club", "netherite_club", () -> new ClubItem(ToolMaterials.NETHERITE, clubDamage, clubSpeed, new Item.Settings().group(ItemGroup.COMBAT).fireproof())));
    /* Spears */
    /* 18 */
    items.add(new ItemInfo("spear", "wooden_spear", () -> new SpearItem(ToolMaterials.WOOD, spearDamage, spearSpeed, new Item.Settings().group(ItemGroup.COMBAT))));
    /* 19 */
    items.add(new ItemInfo("spear", "stone_spear", () -> new SpearItem(ToolMaterials.STONE, spearDamage, spearSpeed, new Item.Settings().group(ItemGroup.COMBAT))));
    /* 20 */
    items.add(new ItemInfo("spear", "iron_spear", () -> new SpearItem(ToolMaterials.IRON, spearDamage, spearSpeed, new Item.Settings().group(ItemGroup.COMBAT))));
    /* 21 */
    items.add(new ItemInfo("spear", "golden_spear", () -> new SpearItem(ToolMaterials.GOLD, spearDamage, spearSpeed, new Item.Settings().group(ItemGroup.COMBAT))));
    /* 22 */
    items.add(new ItemInfo("spear", "diamond_spear", () -> new SpearItem(ToolMaterials.DIAMOND, spearDamage, spearSpeed, new Item.Settings().group(ItemGroup.COMBAT))));
    /* 23 */
    items.add(new ItemInfo("spear", "netherite_spear", () -> new SpearItem(ToolMaterials.NETHERITE, spearDamage, spearSpeed, new Item.Settings().group(ItemGroup.COMBAT).fireproof())));
    /* Quarterstaves */
    /* 24 */
    items.add(new ItemInfo("quarterstaff", "wooden_quarterstaff", () -> new QuarterstaffItem(ToolMaterials.WOOD, quarterstaffDamage, quarterstaffSpeed, new Item.Settings().group(ItemGroup.COMBAT))));
    /* 25 */
    items.add(new ItemInfo("quarterstaff", "stone_quarterstaff", () -> new QuarterstaffItem(ToolMaterials.STONE, quarterstaffDamage, quarterstaffSpeed, new Item.Settings().group(ItemGroup.COMBAT))));
    /* 26 */
    items.add(new ItemInfo("quarterstaff", "iron_quarterstaff", () -> new QuarterstaffItem(ToolMaterials.IRON, quarterstaffDamage, quarterstaffSpeed, new Item.Settings().group(ItemGroup.COMBAT))));
    /* 27 */
    items.add(new ItemInfo("quarterstaff", "golden_quarterstaff", () -> new QuarterstaffItem(ToolMaterials.GOLD, quarterstaffDamage, quarterstaffSpeed, new Item.Settings().group(ItemGroup.COMBAT))));
    /* 28 */
    items.add(new ItemInfo("quarterstaff", "diamond_quarterstaff", () -> new QuarterstaffItem(ToolMaterials.DIAMOND, quarterstaffDamage, quarterstaffSpeed, new Item.Settings().group(ItemGroup.COMBAT))));
    /* 29 */
    items.add(new ItemInfo("quarterstaff", "netherite_quarterstaff", () -> new QuarterstaffItem(ToolMaterials.NETHERITE, quarterstaffDamage, quarterstaffSpeed, new Item.Settings().group(ItemGroup.COMBAT).fireproof())));

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
