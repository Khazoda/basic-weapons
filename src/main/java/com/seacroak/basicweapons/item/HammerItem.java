package com.seacroak.basicweapons.item;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;

public class HammerItem extends BasicWeaponItem {
  public HammerItem(ToolMaterial tier, int damage, float attackSpeed, Settings properties) {
    super(tier, damage, attackSpeed, properties);
  }

  @Override
  public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
    target.addVelocity(0, 0.45, 0);
    stack.damage(1, attacker, (e) -> {
      e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND);
    });
    return true;
  }
}