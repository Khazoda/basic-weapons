package com.khazoda.basicweapons.registry;

import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.EnchantRandomlyFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemDamageFunction;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static net.minecraft.core.registries.Registries.LOOT_TABLE;
import static net.minecraft.world.level.storage.loot.BuiltInLootTables.*;

public class LootTableModifier {
  private static final Map<ResourceKey<LootTable>, LootConfig> LOOT_TABLE_CONFIGS = new HashMap<>();

  static {
    // Overworld - Stone
    LOOT_TABLE_CONFIGS.put(JUNGLE_TEMPLE, new LootConfig(WeaponRegistry.getItemsByMaterial(ToolMaterial.STONE), true, false));
    LOOT_TABLE_CONFIGS.put(IGLOO_CHEST, new LootConfig(WeaponRegistry.getItemsByMaterial(ToolMaterial.STONE), true, false));
    LOOT_TABLE_CONFIGS.put(SHIPWRECK_SUPPLY, new LootConfig(WeaponRegistry.getItemsByMaterial(ToolMaterial.STONE), true, false));

    // Overworld - Iron, Gold
    LOOT_TABLE_CONFIGS.put(SIMPLE_DUNGEON, new LootConfig(WeaponRegistry.getItemsByMaterial(ToolMaterial.IRON), false, false));
    LOOT_TABLE_CONFIGS.put(ABANDONED_MINESHAFT, new LootConfig(WeaponRegistry.getItemsByMaterial(ToolMaterial.IRON), false, false));
    LOOT_TABLE_CONFIGS.put(DESERT_PYRAMID, new LootConfig(WeaponRegistry.getItemsByMaterial(ToolMaterial.IRON), false, false));
    LOOT_TABLE_CONFIGS.put(PILLAGER_OUTPOST, new LootConfig(WeaponRegistry.getItemsByMaterial(ToolMaterial.IRON), false, false));
    LOOT_TABLE_CONFIGS.put(BURIED_TREASURE, new LootConfig(WeaponRegistry.getItemsByMaterial(ToolMaterial.IRON), true, false));
    LOOT_TABLE_CONFIGS.put(SHIPWRECK_TREASURE, new LootConfig(WeaponRegistry.getItemsByMaterial(ToolMaterial.IRON), true, false));
    LOOT_TABLE_CONFIGS.put(STRONGHOLD_CORRIDOR, new LootConfig(WeaponRegistry.getItemsByMaterial(ToolMaterial.IRON), false, false));
    LOOT_TABLE_CONFIGS.put(VILLAGE_WEAPONSMITH, new LootConfig(WeaponRegistry.getItemsByMaterial(ToolMaterial.IRON), false, false));
    LOOT_TABLE_CONFIGS.put(RUINED_PORTAL, new LootConfig(WeaponRegistry.getItemsByMaterial(ToolMaterial.GOLD), true, true));

    // Nether - Gold, Diamond
    LOOT_TABLE_CONFIGS.put(BASTION_TREASURE, new LootConfig(WeaponRegistry.getItemsByMaterial(ToolMaterial.DIAMOND), true, true));
    LOOT_TABLE_CONFIGS.put(BASTION_OTHER, new LootConfig(WeaponRegistry.getItemsByMaterial(ToolMaterial.GOLD), true, true));
    LOOT_TABLE_CONFIGS.put(BASTION_BRIDGE, new LootConfig(WeaponRegistry.getItemsByMaterial(ToolMaterial.GOLD), true, true));
    LOOT_TABLE_CONFIGS.put(BASTION_HOGLIN_STABLE, new LootConfig(WeaponRegistry.getItemsByMaterial(ToolMaterial.GOLD), true, true));

    // End - Diamond
    LOOT_TABLE_CONFIGS.put(END_CITY_TREASURE, new LootConfig(WeaponRegistry.getItemsByMaterial(ToolMaterial.DIAMOND), false, true));
  }

  public static void modifyLootTable(Identifier id, LootTable.Builder tableBuilder) {
    ResourceKey<LootTable> key = ResourceKey.create(LOOT_TABLE, id);
    LootConfig config = LOOT_TABLE_CONFIGS.get(key);
    if (config != null) {
      addLootToTable(config, tableBuilder);
    }
  }

  private static void addLootToTable(LootConfig config, LootTable.Builder tableBuilder) {
    if (config.enchanted()) {
      // Add 0-1 enchanted items
      LootPool.Builder enchantedPool = createBasePoolBuilder(config.items(), 1f)
          .apply(EnchantRandomlyFunction.randomEnchantment());
      if (config.applyDamage()) applyDamage(enchantedPool);

      // Add 0-1 unenchanted items
      LootPool.Builder normalPool = createBasePoolBuilder(config.items(), 1f);
      tableBuilder.withPool(enchantedPool).withPool(normalPool);
    } else {
      // Add 0-2 unenchanted items
      LootPool.Builder pool = createBasePoolBuilder(config.items(), 2f);
      if (config.applyDamage()) applyDamage(pool);
      tableBuilder.withPool(pool);
    }
  }

  private static LootPool.Builder createBasePoolBuilder(List<Item> items, float maxRolls) {
    LootPool.Builder builder = LootPool.lootPool()
        .setRolls(UniformGenerator.between(0.0f, maxRolls));

    for (Item item : items) {
      builder.add(LootItem.lootTableItem(item).setWeight(2));
    }

    return builder;
  }

  private static void applyDamage(LootPool.Builder builder) {
    builder.apply(SetItemDamageFunction.setDamage(UniformGenerator.between(0.5f, 0.8f)))
        .setBonusRolls(ConstantValue.exactly(0.5f));
  }

  private record LootConfig(List<Item> items, boolean applyDamage, boolean enchanted) {
  }
} 