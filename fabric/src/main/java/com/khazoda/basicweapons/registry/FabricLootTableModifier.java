package com.khazoda.basicweapons.registry;

import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.fabricmc.fabric.api.loot.v3.LootTableSource;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootTable;

public class FabricLootTableModifier {
  public static void init() {
    LootTableEvents.MODIFY.register((ResourceKey<LootTable> key, LootTable.Builder tableBuilder, LootTableSource source, HolderLookup.Provider registries) -> {
      if (!source.isBuiltin()) return;
      LootTableModifier.modifyLootTable(key.location(), tableBuilder);
    });
  }
} 