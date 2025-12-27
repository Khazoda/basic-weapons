package com.khazoda.basicweapons.registry;

import com.khazoda.basicweapons.material.ConditionalToolMaterials;
import com.khazoda.basicweapons.materialpack.MaterialPackLoader;
import com.khazoda.basicweapons.struct.WeaponType;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ToolMaterial;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;

import static com.khazoda.basicweapons.BasicWeaponsCommon.ITEM_REGISTRAR;
import static com.khazoda.basicweapons.BasicWeaponsCommon.bronze_mod_loaded;
import static com.khazoda.basicweapons.Constants.ID;
import static com.khazoda.basicweapons.struct.WeaponType.BasicWeaponType;
import static com.khazoda.basicweapons.struct.WeaponType.WeaponTypeInterface;

public class WeaponRegistry {
  private static final Map<String, Supplier<Item>> ITEMS = new LinkedHashMap<>();
  private static final Map<WeaponType.WeaponTypeInterface, List<Supplier<Item>>> ALL_ITEMS_BY_TYPE = new HashMap<>();
  private static final Map<WeaponType.WeaponTypeInterface, List<Supplier<Item>>> BUILTIN_ITEMS_BY_TYPE = new HashMap<>();
  private static final Map<WeaponType.WeaponTypeInterface, List<Supplier<Item>>> MATERIALPACK_ITEMS_BY_TYPE = new HashMap<>();

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
  public static final List<MaterialEntry> COMPAT_MATERIALS = Arrays.asList(
      new MaterialEntry(ConditionalToolMaterials.BRONZE, "bronze"),
      new MaterialEntry(ConditionalToolMaterials.TIN, "tin")
  );

  public static void init() {
    for (MaterialEntry material : VANILLA_MATERIALS) {
      registerAllWeaponsForMaterial(material);
    }
    if (bronze_mod_loaded) { //TODO: Make sure you comment this when running DATAGEN
      for (MaterialEntry material : COMPAT_MATERIALS) {
        registerAllWeaponsForMaterial(material);
      }
    }
  }

  public static void registerWeaponForMaterial(WeaponTypeInterface type, MaterialEntry material) {
    String itemId = material.prefix() + "_" + type.getId();
    Item.Properties itemSettings = material.settingsModifier().apply(new Item.Properties());

    float damageModifier = WeaponType.getDamageModifier(type, material.material());
    float speedModifier = WeaponType.getSpeedModifier(type, material.material());
    float reachModifier = WeaponType.getReachModifier(type, material.material());

    ResourceKey<Item> resourceKey = ResourceKey.create(Registries.ITEM, ID(itemId));
    Item.Properties finalProperties = itemSettings.setId(resourceKey);

    Supplier<Item> itemSupplier = ITEM_REGISTRAR.register(itemId, () ->
        type.create(material.material, damageModifier, speedModifier, reachModifier, finalProperties)
    );

    ITEMS.put(itemId, itemSupplier);

    /* Needed for proper creative tab sorting of entries */
    if (VANILLA_MATERIALS.contains(material) || COMPAT_MATERIALS.contains(material)) {
      BUILTIN_ITEMS_BY_TYPE.computeIfAbsent(type, k -> new ArrayList<>()).add(itemSupplier);
    } else {
      MATERIALPACK_ITEMS_BY_TYPE.computeIfAbsent(type, k -> new ArrayList<>()).add(itemSupplier);
    }
    ALL_ITEMS_BY_TYPE.computeIfAbsent(type, k -> new ArrayList<>()).add(itemSupplier);
    ITEMS_BY_MATERIAL.computeIfAbsent(material.material(), k -> new ArrayList<>()).add(itemSupplier);
  }

  /* Register all weapons from a MaterialEntry */
  public static void registerAllWeaponsForMaterial(MaterialEntry material) {
    for (BasicWeaponType type : WeaponType.BasicWeaponType.values()) {
      registerWeaponForMaterial(type, material);
    }
  }

  /* Register weapons from string of material name (used for material packs) */
  public static void registerAllWeaponsForMaterialPackMaterial(String materialName) {
    ToolMaterial material = MaterialPackLoader.getMaterial(materialName);
    MaterialEntry materialEntry = new MaterialEntry(material, materialName);

    // Always register BasicWeaponType weapons (dagger, hammer, club, etc.) for material packs
    registerAllWeaponsForMaterial(materialEntry);

    // Only register VanillaWeaponType weapons (sword, axe) if their textures exist in the material pack
    for (WeaponType.VanillaWeaponType type : WeaponType.VanillaWeaponType.values()) {
      if (MaterialPackLoader.hasTextureForWeaponType(materialName, type.getId())) {
        registerWeaponForMaterial(type, materialEntry);
      }
    }
  }


  /* ITEMS_BY_TYPE retrieval options for tab registry */
  public enum ITEMS_BY_TYPE {
    ALL,
    BUILTIN,
    MATERIALPACK
  }

  public static List<Item> getItemsByType(ITEMS_BY_TYPE selection, WeaponType.WeaponTypeInterface type) {
    switch (selection) {
      case BUILTIN -> {
        return BUILTIN_ITEMS_BY_TYPE.getOrDefault(type, Collections.emptyList()).stream()
            .map(Supplier::get)
            .filter(Objects::nonNull)
            .toList();
      }
      case MATERIALPACK -> {
        return MATERIALPACK_ITEMS_BY_TYPE.getOrDefault(type, Collections.emptyList()).stream()
            .map(Supplier::get)
            .filter(Objects::nonNull)
            .toList();
      }
      default -> {
        return ALL_ITEMS_BY_TYPE.getOrDefault(type, Collections.emptyList()).stream()
            .map(Supplier::get)
            .filter(Objects::nonNull)
            .toList();
      }
    }
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
