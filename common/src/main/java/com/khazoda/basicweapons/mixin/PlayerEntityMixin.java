package com.khazoda.basicweapons.mixin;

import com.khazoda.basicweapons.mixinutils.PlayerEntityAccessor;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Player.class)
public abstract class PlayerEntityMixin implements PlayerEntityAccessor {

  @Shadow
  public abstract float getAttackStrengthScale(float baseTime);

  @Override
  public float bw$getCooldown(float baseTime) {
    return this.getAttackStrengthScale(baseTime);
  }
}