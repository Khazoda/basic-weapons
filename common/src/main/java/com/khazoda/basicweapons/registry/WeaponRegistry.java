package com.khazoda.basicweapons.registry;

import com.khazoda.basicweapons.data.WeaponType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;

import static com.khazoda.basicweapons.registry.MainRegistry.ITEM_REGISTRAR;

public class WeaponRegistry {
  private static final Map<String, Supplier<Item>> ITEMS = new LinkedHashMap<>();
  private static final Map<WeaponType, List<Supplier<Item>>> ITEMS_BY_TYPE = new EnumMap<>(WeaponType.class);
  private static final Map<Tier, List<Supplier<Item>>> ITEMS_BY_MATERIAL = new HashMap<>();

  // Easy reference fields for common materials
  private static final List<MaterialEntry> VANILLA_MATERIALS = Arrays.asList(new MaterialEntry(Tiers.WOOD, "wooden"), new MaterialEntry(Tiers.STONE, "stone"), new MaterialEntry(Tiers.IRON, "iron"), new MaterialEntry(Tiers.GOLD, "golden"), new MaterialEntry(Tiers.DIAMOND, "diamond"), new MaterialEntry(Tiers.NETHERITE, "netherite", Item.Properties::fireResistant));

  public static void init() {
    // Register vanilla weapons
    for (MaterialEntry material : VANILLA_MATERIALS) {
      registerMaterialVariants(material);
    }
  }

  /**
   * Registers all weapon variants for a given material
   *
   * @param material The material entry containing the material type and naming information
   */
  public static void registerMaterialVariants(MaterialEntry material) {
    for (WeaponType type : WeaponType.values()) {
      String itemId = material.prefix() + "_" + type.getId();
      Item.Properties itemSettings = material.settingsModifier().apply(new Item.Properties());

      // Get any special stat modifications for this material/type combination
      float damageModifier = getDamageModifier(type, material.material());
      float speedModifier = getSpeedModifier(type, material.material());

      // Store the supplier instead of getting the item immediately
      Supplier<Item> itemSupplier = ITEM_REGISTRAR.register(itemId, () -> {
        return type.create(material.material(), damageModifier, speedModifier, itemSettings);
      });

      // Store the supplier in lookup maps
      ITEMS.put(itemId, itemSupplier);
      ITEMS_BY_TYPE.computeIfAbsent(type, k -> new ArrayList<>()).add(itemSupplier);
      ITEMS_BY_MATERIAL.computeIfAbsent(material.material(), k -> new ArrayList<>()).add(itemSupplier);
    }
  }

  /**
   * Gets any special damage modifications for specific material/type combinations (e.g. Hammer)
   */
  private static float getDamageModifier(WeaponType type, Tier material) {
    if (type == WeaponType.DAGGER && material == Tiers.GOLD) return -1;
    if (type == WeaponType.HAMMER) {
      if (material == Tiers.WOOD) return -6;
      if (material == Tiers.STONE) return -3;
      if (material == Tiers.GOLD) return -6;
      if (material == Tiers.DIAMOND) return -1;
      if (material == Tiers.NETHERITE) return -1;
    }
    return 0;
  }

  /**
   * Gets any special speed modifications for specific material/type combinations (e.g. Hammer)
   */
  private static float getSpeedModifier(WeaponType type, Tier material) {
    if (type == WeaponType.DAGGER && material == Tiers.GOLD) return 1;
    if (type == WeaponType.HAMMER) {
      if (material == Tiers.WOOD) return 0.4f;
      if (material == Tiers.STONE) return 0.2f;
      if (material == Tiers.GOLD) return 0.6f;
      if (material == Tiers.DIAMOND) return 0.1f;
      if (material == Tiers.NETHERITE) return 0.2f;
    }
    return 0;
  }

  public static Item getItem(String id) {
    Supplier<Item> supplier = ITEMS.get(id);
    return supplier != null ? supplier.get() : null;
  }

  public static List<Item> getItemsByType(WeaponType type) {
    List<Item> items = ITEMS_BY_TYPE.getOrDefault(type, Collections.emptyList())
        .stream()
        .map(supplier -> {
          try {
            Item item = supplier.get();
            if (item != null) {
            }
            return item;
          } catch (Exception e) {
            return null;
          }
        })
        .filter(Objects::nonNull)
        .toList();
    return items;
  }

  public static List<Item> getItemsByMaterial(Tier material) {
    return ITEMS_BY_MATERIAL.getOrDefault(material, Collections.emptyList()).stream().map(supplier -> {
      try {
        return supplier.get();
      } catch (Exception e) {
        return null;
      }
    }).filter(Objects::nonNull).toList();
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
    MaterialEntry(Tier material, String prefix) {
      this(material, prefix, settings -> settings);
    }
  }
}
