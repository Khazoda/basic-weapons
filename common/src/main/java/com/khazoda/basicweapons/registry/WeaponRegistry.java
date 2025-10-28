package com.khazoda.basicweapons.registry;

import com.khazoda.basicweapons.material.ConditionalToolMaterials;
import com.khazoda.basicweapons.materialpack.MaterialPackLoader;
import com.khazoda.basicweapons.struct.WeaponType;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;

import static com.khazoda.basicweapons.BasicWeaponsCommon.ITEM_REGISTRAR;
import static com.khazoda.basicweapons.BasicWeaponsCommon.bronze_mod_loaded;
import static com.khazoda.basicweapons.Constants.ID;
import static com.khazoda.basicweapons.struct.WeaponType.*;

public class WeaponRegistry {
  private static final Map<String, Supplier<Item>> ITEMS = new LinkedHashMap<>();
  private static final Map<WeaponType, List<Supplier<Item>>> ITEMS_BY_TYPE = new EnumMap<>(WeaponType.class);
  private static final Map<ToolMaterial, List<Supplier<Item>>> ITEMS_BY_MATERIAL = new HashMap<>();

  public static final List<MaterialEntry> VANILLA_MATERIALS = Arrays.asList(
      new MaterialEntry(ToolMaterial.WOOD, "wooden"),
      new MaterialEntry(ToolMaterial.STONE, "stone"),
      new MaterialEntry(ToolMaterial.COPPER, "copper"),
      new MaterialEntry(ToolMaterial.IRON, "iron"),
      new MaterialEntry(ToolMaterial.GOLD, "golden"),
      new MaterialEntry(ToolMaterial.DIAMOND, "diamond"),
      new MaterialEntry(ToolMaterial.NETHERITE, "netherite", Item.Properties::fireResistant)
  );
  public static final MaterialEntry BRONZE_MATERIAL_ENTRY = new MaterialEntry(ConditionalToolMaterials.BRONZE, "bronze");

  public static void init() {
    for (MaterialEntry material : VANILLA_MATERIALS) {
      registerAllWeaponsForMaterial(material);
    }
    if (bronze_mod_loaded) registerAllWeaponsForMaterial(BRONZE_MATERIAL_ENTRY);

  }

  /* Register weapons from MaterialEntry */
  public static void registerAllWeaponsForMaterial(MaterialEntry material) {
    for (WeaponType type : WeaponType.values()) {
      String itemId = material.prefix() + "_" + type.getId();
      Item.Properties itemSettings = material.settingsModifier().apply(new Item.Properties());

      float damageModifier = getDamageModifier(type, material.material());
      float speedModifier = getSpeedModifier(type, material.material());
      float reachModifier = getReachModifier(type, material.material());

      ResourceKey<Item> resourceKey = ResourceKey.create(Registries.ITEM, ID(itemId));
      Item.Properties finalProperties = itemSettings.setId(resourceKey);

      Supplier<Item> itemSupplier = ITEM_REGISTRAR.register(itemId, () ->
          type.create(material.material, damageModifier, speedModifier, reachModifier, finalProperties)
      );

      ITEMS.put(itemId, itemSupplier);
      ITEMS_BY_TYPE.computeIfAbsent(type, k -> new ArrayList<>()).add(itemSupplier);
      ITEMS_BY_MATERIAL.computeIfAbsent(material.material(), k -> new ArrayList<>()).add(itemSupplier);
    }
  }

  /* Register weapons from string of material name (used for material packs) */
  public static void registerAllWeaponsForMaterial(String materialName) {
    ToolMaterial material = MaterialPackLoader.getMaterial(materialName);
    registerAllWeaponsForMaterial(new MaterialEntry(material, materialName));
  }

  public static List<Item> getItemsByType(WeaponType type) {
    return ITEMS_BY_TYPE.getOrDefault(type, Collections.emptyList()).stream()
        .map(Supplier::get)
        .filter(Objects::nonNull)
        .toList();
  }

  public static List<Item> getItemsByMaterial(ToolMaterial material) {
    return ITEMS_BY_MATERIAL.getOrDefault(material, Collections.emptyList()).stream()
        .map(Supplier::get)
        .filter(Objects::nonNull)
        .toList();
  }

  /**
   * Record for defining a material variant with its properties
   */
  public record MaterialEntry(ToolMaterial material, String prefix,
                              Function<Item.Properties, Item.Properties> settingsModifier) {
    public MaterialEntry(ToolMaterial material, String prefix) {
      this(material, prefix, settings -> settings);
    }
  }
}
