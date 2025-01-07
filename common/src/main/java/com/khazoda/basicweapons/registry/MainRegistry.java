package com.khazoda.basicweapons.registry;

import com.khazoda.basicweapons.BasicWeaponsCommon;
import com.khazoda.basicweapons.material.ExternalToolMaterials;
import com.khazoda.basicweapons.platform.Services;
import com.khazoda.basicweapons.registry.helper.Reggie;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;

public class MainRegistry {
  public final static boolean bronze_mod_loaded = Services.PLATFORM.isModLoaded("bronze");
  public final static boolean bettercombat_mod_loaded = Services.PLATFORM.isModLoaded("bettercombat");

  public static final Reggie<Item> ITEM_REGISTRAR = BasicWeaponsCommon.REGISTRARS.get(Registries.ITEM);

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

//    ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(content -> {
//      Item lastItem = Items.NETHERITE_AXE;
//      for (WeaponType type : WeaponType.values()) {
//        for (Item item : WeaponRegistry.getItemsByType(type)) {
//          content.addAfter(lastItem, item);
//          lastItem = item;
//        }
//      }
//    });

    //    LootTableModification.init();
    EnchantmentRegistry.init();
  }
}
