package com.khazoda.basicweapons.item;

import com.khazoda.basicweapons.mixinutils.PlayerEntityAccessor;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.phys.Vec3;

public class HammerItem extends BasicWeaponItem {

  public HammerItem(Tier tier, float attackDamage, float attackSpeed, double reach, Item.Properties properties) {
    super(tier, BlockTags.AIR, attackDamage, attackSpeed, reach, properties);
  }

  @Override
  public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
    Player player = (Player) attacker;
    float f2 = ((PlayerEntityAccessor) player).bw$getCooldown(0.5f);
    if (f2 >= 0.9F) {
      /* If entity is a player */
      if (target.isAlwaysTicking()) {
        Vec3 currentMovement = target.getDeltaMovement();
        target.setDeltaMovement(currentMovement.x, currentMovement.y + 0.8, currentMovement.z);
        target.hurtMarked = true;
      } else {
        target.push(0, 0.45, 0);
      }
    }
    stack.hurtAndBreak(1, attacker, EquipmentSlot.MAINHAND);
    return true;
  }
}