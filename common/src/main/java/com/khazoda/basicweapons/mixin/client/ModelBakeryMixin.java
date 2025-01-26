package com.khazoda.basicweapons.mixin.client;

import com.khazoda.basicweapons.materialpack.MaterialPackLoader;
import com.khazoda.basicweapons.registry.WeaponRegistry;
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
      // Register vanilla materials
      for (WeaponRegistry.MaterialEntry material : WeaponRegistry.VANILLA_MATERIALS) {
        basic_weapons$registerMaterialWeaponModels(material.prefix());
      }

      // Register datapack materials
      for (String materialName : MaterialPackLoader.getMaterialNames()) {
        basic_weapons$registerMaterialWeaponModels(materialName);
      }
    } catch (Exception e) {
      LOG.error("basicweapons: Error registering held models", e);
    }
  }

  @Unique
  private void basic_weapons$registerMaterialWeaponModels(String materialPrefix) {
    // Register models for weapon types that need held variants
    basic_weapons$registerHeldModel(materialPrefix + "_spear");
    basic_weapons$registerHeldModel(materialPrefix + "_quarterstaff");
    basic_weapons$registerHeldModel(materialPrefix + "_glaive");
  }

  @Unique
  private void basic_weapons$registerHeldModel(String baseName) {
    try {
      // First check if the base model exists
      ResourceLocation baseModelLoc = ID("item/" + baseName);
      UnbakedModel baseModel = getModel(baseModelLoc);

      if (baseModel != null) {
        ResourceLocation modelLoc = ID("item/" + baseName + "_held");
        ModelResourceLocation modelId = new ModelResourceLocation(ID(baseName + "_held"), "inventory");

        // If held model doesn't exist, create one based on the handheld_big_quarterstaff parent
        UnbakedModel heldModel = getModel(modelLoc);
        if (heldModel == null) {
          // Use the base model's texture with the handheld_big_quarterstaff parent
          BlockModel parentModel = modelResources.get(ID("models/item/handheld_big_quarterstaff.json"));
          if (parentModel != null) {
            heldModel = parentModel;
          }
        }

        if (heldModel != null) {
          registerModel(modelId, heldModel);
        }
      }
    } catch (Exception e) {
      LOG.error("basicweapons: Failed to register held model for {}", baseName, e);
    }
  }
} 