package com.khazoda.basicweapons.registry;

import com.khazoda.basicweapons.materialpack.MaterialPackLoader;
import com.khazoda.basicweapons.struct.WeaponType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;

import static com.khazoda.basicweapons.registry.MainRegistry.ITEM_REGISTRAR;
import static com.khazoda.basicweapons.struct.WeaponType.*;

public class WeaponRegistry {
  private static final Map<String, Supplier<Item>> ITEMS = new LinkedHashMap<>();
  private static final Map<WeaponType, List<Supplier<Item>>> ITEMS_BY_TYPE = new EnumMap<>(WeaponType.class);
  private static final Map<Tier, List<Supplier<Item>>> ITEMS_BY_MATERIAL = new HashMap<>();

  private static final Map<String, List<Item>> DATAPACK_WEAPONS = new HashMap<>();

  public static final List<MaterialEntry> VANILLA_MATERIALS = Arrays.asList(
      new MaterialEntry(Tiers.WOOD, "wooden"),
      new MaterialEntry(Tiers.STONE, "stone"),
      new MaterialEntry(Tiers.IRON, "iron"),
      new MaterialEntry(Tiers.GOLD, "golden"),
      new MaterialEntry(Tiers.DIAMOND, "diamond"),
      new MaterialEntry(Tiers.NETHERITE, "netherite", Item.Properties::fireResistant)
  );

  public static void init() {
    for (MaterialEntry material : VANILLA_MATERIALS) {
      registerMaterialVariants(material);
    }
  }

  public static void registerMaterialVariants(MaterialEntry material) {
    for (WeaponType type : WeaponType.values()) {
      String itemId = material.prefix() + "_" + type.getId();
      Item.Properties itemSettings = material.settingsModifier().apply(new Item.Properties());

      float damageModifier = getDamageModifier(type, material.material());
      float speedModifier = getSpeedModifier(type, material.material());
      float reachModifier = getReachModifier(type, material.material());

      Supplier<Item> itemSupplier = ITEM_REGISTRAR.register(itemId, () -> type.create(material.material, damageModifier, speedModifier, reachModifier, itemSettings));

      ITEMS.put(itemId, itemSupplier);
      ITEMS_BY_TYPE.computeIfAbsent(type, k -> new ArrayList<>()).add(itemSupplier);
      ITEMS_BY_MATERIAL.computeIfAbsent(material.material(), k -> new ArrayList<>()).add(itemSupplier);
    }
  }

  public static void registerWeaponsForMaterial(String materialName) {
    Tier material = MaterialPackLoader.getMaterial(materialName);
    registerMaterialVariants(new MaterialEntry(material, materialName));
  }

  public static Item getItem(String id) {
    Supplier<Item> supplier = ITEMS.get(id);
    return supplier != null ? supplier.get() : null;
  }

  public static List<Item> getItemsByType(WeaponType type) {
    return ITEMS_BY_TYPE.getOrDefault(type, Collections.emptyList()).stream()
        .map(Supplier::get)
        .filter(Objects::nonNull)
        .toList();
  }

  public static List<Item> getItemsByMaterial(Tier material) {
    return ITEMS_BY_MATERIAL.getOrDefault(material, Collections.emptyList()).stream()
        .map(Supplier::get)
        .filter(Objects::nonNull)
        .toList();
  }

  public static Collection<Item> getAllItems() {
    return ITEMS.values().stream().map(supplier -> {
      try {
        return supplier.get();
      } catch (Exception e) {
        return null;
      }
    }).filter(Objects::nonNull).toList();
  }

  /**
   * Record for defining a material variant with its properties
   */
  public record MaterialEntry(Tier material, String prefix,
                              Function<Item.Properties, Item.Properties> settingsModifier) {
    public MaterialEntry(Tier material, String prefix) {
      this(material, prefix, settings -> settings);
    }
  }
}
