package com.seacroak.basicweapons.item;

import com.seacroak.basicweapons.mixin.PlayerEntityAccessor;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.math.Vec3d;

public class HammerItem extends BasicWeaponItem {
  public HammerItem(ToolMaterial tier, int damage, float attackSpeed, Settings properties) {
    super(tier, damage, attackSpeed, properties);
  }


  @Override
  public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
    PlayerEntity player = (PlayerEntity) attacker;
    float f2 = ((PlayerEntityAccessor) player).bw$getCooldown(0.5f);
    if (f2 >= 0.9F) {
      if (target.isPlayer()) {
        Vec3d currentMovement = target.getVelocity();
        target.setVelocity(currentMovement.x, currentMovement.y + 0.8, currentMovement.z);
        target.velocityModified = true;
      } else {
        target.addVelocity(0, 0.45, 0);
      }
    }
    stack.damage(1, attacker, (e) -> {
      e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND);
    });
    return true;
  }
}