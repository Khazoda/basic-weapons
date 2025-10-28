package com.khazoda.basicweapons.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class BasicWeaponsRecipeProvider extends FabricRecipeProvider {

  public BasicWeaponsRecipeProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
    super(output, registriesFuture);
  }

  @Override
  protected @NotNull RecipeProvider createRecipeProvider(HolderLookup.Provider registryLookup, RecipeOutput exporter) {
    return new RecipeProvider(registryLookup, exporter) {

      @Override
      public void buildRecipes() {
        HolderLookup.RegistryLookup<Item> itemLookup = registryLookup.lookupOrThrow(Registries.ITEM);
        /* Todo: Implement this whole thing one day when you figure out how to do mojmap in fabric datagen for recipes.. */
      }
    };
  }

  @Override
  public @NotNull String getName() {
    return "Basic Weapons Recipe Provider";
  }
}