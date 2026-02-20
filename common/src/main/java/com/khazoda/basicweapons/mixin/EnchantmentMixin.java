package com.khazoda.basicweapons.mixin;

import com.khazoda.basicweapons.platform.ItemExtension;
import com.khazoda.basicweapons.platform.Services;
import com.khazoda.basicweapons.utils.AllowDenyPass;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Enchantment.class)
public class EnchantmentMixin {

  // Overrides enchantment compatibility checks used by anvils and commands
  @Inject(method = "canEnchant(Lnet/minecraft/world/item/ItemStack;)Z", at = @At(value = "HEAD"), cancellable = true)
  private void bw$overrideCanEnchant(ItemStack itemStack, CallbackInfoReturnable<Boolean> cir) {
    bw$resolveEnchantability(itemStack, cir, false);
  }

  // Overrides enchanting table eligibility, promoting supported enchantments to primary without requiring the minecraft:swords tag (which would enable sweeping)
  @Inject(method = "isPrimaryItem(Lnet/minecraft/world/item/ItemStack;)Z", at = @At(value = "HEAD"), cancellable = true)
  private void bw$overrideIsPrimaryItem(ItemStack itemStack, CallbackInfoReturnable<Boolean> cir) {
    bw$resolveEnchantability(itemStack, cir, true);
  }

  // Overrides broad enchantment support checks used across all enchanting methods
  @Inject(method = "isSupportedItem(Lnet/minecraft/world/item/ItemStack;)Z", at = @At(value = "HEAD"), cancellable = true)
  private void bw$overrideIsSupportedItem(ItemStack itemStack, CallbackInfoReturnable<Boolean> cir) {
    bw$resolveEnchantability(itemStack, cir, false);
  }

  // Resolves enchantability by delegating to each weapon's bw$canEnchant deny-list in their respective classes
  @Unique
  private void bw$resolveEnchantability(ItemStack itemStack, CallbackInfoReturnable<Boolean> cir, boolean promoteSupported) {
    if (itemStack.getItem() instanceof ItemExtension itemExtension) {
      Enchantment enchantment = (Enchantment) (Object) this;

      Services.PLATFORM.getCurrentRegistryAccess().lookup(Registries.ENCHANTMENT).ifPresent(enchantmentRegistry -> {
        enchantmentRegistry.getResourceKey(enchantment).ifPresent(resourceKey -> {
          Holder<Enchantment> enchantmentHolder = enchantmentRegistry.getOrThrow(resourceKey);

          AllowDenyPass result = itemExtension.bw$canEnchant(itemStack, enchantmentHolder);
          if (result == AllowDenyPass.DENY) {
            cir.setReturnValue(false);
          } else if (result == AllowDenyPass.ALLOW) {
            cir.setReturnValue(true);
          } else if (promoteSupported && enchantment.isSupportedItem(itemStack)) {
            cir.setReturnValue(true);
          }
        });
      });
    }
  }
}