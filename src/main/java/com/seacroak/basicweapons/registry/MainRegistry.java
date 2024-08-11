package com.seacroak.basicweapons.registry;

import com.seacroak.basicweapons.Constants;
import com.seacroak.basicweapons.item.*;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MainRegistry {
  public final static boolean bronze_mod_loaded = FabricLoader.getInstance().isModLoaded("bronze");

  public final static Map<Integer, RegisteredItemInfo> registeredItems = new HashMap<>();
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
    for (BWItems.UnregisteredItemInfo item : BWItems.items
    ) {
      registeredItems.put(x, new RegisteredItemInfo(item.weaponType, item.weaponMaterial, item.name, registerItem(item.name, item.itemSupplier.get())));
      x += 1;
    }

    for (Map.Entry<Integer, RegisteredItemInfo> item : registeredItems.entrySet()) {
      switch (item.getValue().weaponType) {
        case DAGGER -> daggers.add((DaggerItem) item.getValue().item);
        case CLUB -> clubs.add((ClubItem) item.getValue().item);
        case HAMMER -> hammers.add((HammerItem) item.getValue().item);
        case SPEAR -> spears.add((SpearItem) item.getValue().item);
        case QUARTERSTAFF -> quarterstaves.add((QuarterstaffItem) item.getValue().item);
        case GLAIVE -> glaives.add((GlaiveItem) item.getValue().item);
        default -> {
          break;
        }
      }
      switch (item.getValue().weaponMaterial) {
        case WOOD -> woodenWeapons.add(item.getValue().item);
        case STONE -> stoneWeapons.add(item.getValue().item);
        case IRON -> ironWeapons.add(item.getValue().item);
        case BRONZE -> bronzeWeapons.add(item.getValue().item);
        case GOLD -> goldenWeapons.add(item.getValue().item);
        case DIAMOND -> diamondWeapons.add(item.getValue().item);
        case NETHERITE -> netheriteWeapons.add(item.getValue().item);
        default -> {
          break;
        }
      }
    }

    ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(content -> {
      content.addAfter(Items.NETHERITE_AXE, registeredItems.get(0).item);
      for (int i = 1; i < registeredItems.size(); i++) {
        content.addAfter(registeredItems.get(i - 1).item, registeredItems.get(i).item);
      }
    });
  }

  public static Item registerItem(String name, Item item) {
    return Registry.register(Registries.ITEM, Identifier.of(Constants.BW_NAMESPACE, name), item);
  }

  public static class RegisteredItemInfo {
    public final BWItems.WEAPON_TYPE weaponType;
    public final BWItems.WEAPON_MATERIAL weaponMaterial;
    public final String name;
    public final Item item;

    public RegisteredItemInfo(BWItems.WEAPON_TYPE weaponType, BWItems.WEAPON_MATERIAL weaponMaterial, String name, Item item) {
      this.weaponType = weaponType;
      this.weaponMaterial = weaponMaterial;
      this.name = name;
      this.item = item;
    }
  }

}
