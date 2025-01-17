package com.khazoda.basicweapons;

import com.khazoda.basicweapons.materialpack.MaterialPackLoader;
import com.khazoda.basicweapons.platform.Services;
import com.khazoda.basicweapons.registry.MainRegistry;
import com.khazoda.basicweapons.registry.helper.Reginald;

public class BasicWeaponsCommon {
  // Other Mods
  public final static boolean bettercombat_mod_loaded = Services.PLATFORM.isModLoaded("bettercombat");
  public final static boolean bronze_mod_loaded = Services.PLATFORM.isModLoaded("bronze");

  // Global Datapack Loader Mods
  public final static boolean openloader_mod_loaded = Services.PLATFORM.isModLoaded("openloader");
  public final static boolean globalpacks_mod_loaded = Services.PLATFORM.isModLoaded("globalpacks");
  public final static boolean hasGlobalDatapackLoader = openloader_mod_loaded || globalpacks_mod_loaded;

  public static final Reginald REGISTRARS = new Reginald();

  public static void init() {
    MaterialPackLoader.loadPacks();
        
    MainRegistry.init();
    if (Services.PLATFORM.isModLoaded("basicweapons"))
      Constants.LOG.info("- Basic Weapons Loaded -");
  }

  public static void postInit() {
  }
}