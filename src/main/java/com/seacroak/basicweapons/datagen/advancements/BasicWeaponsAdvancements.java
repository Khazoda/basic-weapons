package com.seacroak.basicweapons.datagen.advancements;

import com.seacroak.basicweapons.Constants;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.advancement.AdvancementRewards;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.recipe.Recipe;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.function.Consumer;

@SuppressWarnings("removal")
public class BasicWeaponsAdvancements implements Consumer<Consumer<AdvancementEntry>> {
  RegistryEntryLookup<Item> registryEntryLookup;

  private RegistryKey<Recipe<?>> getRegistryKey(String recipeName) {
    return RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(Constants.BW_NAMESPACE, recipeName));
  }

  public void accept(RegistryEntryLookup.RegistryLookup registryLookup, Consumer<AdvancementEntry> advancementEntryConsumer) {
    registryEntryLookup = registryLookup.getOrThrow(RegistryKeys.ITEM);
    accept(advancementEntryConsumer);
  }

  @Override
  public void accept(Consumer<AdvancementEntry> advancementConsumer) {
    AdvancementEntry wooden_weapons = Advancement.Builder.create()
        .parent(Identifier.of("recipes/root"))
        .criterion("got_sticks", InventoryChangedCriterion.Conditions.items(Items.STICK))
        .rewards(AdvancementRewards.Builder
            .recipe(getRegistryKey("wooden_dagger"))
            .addRecipe(getRegistryKey("wooden_club"))
            .addRecipe(getRegistryKey("wooden_club_variant"))
            .addRecipe(getRegistryKey("wooden_hammer"))
            .addRecipe(getRegistryKey("wooden_spear"))
            .addRecipe(getRegistryKey("wooden_quarterstaff"))
            .addRecipe(getRegistryKey("wooden_glaive"))

        )
        .build(advancementConsumer, Constants.BW_NAMESPACE + "/got_sticks");
    AdvancementEntry stone_weapons = Advancement.Builder.create()
        .parent(Identifier.of("recipes/root"))
        .criterion("got_cobblestone", InventoryChangedCriterion.Conditions.items(Items.COBBLESTONE))
        .rewards(AdvancementRewards.Builder
            .recipe(getRegistryKey("stone_dagger"))
            .addRecipe(getRegistryKey("stone_club"))
            .addRecipe(getRegistryKey("stone_club_variant"))
            .addRecipe(getRegistryKey("stone_hammer"))
            .addRecipe(getRegistryKey("stone_spear"))
            .addRecipe(getRegistryKey("stone_quarterstaff"))
            .addRecipe(getRegistryKey("stone_glaive"))

        )
        .build(advancementConsumer, Constants.BW_NAMESPACE + "/got_cobblestone");
    AdvancementEntry iron_weapons = Advancement.Builder.create()
        .parent(Identifier.of("recipes/root"))
        .criterion("got_iron_ingot", InventoryChangedCriterion.Conditions.items(Items.IRON_INGOT))
        .rewards(AdvancementRewards.Builder
            .recipe(getRegistryKey("iron_dagger"))
            .addRecipe(getRegistryKey("iron_club"))
            .addRecipe(getRegistryKey("iron_club_variant"))
            .addRecipe(getRegistryKey("iron_hammer"))
            .addRecipe(getRegistryKey("iron_spear"))
            .addRecipe(getRegistryKey("iron_quarterstaff"))
            .addRecipe(getRegistryKey("iron_glaive"))

        )
        .build(advancementConsumer, Constants.BW_NAMESPACE + "/got_iron_ingot");
    AdvancementEntry bronze_weapons = Advancement.Builder.create()
        .parent(Identifier.of("recipes/root"))
        .criterion("got_bronze_ingot", InventoryChangedCriterion.Conditions.items(ItemPredicate.Builder.create().tag(registryEntryLookup, Constants.BRONZE_INGOTS)))
        .rewards(AdvancementRewards.Builder
            .recipe(getRegistryKey("bronze_dagger"))
            .addRecipe(getRegistryKey("bronze_club"))
            .addRecipe(getRegistryKey("bronze_club_variant"))
            .addRecipe(getRegistryKey("bronze_hammer"))
            .addRecipe(getRegistryKey("bronze_spear"))
            .addRecipe(getRegistryKey("bronze_quarterstaff"))
            .addRecipe(getRegistryKey("bronze_glaive"))

        )
        .build(advancementConsumer, Constants.BW_NAMESPACE + "/got_bronze_ingot");
    AdvancementEntry golden_weapons = Advancement.Builder.create()
        .parent(Identifier.of("recipes/root"))
        .criterion("got_gold_ingot", InventoryChangedCriterion.Conditions.items(Items.GOLD_INGOT))
        .rewards(AdvancementRewards.Builder
            .recipe(getRegistryKey("golden_dagger"))
            .addRecipe(getRegistryKey("golden_club"))
            .addRecipe(getRegistryKey("golden_club_variant"))
            .addRecipe(getRegistryKey("golden_hammer"))
            .addRecipe(getRegistryKey("golden_spear"))
            .addRecipe(getRegistryKey("golden_quarterstaff"))
            .addRecipe(getRegistryKey("golden_glaive"))

        )
        .build(advancementConsumer, Constants.BW_NAMESPACE + "/got_gold_ingot");
    AdvancementEntry diamond_weapons = Advancement.Builder.create()
        .parent(Identifier.of("recipes/root"))
        .criterion("got_diamond", InventoryChangedCriterion.Conditions.items(Items.DIAMOND))
        .rewards(AdvancementRewards.Builder
            .recipe(getRegistryKey("diamond_dagger"))
            .addRecipe(getRegistryKey("diamond_club"))
            .addRecipe(getRegistryKey("diamond_club_variant"))
            .addRecipe(getRegistryKey("diamond_hammer"))
            .addRecipe(getRegistryKey("diamond_spear"))
            .addRecipe(getRegistryKey("diamond_quarterstaff"))
            .addRecipe(getRegistryKey("diamond_glaive"))

        )
        .build(advancementConsumer, Constants.BW_NAMESPACE + "/got_diamond");
  }
}
