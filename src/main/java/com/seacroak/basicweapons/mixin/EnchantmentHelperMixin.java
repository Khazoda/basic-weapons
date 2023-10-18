package com.seacroak.basicweapons.mixin;

import com.seacroak.basicweapons.item.BasicWeaponItem;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

import static net.minecraft.enchantment.Enchantments.*;

@Mixin(EnchantmentHelper.class)
public class EnchantmentHelperMixin {

  @Inject(method = "getPossibleEntries", at = @At("TAIL"))
  private static void applyEnchantmentsToBasicWeapons(int power, ItemStack stack, boolean treasureAllowed, CallbackInfoReturnable<List<EnchantmentLevelEntry>> cir) {

    Enchantment[] bwValidEnchantments = {SHARPNESS, SMITE, BANE_OF_ARTHROPODS, KNOCKBACK, FIRE_ASPECT, LOOTING};
    if (stack.getItem() instanceof BasicWeaponItem) {
      var currentEntries = cir.getReturnValue();

      for (Enchantment enchantment : bwValidEnchantments) {
        for (int i = enchantment.getMaxLevel(); i > enchantment.getMinLevel() - 1; --i) {
          if (power < enchantment.getMinPower(i) || power > enchantment.getMaxPower(i)) continue;
          currentEntries.add(new EnchantmentLevelEntry(enchantment, i));
        }
      }
    }
  }
}
