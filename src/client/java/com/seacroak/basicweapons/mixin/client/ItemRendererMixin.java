package com.seacroak.basicweapons.mixin.client;

import com.seacroak.basicweapons.Constants;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import static com.seacroak.basicweapons.registry.MainRegistry.registeredItems;

@Mixin(ItemRenderer.class)
public abstract class ItemRendererMixin {
  @ModifyVariable(method = "renderItem", at = @At(value = "HEAD"), argsOnly = true)
  public BakedModel useHeldModels(BakedModel value, ItemStack stack, ModelTransformationMode renderMode, boolean leftHanded, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
    if (renderMode == ModelTransformationMode.GUI || renderMode == ModelTransformationMode.GROUND || renderMode == ModelTransformationMode.FIXED)
      return value;
    boolean flag = false;
    String resourceLocation = "";
    Item item = stack.getItem();

    for (var entry : registeredItems.entrySet()) {
      if (entry.getValue().item == item) {
        flag = switch (item.getTranslationKey()) {
          /* Spears */
          case "item.basicweapons.wooden_spear" -> {
            resourceLocation = "wooden_spear_held";
            yield true;
          }
          case "item.basicweapons.stone_spear" -> {
            resourceLocation = "stone_spear_held";
            yield true;
          }
          case "item.basicweapons.iron_spear" -> {
            resourceLocation = "iron_spear_held";
            yield true;
          }
          case "item.basicweapons.golden_spear" -> {
            resourceLocation = "golden_spear_held";
            yield true;
          }
          case "item.basicweapons.diamond_spear" -> {
            resourceLocation = "diamond_spear_held";
            yield true;
          }
          case "item.basicweapons.netherite_spear" -> {
            resourceLocation = "netherite_spear_held";
            yield true;
          }

          /* Quarterstaves */
          case "item.basicweapons.wooden_quarterstaff" -> {
            resourceLocation = "wooden_quarterstaff_held";
            yield true;
          }
          case "item.basicweapons.stone_quarterstaff" -> {
            resourceLocation = "stone_quarterstaff_held";
            yield true;
          }
          case "item.basicweapons.iron_quarterstaff" -> {
            resourceLocation = "iron_quarterstaff_held";
            yield true;
          }
          case "item.basicweapons.golden_quarterstaff" -> {
            resourceLocation = "golden_quarterstaff_held";
            yield true;
          }
          case "item.basicweapons.diamond_quarterstaff" -> {
            resourceLocation = "diamond_quarterstaff_held";
            yield true;
          }
          case "item.basicweapons.netherite_quarterstaff" -> {
            resourceLocation = "netherite_quarterstaff_held";
            yield true;
          }

          /* Glaives */
          case "item.basicweapons.wooden_glaive" -> {
            resourceLocation = "wooden_glaive_held";
            yield true;
          }
          case "item.basicweapons.stone_glaive" -> {
            resourceLocation = "stone_glaive_held";
            yield true;
          }
          case "item.basicweapons.iron_glaive" -> {
            resourceLocation = "iron_glaive_held";
            yield true;
          }
          case "item.basicweapons.golden_glaive" -> {
            resourceLocation = "golden_glaive_held";
            yield true;
          }
          case "item.basicweapons.diamond_glaive" -> {
            resourceLocation = "diamond_glaive_held";
            yield true;
          }
          case "item.basicweapons.netherite_glaive" -> {
            resourceLocation = "netherite_glaive_held";
            yield true;
          }

          /* Bronze mod integration */
          case "item.basicweapons.bronze_spear" -> {
            resourceLocation = "bronze_spear_held";
            yield true;
          }
          case "item.basicweapons.bronze_quarterstaff" -> {
            resourceLocation = "bronze_quarterstaff_held";
            yield true;
          }
          case "item.basicweapons.bronze_glaive" -> {
            resourceLocation = "bronze_glaive_held";
            yield true;
          }
          default -> false;
        };
      }
    }

    if (flag) {
      ModelIdentifier a = new ModelIdentifier(Identifier.of(Constants.BW_NAMESPACE, resourceLocation), "inventory");
      BakedModel x = ((ItemRendererAccessor) this).bw$getModels().getModelManager().getModel(a);
      return x;
    } else {
      return value;
    }
  }
}