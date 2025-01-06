package com.seacroak.basicweapons.mixin;

import com.seacroak.basicweapons.item.BasicWeaponSweeplessItem;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.SweepingEnchantment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(SweepingEnchantment.class)
public abstract class SweepingEnchantmentMixin extends Enchantment {
  @SuppressWarnings("unused")
  private SweepingEnchantmentMixin(Rarity weight, EnchantmentTarget target, EquipmentSlot[] slotTypes) {
    super(weight, target, slotTypes);
  }

  @Override
  public boolean isAcceptableItem(ItemStack stack) {
    if (stack.getItem() instanceof BasicWeaponSweeplessItem) {
      return false;
    }
    return super.isAcceptableItem(stack);
  }
}