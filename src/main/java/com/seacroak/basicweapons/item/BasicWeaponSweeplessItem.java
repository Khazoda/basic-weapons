package com.seacroak.basicweapons.item;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
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
  public boolean canPerformAction(ItemStack stack, net.minecraftforge.common.ToolAction toolAction) {
    return SWEEPLESS_BASIC_WEAPON_ACTIONS.contains(toolAction);
  }
}
