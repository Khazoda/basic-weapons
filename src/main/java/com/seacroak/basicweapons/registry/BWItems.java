package com.seacroak.basicweapons.registry;

import com.seacroak.basicweapons.item.BatonItem;
import com.seacroak.basicweapons.item.DaggerItem;
import com.seacroak.basicweapons.item.HammerItem;
import com.seacroak.basicweapons.item.SpearItem;
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
  public static final int batonDamage = 2;
  public static final float batonSpeed = -2.4f;
  public static final int spearDamage = 2;
  public static final float spearSpeed = -2.4f;
  public static List<ItemInfo> items = new LinkedList<>();

  public static void init() {
    /* Daggers */
    items.add(new ItemInfo("dagger", "wooden_dagger", () -> new DaggerItem(ToolMaterials.WOOD, daggerDamage, daggerSpeed, new Item.Settings())));
    items.add(new ItemInfo("dagger", "stone_dagger", () -> new DaggerItem(ToolMaterials.STONE, daggerDamage, daggerSpeed, new Item.Settings())));
    items.add(new ItemInfo("dagger", "iron_dagger", () -> new DaggerItem(ToolMaterials.IRON, daggerDamage, daggerSpeed, new Item.Settings())));
    items.add(new ItemInfo("dagger", "golden_dagger", () -> new DaggerItem(ToolMaterials.GOLD, daggerDamage, daggerSpeed, new Item.Settings())));
    items.add(new ItemInfo("dagger", "diamond_dagger", () -> new DaggerItem(ToolMaterials.DIAMOND, daggerDamage, daggerSpeed, new Item.Settings())));
    items.add(new ItemInfo("dagger", "netherite_dagger", () -> new DaggerItem(ToolMaterials.NETHERITE, daggerDamage, daggerSpeed, new Item.Settings().fireproof())));
    /* Hammers */
    items.add(new ItemInfo("hammer", "wooden_hammer", () -> new HammerItem(ToolMaterials.WOOD, hammerDamage, hammerSpeed - 0.2f, new Item.Settings())));
    items.add(new ItemInfo("hammer", "stone_hammer", () -> new HammerItem(ToolMaterials.STONE, hammerDamage + 1, hammerSpeed - 0.2f, new Item.Settings())));
    items.add(new ItemInfo("hammer", "iron_hammer", () -> new HammerItem(ToolMaterials.IRON, hammerDamage, hammerSpeed - 0.1f, new Item.Settings())));
    items.add(new ItemInfo("hammer", "golden_hammer", () -> new HammerItem(ToolMaterials.GOLD, hammerDamage - 1, hammerSpeed, new Item.Settings())));
    items.add(new ItemInfo("hammer", "diamond_hammer", () -> new HammerItem(ToolMaterials.DIAMOND, hammerDamage - 1, hammerSpeed, new Item.Settings())));
    items.add(new ItemInfo("hammer", "netherite_hammer", () -> new HammerItem(ToolMaterials.NETHERITE, hammerDamage - 1, hammerSpeed, new Item.Settings().fireproof())));
    /* Batons */
    items.add(new ItemInfo("baton", "wooden_baton", () -> new BatonItem(ToolMaterials.WOOD, batonDamage, batonSpeed, new Item.Settings())));
    items.add(new ItemInfo("baton", "stone_baton", () -> new BatonItem(ToolMaterials.STONE, batonDamage, batonSpeed, new Item.Settings())));
    items.add(new ItemInfo("baton", "iron_baton", () -> new BatonItem(ToolMaterials.IRON, batonDamage, batonSpeed, new Item.Settings())));
    items.add(new ItemInfo("baton", "golden_baton", () -> new BatonItem(ToolMaterials.GOLD, batonDamage, batonSpeed, new Item.Settings())));
    items.add(new ItemInfo("baton", "diamond_baton", () -> new BatonItem(ToolMaterials.DIAMOND, batonDamage, batonSpeed, new Item.Settings())));
    items.add(new ItemInfo("baton", "netherite_baton", () -> new BatonItem(ToolMaterials.NETHERITE, batonDamage, batonSpeed, new Item.Settings().fireproof())));
    /* Spears */
    items.add(new ItemInfo("spear", "wooden_spear", () -> new SpearItem(ToolMaterials.WOOD, spearDamage, spearSpeed, new Item.Settings())));
    items.add(new ItemInfo("spear", "stone_spear", () -> new SpearItem(ToolMaterials.STONE, spearDamage, spearSpeed, new Item.Settings())));
    items.add(new ItemInfo("spear", "iron_spear", () -> new SpearItem(ToolMaterials.IRON, spearDamage, spearSpeed, new Item.Settings())));
    items.add(new ItemInfo("spear", "golden_spear", () -> new SpearItem(ToolMaterials.GOLD, spearDamage, spearSpeed, new Item.Settings())));
    items.add(new ItemInfo("spear", "diamond_spear", () -> new SpearItem(ToolMaterials.DIAMOND, spearDamage, spearSpeed, new Item.Settings())));
    items.add(new ItemInfo("spear", "netherite_spear", () -> new SpearItem(ToolMaterials.NETHERITE, spearDamage, spearSpeed, new Item.Settings().fireproof())));

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
