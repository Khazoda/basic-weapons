package com.seacroak.basicweapons.mixin.client;

import com.seacroak.basicweapons.Constants;
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
        Identifier x = Identifier.of(Constants.BW_NAMESPACE, "models/item/wooden_spear_held.json");
        Identifier y = Identifier.of("wooden_spear_held");
        JsonUnbakedModel z = jsonUnbakedModels.get(Identifier.of(Constants.BW_NAMESPACE, "models/item/wooden_spear_held.json"));

        this.addModelToBake(ModelIdentifier.ofInventoryVariant(Identifier.of(Constants.BW_NAMESPACE, "wooden_spear_held")), jsonUnbakedModels.get(x));
//        this.addModelToBake(ModelIdentifier.ofInventoryVariant(Constants.BW_ID.withPath("stone_spear_held")), jsonUnbakedModels.get(Identifier.of("stone_spear_held")));
//        this.addModelToBake(ModelIdentifier.ofInventoryVariant(Constants.BW_ID.withPath("iron_spear_held")), jsonUnbakedModels.get(Identifier.of("iron_spear_held")));
//        this.addModelToBake(ModelIdentifier.ofInventoryVariant(Constants.BW_ID.withPath("golden_spear_held")), jsonUnbakedModels.get(Identifier.of("golden_spear_held")));
//        this.addModelToBake(ModelIdentifier.ofInventoryVariant(Constants.BW_ID.withPath("diamond_spear_held")), jsonUnbakedModels.get(Identifier.of("diamond_spear_held")));
//        this.addModelToBake(ModelIdentifier.ofInventoryVariant(Constants.BW_ID.withPath("netherite_spear_held")), jsonUnbakedModels.get(Identifier.of("netherite_spear_held")));
//        this.addModelToBake(ModelIdentifier.ofInventoryVariant(Constants.BW_ID.withPath("wooden_quarterstaff_held")), jsonUnbakedModels.get(Identifier.of("wooden_quarterstaff_held")));
//        this.addModelToBake(ModelIdentifier.ofInventoryVariant(Constants.BW_ID.withPath("stone_quarterstaff_held")), jsonUnbakedModels.get(Identifier.of("stone_quarterstaff_held")));
//        this.addModelToBake(ModelIdentifier.ofInventoryVariant(Constants.BW_ID.withPath("iron_quarterstaff_held")), jsonUnbakedModels.get(Identifier.of("iron_quarterstaff_held")));
//        this.addModelToBake(ModelIdentifier.ofInventoryVariant(Constants.BW_ID.withPath("golden_quarterstaff_held")), jsonUnbakedModels.get(Identifier.of("golden_quarterstaff_held")));
//        this.addModelToBake(ModelIdentifier.ofInventoryVariant(Constants.BW_ID.withPath("diamond_quarterstaff_held")), jsonUnbakedModels.get(Identifier.of("diamond_quarterstaff_held")));
//        this.addModelToBake(ModelIdentifier.ofInventoryVariant(Constants.BW_ID.withPath("netherite_quarterstaff_held")), jsonUnbakedModels.get(Identifier.of("netherite_quarterstaff_held")));
    }

}