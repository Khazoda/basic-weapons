package com.seacroak.basicweapons.registry;

import com.seacroak.basicweapons.Constants;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.minecraft.util.Identifier;

public class ModelLoadingRegistry {

  public static void register() {
    ModelLoadingPlugin.register(pluginContext -> {
      registerWeaponModel(pluginContext, "wooden_spear");
      registerWeaponModel(pluginContext, "stone_spear");
      registerWeaponModel(pluginContext, "iron_spear");
      registerWeaponModel(pluginContext, "golden_spear");
      registerWeaponModel(pluginContext, "diamond_spear");
      registerWeaponModel(pluginContext, "netherite_spear");

      if (MainRegistry.bronze_mod_loaded) {
        registerWeaponModel(pluginContext, "bronze_spear");
      }
    });
  }

  private static void registerWeaponModel(ModelLoadingPlugin.Context pluginContext, String weaponName) {
    Identifier modelId = Identifier.of(Constants.BW_NAMESPACE, weaponName + "_held");
    Identifier modelPath = Identifier.of(Constants.BW_NAMESPACE, "models/item/" + weaponName + "_held.json");
    pluginContext.addModels(modelId);
  }
}