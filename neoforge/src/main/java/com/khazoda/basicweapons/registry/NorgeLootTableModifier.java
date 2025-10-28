package com.khazoda.basicweapons.registry;

import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.NestedLootTable;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.LootTableLoadEvent;

import static com.khazoda.basicweapons.Constants.MOD_ID;

@EventBusSubscriber(modid = MOD_ID)
public class NorgeLootTableModifier {

  @SubscribeEvent
  public static void onLootTableLoad(LootTableLoadEvent event) {
    if (!event.getName().getNamespace().equals("minecraft")) return;
    LootTable.Builder builder = LootTable.lootTable();
    LootTableModifier.modifyLootTable(event.getName(), builder);
    LootPool pool = LootPool.lootPool()
        .add(NestedLootTable.inlineLootTable(builder.build()))
        .build();
    event.getTable().addPool(pool);
  }
} 