package com.seacroak.basicweapons;

import com.seacroak.basicweapons.registry.MainRegistry;
import net.fabricmc.api.ModInitializer;

import static com.seacroak.basicweapons.Constants.BW_LOG;

public class BasicWeapons implements ModInitializer {

  @Override
  public void onInitialize() {
    MainRegistry.init();
    BW_LOG.info("[Basic Weapons] Weapons Loaded");
  }
}