package com.seacroak.basicweapons.item;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.phys.Vec3;

public class HammerItem extends BasicWeaponItem {
  public HammerItem(Tier tier, int damage, float attackSpeed, Properties properties) {
    super(tier, damage, attackSpeed, properties);
  }

  @Override
  public boolean hurtEnemy(ItemStack itemStack, LivingEntity target, LivingEntity attacker) {
    Player player = (Player) attacker;
    float f2 = player.getAttackStrengthScale(0.5f);
    if (f2 > 0.9F) {
      if (target.isAlwaysTicking()) {
        Vec3 currentMovement = target.getDeltaMovement();
        target.setDeltaMovement(currentMovement.x, currentMovement.y + 0.8, currentMovement.z);
        target.hurtMarked = true;
      } else {
        target.addDeltaMovement(new Vec3(0, 0.45, 0));
      }
    }
    itemStack.hurtAndBreak(1, attacker, (entity) -> {
      entity.broadcastBreakEvent(EquipmentSlot.MAINHAND);
    });
    return true;
  }
}
