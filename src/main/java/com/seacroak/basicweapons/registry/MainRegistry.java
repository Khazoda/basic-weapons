package com.seacroak.basicweapons.registry;

import com.seacroak.basicweapons.item.*;
import com.seacroak.basicweapons.registry.WeaponRegistry.ItemInfo;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static com.seacroak.basicweapons.material.BWToolMaterials.BRONZE;
import static net.minecraft.item.ToolMaterial.*;

public class MainRegistry {
  public final static boolean bronze_mod_loaded = FabricLoader.getInstance().isModLoaded("bronze");

  public final static Map<Integer, ItemInfo> registeredItems = new HashMap<>();
  /*
  These holders are for loot table creation use
  to simplify selection of weapons by type or material
  */
  public static List<DaggerItem> daggers = new LinkedList<>();
  public static List<ClubItem> clubs = new LinkedList<>();
  public static List<HammerItem> hammers = new LinkedList<>();
  public static List<SpearItem> spears = new LinkedList<>();
  public static List<QuarterstaffItem> quarterstaves = new LinkedList<>();
  public static List<GlaiveItem> glaives = new LinkedList<>();

  public static List<Item> woodenWeapons = new LinkedList<>();
  public static List<Item> stoneWeapons = new LinkedList<>();
  public static List<Item> ironWeapons = new LinkedList<>();
  public static List<Item> bronzeWeapons = new LinkedList<>();
  public static List<Item> goldenWeapons = new LinkedList<>();
  public static List<Item> diamondWeapons = new LinkedList<>();
  public static List<Item> netheriteWeapons = new LinkedList<>();

  public static void init() {
    int x = 0;
    for (ItemInfo itemWithInfo : WeaponRegistry.itemsWithInfo) {
      registeredItems.put(x++, new ItemInfo(itemWithInfo.weaponType, itemWithInfo.weaponMaterial, itemWithInfo.name, itemWithInfo.item));
    }

    // Populate type lists
    for (Map.Entry<Integer, ItemInfo> entry : registeredItems.entrySet()) {
      Item item = entry.getValue().item;
      switch (entry.getValue().weaponType) {
        case DAGGER -> daggers.add((DaggerItem) item);
        case CLUB -> clubs.add((ClubItem) item);
        case HAMMER -> hammers.add((HammerItem) item);
        case SPEAR -> spears.add((SpearItem) item);
        case QUARTERSTAFF -> quarterstaves.add((QuarterstaffItem) item);
        case GLAIVE -> glaives.add((GlaiveItem) item);
      }
      if (entry.getValue().weaponMaterial.equals(WOOD)) {
        woodenWeapons.add(item);
      } else if (entry.getValue().weaponMaterial.equals(STONE)) {
        stoneWeapons.add(item);
      } else if (entry.getValue().weaponMaterial.equals(IRON)) {
        ironWeapons.add(item);
      } else if (entry.getValue().weaponMaterial.equals(BRONZE)) {
        bronzeWeapons.add(item);
      } else if (entry.getValue().weaponMaterial.equals(GOLD)) {
        goldenWeapons.add(item);
      } else if (entry.getValue().weaponMaterial.equals(DIAMOND)) {
        diamondWeapons.add(item);
      } else if (entry.getValue().weaponMaterial.equals(NETHERITE)) {
        netheriteWeapons.add(item);
      }
    }

    // Item group registration remains the same
    ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(content -> {
      content.addAfter(Items.NETHERITE_AXE, registeredItems.get(0).item);
      for (int i = 1; i < registeredItems.size(); i++) {
        content.addAfter(registeredItems.get(i - 1).item, registeredItems.get(i).item);
      }
    });
  }
}
