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

  @Shadow
  public abstract float getAttackStrengthScale(float baseTime);

  @Unique
  public float bw$lastAttackStrength = 0;

  @Override
  public float bw$getLastAttackStrength() {
    return bw$lastAttackStrength;
  }

  @Inject(method = "attack", at = @At("HEAD"))
  public void bw$captureAttackStrength(Entity target, CallbackInfo ci) {
    this.bw$lastAttackStrength = this.getAttackStrengthScale(0.5f);
  }
}