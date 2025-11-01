package com.khazoda.basicweapons;

import com.khazoda.basicweapons.fabric.FabricEventManager;
import com.khazoda.basicweapons.registry.FabricLootTableModifier;
import net.fabricmc.api.ModInitializer;

public class BasicWeaponsFabric implements ModInitializer {
  @Override
  public void onInitialize() {
    BasicWeaponsCommon.init();
    BasicWeaponsCommon.postInit();
    BasicWeaponsCommon.REGISTRARS.registerAll();

    FabricEventManager.init();
    FabricLootTableModifier.init();
  }
}
