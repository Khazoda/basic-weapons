package com.seacroak.basicweapons.mixin.client;

import com.seacroak.basicweapons.Constants;
import com.seacroak.basicweapons.registry.MainRegistry;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.render.model.ModelLoader;
import net.minecraft.client.render.model.UnbakedModel;
import net.minecraft.client.render.model.json.JsonUnbakedModel;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Map;

@Mixin(ModelLoader.class)
public abstract class ModelLoaderMixin {
    @Shadow
    protected abstract void addModelToBake(ModelIdentifier modelId, UnbakedModel model);

    @Inject(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/model/ModelLoader;addModelToBake(Lnet/minecraft/client/util/ModelIdentifier;Lnet/minecraft/client/render/model/UnbakedModel;)V", ordinal = 0, shift = At.Shift.AFTER))
    public void putBakedModels(BlockColors blockColors, Profiler profiler, Map<Identifier, JsonUnbakedModel> jsonUnbakedModels, Map<Identifier, List<ModelLoader.SpriteGetter>> blockStates, CallbackInfo ci) {
        this.addModelToBake(ModelIdentifier.ofInventoryVariant(Identifier.of(Constants.BW_NAMESPACE, "wooden_spear_held")), jsonUnbakedModels.get(Identifier.of(Constants.BW_NAMESPACE, "models/item/wooden_spear_held.json")));
        this.addModelToBake(ModelIdentifier.ofInventoryVariant(Identifier.of(Constants.BW_NAMESPACE, "stone_spear_held")), jsonUnbakedModels.get(Identifier.of(Constants.BW_NAMESPACE, "models/item/stone_spear_held.json")));
        this.addModelToBake(ModelIdentifier.ofInventoryVariant(Identifier.of(Constants.BW_NAMESPACE, "iron_spear_held")), jsonUnbakedModels.get(Identifier.of(Constants.BW_NAMESPACE, "models/item/iron_spear_held.json")));
        this.addModelToBake(ModelIdentifier.ofInventoryVariant(Identifier.of(Constants.BW_NAMESPACE, "golden_spear_held")), jsonUnbakedModels.get(Identifier.of(Constants.BW_NAMESPACE, "models/item/golden_spear_held.json")));
        this.addModelToBake(ModelIdentifier.ofInventoryVariant(Identifier.of(Constants.BW_NAMESPACE, "diamond_spear_held")), jsonUnbakedModels.get(Identifier.of(Constants.BW_NAMESPACE, "models/item/diamond_spear_held.json")));
        this.addModelToBake(ModelIdentifier.ofInventoryVariant(Identifier.of(Constants.BW_NAMESPACE, "netherite_spear_held")), jsonUnbakedModels.get(Identifier.of(Constants.BW_NAMESPACE, "models/item/netherite_spear_held.json")));
        this.addModelToBake(ModelIdentifier.ofInventoryVariant(Identifier.of(Constants.BW_NAMESPACE, "wooden_quarterstaff_held")), jsonUnbakedModels.get(Identifier.of(Constants.BW_NAMESPACE, "models/item/wooden_quarterstaff_held.json")));
        this.addModelToBake(ModelIdentifier.ofInventoryVariant(Identifier.of(Constants.BW_NAMESPACE, "stone_quarterstaff_held")), jsonUnbakedModels.get(Identifier.of(Constants.BW_NAMESPACE, "models/item/stone_quarterstaff_held.json")));
        this.addModelToBake(ModelIdentifier.ofInventoryVariant(Identifier.of(Constants.BW_NAMESPACE, "iron_quarterstaff_held")), jsonUnbakedModels.get(Identifier.of(Constants.BW_NAMESPACE, "models/item/iron_quarterstaff_held.json")));
        this.addModelToBake(ModelIdentifier.ofInventoryVariant(Identifier.of(Constants.BW_NAMESPACE, "golden_quarterstaff_held")), jsonUnbakedModels.get(Identifier.of(Constants.BW_NAMESPACE, "models/item/golden_quarterstaff_held.json")));
        this.addModelToBake(ModelIdentifier.ofInventoryVariant(Identifier.of(Constants.BW_NAMESPACE, "diamond_quarterstaff_held")), jsonUnbakedModels.get(Identifier.of(Constants.BW_NAMESPACE, "models/item/diamond_quarterstaff_held.json")));
        this.addModelToBake(ModelIdentifier.ofInventoryVariant(Identifier.of(Constants.BW_NAMESPACE, "netherite_quarterstaff_held")), jsonUnbakedModels.get(Identifier.of(Constants.BW_NAMESPACE, "models/item/netherite_quarterstaff_held.json")));
      this.addModelToBake(ModelIdentifier.ofInventoryVariant(Identifier.of(Constants.BW_NAMESPACE, "wooden_glaive_held")), jsonUnbakedModels.get(Identifier.of(Constants.BW_NAMESPACE, "models/item/wooden_glaive_held.json")));
      this.addModelToBake(ModelIdentifier.ofInventoryVariant(Identifier.of(Constants.BW_NAMESPACE, "stone_glaive_held")), jsonUnbakedModels.get(Identifier.of(Constants.BW_NAMESPACE, "models/item/stone_glaive_held.json")));
      this.addModelToBake(ModelIdentifier.ofInventoryVariant(Identifier.of(Constants.BW_NAMESPACE, "iron_glaive_held")), jsonUnbakedModels.get(Identifier.of(Constants.BW_NAMESPACE, "models/item/iron_glaive_held.json")));
      this.addModelToBake(ModelIdentifier.ofInventoryVariant(Identifier.of(Constants.BW_NAMESPACE, "golden_glaive_held")), jsonUnbakedModels.get(Identifier.of(Constants.BW_NAMESPACE, "models/item/golden_glaive_held.json")));
      this.addModelToBake(ModelIdentifier.ofInventoryVariant(Identifier.of(Constants.BW_NAMESPACE, "diamond_glaive_held")), jsonUnbakedModels.get(Identifier.of(Constants.BW_NAMESPACE, "models/item/diamond_glaive_held.json")));
      this.addModelToBake(ModelIdentifier.ofInventoryVariant(Identifier.of(Constants.BW_NAMESPACE, "netherite_glaive_held")), jsonUnbakedModels.get(Identifier.of(Constants.BW_NAMESPACE, "models/item/netherite_glaive_held.json")));
        if (MainRegistry.bronze_mod_loaded) {
            this.addModelToBake(ModelIdentifier.ofInventoryVariant(Identifier.of(Constants.BW_NAMESPACE, "bronze_spear_held")), jsonUnbakedModels.get(Identifier.of(Constants.BW_NAMESPACE, "models/item/bronze_spear_held.json")));
            this.addModelToBake(ModelIdentifier.ofInventoryVariant(Identifier.of(Constants.BW_NAMESPACE, "bronze_quarterstaff_held")), jsonUnbakedModels.get(Identifier.of(Constants.BW_NAMESPACE, "models/item/bronze_quarterstaff_held.json")));
          this.addModelToBake(ModelIdentifier.ofInventoryVariant(Identifier.of(Constants.BW_NAMESPACE, "bronze_glaive_held")), jsonUnbakedModels.get(Identifier.of(Constants.BW_NAMESPACE, "models/item/bronze_glaive_held.json")));
        }
    }
}