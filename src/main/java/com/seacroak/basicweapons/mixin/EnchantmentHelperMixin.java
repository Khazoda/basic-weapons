package com.seacroak.basicweapons.mixin;

import com.seacroak.basicweapons.item.BasicWeaponItem;
import com.seacroak.basicweapons.item.BasicWeaponSweeplessItem;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

import static net.minecraft.enchantment.Enchantments.SWEEPING;

@Mixin(EnchantmentHelper.class)
public class EnchantmentHelperMixin {

  @Inject(method = "getPossibleEntries", at = @At("TAIL"))
  private static void blacklistEnchantments(int power, ItemStack stack, boolean treasureAllowed, CallbackInfoReturnable<List<EnchantmentLevelEntry>> cir) {
    if (stack.getItem() instanceof BasicWeaponItem) {
      var currentEntries = cir.getReturnValue();
      if (stack.getItem() instanceof BasicWeaponSweeplessItem) {
        currentEntries.removeIf(entry -> entry.enchantment.equals(SWEEPING));

      }
    }
  }
}
