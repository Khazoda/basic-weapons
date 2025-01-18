package com.khazoda.basicweapons.registry;

import com.khazoda.basicweapons.BasicWeaponsCommon;
import com.khazoda.basicweapons.material.ConditionalToolMaterials;
import com.khazoda.basicweapons.registry.helper.Reggie;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;

import static com.khazoda.basicweapons.BasicWeaponsCommon.bronze_mod_loaded;

public class MainRegistry {

  public static final Reggie<Item> ITEM_REGISTRAR = BasicWeaponsCommon.REGISTRARS.get(Registries.ITEM);

  public static void init() {
    WeaponRegistry.init();
    if (bronze_mod_loaded) {
      WeaponRegistry.registerMaterialVariants(
          new WeaponRegistry.MaterialEntry(ConditionalToolMaterials.BRONZE, "bronze")
      );
    }
    EnchantmentRegistry.init();
  }
}
