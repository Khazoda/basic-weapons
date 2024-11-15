package com.seacroak.basicweapons.registry;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LootPoolEntry;
import net.minecraft.loot.function.EnchantRandomlyLootFunction;
import net.minecraft.loot.function.SetDamageLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static com.seacroak.basicweapons.registry.MainRegistry.*;
import static net.minecraft.loot.LootTables.*;

public class LootTableModification {
  private static final List<LootPoolEntry> stone_loot_pool = Arrays.asList(
      ItemEntry.builder(stoneWeapons.get(0)).weight(2).build(),
      ItemEntry.builder(stoneWeapons.get(1)).weight(1).build(),
      ItemEntry.builder(stoneWeapons.get(2)).weight(2).build(),
      ItemEntry.builder(stoneWeapons.get(3)).weight(2).build(),
      ItemEntry.builder(stoneWeapons.get(4)).weight(2).build(),
      ItemEntry.builder(stoneWeapons.get(5)).weight(2).build()
  );
  private static final List<LootPoolEntry> iron_loot_pool = Arrays.asList(
      ItemEntry.builder(ironWeapons.get(0)).weight(2).build(),
      ItemEntry.builder(ironWeapons.get(1)).weight(1).build(),
      ItemEntry.builder(ironWeapons.get(2)).weight(2).build(),
      ItemEntry.builder(ironWeapons.get(3)).weight(2).build(),
      ItemEntry.builder(ironWeapons.get(4)).weight(2).build(),
      ItemEntry.builder(ironWeapons.get(5)).weight(2).build()
  );
  private static final List<LootPoolEntry> golden_loot_pool = Arrays.asList(
      ItemEntry.builder(goldenWeapons.get(0)).weight(2).build(),
      ItemEntry.builder(goldenWeapons.get(1)).weight(1).build(),
      ItemEntry.builder(goldenWeapons.get(2)).weight(2).build(),
      ItemEntry.builder(goldenWeapons.get(3)).weight(2).build(),
      ItemEntry.builder(goldenWeapons.get(4)).weight(2).build(),
      ItemEntry.builder(goldenWeapons.get(5)).weight(2).build()
  );
  private static final List<LootPoolEntry> diamond_loot_pool = Arrays.asList(
      ItemEntry.builder(diamondWeapons.get(0)).weight(2).build(),
      ItemEntry.builder(diamondWeapons.get(1)).weight(1).build(),
      ItemEntry.builder(diamondWeapons.get(2)).weight(2).build(),
      ItemEntry.builder(diamondWeapons.get(3)).weight(2).build(),
      ItemEntry.builder(diamondWeapons.get(4)).weight(2).build(),
      ItemEntry.builder(diamondWeapons.get(5)).weight(2).build()
  );

  public static void addItems(List<LootPoolEntry> loot_pool, LootTable.Builder tableBuilder, boolean applyDamage) {
    /* Function adds 0-2 items that are undamaged with no enchantment */
    LootPool.Builder poolBuilder = LootPool.builder()
        .rolls(UniformLootNumberProvider.create(0f, 2f))
        .with(loot_pool);
    if (applyDamage) poolBuilder.apply(SetDamageLootFunction.builder(UniformLootNumberProvider.create(0.7f, 0.95f)))
        .bonusRolls(ConstantLootNumberProvider.create(0.5f));
    tableBuilder.pool(poolBuilder);
  }

  public static void addEnchantedItems(List<LootPoolEntry> loot_pool, LootTable.Builder tableBuilder, boolean applyDamage) {
    /* Function adds 0-2 items that are either enchanted and damaged, or full health with no enchantment */
    Collection<LootPool> lootPools = new ArrayList<>();
    LootPool.Builder enchantedPoolBuilder = LootPool.builder()
        .rolls(UniformLootNumberProvider.create(0f, 1f))
        .with(loot_pool)
        .apply(EnchantRandomlyLootFunction.create());
    if (applyDamage)
      enchantedPoolBuilder.apply(SetDamageLootFunction.builder(UniformLootNumberProvider.create(0.7f, 0.95f)))
          .bonusRolls(ConstantLootNumberProvider.create(0.5f));

    LootPool.Builder normalPoolBuilder = LootPool.builder()
        .rolls(UniformLootNumberProvider.create(0f, 1f))
        .with(loot_pool);
    lootPools.add(enchantedPoolBuilder.build());
    lootPools.add(normalPoolBuilder.build());
    tableBuilder.pools(lootPools);
  }

  public static void init() {
    LootTableEvents.MODIFY.register((key, tableBuilder, source) -> {
      /* Overworld */
      if (source.isBuiltin() && JUNGLE_TEMPLE_CHEST.equals(key)) {
        addItems(stone_loot_pool, tableBuilder, false);
      }
      if (source.isBuiltin() && IGLOO_CHEST_CHEST.equals(key)) {
        addItems(stone_loot_pool, tableBuilder, true);
      }
      if (source.isBuiltin() && SHIPWRECK_SUPPLY_CHEST.equals(key)) {
        addItems(stone_loot_pool, tableBuilder, true);
      }

      if (source.isBuiltin() && SIMPLE_DUNGEON_CHEST.equals(key)) {
        addItems(iron_loot_pool, tableBuilder, false);
      }
      if (source.isBuiltin() && ABANDONED_MINESHAFT_CHEST.equals(key)) {
        addItems(iron_loot_pool, tableBuilder, false);
      }
      if (source.isBuiltin() && DESERT_PYRAMID_CHEST.equals(key)) {
        addItems(iron_loot_pool, tableBuilder, false);
      }
      if (source.isBuiltin() && PILLAGER_OUTPOST_CHEST.equals(key)) {
        addItems(iron_loot_pool, tableBuilder, false);
      }
      if (source.isBuiltin() && BURIED_TREASURE_CHEST.equals(key)) {
        addItems(iron_loot_pool, tableBuilder, false);
      }
      if (source.isBuiltin() && SHIPWRECK_TREASURE_CHEST.equals(key)) {
        addItems(iron_loot_pool, tableBuilder, false);
      }
      if (source.isBuiltin() && STRONGHOLD_CORRIDOR_CHEST.equals(key)) {
        addItems(iron_loot_pool, tableBuilder, false);
      }
      if (source.isBuiltin() && VILLAGE_WEAPONSMITH_CHEST.equals(key)) {
        addItems(iron_loot_pool, tableBuilder, false);
      }
      /* Nether */
      if (source.isBuiltin() && BASTION_TREASURE_CHEST.equals(key)) {
        addEnchantedItems(diamond_loot_pool, tableBuilder, true);
      }
      if (source.isBuiltin() && BASTION_OTHER_CHEST.equals(key)) {
        addEnchantedItems(golden_loot_pool, tableBuilder, true);
      }
      if (source.isBuiltin() && BASTION_BRIDGE_CHEST.equals(key)) {
        addItems(golden_loot_pool, tableBuilder, false);
      }
      if (source.isBuiltin() && BASTION_HOGLIN_STABLE_CHEST.equals(key)) {
        addEnchantedItems(golden_loot_pool, tableBuilder, true);
      }
      if (source.isBuiltin() && RUINED_PORTAL_CHEST.equals(key)) {
        addEnchantedItems(golden_loot_pool, tableBuilder, false);
      }
      /* End */
      if (source.isBuiltin() && END_CITY_TREASURE_CHEST.equals(key)) {
        addEnchantedItems(diamond_loot_pool, tableBuilder, false);
      }
    });
  }
}
