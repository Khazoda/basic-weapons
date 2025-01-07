package com.khazoda.basicweapons;

import com.khazoda.basicweapons.platform.Services;
import com.khazoda.basicweapons.registry.MainRegistry;
import com.khazoda.basicweapons.registry.helper.Reginald;

public class BasicWeaponsCommon {
  public final static boolean bronze_mod_loaded = Services.PLATFORM.isModLoaded("bronze");
  public final static boolean bettercombat_mod_loaded = Services.PLATFORM.isModLoaded("bettercombat");

  public static final Reginald REGISTRARS = new Reginald();

  public static void init() {
    MainRegistry.init();
    if (Services.PLATFORM.isModLoaded("basicweapons")) Constants.LOG.info("- Basic Weapons Loaded -");
  }

  public static void postInit() {

  }
}