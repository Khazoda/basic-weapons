package com.seacroak.basicweapons;

import com.seacroak.basicweapons.registry.BWItems;
import com.seacroak.basicweapons.registry.LootTableModification;
import com.seacroak.basicweapons.registry.MainRegistry;
import net.fabricmc.api.ModInitializer;

import static com.seacroak.basicweapons.Constants.BW_LOG;

public class BasicWeapons implements ModInitializer {

  @Override
  public void onInitialize() {
    BWItems.init();
    MainRegistry.init();
    LootTableModification.init();
    BW_LOG.info("Hello Fabric world!");
  }
}