package com.khazoda.basicweapons.item;

import com.khazoda.basicweapons.registry.TagRegistry;
import com.khazoda.basicweapons.utils.AllowDenyPass;
import net.minecraft.core.Holder;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.enchantment.Enchantment;

public class QuarterstaffItem extends BasicWeaponItem {
  public QuarterstaffItem(ToolMaterial ToolMaterial, float attackDamage, float attackSpeed, double reach, Item.Properties properties) {
    super(ToolMaterial, BlockTags.AIR, attackDamage, attackSpeed, reach, properties);
  }

  @Override
  public AllowDenyPass bw$canEnchant(ItemStack itemstack, Holder<Enchantment> enchantment) {
    // The item can't be enchanted by enchantments listed here
    return enchantment.is(TagRegistry.SHARPNESS_ENCHANTABLE) ? AllowDenyPass.DENY : AllowDenyPass.PASS;
  }
}