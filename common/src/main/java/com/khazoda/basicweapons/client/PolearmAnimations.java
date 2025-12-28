package com.khazoda.basicweapons.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.util.Ease;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;

public class PolearmAnimations {

  static float progress(float time, float start, float end) {
    return Mth.clamp(Mth.inverseLerp(time, start, end), 0.0F, 1.0F);
  }

  public static void animateFirstPersonAttack(PoseStack poseStack, float t) {
    float pullBackAmount = -0.2F;
    float thrustDist = 0.5F;

    float windup = Ease.inOutSine(progress(t, 0.0F, 0.2F));
    float thrust = Ease.outBack(progress(t, 0.2F, 0.6F));
    float retract = Ease.inOutQuad(progress(t, 0.6F, 1.0F));

    float currentExtension = (windup * pullBackAmount)
        + (thrust * (thrustDist - pullBackAmount))
        - (retract * thrustDist);

    poseStack.translate(0.0F, 0.0F, -currentExtension);
  }

  public static void animatePose(ModelPart arm, ModelPart body, boolean isRightArm) {
    int i = isRightArm ? 1 : -1;
    arm.yRot = -0.1F * (float) i + body.yRot;
    arm.xRot = -Mth.HALF_PI + body.xRot + 1.0F;
  }

  public static void animateThirdPersonAttack(HumanoidModel<?> model, HumanoidRenderState state) {
    float f = state.attackTime;
    HumanoidArm attackArm = state.attackArm;

    ModelPart armMain = attackArm == HumanoidArm.RIGHT ? model.rightArm : model.leftArm;
    ModelPart armOffhand = attackArm == HumanoidArm.RIGHT ? model.leftArm : model.rightArm;

    float swing = 1.0F - f;
    swing *= swing;
    swing *= swing;
    swing = 1.0F - swing;

    float rot = Mth.sin(swing * Mth.PI) * -0.6F;
    armMain.xRot += rot;
    armOffhand.xRot += rot * 0.5F;

    model.body.yRot = Mth.sin(swing * Mth.PI) * 0.2F * (attackArm == HumanoidArm.RIGHT ? -1.0F : 1.0F);
  }
}