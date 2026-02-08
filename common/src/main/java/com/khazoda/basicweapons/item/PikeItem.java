package com.khazoda.basicweapons.item;

import com.khazoda.basicweapons.registry.TagRegistry;
import com.khazoda.basicweapons.utils.AllowDenyPass;
import net.minecraft.core.Holder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.enchantment.Enchantment;

public class PikeItem extends BasicWeaponSweeplessItem {
  public PikeItem(ToolMaterial material, float attackDamage, float attackSpeed, double reach, Properties properties) {
    super(material, attackDamage, attackSpeed, reach, properties);
  }

  @Override
  public AllowDenyPass bw$canEnchant(ItemStack itemstack, Holder<Enchantment> enchantment) {
    // The item can't be enchanted by enchantments listed here
    return enchantment.is(TagRegistry.MIGHT_ENCHANTABLE) || enchantment.is(TagRegistry.SWEEPING_EDGE_ENCHANTABLE) ? AllowDenyPass.DENY : AllowDenyPass.PASS;
  }

  @Override
  public ItemUseAnimation getUseAnimation(ItemStack stack) {
    return ItemUseAnimation.SPEAR;
  }
}