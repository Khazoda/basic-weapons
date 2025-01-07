package com.khazoda.basicweapons.mixin.client;

import com.khazoda.basicweapons.registry.WeaponRegistry;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import static com.khazoda.basicweapons.Constants.ID;

/* Remove in 1.21.4 in favour of new item model loading system */
@Mixin(ItemRenderer.class)
public abstract class ItemRendererMixin {
  @ModifyVariable(method = "render", at = @At(value = "HEAD"), argsOnly = true)
  public BakedModel useHeldModels(BakedModel value, ItemStack itemStack, ItemDisplayContext displayContext, boolean leftHand, PoseStack poseStack, MultiBufferSource bufferSource, int combinedLight, int combinedOverlay, BakedModel model) {
    if (displayContext == ItemDisplayContext.GUI || displayContext == ItemDisplayContext.GROUND || displayContext == ItemDisplayContext.FIXED)
      return value;

    boolean flag = false;
    String resourceLocation = "";
    Item item = itemStack.getItem();

    for (Item entry : WeaponRegistry.getAllItems()) {
      if (entry == item) {
        flag = switch (item.getDescriptionId()) {
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
      ModelResourceLocation modelId = new ModelResourceLocation(ID(resourceLocation), "inventory");
      BakedModel heldModel = ((ItemRendererAccessor) this).bw$getItemModelShaper().getModelManager().getModel(modelId);
      return heldModel;
    } else {
      return value;
    }
  }
}