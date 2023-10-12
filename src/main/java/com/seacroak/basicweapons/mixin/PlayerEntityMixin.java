package com.seacroak.basicweapons.mixin;

import com.seacroak.basicweapons.item.BasicWeaponItem;
import com.seacroak.basicweapons.mixinutils.PlayerEntityAccessor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
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

  @Unique
  protected boolean bl4 = false;
  @Unique
  protected ItemStack itemStack;

  @Inject(method = "attack", at = @At("HEAD"))
  private void retrieveCooldownEarly(Entity target, CallbackInfo ci) {
    attackCooldownProgress = this.getAttackCooldownProgress(0.5f);
  }

  @Inject(method = "attack", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;getItem()Lnet/minecraft/item/Item;", shift = At.Shift.AFTER))
  private void preventSweeping(Entity target, CallbackInfo ci) {
    if (itemStack.getItem() instanceof BasicWeaponItem) {
      bl4 = false;
    }
  }
}