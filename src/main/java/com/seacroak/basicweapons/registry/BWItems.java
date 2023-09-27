package com.seacroak.basicweapons.registry;

import com.seacroak.basicweapons.item.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;

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
  public static List<ItemInfo> items = new LinkedList<>();

  public static void init() {
    /* Daggers */
    items.add(new ItemInfo("dagger", "wooden_dagger", () -> new DaggerItem(Tiers.WOOD, daggerDamage, daggerSpeed, new Item.Properties())));
    items.add(new ItemInfo("dagger", "stone_dagger", () -> new DaggerItem(Tiers.STONE, daggerDamage, daggerSpeed, new Item.Properties())));
    items.add(new ItemInfo("dagger", "iron_dagger", () -> new DaggerItem(Tiers.IRON, daggerDamage, daggerSpeed, new Item.Properties())));
    items.add(new ItemInfo("dagger", "golden_dagger", () -> new DaggerItem(Tiers.GOLD, daggerDamage - 1, daggerSpeed + 1, new Item.Properties())));
    items.add(new ItemInfo("dagger", "diamond_dagger", () -> new DaggerItem(Tiers.DIAMOND, daggerDamage, daggerSpeed, new Item.Properties())));
    items.add(new ItemInfo("dagger", "netherite_dagger", () -> new DaggerItem(Tiers.NETHERITE, daggerDamage, daggerSpeed, new Item.Properties().fireResistant())));
    /* Hammers */
    items.add(new ItemInfo("hammer", "wooden_hammer", () -> new HammerItem(Tiers.WOOD, hammerDamage, hammerSpeed - 0.2f, new Item.Properties())));
    items.add(new ItemInfo("hammer", "stone_hammer", () -> new HammerItem(Tiers.STONE, hammerDamage + 1, hammerSpeed - 0.2f, new Item.Properties())));
    items.add(new ItemInfo("hammer", "iron_hammer", () -> new HammerItem(Tiers.IRON, hammerDamage, hammerSpeed - 0.1f, new Item.Properties())));
    items.add(new ItemInfo("hammer", "golden_hammer", () -> new HammerItem(Tiers.GOLD, hammerDamage - 1, hammerSpeed, new Item.Properties())));
    items.add(new ItemInfo("hammer", "diamond_hammer", () -> new HammerItem(Tiers.DIAMOND, hammerDamage - 1, hammerSpeed, new Item.Properties())));
    items.add(new ItemInfo("hammer", "netherite_hammer", () -> new HammerItem(Tiers.NETHERITE, hammerDamage - 1, hammerSpeed, new Item.Properties().fireResistant())));
    /* Batons */
    items.add(new ItemInfo("club", "wooden_club", () -> new ClubItem(Tiers.WOOD, clubDamage, clubSpeed, new Item.Properties())));
    items.add(new ItemInfo("club", "stone_club", () -> new ClubItem(Tiers.STONE, clubDamage, clubSpeed, new Item.Properties())));
    items.add(new ItemInfo("club", "iron_club", () -> new ClubItem(Tiers.IRON, clubDamage, clubSpeed, new Item.Properties())));
    items.add(new ItemInfo("club", "golden_club", () -> new ClubItem(Tiers.GOLD, clubDamage, clubSpeed, new Item.Properties())));
    items.add(new ItemInfo("club", "diamond_club", () -> new ClubItem(Tiers.DIAMOND, clubDamage, clubSpeed, new Item.Properties())));
    items.add(new ItemInfo("club", "netherite_club", () -> new ClubItem(Tiers.NETHERITE, clubDamage, clubSpeed, new Item.Properties().fireResistant())));
    /* Spears */
    items.add(new ItemInfo("spear", "wooden_spear", () -> new SpearItem(Tiers.WOOD, spearDamage, spearSpeed, new Item.Properties())));
    items.add(new ItemInfo("spear", "stone_spear", () -> new SpearItem(Tiers.STONE, spearDamage, spearSpeed, new Item.Properties())));
    items.add(new ItemInfo("spear", "iron_spear", () -> new SpearItem(Tiers.IRON, spearDamage, spearSpeed, new Item.Properties())));
    items.add(new ItemInfo("spear", "golden_spear", () -> new SpearItem(Tiers.GOLD, spearDamage, spearSpeed, new Item.Properties())));
    items.add(new ItemInfo("spear", "diamond_spear", () -> new SpearItem(Tiers.DIAMOND, spearDamage, spearSpeed, new Item.Properties())));
    items.add(new ItemInfo("spear", "netherite_spear", () -> new SpearItem(Tiers.NETHERITE, spearDamage, spearSpeed, new Item.Properties().fireResistant())));
    /* Quarterstaves */
    items.add(new ItemInfo("quarterstaff", "wooden_quarterstaff", () -> new QuarterstaffItem(Tiers.WOOD, quarterstaffDamage, quarterstaffSpeed, new Item.Properties())));
    items.add(new ItemInfo("quarterstaff", "stone_quarterstaff", () -> new QuarterstaffItem(Tiers.STONE, quarterstaffDamage, quarterstaffSpeed, new Item.Properties())));
    items.add(new ItemInfo("quarterstaff", "iron_quarterstaff", () -> new QuarterstaffItem(Tiers.IRON, quarterstaffDamage, quarterstaffSpeed, new Item.Properties())));
    items.add(new ItemInfo("quarterstaff", "golden_quarterstaff", () -> new QuarterstaffItem(Tiers.GOLD, quarterstaffDamage, quarterstaffSpeed, new Item.Properties())));
    items.add(new ItemInfo("quarterstaff", "diamond_quarterstaff", () -> new QuarterstaffItem(Tiers.DIAMOND, quarterstaffDamage, quarterstaffSpeed, new Item.Properties())));
    items.add(new ItemInfo("quarterstaff", "netherite_quarterstaff", () -> new QuarterstaffItem(Tiers.NETHERITE, quarterstaffDamage, quarterstaffSpeed, new Item.Properties().fireResistant())));

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
