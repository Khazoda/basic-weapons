package com.seacroak.basicweapons.item;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ToolComponent;
import net.minecraft.item.ToolMaterial;

public class ClubItem extends BasicWeaponSweeplessItem {
  public ClubItem(ToolMaterial tier, Settings settings) {
    super(tier, settings.component(DataComponentTypes.TOOL, createToolComponent()));
  }

  private static ToolComponent createToolComponent() {
    return null;
  }
//  @Override
//  public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
//    PlayerEntity player = (PlayerEntity) attacker;
//    float f2 = ((PlayerEntityAccessor) player).bw$getCooldown(0.5f);
//    if (f2 > 0.9F) {
//      target.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 15));
//    }
//    stack.damage(1, attacker, (e) -> {
//      e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND);
//    });
//    return true;
//  }
}