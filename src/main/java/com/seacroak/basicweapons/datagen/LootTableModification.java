package com.seacroak.basicweapons.datagen;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.seacroak.basicweapons.Constants;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer;
import net.minecraft.world.level.storage.loot.entries.LootTableReference;
import net.minecraft.world.level.storage.loot.functions.EnchantRandomlyFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemDamageFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;

import static com.seacroak.basicweapons.registry.MainRegistry.registeredItems;


public class LootTableModification {

  private static final List<LootPoolSingletonContainer.Builder<?>> stone_loot_pool = Arrays.asList(
      createEntry(new ItemStack(registeredItems.get(1).get()), 2),
      createEntry(new ItemStack(registeredItems.get(1).get()), 2),
      createEntry(new ItemStack(registeredItems.get(7).get()), 1),
      createEntry(new ItemStack(registeredItems.get(13).get()), 2),
      createEntry(new ItemStack(registeredItems.get(19).get()), 2),
      createEntry(new ItemStack(registeredItems.get(25).get()), 2)
  );
  private static final List<LootPoolSingletonContainer.Builder<?>> iron_loot_pool = Arrays.asList(
      createEntry(new ItemStack(registeredItems.get(2).get()), 2),
      createEntry(new ItemStack(registeredItems.get(8).get()), 1),
      createEntry(new ItemStack(registeredItems.get(14).get()), 2),
      createEntry(new ItemStack(registeredItems.get(20).get()), 2),
      createEntry(new ItemStack(registeredItems.get(26).get()), 2)
  );
  private static final List<LootPoolSingletonContainer.Builder<?>> golden_loot_pool = Arrays.asList(
      createEntry(new ItemStack(registeredItems.get(3).get()), 2),
      createEntry(new ItemStack(registeredItems.get(9).get()), 1),
      createEntry(new ItemStack(registeredItems.get(15).get()), 2),
      createEntry(new ItemStack(registeredItems.get(21).get()), 2),
      createEntry(new ItemStack(registeredItems.get(27).get()), 2)
  );
  private static final List<LootPoolSingletonContainer.Builder<?>> diamond_loot_pool = Arrays.asList(
      createEntry(new ItemStack(registeredItems.get(4).get()), 2),
      createEntry(new ItemStack(registeredItems.get(10).get()), 1),
      createEntry(new ItemStack(registeredItems.get(16).get()), 2),
      createEntry(new ItemStack(registeredItems.get(22).get()), 2),
      createEntry(new ItemStack(registeredItems.get(28).get()), 2)
  );

  private static final Set<ResourceLocation> VANILLA_TABLES = Sets.newHashSet();
  private static final Map<String, ResourceLocation> BW_TABLES = Maps.newHashMap();
  public static final ResourceLocation jungle_temple = registerInject("jungle_temple");
  public static final ResourceLocation igloo_chest = registerInject("igloo_chest");
  public static final ResourceLocation shipwreck_supply = registerInject("shipwreck_supply");
  public static final ResourceLocation simple_dungeon = registerInject("simple_dungeon");
  public static final ResourceLocation abandoned_mineshaft = registerInject("abandoned_mineshaft");
  public static final ResourceLocation desert_pyramid = registerInject("desert_pyramid");
  public static final ResourceLocation pillager_outpost = registerInject("pillager_outpost");
  public static final ResourceLocation buried_treasure = registerInject("buried_treasure");
  public static final ResourceLocation shipwreck_treasure = registerInject("shipwreck_treasure");
  public static final ResourceLocation stronghold_corridor = registerInject("stronghold_corridor");
  public static final ResourceLocation village_weaponsmith = registerInject("village/village_weaponsmith");
  public static final ResourceLocation bastion_treasure = registerInject("bastion_treasure");
  public static final ResourceLocation bastion_other = registerInject("bastion_other");
  public static final ResourceLocation bastion_bridge = registerInject("bastion_bridge");
  public static final ResourceLocation bastion_hoglin_stable = registerInject("bastion_hoglin_stable");
  public static final ResourceLocation ruined_portal = registerInject("ruined_portal");
  public static final ResourceLocation end_city_treasure = registerInject("end_city_treasure");
  private static int num_pools_added = 0;

  static @NotNull ResourceLocation registerInject(String resourceName) {
    ResourceLocation registryName = register("inject/" + resourceName);
    BW_TABLES.put(resourceName, registryName);
    return registryName;
  }

  static @NotNull ResourceLocation register(@NotNull String resourceName) {
    return register(new ResourceLocation(Constants.BW_ID, resourceName));
  }

  static @NotNull ResourceLocation register(@NotNull ResourceLocation resourceLocation) {
    VANILLA_TABLES.add(resourceLocation);
    return resourceLocation;
  }

  public static @NotNull Set<ResourceLocation> getLootTables() {
    return ImmutableSet.copyOf(VANILLA_TABLES);
  }

  private static @NotNull LootPool getInjectPool(String entryName) {
    LootPoolEntryContainer.Builder<?> entryBuilder = LootTableReference.lootTableReference(BW_TABLES.get(entryName)).setWeight(1);
    return LootPool.lootPool().setBonusRolls(UniformGenerator.between(0, 1)).setRolls(ConstantValue.exactly(1)).add(entryBuilder).build();
  }

