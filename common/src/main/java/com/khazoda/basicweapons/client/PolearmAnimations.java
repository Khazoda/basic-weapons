package com.khazoda.basicweapons.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;

public class PolearmAnimations {

    public static void animateFirstPersonAttack(PoseStack poseStack, float t) {
        float extension, twist, pitch;

        if (t < 0.1f) {
            float ease = (t * 10f) * (t * 10f);
            extension = -0.15f * ease;
            twist = 15.0f * ease;
            pitch = 10.0f * ease;
        } else if (t < 0.25f) {
            float p = (t - 0.1f) / 0.15f;
            float ease = 1 + 2.70158f * (p - 1) * (p - 1) * (p - 1) + 1.70158f * (p - 1) * (p - 1);
            extension = Mth.lerp(ease, -0.15f, 0.75f);
            twist = Mth.lerp(ease, 15.0f, -60.0f);
            pitch = Mth.lerp(ease, 10.0f, -5.0f);
        } else {
            float p = (t - 0.25f) / 0.75f;
            float ease = (1.0f - p) * (1.0f - p);
            extension = 0.75f * ease;
            twist = -60.0f * ease;
            pitch = -5.0f * ease;
        }

        poseStack.translate(0, extension * 0.2f, -extension);
        poseStack.mulPose(Axis.ZP.rotationDegrees(twist));
        poseStack.mulPose(Axis.YP.rotationDegrees(twist * 0.1f));
        poseStack.mulPose(Axis.XP.rotationDegrees(pitch));
    }

    public static void animatePose(ModelPart arm, ModelPart body, boolean isRightArm) {
        arm.yRot = -0.1F * (isRightArm ? 1 : -1) + body.yRot;
        arm.xRot = -Mth.HALF_PI + body.xRot + 1.0F;
    }

    public static void animateThirdPersonAttack(HumanoidModel<?> model, HumanoidRenderState state) {
        float swing = 1.0F - state.attackTime;
        swing = 1.0F - (swing * swing * swing * swing);
        
        float rot = Mth.sin(swing * Mth.PI) * -0.6F;
        (state.attackArm == HumanoidArm.RIGHT ? model.rightArm : model.leftArm).xRot += rot;
        (state.attackArm == HumanoidArm.RIGHT ? model.leftArm : model.rightArm).xRot += rot * 0.5F;
        model.body.yRot = Mth.sin(swing * Mth.PI) * 0.2F * (state.attackArm == HumanoidArm.RIGHT ? -1.0F : 1.0F);
    }
}