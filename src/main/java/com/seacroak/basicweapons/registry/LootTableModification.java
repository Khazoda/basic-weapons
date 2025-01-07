package com.seacroak.basicweapons.registry;

import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.EnchantRandomlyLootFunction;
import net.minecraft.loot.function.SetDamageLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static net.minecraft.loot.LootTables.*;

public class LootTableModification {
  private static final Map<Identifier, LootConfig> LOOT_TABLE_CONFIGS = new HashMap<>();

  static {
    // Overworld - Stone
    LOOT_TABLE_CONFIGS.put(JUNGLE_TEMPLE_CHEST.getValue(), new LootConfig(WeaponRegistry.getItemsByMaterial(ToolMaterial.STONE), true, false));
    LOOT_TABLE_CONFIGS.put(IGLOO_CHEST_CHEST.getValue(), new LootConfig(WeaponRegistry.getItemsByMaterial(ToolMaterial.STONE), true, false));
    LOOT_TABLE_CONFIGS.put(SHIPWRECK_SUPPLY_CHEST.getValue(), new LootConfig(WeaponRegistry.getItemsByMaterial(ToolMaterial.STONE), true, false));

    // Overworld - Iron, Gold
    LOOT_TABLE_CONFIGS.put(SIMPLE_DUNGEON_CHEST.getValue(), new LootConfig(WeaponRegistry.getItemsByMaterial(ToolMaterial.IRON), false, false));
    LOOT_TABLE_CONFIGS.put(ABANDONED_MINESHAFT_CHEST.getValue(), new LootConfig(WeaponRegistry.getItemsByMaterial(ToolMaterial.IRON), false, false));
    LOOT_TABLE_CONFIGS.put(DESERT_PYRAMID_CHEST.getValue(), new LootConfig(WeaponRegistry.getItemsByMaterial(ToolMaterial.IRON), false, false));
    LOOT_TABLE_CONFIGS.put(PILLAGER_OUTPOST_CHEST.getValue(), new LootConfig(WeaponRegistry.getItemsByMaterial(ToolMaterial.IRON), false, false));
    LOOT_TABLE_CONFIGS.put(BURIED_TREASURE_CHEST.getValue(), new LootConfig(WeaponRegistry.getItemsByMaterial(ToolMaterial.IRON), true, false));
    LOOT_TABLE_CONFIGS.put(SHIPWRECK_TREASURE_CHEST.getValue(), new LootConfig(WeaponRegistry.getItemsByMaterial(ToolMaterial.IRON), true, false));
    LOOT_TABLE_CONFIGS.put(STRONGHOLD_CORRIDOR_CHEST.getValue(), new LootConfig(WeaponRegistry.getItemsByMaterial(ToolMaterial.IRON), false, false));
    LOOT_TABLE_CONFIGS.put(VILLAGE_WEAPONSMITH_CHEST.getValue(), new LootConfig(WeaponRegistry.getItemsByMaterial(ToolMaterial.IRON), false, false));
    LOOT_TABLE_CONFIGS.put(RUINED_PORTAL_CHEST.getValue(), new LootConfig(WeaponRegistry.getItemsByMaterial(ToolMaterial.GOLD), true, true));

    // Nether - Gold, Diamond
    LOOT_TABLE_CONFIGS.put(BASTION_TREASURE_CHEST.getValue(), new LootConfig(WeaponRegistry.getItemsByMaterial(ToolMaterial.DIAMOND), true, true));
    LOOT_TABLE_CONFIGS.put(BASTION_OTHER_CHEST.getValue(), new LootConfig(WeaponRegistry.getItemsByMaterial(ToolMaterial.GOLD), true, true));
    LOOT_TABLE_CONFIGS.put(BASTION_BRIDGE_CHEST.getValue(), new LootConfig(WeaponRegistry.getItemsByMaterial(ToolMaterial.GOLD), true, true));
    LOOT_TABLE_CONFIGS.put(BASTION_HOGLIN_STABLE_CHEST.getValue(), new LootConfig(WeaponRegistry.getItemsByMaterial(ToolMaterial.GOLD), true, true));

    // End - Diamond
    LOOT_TABLE_CONFIGS.put(END_CITY_TREASURE_CHEST.getValue(), new LootConfig(WeaponRegistry.getItemsByMaterial(ToolMaterial.DIAMOND), false, true));
  }

  public static void init() {
    LootTableEvents.MODIFY.register((key, tableBuilder, source, wrapperLookup) -> {
      if (!source.isBuiltin()) return;
      LootConfig config = LOOT_TABLE_CONFIGS.get(key.getValue());
      if (config != null) {
        addLootToTable(config, tableBuilder);
      }
    });
  }

  private static void addLootToTable(LootConfig config, LootTable.Builder tableBuilder) {
    if (config.enchanted()) {
      // Add 0-1 enchanted items
      LootPool.Builder enchantedPool = createBasePoolBuilder(config.items(), 1f).apply(EnchantRandomlyLootFunction.create());
      if (config.applyDamage()) applyDamage(enchantedPool);
      // Add 0-1 unenchanted items
      LootPool.Builder normalPool = createBasePoolBuilder(config.items(), 1f);
      tableBuilder.pools(List.of(enchantedPool.build(), normalPool.build()));
    } else {
      // Add 0-2 unenchanted items
      LootPool.Builder pool = createBasePoolBuilder(config.items(), 2f);
      if (config.applyDamage()) applyDamage(pool);
      tableBuilder.pool(pool);
    }
  }

  private static LootPool.Builder createBasePoolBuilder(List<Item> items, float maxRolls) {
    return LootPool.builder().rolls(UniformLootNumberProvider.create(0.0f, maxRolls)).with(items.stream().map(item -> ItemEntry.builder(item).weight(2).build()).toList());
  }

  private static void applyDamage(LootPool.Builder builder) {
    builder.apply(SetDamageLootFunction.builder(UniformLootNumberProvider.create(0.7f, 0.95f))).bonusRolls(ConstantLootNumberProvider.create(0.5f));
  }

  private record LootConfig(List<Item> items, boolean applyDamage, boolean enchanted) {
  }
}
