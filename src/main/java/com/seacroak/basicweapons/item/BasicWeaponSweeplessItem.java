package com.seacroak.basicweapons.item;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraftforge.common.ToolAction;

import java.util.Set;

import static com.google.common.collect.ImmutableSet.of;
import static net.minecraftforge.common.ToolActions.SWORD_DIG;

public class BasicWeaponSweeplessItem extends BasicWeaponItem {
  private static final Set<ToolAction> SWEEPLESS_BASIC_WEAPON_ACTIONS = of(SWORD_DIG);

  public BasicWeaponSweeplessItem(Tier tier, int attackDamage, float attackSpeed, Properties properties) {
    super(tier, attackDamage, attackSpeed, properties);
  }

  @Override
  public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
    if (enchantment == Enchantments.SWEEPING_EDGE) {
      return false;
    }
    return super.canApplyAtEnchantingTable(stack, enchantment);
  }
  @Override
  public boolean canPerformAction(ItemStack stack, net.minecraftforge.common.ToolAction toolAction) {
    return SWEEPLESS_BASIC_WEAPON_ACTIONS.contains(toolAction);
  }
}
