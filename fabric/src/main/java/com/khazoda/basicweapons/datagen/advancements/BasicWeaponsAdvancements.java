package com.khazoda.basicweapons.datagen.advancements;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.function.Consumer;

import static com.khazoda.basicweapons.Constants.ID;
import static com.khazoda.basicweapons.registry.TagRegistry.BRONZE_INGOTS;

@SuppressWarnings("removal")
public class BasicWeaponsAdvancements implements Consumer<Consumer<AdvancementHolder>> {
  HolderGetter<Item> registryEntryLookup;

  public void accept(HolderGetter.Provider registryLookup, Consumer<AdvancementHolder> AdvancementHolderConsumer) {
    registryEntryLookup = registryLookup.lookupOrThrow(Registries.ITEM);
    accept(AdvancementHolderConsumer);
  }

  @Override
  public void accept(Consumer<AdvancementHolder> advancementConsumer) {
    AdvancementHolder wooden_weapons = Advancement.Builder.recipeAdvancement()
        .parent(ResourceLocation.withDefaultNamespace("recipes/root"))
        .addCriterion("got_sticks", InventoryChangeTrigger.TriggerInstance.hasItems(Items.STICK))
        .rewards(AdvancementRewards.Builder
            .recipe(ID("wooden_dagger"))
            .addRecipe(ID("wooden_club"))
            .addRecipe(ID("wooden_club_variant"))
            .addRecipe(ID("wooden_hammer"))
            .addRecipe(ID("wooden_spear"))
            .addRecipe(ID("wooden_quarterstaff"))
            .addRecipe(ID("wooden_glaive"))

        )
        .build(ID("recipes/got_sticks"));
    advancementConsumer.accept(wooden_weapons);

    AdvancementHolder stone_weapons = Advancement.Builder.recipeAdvancement()
        .parent(ResourceLocation.withDefaultNamespace("recipes/root"))
        .addCriterion("got_cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Items.COBBLESTONE))
        .rewards(AdvancementRewards.Builder
            .recipe(ID("stone_dagger"))
            .addRecipe(ID("stone_club"))
            .addRecipe(ID("stone_club_variant"))
            .addRecipe(ID("stone_hammer"))
            .addRecipe(ID("stone_spear"))
            .addRecipe(ID("stone_quarterstaff"))
            .addRecipe(ID("stone_glaive"))

        )
        .build(ID("recipes/got_cobblestone"));
    advancementConsumer.accept(stone_weapons);

    AdvancementHolder iron_weapons = Advancement.Builder.recipeAdvancement()
        .parent(ResourceLocation.withDefaultNamespace("recipes/root"))
        .addCriterion("got_iron_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(Items.IRON_INGOT))
        .rewards(AdvancementRewards.Builder
            .recipe(ID("iron_dagger"))
            .addRecipe(ID("iron_club"))
            .addRecipe(ID("iron_club_variant"))
            .addRecipe(ID("iron_hammer"))
            .addRecipe(ID("iron_spear"))
            .addRecipe(ID("iron_quarterstaff"))
            .addRecipe(ID("iron_glaive"))

        )
        .build(ID("recipes/got_iron_ingot"));
    advancementConsumer.accept(iron_weapons);

    AdvancementHolder bronze_weapons = Advancement.Builder.recipeAdvancement()
        .parent(ResourceLocation.withDefaultNamespace("recipes/root"))
        .addCriterion("got_bronze_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(BRONZE_INGOTS)))
        .rewards(AdvancementRewards.Builder
            .recipe(ID("compat/bronze_dagger"))
            .addRecipe(ID("compat/bronze_club"))
            .addRecipe(ID("compat/bronze_club_variant"))
            .addRecipe(ID("compat/bronze_hammer"))
            .addRecipe(ID("compat/bronze_spear"))
            .addRecipe(ID("compat/bronze_quarterstaff"))
            .addRecipe(ID("compat/bronze_glaive"))

        )
        .build(ID("recipes/got_bronze_ingot"));
    advancementConsumer.accept(bronze_weapons);

    AdvancementHolder golden_weapons = Advancement.Builder.recipeAdvancement()
        .parent(ResourceLocation.withDefaultNamespace("recipes/root"))
        .addCriterion("got_gold_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(Items.GOLD_INGOT))
        .rewards(AdvancementRewards.Builder
            .recipe(ID("golden_dagger"))
            .addRecipe(ID("golden_club"))
            .addRecipe(ID("golden_club_variant"))
            .addRecipe(ID("golden_hammer"))
            .addRecipe(ID("golden_spear"))
            .addRecipe(ID("golden_quarterstaff"))
            .addRecipe(ID("golden_glaive"))

        )
        .build(ID("recipes/got_gold_ingot"));
    advancementConsumer.accept(golden_weapons);

    AdvancementHolder diamond_weapons = Advancement.Builder.recipeAdvancement()
        .parent(ResourceLocation.withDefaultNamespace("recipes/root"))
        .addCriterion("got_diamond", InventoryChangeTrigger.TriggerInstance.hasItems(Items.DIAMOND))
        .rewards(AdvancementRewards.Builder
            .recipe(ID("diamond_dagger"))
            .addRecipe(ID("diamond_club"))
            .addRecipe(ID("diamond_club_variant"))
            .addRecipe(ID("diamond_hammer"))
            .addRecipe(ID("diamond_spear"))
            .addRecipe(ID("diamond_quarterstaff"))
            .addRecipe(ID("diamond_glaive"))

        )
        .build(ID("recipes/got_diamond"));
    advancementConsumer.accept(diamond_weapons);
  }
}
