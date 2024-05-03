package com.seacroak.basicweapons.item;

import com.seacroak.basicweapons.mixinutils.PlayerEntityAccessor;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ToolComponent;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.math.Vec3d;

public class HammerItem extends BasicWeaponSweeplessItem {

  public HammerItem(ToolMaterial tier, Settings settings) {
    super(tier, settings.component(DataComponentTypes.TOOL, createToolComponent()));
  }

  private static ToolComponent createToolComponent() {
    return null;
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
    stack.damage(1, attacker, EquipmentSlot.MAINHAND);
    return true;
  }
}