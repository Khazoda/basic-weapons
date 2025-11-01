package com.khazoda.basicweapons;

import com.khazoda.basicweapons.materialpack.MaterialPackLoader;
import com.khazoda.basicweapons.platform.Services;
import com.khazoda.basicweapons.registry.EnchantmentRegistry;
import com.khazoda.basicweapons.registry.TabRegistry;
import com.khazoda.basicweapons.registry.WeaponRegistry;
import com.khazoda.basicweapons.registry.helper.Reggie;
import com.khazoda.basicweapons.registry.helper.Reginald;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;

public class BasicWeaponsCommon {
  // Other Mods
  public final static boolean bettercombat_mod_loaded = Services.PLATFORM.isModLoaded("bettercombat");
  public final static boolean bronze_mod_loaded = Services.PLATFORM.isModLoaded("bronze");

  public static final Reginald REGISTRARS = new Reginald();
  public static final Reggie<Item> ITEM_REGISTRAR = REGISTRARS.get(Registries.ITEM);

  public static void init() {
    MaterialPackLoader.loadPacks();

    WeaponRegistry.init();
    EnchantmentRegistry.init();
    TabRegistry.init();

    if(!Services.PLATFORM.registerFurnaceFuels()) {
      Constants.LOG.info("Wooden weapons not registered correctly as furnace fuels. Please report this on the GitHub repository.");
    }

    if (Services.PLATFORM.isModLoaded("basicweapons"))
      Constants.LOG.info("- Basic Weapons Loaded -");
  }

  public static void postInit() {
  }
}