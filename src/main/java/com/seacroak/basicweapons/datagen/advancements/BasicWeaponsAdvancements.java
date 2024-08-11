package com.seacroak.basicweapons.datagen.advancements;

import com.seacroak.basicweapons.Constants;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.advancement.AdvancementRewards;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.item.Items;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.util.Identifier;

import java.util.function.Consumer;

@SuppressWarnings("removal")
public class BasicWeaponsAdvancements implements Consumer<Consumer<AdvancementEntry>> {
  @Override
  public void accept(Consumer<AdvancementEntry> advancementConsumer) {
    AdvancementEntry wooden_weapons = Advancement.Builder.create()
        .parent(Identifier.of("recipes/root"))
        .criterion("got_sticks", InventoryChangedCriterion.Conditions.items(Items.STICK))
        .rewards(AdvancementRewards.Builder
            .recipe(Identifier.of(Constants.BW_NAMESPACE, "wooden_dagger"))
            .addRecipe(Identifier.of(Constants.BW_NAMESPACE, "wooden_club"))
            .addRecipe(Identifier.of(Constants.BW_NAMESPACE, "wooden_club_variant"))
            .addRecipe(Identifier.of(Constants.BW_NAMESPACE, "wooden_hammer"))
            .addRecipe(Identifier.of(Constants.BW_NAMESPACE, "wooden_spear"))
            .addRecipe(Identifier.of(Constants.BW_NAMESPACE, "wooden_quarterstaff"))
            .addRecipe(Identifier.of(Constants.BW_NAMESPACE, "wooden_glaive"))

        )
        .build(advancementConsumer, Constants.BW_NAMESPACE + "/got_sticks");
    AdvancementEntry stone_weapons = Advancement.Builder.create()
        .parent(Identifier.of("recipes/root"))
        .criterion("got_cobblestone", InventoryChangedCriterion.Conditions.items(Items.COBBLESTONE))
        .rewards(AdvancementRewards.Builder
            .recipe(Identifier.of(Constants.BW_NAMESPACE, "stone_dagger"))
            .addRecipe(Identifier.of(Constants.BW_NAMESPACE, "stone_club"))
            .addRecipe(Identifier.of(Constants.BW_NAMESPACE, "stone_club_variant"))
            .addRecipe(Identifier.of(Constants.BW_NAMESPACE, "stone_hammer"))
            .addRecipe(Identifier.of(Constants.BW_NAMESPACE, "stone_spear"))
            .addRecipe(Identifier.of(Constants.BW_NAMESPACE, "stone_quarterstaff"))
            .addRecipe(Identifier.of(Constants.BW_NAMESPACE, "stone_glaive"))

        )
        .build(advancementConsumer, Constants.BW_NAMESPACE + "/got_cobblestone");
    AdvancementEntry iron_weapons = Advancement.Builder.create()
        .parent(Identifier.of("recipes/root"))
        .criterion("got_iron_ingot", InventoryChangedCriterion.Conditions.items(Items.IRON_INGOT))
        .rewards(AdvancementRewards.Builder
            .recipe(Identifier.of(Constants.BW_NAMESPACE, "iron_dagger"))
            .addRecipe(Identifier.of(Constants.BW_NAMESPACE, "iron_club"))
            .addRecipe(Identifier.of(Constants.BW_NAMESPACE, "iron_club_variant"))
            .addRecipe(Identifier.of(Constants.BW_NAMESPACE, "iron_hammer"))
            .addRecipe(Identifier.of(Constants.BW_NAMESPACE, "iron_spear"))
            .addRecipe(Identifier.of(Constants.BW_NAMESPACE, "iron_quarterstaff"))
            .addRecipe(Identifier.of(Constants.BW_NAMESPACE, "iron_glaive"))

        )
        .build(advancementConsumer, Constants.BW_NAMESPACE + "/got_iron_ingot");
    AdvancementEntry bronze_weapons = Advancement.Builder.create()
        .parent(Identifier.of("recipes/root"))
        .criterion("got_bronze_ingot", InventoryChangedCriterion.Conditions.items(ItemPredicate.Builder.create().tag(Constants.BRONZE_INGOTS)))
        .rewards(AdvancementRewards.Builder
            .recipe(Identifier.of(Constants.BRONZE_NAMESPACE, "bronze_dagger"))
            .addRecipe(Identifier.of(Constants.BRONZE_NAMESPACE, "bronze_club"))
            .addRecipe(Identifier.of(Constants.BRONZE_NAMESPACE, "bronze_club_variant"))
            .addRecipe(Identifier.of(Constants.BRONZE_NAMESPACE, "bronze_hammer"))
            .addRecipe(Identifier.of(Constants.BRONZE_NAMESPACE, "bronze_spear"))
            .addRecipe(Identifier.of(Constants.BRONZE_NAMESPACE, "bronze_quarterstaff"))
            .addRecipe(Identifier.of(Constants.BRONZE_NAMESPACE, "bronze_glaive"))

        )
        .build(advancementConsumer, Constants.BW_NAMESPACE + "/got_bronze_ingot");
    AdvancementEntry golden_weapons = Advancement.Builder.create()
        .parent(Identifier.of("recipes/root"))
        .criterion("got_gold_ingot", InventoryChangedCriterion.Conditions.items(Items.GOLD_INGOT))
        .rewards(AdvancementRewards.Builder
            .recipe(Identifier.of(Constants.BW_NAMESPACE, "golden_dagger"))
            .addRecipe(Identifier.of(Constants.BW_NAMESPACE, "golden_club"))
            .addRecipe(Identifier.of(Constants.BW_NAMESPACE, "golden_club_variant"))
            .addRecipe(Identifier.of(Constants.BW_NAMESPACE, "golden_hammer"))
            .addRecipe(Identifier.of(Constants.BW_NAMESPACE, "golden_spear"))
            .addRecipe(Identifier.of(Constants.BW_NAMESPACE, "golden_quarterstaff"))
            .addRecipe(Identifier.of(Constants.BW_NAMESPACE, "golden_glaive"))

        )
        .build(advancementConsumer, Constants.BW_NAMESPACE + "/got_gold_ingot");
    AdvancementEntry diamond_weapons = Advancement.Builder.create()
        .parent(Identifier.of("recipes/root"))
        .criterion("got_diamond", InventoryChangedCriterion.Conditions.items(Items.DIAMOND))
        .rewards(AdvancementRewards.Builder
            .recipe(Identifier.of(Constants.BW_NAMESPACE, "diamond_dagger"))
            .addRecipe(Identifier.of(Constants.BW_NAMESPACE, "diamond_club"))
            .addRecipe(Identifier.of(Constants.BW_NAMESPACE, "diamond_club_variant"))
            .addRecipe(Identifier.of(Constants.BW_NAMESPACE, "diamond_hammer"))
            .addRecipe(Identifier.of(Constants.BW_NAMESPACE, "diamond_spear"))
            .addRecipe(Identifier.of(Constants.BW_NAMESPACE, "diamond_quarterstaff"))
            .addRecipe(Identifier.of(Constants.BW_NAMESPACE, "diamond_glaive"))

        )
        .build(advancementConsumer, Constants.BW_NAMESPACE + "/got_diamond");
  }
}
