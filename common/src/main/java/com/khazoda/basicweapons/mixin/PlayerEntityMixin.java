package com.khazoda.basicweapons.mixin;

import com.khazoda.basicweapons.item.BasicWeaponSweeplessItem;
import com.khazoda.basicweapons.mixinutils.PlayerEntityAccessor;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.tags.ItemTags;
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

  @Override
  public float bw$getCooldown(float baseTime) {
    return this.getAttackStrengthScale(baseTime);
  }

  /*** Cursed mixins to prevent sweeping on BasicWeaponItems ***/
//  @WrapOperation(method = "attack", at = @At(value = "FIELD", target = "Lnet/minecraft/world/entity/player/Player;walkDist:F"))
//  private float alterHorizontalSpeedToSneakilyPreventSweeping(Player instance, Operation<Float> original) {
//    if (!instance.getMainHandItem().is(ItemTags.SWORDS) && instance.getMainHandItem().getItem() instanceof BasicWeaponSweeplessItem)
//      return 10f;
//    return original.call(instance);
//  }
//
//  @WrapOperation(method = "attack", at = @At(value = "FIELD", target = "Lnet/minecraft/world/entity/player/Player;walkDistO:F"))
//  private float alterPreviousHorizontalSpeedToSneakilyPreventSweeping(Player instance, Operation<Float> original) {
//    if (!instance.getMainHandItem().is(ItemTags.SWORDS) && instance.getMainHandItem().getItem() instanceof BasicWeaponSweeplessItem)
//      return 0f;
//    return original.call(instance);
//  }
}