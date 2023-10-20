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

import static com.seacroak.basicweapons.registry.MainRegistry.registeredItems;
import static net.minecraft.loot.LootTables.*;

public class LootTableModification {

  private static final List<LootPoolEntry> stone_loot_pool = Arrays.asList(
      ItemEntry.builder(registeredItems.get(1)).weight(2).build(),
      ItemEntry.builder(registeredItems.get(7)).weight(1).build(),
      ItemEntry.builder(registeredItems.get(13)).weight(2).build(),
      ItemEntry.builder(registeredItems.get(19)).weight(2).build(),
      ItemEntry.builder(registeredItems.get(25)).weight(2).build()
  );
  private static final List<LootPoolEntry> iron_loot_pool = Arrays.asList(
      ItemEntry.builder(registeredItems.get(2)).weight(2).build(),
      ItemEntry.builder(registeredItems.get(8)).weight(1).build(),
      ItemEntry.builder(registeredItems.get(14)).weight(2).build(),
      ItemEntry.builder(registeredItems.get(20)).weight(2).build(),
      ItemEntry.builder(registeredItems.get(26)).weight(2).build()
  );
  private static final List<LootPoolEntry> golden_loot_pool = Arrays.asList(
      ItemEntry.builder(registeredItems.get(3)).weight(2).build(),
      ItemEntry.builder(registeredItems.get(9)).weight(1).build(),
      ItemEntry.builder(registeredItems.get(15)).weight(2).build(),
      ItemEntry.builder(registeredItems.get(21)).weight(2).build(),
      ItemEntry.builder(registeredItems.get(27)).weight(2).build()
  );
  private static final List<LootPoolEntry> diamond_loot_pool = Arrays.asList(
      ItemEntry.builder(registeredItems.get(4)).weight(2).build(),
      ItemEntry.builder(registeredItems.get(10)).weight(1).build(),
      ItemEntry.builder(registeredItems.get(16)).weight(2).build(),
      ItemEntry.builder(registeredItems.get(22)).weight(2).build(),
      ItemEntry.builder(registeredItems.get(28)).weight(2).build()
  );

  public static void addItems(List<LootPoolEntry> loot_pool, LootTable.Builder tableBuilder, boolean applyDamage) {
    /* Function adds 0-2 items that are undamaged with no enchantment */
    LootPool.Builder poolBuilder = LootPool.builder()
        .rolls(UniformLootNumberProvider.create(0f, 2f))
        .with(loot_pool);
    if (applyDamage) poolBuilder.apply(SetDamageLootFunction.builder(UniformLootNumberProvider.create(0.7f, 0.9f)));
    poolBuilder.bonusRolls(ConstantLootNumberProvider.create(0.5f));
    tableBuilder.pool(poolBuilder);
  }

  public static void addEnchantedItems(List<LootPoolEntry> loot_pool, LootTable.Builder tableBuilder, boolean applyDamage) {
    /* Function adds 0-2 items that are either enchanted and damaged, or full health with no enchantment */
    Collection<LootPool> lootPools = new ArrayList<>();
    LootPool.Builder enchantedPoolBuilder = LootPool.builder()
        .rolls(UniformLootNumberProvider.create(0f, 1f))
        .with(loot_pool)
        .apply(EnchantRandomlyLootFunction.create());
    if (applyDamage) enchantedPoolBuilder.apply(SetDamageLootFunction.builder(UniformLootNumberProvider.create(0.7f, 0.95f)))
        .bonusRolls(ConstantLootNumberProvider.create(0.5f));

    LootPool.Builder normalPoolBuilder = LootPool.builder()
        .rolls(UniformLootNumberProvider.create(0f, 1f))
        .with(loot_pool);
    lootPools.add(enchantedPoolBuilder.build());
    lootPools.add(normalPoolBuilder.build());
    tableBuilder.pools(lootPools);
  }

  public static void init() {
    LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
      /* Overworld */
      if (source.isBuiltin() && JUNGLE_TEMPLE_CHEST.equals(id)) {
        addItems(stone_loot_pool, tableBuilder, false);
      }
      if (source.isBuiltin() && IGLOO_CHEST_CHEST.equals(id)) {
        addItems(stone_loot_pool, tableBuilder, true);
      }
      if (source.isBuiltin() && SHIPWRECK_SUPPLY_CHEST.equals(id)) {
        addItems(stone_loot_pool, tableBuilder, true);
      }

      if (source.isBuiltin() && SIMPLE_DUNGEON_CHEST.equals(id)) {
        addItems(iron_loot_pool, tableBuilder, false);
      }
      if (source.isBuiltin() && ABANDONED_MINESHAFT_CHEST.equals(id)) {
        addItems(iron_loot_pool, tableBuilder, false);
      }
      if (source.isBuiltin() && DESERT_PYRAMID_CHEST.equals(id)) {
        addItems(iron_loot_pool, tableBuilder, false);
      }
      if (source.isBuiltin() && PILLAGER_OUTPOST_CHEST.equals(id)) {
        addItems(iron_loot_pool, tableBuilder, false);
      }
      if (source.isBuiltin() && BURIED_TREASURE_CHEST.equals(id)) {
        addItems(iron_loot_pool, tableBuilder, false);
      }
      if (source.isBuiltin() && SHIPWRECK_TREASURE_CHEST.equals(id)) {
        addItems(iron_loot_pool, tableBuilder, false);
      }
      if (source.isBuiltin() && STRONGHOLD_CORRIDOR_CHEST.equals(id)) {
        addItems(iron_loot_pool, tableBuilder, false);
      }
      if (source.isBuiltin() && VILLAGE_WEAPONSMITH_CHEST.equals(id)) {
        addItems(iron_loot_pool, tableBuilder, false);
      }
      /* Nether */
      if (source.isBuiltin() && BASTION_TREASURE_CHEST.equals(id)) {
        addEnchantedItems(diamond_loot_pool, tableBuilder, true);
      }
      if (source.isBuiltin() && BASTION_OTHER_CHEST.equals(id)) {
        addEnchantedItems(golden_loot_pool, tableBuilder, true);
      }
      if (source.isBuiltin() && BASTION_BRIDGE_CHEST.equals(id)) {
        addItems(golden_loot_pool, tableBuilder, false);
      }
      if (source.isBuiltin() && BASTION_HOGLIN_STABLE_CHEST.equals(id)) {
        addEnchantedItems(golden_loot_pool, tableBuilder, true);
      }
      if (source.isBuiltin() && RUINED_PORTAL_CHEST.equals(id)) {
        addEnchantedItems(golden_loot_pool, tableBuilder, false);
      }
      /* End */
      if (source.isBuiltin() && END_CITY_TREASURE_CHEST.equals(id)) {
        addEnchantedItems(diamond_loot_pool, tableBuilder, false);
      }
    });
  }
}
