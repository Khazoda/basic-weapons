package com.khazoda.basicweapons.datagen.advancements;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class BasicWeaponsAdvancementsProvider extends FabricAdvancementProvider {
  public BasicWeaponsAdvancementsProvider(FabricDataOutput dataGenerator, CompletableFuture<HolderLookup.Provider> registryLookup) {
    super(dataGenerator, registryLookup);
  }

  @Override
  public void generateAdvancement(HolderLookup.Provider provider, Consumer<AdvancementHolder> consumer) {
    new BasicWeaponsAdvancements().accept(provider.asGetterLookup(), consumer);
  }
}