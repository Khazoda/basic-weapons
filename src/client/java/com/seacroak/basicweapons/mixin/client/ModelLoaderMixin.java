package com.seacroak.basicweapons.mixin.client;

import com.seacroak.basicweapons.Constants;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.render.model.ModelLoader;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.profiler.Profiler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ModelLoader.class)
public abstract class ModelLoaderMixin {
  @Shadow
  protected abstract void addModel(ModelIdentifier modelId);

  @Inject(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/model/ModelLoader;addModel(Lnet/minecraft/client/util/ModelIdentifier;)V", ordinal = 3, shift = At.Shift.AFTER))

  public void addWoodenSpear(ResourceManager resourceManager, BlockColors blockColors, Profiler profiler, int mipmapLevel, CallbackInfo ci) {
    this.addModel(new ModelIdentifier(Constants.BW_ID, "wooden_spear_held", "inventory"));
    this.addModel(new ModelIdentifier(Constants.BW_ID, "stone_spear_held", "inventory"));
    this.addModel(new ModelIdentifier(Constants.BW_ID, "iron_spear_held", "inventory"));
    this.addModel(new ModelIdentifier(Constants.BW_ID, "golden_spear_held", "inventory"));
    this.addModel(new ModelIdentifier(Constants.BW_ID, "diamond_spear_held", "inventory"));
    this.addModel(new ModelIdentifier(Constants.BW_ID, "netherite_spear_held", "inventory"));
    this.addModel(new ModelIdentifier(Constants.BW_ID, "wooden_quarterstaff_held", "inventory"));
    this.addModel(new ModelIdentifier(Constants.BW_ID, "stone_quarterstaff_held", "inventory"));
    this.addModel(new ModelIdentifier(Constants.BW_ID, "iron_quarterstaff_held", "inventory"));
    this.addModel(new ModelIdentifier(Constants.BW_ID, "golden_quarterstaff_held", "inventory"));
    this.addModel(new ModelIdentifier(Constants.BW_ID, "diamond_quarterstaff_held", "inventory"));
    this.addModel(new ModelIdentifier(Constants.BW_ID, "netherite_quarterstaff_held", "inventory"));
  }

}