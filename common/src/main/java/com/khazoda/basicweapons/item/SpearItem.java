package com.khazoda.basicweapons.item;

import com.khazoda.basicweapons.registry.TagRegistry;
import com.khazoda.basicweapons.utils.AllowDenyPass;
import net.minecraft.core.Holder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.enchantment.Enchantment;

/* DEPRECATION: This class (and all associated data) remains to existing spears are not deleted, but the pike */
/* has functionally replaced the spear in 1.21.11+ (26.1+), as mojang implemented a spear into vanilla. */
@Deprecated
public class SpearItem extends BasicWeaponSweeplessItem {
  public SpearItem(ToolMaterial material, float attackDamage, float attackSpeed, double reach, Item.Properties properties) {
    super(material, attackDamage, attackSpeed, reach, properties);
  }

  @Override
  public AllowDenyPass bw$canEnchant(ItemStack itemstack, Holder<Enchantment> enchantment) {
    // The item can't be enchanted by enchantments listed here
    return enchantment.is(TagRegistry.MIGHT_ENCHANTABLE) || enchantment.is(TagRegistry.SWEEPING_EDGE_ENCHANTABLE) ? AllowDenyPass.DENY : AllowDenyPass.PASS;
  }
}