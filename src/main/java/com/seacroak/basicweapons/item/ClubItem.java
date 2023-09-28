package com.seacroak.basicweapons.item;

import com.seacroak.basicweapons.PlayerEntityAccessor;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;

public class ClubItem extends BasicWeaponItem {
  public ClubItem(ToolMaterial tier, int damage, float attackSpeed, Settings properties) {
    super(tier, damage, attackSpeed, properties);
  }

  @Override
  public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
    PlayerEntity player = (PlayerEntity) attacker;
    float f2 = ((PlayerEntityAccessor) player).bw$getCooldown(0.5f);
    if (f2 > 0.9F) {
      target.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 15));
    }
    stack.damage(1, attacker, (e) -> {
      e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND);
    });
    return true;
  }
}