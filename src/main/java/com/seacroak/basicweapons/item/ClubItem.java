package com.seacroak.basicweapons.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;

public class ClubItem extends BasicWeaponItem {
  public ClubItem(Tier tier, int damage, float attackSpeed, Properties properties) {
    super(tier, damage, attackSpeed, properties);
  }


  @Override
  public boolean hurtEnemy(ItemStack itemStack, LivingEntity target, LivingEntity attacker) {
    Player player = (Player) attacker;
    float f2 = player.getAttackStrengthScale(0.5f);
    if (f2 > 0.9F) {
      target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 15));
    }
    ;
    itemStack.hurtAndBreak(1, attacker, (entity) -> {
      entity.broadcastBreakEvent(EquipmentSlot.MAINHAND);
    });
    return true;
  }
}
