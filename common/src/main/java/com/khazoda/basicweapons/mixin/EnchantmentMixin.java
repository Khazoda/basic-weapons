package com.khazoda.basicweapons.mixin;

import com.khazoda.basicweapons.registry.TagRegistry;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@SuppressWarnings("all")
@Mixin(Enchantment.class)
public class EnchantmentMixin {

  @Inject(method = "getSupportedItems", at = @At("RETURN"), cancellable = true)
  private void onGetSupportedItems(CallbackInfoReturnable<HolderSet<Item>> cir) {
    HolderSet<Item> currentItems = cir.getReturnValue();
    if (currentItems == null) return;

    Enchantment enchantment = (Enchantment) (Object) this;
    String enchantDesc = enchantment.description().getString().toLowerCase();

    List<Holder<Item>> filteredItems = currentItems.stream()
        .filter(itemHolder -> {
          ItemStack stack = new ItemStack(itemHolder.value());
          if (!stack.is(TagRegistry.BASIC_WEAPON)) return true;
          if (enchantDesc.contains("sweeping") && stack.is(TagRegistry.SWEEPING_BLACKLISTED)) {
            return false;
          }
          if (enchantDesc.contains("sharpness") && stack.is(TagRegistry.SHARPNESS_BLACKLISTED)) {
            return false;
          }
          return true;
        }).toList();
    cir.setReturnValue(HolderSet.direct(filteredItems));
  }

  @Inject(method = "canEnchant", at = @At("HEAD"), cancellable = true)
  private void onCanEnchant(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
    if (!stack.is(TagRegistry.BASIC_WEAPON)) return;

    Enchantment enchantment = (Enchantment) (Object) this;
    String enchantDesc = enchantment.description().getString().toLowerCase();

    if (enchantDesc.contains("sweeping") && stack.is(TagRegistry.SWEEPING_BLACKLISTED)) {
      cir.setReturnValue(false);
    } else if (enchantDesc.contains("sharpness") && stack.is(TagRegistry.SHARPNESS_BLACKLISTED)) {
      cir.setReturnValue(false);
    }
  }

  @Inject(method = "isPrimaryItem", at = @At("HEAD"), cancellable = true)
  private void onIsPrimaryItem(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
    if (stack.is(TagRegistry.BASIC_WEAPON)) {
      String enchantDesc = ((Enchantment) (Object) this).toString().toLowerCase();

      if (stack.is(TagRegistry.SWEEPING_BLACKLISTED) && enchantDesc.contains("sweeping")) {
        cir.setReturnValue(false);
        return;
      }
      if (stack.is(TagRegistry.SHARPNESS_BLACKLISTED) && enchantDesc.contains("sharpness")) {
        cir.setReturnValue(false);
      }
    }
  }
}