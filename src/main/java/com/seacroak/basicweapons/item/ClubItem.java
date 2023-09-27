package com.seacroak.basicweapons.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;

public class ClubItem extends BasicWeaponItem {
  public ClubItem(Tier tier, int damage, float attackSpeed, Properties properties) {
    super(tier, damage, attackSpeed, properties);
  }

  public boolean hurtEnemy(ItemStack itemStack, LivingEntity target, LivingEntity attacker) {
    target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 10));
    itemStack.hurtAndBreak(1, attacker, (p_43296_) -> {
      p_43296_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
    });
    return true;
  }
}
