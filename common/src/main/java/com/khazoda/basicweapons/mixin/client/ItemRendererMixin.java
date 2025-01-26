package com.khazoda.basicweapons.mixin.client;

import com.khazoda.basicweapons.Constants;
import com.khazoda.basicweapons.item.BasicWeaponItem;
import com.khazoda.basicweapons.item.BasicWeaponSweeplessItem;
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

    Item item = itemStack.getItem();
    if (!(item instanceof BasicWeaponItem || item instanceof BasicWeaponSweeplessItem)) {
      return value;
    }

    String itemId = item.getDescriptionId()
        .replace("item." + Constants.MOD_ID + ".", "");

    // Remove material prefix (wooden_, iron_) to get base weapon type
    String baseType = itemId.substring(itemId.indexOf('_') + 1);

    if (baseType.equals("spear") || baseType.equals("quarterstaff") || baseType.equals("glaive")) {
      ModelResourceLocation modelId = new ModelResourceLocation(
          ID(itemId + "_held"), "inventory");
      // Never inline heldModel as the return value. For some reason that breaks things!
      BakedModel heldModel = ((ItemRendererAccessor) this)
          .bw$getItemModelShaper()
          .getModelManager()
          .getModel(modelId);
      return heldModel;
    }
    return value;
  }
}