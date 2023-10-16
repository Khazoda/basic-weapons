package com.seacroak.basicweapons.mixin;

import com.seacroak.basicweapons.item.*;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import static net.minecraft.enchantment.Enchantments.*;

@Mixin(EnchantmentHelper.class)
public class EnchantmentHelperMixin {
  @Unique
  private static Enchantment currentEnchantment;

  @Redirect(method = "getPossibleEntries", at = @At(value = "INVOKE", target = "Lnet/minecraft/enchantment/Enchantment;isAvailableForRandomSelection()Z"))
  private static boolean isAvailableForRandomSelection(Enchantment enchantment) {
    currentEnchantment = enchantment;

    return enchantment.isAvailableForRandomSelection();
  }

  @Redirect(method = "getPossibleEntries", at = @At(value = "INVOKE", target = "Lnet/minecraft/enchantment/EnchantmentTarget;isAcceptableItem(Lnet/minecraft/item/Item;)Z"))
  private static boolean isAcceptableItem(EnchantmentTarget enchantmentTarget, Item item) {
    ItemStack stack = new ItemStack(item);

    if (item instanceof ClubItem || item instanceof DaggerItem || item instanceof HammerItem || item instanceof QuarterstaffItem || item instanceof SpearItem)
      return currentEnchantment.isAcceptableItem(stack) || currentEnchantment == SHARPNESS || currentEnchantment == SMITE || currentEnchantment == BANE_OF_ARTHROPODS || currentEnchantment == KNOCKBACK || currentEnchantment == FIRE_ASPECT || currentEnchantment == LOOTING;

    return enchantmentTarget.isAcceptableItem(item);
  }
}
