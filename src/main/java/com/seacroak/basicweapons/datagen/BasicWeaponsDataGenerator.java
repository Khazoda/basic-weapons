package com.seacroak.basicweapons.datagen;

import com.seacroak.basicweapons.datagen.advancements.BasicWeaponsAdvancementsProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class BasicWeaponsDataGenerator implements DataGeneratorEntrypoint {
  @Override
  public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
    FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

    pack.addProvider(BasicWeaponsAdvancementsProvider::new);
  }
}
