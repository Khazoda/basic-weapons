package com.seacroak.basicweapons.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.seacroak.basicweapons.item.BasicWeaponSweeplessItem;
import com.seacroak.basicweapons.mixinutils.PlayerEntityAccessor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin implements PlayerEntityAccessor {

  /*** Pull attack cooldown for use in custom logic based on it, such as hammer swing & club bash ***/
  @Unique
  protected float attackCooldownProgress = 0.0f;

  @Shadow
  public abstract float getAttackCooldownProgress(float baseTime);

  @Override
  public float bw$getCooldown(float baseTime) {
    return this.attackCooldownProgress;
  }

  @Inject(method = "attack", at = @At("HEAD"))
  private void retrieveCooldownEarly(Entity target, CallbackInfo ci) {
    attackCooldownProgress = this.getAttackCooldownProgress(0.5f);
  }

  /*** Cursed mixins to prevent sweeping on BasicWeaponItems ***/
  @WrapOperation(method = "attack", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/player/PlayerEntity;horizontalSpeed:F", opcode = Opcodes.GETFIELD))
  private float alterHorizontalSpeedToSneakilyPreventSweeping(PlayerEntity instance, Operation<Float> original) {
    if (instance.getStackInHand(Hand.MAIN_HAND).getItem() instanceof BasicWeaponSweeplessItem)
      return 10f;
    return original.call(instance);
  }

  @WrapOperation(method = "attack", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/player/PlayerEntity;prevHorizontalSpeed:F", opcode = Opcodes.GETFIELD))
  private float alterPreviousHorizontalSpeedToSneakilyPreventSweeping(PlayerEntity instance, Operation<Float> original) {
    if (instance.getStackInHand(Hand.MAIN_HAND).getItem() instanceof BasicWeaponSweeplessItem)
      return 0f;
    return original.call(instance);
  }

}