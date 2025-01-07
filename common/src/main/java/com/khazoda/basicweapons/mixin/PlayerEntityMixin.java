package com.khazoda.basicweapons.mixin;

import com.khazoda.basicweapons.mixinutils.PlayerEntityAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public abstract class PlayerEntityMixin implements PlayerEntityAccessor {

  /*** Pull attack cooldown for use in custom logic based on it, such as hammer swing & club bash ***/
  @Unique
  protected float basic_weapons$attackCooldownProgress = 0.0f;

  @Shadow
  public abstract float getAttackStrengthScale(float baseTime);

  @Override
  public float bw$getCooldown(float baseTime) {
    return this.basic_weapons$attackCooldownProgress;
  }

  @Inject(method = "attack", at = @At("HEAD"))
  private void retrieveCooldownEarly(Entity target, CallbackInfo ci) {
    basic_weapons$attackCooldownProgress = this.getAttackStrengthScale(0.5f);
  }
}