package com.khazoda.basicweapons.mixin.client;

import com.khazoda.basicweapons.client.PolearmAnimations;
import com.khazoda.basicweapons.registry.TagRegistry;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.khazoda.basicweapons.platform.Services;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(ItemInHandRenderer.class)
public abstract class ItemInHandRendererMixin {

  @Shadow
  public abstract void renderItem(net.minecraft.world.entity.LivingEntity entity, ItemStack stack, ItemDisplayContext displayContext, PoseStack poseStack, SubmitNodeCollector nodeCollector, int packedLight);

  @Unique
  private boolean bw$isPike(ItemStack stack) {
    return !stack.isEmpty() && stack.is(TagRegistry.PIKE);
  }

  @Inject(method = "renderArmWithItem", at = @At("HEAD"), cancellable = true)
  private void bw$renderPike(AbstractClientPlayer player, float partialTick, float pitch, InteractionHand hand, float swingProgress, ItemStack stack, float equipProgress, PoseStack poseStack, SubmitNodeCollector nodeCollector, int packedLight, CallbackInfo ci) {

    // Let locomotion handle rendering if it's loaded
    if (Services.PLATFORM.isModLoaded("locomotion")) {
      return;
    }

    if (bw$isPike(stack)) {
      HumanoidArm humanoidArm = (hand == InteractionHand.MAIN_HAND) ? player.getMainArm() : player.getMainArm().getOpposite();
      boolean isRightHand = (humanoidArm == HumanoidArm.RIGHT);
      int side = isRightHand ? 1 : -1;

      poseStack.pushPose();

      /* Base Transform */
      float xBase = side * 0.36F;
      float yBase = -0.32F + (equipProgress * -0.6F);
      float zBase = -0.42F;
      poseStack.translate(xBase, yBase, zBase);
      poseStack.mulPose(Axis.XP.rotationDegrees(20F));

      if (swingProgress > 0.0F) {
        PolearmAnimations.animateFirstPersonAttack(poseStack, swingProgress);
      }

      this.renderItem(player, stack, isRightHand ? ItemDisplayContext.FIRST_PERSON_RIGHT_HAND : ItemDisplayContext.FIRST_PERSON_LEFT_HAND, poseStack, nodeCollector, packedLight);

      poseStack.popPose();

      ci.cancel();
    }
  }
}