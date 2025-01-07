package com.khazoda.basicweapons.mixin.client;

import com.khazoda.basicweapons.BasicWeaponsCommon;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.client.resources.model.BlockStateModelLoader;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.client.resources.model.UnbakedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.profiling.ProfilerFiller;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Map;

import static com.khazoda.basicweapons.Constants.ID;
import static com.khazoda.basicweapons.Constants.LOG;

/* Remove in 1.21.4 in favour of new item model loading system */
@Mixin(ModelBakery.class)
public abstract class ModelBakeryMixin {
  @Shadow
  protected abstract void registerModel(ModelResourceLocation modelId, UnbakedModel model);

  @Shadow
  protected abstract UnbakedModel getModel(ResourceLocation location);

  @Shadow
  @Final
  private Map<ResourceLocation, BlockModel> modelResources;

  @SuppressWarnings("all") //Suppresses @Inject incorrect error
  @Inject(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/profiling/ProfilerFiller;popPush(Ljava/lang/String;)V", ordinal = 0))
  private void registerCustomModels(BlockColors blockColors, ProfilerFiller profilerFiller, Map<ResourceLocation, BlockModel> modelResources, Map<ResourceLocation, List<BlockStateModelLoader.LoadedJson>> blockStateResources, CallbackInfo ci) {
    try {
      // Spears
      basic_weapons$registerHeldModel("wooden_spear");
      basic_weapons$registerHeldModel("stone_spear");
      basic_weapons$registerHeldModel("iron_spear");
      basic_weapons$registerHeldModel("golden_spear");
      basic_weapons$registerHeldModel("diamond_spear");
      basic_weapons$registerHeldModel("netherite_spear");

      // Quarterstaves
      basic_weapons$registerHeldModel("wooden_quarterstaff");
      basic_weapons$registerHeldModel("stone_quarterstaff");
      basic_weapons$registerHeldModel("iron_quarterstaff");
      basic_weapons$registerHeldModel("golden_quarterstaff");
      basic_weapons$registerHeldModel("diamond_quarterstaff");
      basic_weapons$registerHeldModel("netherite_quarterstaff");

      // Glaives
      basic_weapons$registerHeldModel("wooden_glaive");
      basic_weapons$registerHeldModel("stone_glaive");
      basic_weapons$registerHeldModel("iron_glaive");
      basic_weapons$registerHeldModel("golden_glaive");
      basic_weapons$registerHeldModel("diamond_glaive");
      basic_weapons$registerHeldModel("netherite_glaive");

      if (BasicWeaponsCommon.bronze_mod_loaded) {
        basic_weapons$registerHeldModel("bronze_spear");
        basic_weapons$registerHeldModel("bronze_quarterstaff");
        basic_weapons$registerHeldModel("bronze_glaive");
      }
    } catch (Exception e) {
      LOG.error("basicweapons: Error registering held models", e);
    }
  }

  @Unique
  private void basic_weapons$registerHeldModel(String baseName) {
    try {
      ResourceLocation modelLoc = ID("models/item/" + baseName + "_held.json");
      ModelResourceLocation modelId = new ModelResourceLocation(ID(baseName + "_held"), "inventory");
      UnbakedModel model = modelResources.get(modelLoc);
      registerModel(modelId, model);
    } catch (Exception e) {
      LOG.error("basicweapons: Failed to register held model for {}", baseName, e);
    }
  }
} 