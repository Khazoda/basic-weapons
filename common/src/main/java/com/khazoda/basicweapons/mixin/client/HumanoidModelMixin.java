package com.khazoda.basicweapons.mixin.client;

import com.khazoda.basicweapons.client.PolearmAnimations;
import com.khazoda.basicweapons.registry.TagRegistry;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HumanoidModel.class)
public class HumanoidModelMixin<T extends HumanoidRenderState> {

  @Unique
  @SuppressWarnings("unchecked")
  HumanoidModel<T> bw$humanoidModel = (HumanoidModel<T>) (Object) this;

  @Final
  @Shadow
  public ModelPart rightArm;
  @Final
  @Shadow
  public ModelPart leftArm;
  @Final
  @Shadow
  public ModelPart head;

  @Unique
  private boolean bw$isPike(ItemStack stack) {
    return !stack.isEmpty() && stack.is(TagRegistry.PIKE);
  }

  @Inject(method = "poseRightArm", at = @At("RETURN"))
  private void poseRightArmForPike(T state, CallbackInfo ci) {
    ItemStack stack = state.rightHandItemStack;
    if (bw$isPike(stack) && !state.isUsingItem) {
      PolearmAnimations.animatePose(this.rightArm, this.head, true);
    }
  }

  @Inject(method = "poseLeftArm", at = @At("RETURN"))
  private void poseLeftArmForPike(T state, CallbackInfo ci) {
    ItemStack stack = state.leftHandItemStack;
    if (bw$isPike(stack) && !state.isUsingItem) {
      PolearmAnimations.animatePose(this.leftArm, this.head, false);
    }
  }

  @Inject(method = "setupAttackAnimation", at = @At("HEAD"), cancellable = true)
  private void setupPikeAttackAnimation(T state, CallbackInfo ci) {
    if (state.attackTime > 0) {
      boolean isRightHandAttack = (state.attackArm == HumanoidArm.RIGHT);
      ItemStack attackingStack = isRightHandAttack ? state.rightHandItemStack : state.leftHandItemStack;

      if (bw$isPike(attackingStack)) {
        PolearmAnimations.animateThirdPersonAttack(bw$humanoidModel, state);
        ci.cancel();
      }
    }
  }
}