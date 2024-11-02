package com.seacroak.basicweapons.mixin;

import com.seacroak.basicweapons.mixinutils.PlayerEntityAccessor;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin implements PlayerEntityAccessor {

  /*** Pull attack cooldown for use in custom logic based on it, such as hammer swing & club bash ***/
  @Unique
  protected float attackCooldownProgress = 0.0f;

  @Shadow
  public abstract float getAttackCooldownProgress(float baseTime);

  @Shadow
  public abstract float getMovementSpeed();

  @Override
  public float bw$getCooldown(float baseTime) {
    return this.attackCooldownProgress;
  }

}