  @SubscribeEvent
  public static void onLootLoad(@NotNull LootTableLoadEvent event) {
    String prefix = "minecraft:chests/";
    String name = event.getName().toString();
    if (name.startsWith(prefix)) {
      String file = name.substring(name.indexOf(prefix) + prefix.length());
      if (BW_TABLES.containsKey(file)) {
        try {
          event.getTable().addPool(getInjectPool(file));
          num_pools_added++;
        } catch (NullPointerException e) {
          Constants.BW_LOG.warn("Failed to add to vanilla loot table: {}. This is most likely due to an incompatibility with another mod. Please report this on the Basic Weapons issue tracker, thank you.", name);
        }
      }
    }
  }

  public static LootTableProvider getProvider(PackOutput output) {
    return new LootTableProvider(output, getLootTables(),
        List.of(
            new LootTableProvider.SubProviderEntry(AddToLootTables::new, LootContextParamSets.CHEST))
    );
  }

  private static LootTable.Builder newPool(List<LootPoolSingletonContainer.Builder<?>> itemBuilderList, boolean applyDamage) {
    LootPool.Builder lootPool = LootPool.lootPool();
    LootTable.Builder lootTable = LootTable.lootTable();

    lootPool.setRolls(UniformGenerator.between(0f, 2f));
    for (LootPoolSingletonContainer.Builder<?> itemBuilder : itemBuilderList
    ) {
      lootPool.add(itemBuilder);
    }
    if (applyDamage) lootPool.apply(SetItemDamageFunction.setDamage(UniformGenerator.between(0.7f, 0.95f)))
        .setBonusRolls(ConstantValue.exactly(0.5f));

    lootTable.withPool(lootPool);
    return lootTable;
  }

  private static LootTable.Builder newEnchantedPool(List<LootPoolSingletonContainer.Builder<?>> itemBuilderList, boolean applyDamage) {
    LootPool.Builder enchantedPoolBuilder = LootPool.lootPool();
    LootTable.Builder lootTable = LootTable.lootTable();

    enchantedPoolBuilder.setRolls(UniformGenerator.between(0f, 1f));
    for (LootPoolSingletonContainer.Builder<?> itemBuilder : itemBuilderList
    ) {
      enchantedPoolBuilder.add(itemBuilder);
    }
    enchantedPoolBuilder.apply(EnchantRandomlyFunction.randomApplicableEnchantment());
    if (applyDamage) enchantedPoolBuilder.apply(SetItemDamageFunction.setDamage(UniformGenerator.between(0.7f, 0.95f)))
        .setBonusRolls(ConstantValue.exactly(0.5f));

    LootPool.Builder normalPoolBuilder = LootPool.lootPool()
        .setRolls(UniformGenerator.between(0f, 1f));
    for (LootPoolSingletonContainer.Builder<?> itemBuilder : itemBuilderList
    ) {
      normalPoolBuilder.add(itemBuilder);
    }

    lootTable.withPool(enchantedPoolBuilder);
    lootTable.withPool(normalPoolBuilder);
    return lootTable;
  }

  private static LootPoolSingletonContainer.Builder<?> createEntry(ItemStack item, int weight) {
    return LootItem.lootTableItem(item.getItem()).setWeight(weight);
  }

  public static class AddToLootTables implements LootTableSubProvider {
    @Override
    public void generate(@NotNull BiConsumer<ResourceLocation, LootTable.Builder> consumer) {
      consumer.accept(jungle_temple, newPool(stone_loot_pool, false));
      consumer.accept(igloo_chest, newPool(stone_loot_pool, true));
      consumer.accept(shipwreck_supply, newPool(stone_loot_pool, true));

      consumer.accept(simple_dungeon, newPool(iron_loot_pool, false));
      consumer.accept(abandoned_mineshaft, newPool(iron_loot_pool, false));
      consumer.accept(desert_pyramid, newPool(iron_loot_pool, false));
      consumer.accept(pillager_outpost, newPool(iron_loot_pool, false));
      consumer.accept(buried_treasure, newPool(iron_loot_pool, false));
      consumer.accept(shipwreck_treasure, newPool(iron_loot_pool, false));
      consumer.accept(stronghold_corridor, newPool(iron_loot_pool, false));
      consumer.accept(village_weaponsmith, newPool(iron_loot_pool, false));

      consumer.accept(bastion_treasure, newEnchantedPool(diamond_loot_pool, true));
      consumer.accept(bastion_other, newEnchantedPool(golden_loot_pool, true));
      consumer.accept(bastion_bridge, newPool(golden_loot_pool, false));
      consumer.accept(bastion_hoglin_stable, newEnchantedPool(golden_loot_pool, true));
      consumer.accept(ruined_portal, newEnchantedPool(golden_loot_pool, false));

      consumer.accept(end_city_treasure, newEnchantedPool(diamond_loot_pool, false));
    }
  }
}
