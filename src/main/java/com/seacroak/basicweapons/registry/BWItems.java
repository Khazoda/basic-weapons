package com.seacroak.basicweapons.registry;

import com.seacroak.basicweapons.item.BatonItem;
import com.seacroak.basicweapons.item.DaggerItem;
import com.seacroak.basicweapons.item.HammerItem;
import com.seacroak.basicweapons.item.SpearItem;
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
  public static final int batonDamage = 2;
  public static final float batonSpeed = -2.4f;
  public static final int spearDamage = 2;
  public static final float spearSpeed = -2.4f;
  public static List<ItemInfo> items = new LinkedList<>();

  public static void init() {
    /* Daggers */
    items.add(new ItemInfo("dagger", "wooden_dagger", () -> new DaggerItem(Tiers.WOOD, daggerDamage, daggerSpeed, new Item.Properties())));
    items.add(new ItemInfo("dagger", "stone_dagger", () -> new DaggerItem(Tiers.STONE, daggerDamage, daggerSpeed, new Item.Properties())));
    items.add(new ItemInfo("dagger", "iron_dagger", () -> new DaggerItem(Tiers.IRON, daggerDamage, daggerSpeed, new Item.Properties())));
    items.add(new ItemInfo("dagger", "golden_dagger", () -> new DaggerItem(Tiers.GOLD, daggerDamage, daggerSpeed, new Item.Properties())));
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
    items.add(new ItemInfo("baton", "wooden_baton", () -> new BatonItem(Tiers.WOOD, batonDamage, batonSpeed, new Item.Properties())));
    items.add(new ItemInfo("baton", "stone_baton", () -> new BatonItem(Tiers.STONE, batonDamage, batonSpeed, new Item.Properties())));
    items.add(new ItemInfo("baton", "iron_baton", () -> new BatonItem(Tiers.IRON, batonDamage, batonSpeed, new Item.Properties())));
    items.add(new ItemInfo("baton", "golden_baton", () -> new BatonItem(Tiers.GOLD, batonDamage, batonSpeed, new Item.Properties())));
    items.add(new ItemInfo("baton", "diamond_baton", () -> new BatonItem(Tiers.DIAMOND, batonDamage, batonSpeed, new Item.Properties())));
    items.add(new ItemInfo("baton", "netherite_baton", () -> new BatonItem(Tiers.NETHERITE, batonDamage, batonSpeed, new Item.Properties().fireResistant())));
    /* Spears */
    items.add(new ItemInfo("spear", "wooden_spear", () -> new SpearItem(Tiers.WOOD, spearDamage, spearSpeed, new Item.Properties())));
    items.add(new ItemInfo("spear", "stone_spear", () -> new SpearItem(Tiers.STONE, spearDamage, spearSpeed, new Item.Properties())));
    items.add(new ItemInfo("spear", "iron_spear", () -> new SpearItem(Tiers.IRON, spearDamage, spearSpeed, new Item.Properties())));
    items.add(new ItemInfo("spear", "golden_spear", () -> new SpearItem(Tiers.GOLD, spearDamage, spearSpeed, new Item.Properties())));
    items.add(new ItemInfo("spear", "diamond_spear", () -> new SpearItem(Tiers.DIAMOND, spearDamage, spearSpeed, new Item.Properties())));
    items.add(new ItemInfo("spear", "netherite_spear", () -> new SpearItem(Tiers.NETHERITE, spearDamage, spearSpeed, new Item.Properties().fireResistant())));

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
