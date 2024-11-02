package com.seacroak.basicweapons;

import com.seacroak.basicweapons.registry.ModelLoadingRegistry;
import net.fabricmc.api.ClientModInitializer;

public class BasicWeaponsClient implements ClientModInitializer {
  @Override
  public void onInitializeClient() {
    ModelLoadingRegistry.register();
  }
}