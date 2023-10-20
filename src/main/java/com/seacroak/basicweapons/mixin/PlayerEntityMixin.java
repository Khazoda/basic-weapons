package com.seacroak.basicweapons.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.seacroak.basicweapons.item.BasicWeaponItem;
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


//  @ModifyVariable(method = "attack", at = @At("STORE"), ordinal = 15)
//  public double modifyAttackSpeed(double x) {
//    PlayerEntity pe = (PlayerEntity) (Object) this;
//    return pe.getMovementSpeed() +x + 0.1f;
//  }

  @WrapOperation(method = "attack", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/player/PlayerEntity;horizontalSpeed:F", opcode = Opcodes.GETFIELD))
  private float alterHorizontalSpeedToSneakilyPreventSweeping(PlayerEntity instance, Operation<Float> original) {
    if (instance.getStackInHand(Hand.MAIN_HAND).getItem() instanceof BasicWeaponItem)
      return 10f;
    return original.call(instance);
  }

  @WrapOperation(method = "attack", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/player/PlayerEntity;prevHorizontalSpeed:F", opcode = Opcodes.GETFIELD))
  private float alterPreviousHorizontalSpeedToSneakilyPreventSweeping(PlayerEntity instance, Operation<Float> original) {
    if (instance.getStackInHand(Hand.MAIN_HAND).getItem() instanceof BasicWeaponItem)
      return 0f;
    return original.call(instance);
  }

//  @Inject(method = "tick()V", at = @At("HEAD"))
//  private void amogus(CallbackInfo ci) {
//    PlayerEntity t = ((PlayerEntity) (Object) this);
//    float f = t.horizontalSpeed;
//    float g = t.prevHorizontalSpeed;
//    System.out.println("S: " + f + " P: " + g + " T: " + (f - g));
//  }
}