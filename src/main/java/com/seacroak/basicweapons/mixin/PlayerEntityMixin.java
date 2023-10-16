package com.seacroak.basicweapons.mixin;

import com.seacroak.basicweapons.mixinutils.PlayerEntityAccessor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
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
}