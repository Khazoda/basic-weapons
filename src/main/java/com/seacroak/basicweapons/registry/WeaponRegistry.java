package com.seacroak.basicweapons.registry;

import com.seacroak.basicweapons.data.WeaponType;
import com.seacroak.basicweapons.util.Reggie;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;

import java.util.*;
import java.util.function.Function;

public class WeaponRegistry {
    private static final Map<String, Item> ITEMS = new LinkedHashMap<>();
    private static final Map<WeaponType, List<Item>> ITEMS_BY_TYPE = new EnumMap<>(WeaponType.class);
    private static final Map<ToolMaterial, List<Item>> ITEMS_BY_MATERIAL = new HashMap<>();

    // Easy reference fields for common materials
    private static final List<MaterialEntry> VANILLA_MATERIALS = Arrays.asList(
        new MaterialEntry(ToolMaterial.WOOD, "wooden"),
        new MaterialEntry(ToolMaterial.STONE, "stone"),
        new MaterialEntry(ToolMaterial.IRON, "iron"),
        new MaterialEntry(ToolMaterial.GOLD, "golden"),
        new MaterialEntry(ToolMaterial.DIAMOND, "diamond"),
        new MaterialEntry(ToolMaterial.NETHERITE, "netherite", Item.Settings::fireproof)
    );

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
            Item.Settings itemSettings = material.settingsModifier().apply(new Item.Settings());

            // Get any special stat modifications for this material/type combination
            float damageModifier = getDamageModifier(type, material.material());
            float speedModifier = getSpeedModifier(type, material.material());

            // Create and register the weapon
            Item item = Reggie.register(itemId,
                settings -> type.create(material.material(), damageModifier, speedModifier, settings),
                itemSettings
            );

            // Store the item in lookup maps
            ITEMS.put(itemId, item);
            ITEMS_BY_TYPE.computeIfAbsent(type, k -> new ArrayList<>()).add(item);
            ITEMS_BY_MATERIAL.computeIfAbsent(material.material(), k -> new ArrayList<>()).add(item);
        }
    }

    /**
     * Gets any special damage modifications for specific material/type combinations (e.g. Hammer)
     */
    private static float getDamageModifier(WeaponType type, ToolMaterial material) {
        if (type == WeaponType.DAGGER && material == ToolMaterial.GOLD) return -1;
        if (type == WeaponType.HAMMER) {
            if (material == ToolMaterial.WOOD) return -6;
            if (material == ToolMaterial.STONE) return -3;
            if (material == ToolMaterial.GOLD) return -6;
            if (material == ToolMaterial.DIAMOND) return -1;
            if (material == ToolMaterial.NETHERITE) return -1;
        }
        return 0;
    }

    /**
     * Gets any special speed modifications for specific material/type combinations (e.g. Hammer)
     */
    private static float getSpeedModifier(WeaponType type, ToolMaterial material) {
        if (type == WeaponType.DAGGER && material == ToolMaterial.GOLD) return 1;
        if (type == WeaponType.HAMMER) {
            if (material == ToolMaterial.WOOD) return 0.4f;
            if (material == ToolMaterial.STONE) return 0.2f;
            if (material == ToolMaterial.GOLD) return 0.6f;
            if (material == ToolMaterial.DIAMOND) return 0.1f;
            if (material == ToolMaterial.NETHERITE) return 0.2f;
        }
        return 0;
    }

    public static Item getItem(String id) {
        return ITEMS.get(id);
    }

    public static List<Item> getItemsByType(WeaponType type) {
        return ITEMS_BY_TYPE.getOrDefault(type, Collections.emptyList());
    }

    public static List<Item> getItemsByMaterial(ToolMaterial material) {
        return ITEMS_BY_MATERIAL.getOrDefault(material, Collections.emptyList());
    }

    public static Collection<Item> getAllItems() {
        return ITEMS.values();
    }

    /**
     * Record for defining a material variant with its properties
     */
    public record MaterialEntry(ToolMaterial material, String prefix,
                                Function<Item.Settings, Item.Settings> settingsModifier) {
        MaterialEntry(ToolMaterial material, String prefix) {
            this(material, prefix, settings -> settings);
        }
    }
}
