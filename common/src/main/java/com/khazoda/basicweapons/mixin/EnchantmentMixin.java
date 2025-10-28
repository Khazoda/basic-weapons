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
  @Inject(method = "canEnchant(Lnet/minecraft/world/item/ItemStack;)Z",
      at = @At(value = "HEAD"),
      cancellable = true)
  private void bw$customAllowDisallowEnchantments1(ItemStack itemStack, CallbackInfoReturnable<Boolean> cir) {
    bw$checkEnchantability(itemStack, cir);
  }

  @Inject(method = "isPrimaryItem(Lnet/minecraft/world/item/ItemStack;)Z",
      at = @At(value = "HEAD"),
      cancellable = true)
  private void bw$customAllowDisallowEnchantments2(ItemStack itemStack, CallbackInfoReturnable<Boolean> cir) {
    bw$checkEnchantability(itemStack, cir);
  }

  @Inject(method = "isSupportedItem(Lnet/minecraft/world/item/ItemStack;)Z",
      at = @At(value = "HEAD"),
      cancellable = true)
  private void bw$customAllowDisallowEnchantments3(ItemStack itemStack, CallbackInfoReturnable<Boolean> cir) {
    bw$checkEnchantability(itemStack, cir);
  }

  @Unique
  private void bw$checkEnchantability(ItemStack itemStack, CallbackInfoReturnable<Boolean> cir) {
    if (itemStack.getItem() instanceof ItemExtension itemExtension) {
      Enchantment enchantment = (Enchantment) (Object) this;

      Services.PLATFORM.getCurrentRegistryAccess()
          .lookup(Registries.ENCHANTMENT)
          .ifPresent(enchantmentRegistry -> {
            enchantmentRegistry.getResourceKey(enchantment).ifPresent(resourceKey -> {
              Holder<Enchantment> enchantmentHolder = enchantmentRegistry.getOrThrow(resourceKey);

              AllowDenyPass result = itemExtension.bw$canEnchant(itemStack, enchantmentHolder);
              if (result != AllowDenyPass.PASS) {
                cir.setReturnValue(result == AllowDenyPass.ALLOW);
              }
            });
          });
    }
  }
}