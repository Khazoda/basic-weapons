package com.khazoda.basicweapons.datagen.advancements;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Recipe;

import java.util.function.Consumer;

import static com.khazoda.basicweapons.Constants.ID;
import static com.khazoda.basicweapons.registry.TagRegistry.BRONZE_INGOTS;

@SuppressWarnings("removal")
public class BasicWeaponsAdvancements implements Consumer<Consumer<AdvancementHolder>> {
  HolderGetter<Item> registryEntryLookup;

  public void accept(HolderGetter.Provider registryLookup, Consumer<AdvancementHolder> advancementHolderConsumer) {
    registryEntryLookup = registryLookup.lookupOrThrow(Registries.ITEM);
    accept(advancementHolderConsumer);
  }

  public static ResourceKey<Recipe<?>> recipeKey(ResourceLocation location) {
    return ResourceKey.create(Registries.RECIPE, location);
  }

  @Override
  public void accept(Consumer<AdvancementHolder> advancementConsumer) {

    AdvancementHolder wooden_weapons = Advancement.Builder.recipeAdvancement()
        .parent(ResourceLocation.withDefaultNamespace("recipes/root"))
        .addCriterion("got_sticks", InventoryChangeTrigger.TriggerInstance.hasItems(Items.STICK))
        .rewards(AdvancementRewards.Builder
            .recipe(ResourceKey.create(Registries.RECIPE, ID("wooden_dagger")))
            .addRecipe(recipeKey(ID("wooden_club")))
            .addRecipe(recipeKey(ID("wooden_club_variant")))
            .addRecipe(recipeKey(ID("wooden_hammer")))
            .addRecipe(recipeKey(ID("wooden_spear")))
            .addRecipe(recipeKey(ID("wooden_quarterstaff")))
            .addRecipe(recipeKey(ID("wooden_glaive")))

        )
        .build(ID("recipes/got_sticks"));
    advancementConsumer.accept(wooden_weapons);

    AdvancementHolder stone_weapons = Advancement.Builder.recipeAdvancement()
        .parent(ResourceLocation.withDefaultNamespace("recipes/root"))
        .addCriterion("got_cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Items.COBBLESTONE))
        .rewards(AdvancementRewards.Builder
            .recipe(recipeKey(ID("stone_dagger")))
            .addRecipe(recipeKey(ID("stone_club")))
            .addRecipe(recipeKey(ID("stone_club_variant")))
            .addRecipe(recipeKey(ID("stone_hammer")))
            .addRecipe(recipeKey(ID("stone_spear")))
            .addRecipe(recipeKey(ID("stone_quarterstaff")))
            .addRecipe(recipeKey(ID("stone_glaive")))

        )
        .build(ID("recipes/got_cobblestone"));
    advancementConsumer.accept(stone_weapons);

    AdvancementHolder iron_weapons = Advancement.Builder.recipeAdvancement()
        .parent(ResourceLocation.withDefaultNamespace("recipes/root"))
        .addCriterion("got_iron_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(Items.IRON_INGOT))
        .rewards(AdvancementRewards.Builder
            .recipe(recipeKey(ID("iron_dagger")))
            .addRecipe(recipeKey(ID("iron_club")))
            .addRecipe(recipeKey(ID("iron_club_variant")))
            .addRecipe(recipeKey(ID("iron_hammer")))
            .addRecipe(recipeKey(ID("iron_spear")))
            .addRecipe(recipeKey(ID("iron_quarterstaff")))
            .addRecipe(recipeKey(ID("iron_glaive")))

        )
        .build(ID("recipes/got_iron_ingot"));
    advancementConsumer.accept(iron_weapons);

    AdvancementHolder bronze_weapons = Advancement.Builder.recipeAdvancement()
        .parent(ResourceLocation.withDefaultNamespace("recipes/root"))
        .addCriterion("got_bronze_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(registryEntryLookup, BRONZE_INGOTS)))
        .rewards(AdvancementRewards.Builder
            .recipe(recipeKey(ID("compat/bronze_dagger")))
            .addRecipe(recipeKey(ID("compat/bronze_club")))
            .addRecipe(recipeKey(ID("compat/bronze_club_variant")))
            .addRecipe(recipeKey(ID("compat/bronze_hammer")))
            .addRecipe(recipeKey(ID("compat/bronze_spear")))
            .addRecipe(recipeKey(ID("compat/bronze_quarterstaff")))
            .addRecipe(recipeKey(ID("compat/bronze_glaive")))

        )
        .build(ID("recipes/got_bronze_ingot"));
    advancementConsumer.accept(bronze_weapons);

    AdvancementHolder golden_weapons = Advancement.Builder.recipeAdvancement()
        .parent(ResourceLocation.withDefaultNamespace("recipes/root"))
        .addCriterion("got_gold_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(Items.GOLD_INGOT))
        .rewards(AdvancementRewards.Builder
            .recipe(recipeKey(ID("golden_dagger")))
            .addRecipe(recipeKey(ID("golden_club")))
            .addRecipe(recipeKey(ID("golden_club_variant")))
            .addRecipe(recipeKey(ID("golden_hammer")))
            .addRecipe(recipeKey(ID("golden_spear")))
            .addRecipe(recipeKey(ID("golden_quarterstaff")))
            .addRecipe(recipeKey(ID("golden_glaive")))

        )
        .build(ID("recipes/got_gold_ingot"));
    advancementConsumer.accept(golden_weapons);

    AdvancementHolder diamond_weapons = Advancement.Builder.recipeAdvancement()
        .parent(ResourceLocation.withDefaultNamespace("recipes/root"))
        .addCriterion("got_diamond", InventoryChangeTrigger.TriggerInstance.hasItems(Items.DIAMOND))
        .rewards(AdvancementRewards.Builder
            .recipe(recipeKey(ID("diamond_dagger")))
            .addRecipe(recipeKey(ID("diamond_club")))
            .addRecipe(recipeKey(ID("diamond_club_variant")))
            .addRecipe(recipeKey(ID("diamond_hammer")))
            .addRecipe(recipeKey(ID("diamond_spear")))
            .addRecipe(recipeKey(ID("diamond_quarterstaff")))
            .addRecipe(recipeKey(ID("diamond_glaive")))

        )
        .build(ID("recipes/got_diamond"));
    advancementConsumer.accept(diamond_weapons);
  }
}
