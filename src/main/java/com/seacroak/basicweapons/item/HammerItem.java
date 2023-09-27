package com.seacroak.basicweapons.item;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.phys.Vec3;

public class HammerItem extends BasicWeaponItem {
  public HammerItem(Tier tier, int damage, float attackSpeed, Properties properties) {
    super(tier, damage, attackSpeed, properties);
  }

  @Override
  public boolean hurtEnemy(ItemStack itemStack, LivingEntity target, LivingEntity attacker) {
    target.addDeltaMovement(new Vec3(0, 0.45, 0));
    itemStack.hurtAndBreak(1, attacker, (p_43296_) -> {
      p_43296_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
    });
    return true;
  }
}
