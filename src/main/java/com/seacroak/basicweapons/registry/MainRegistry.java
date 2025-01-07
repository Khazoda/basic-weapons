package com.seacroak.basicweapons.registry;

import com.seacroak.basicweapons.data.WeaponType;
import com.seacroak.basicweapons.material.ExternalToolMaterials;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;

public class MainRegistry {
    public final static boolean bronze_mod_loaded = FabricLoader.getInstance().isModLoaded("bronze");
    public final static boolean bettercombat_mod_loaded = FabricLoader.getInstance().isModLoaded("bettercombat");

    public static void init() {
        // Initialize base weapons
        WeaponRegistry.init();

        // Register bronze weapons if mod is present
        if (bronze_mod_loaded) {
            WeaponRegistry.registerMaterialVariants(
                new WeaponRegistry.MaterialEntry(ExternalToolMaterials.BRONZE, "bronze")
            );
        }

        // Register weapons to combat item group
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(content -> {
            Item lastItem = Items.NETHERITE_AXE;
            for (WeaponType type : WeaponType.values()) {
                for (Item item : WeaponRegistry.getItemsByType(type)) {
                    content.addAfter(lastItem, item);
                    lastItem = item;
                }
            }
        });

        // Initialize loot tables and enchantments
        LootTableModification.init();
        EnchantmentRegistry.init();
    }
}
