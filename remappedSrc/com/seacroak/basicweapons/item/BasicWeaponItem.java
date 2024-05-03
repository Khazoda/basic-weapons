package com.seacroak.basicweapons.item;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/* Class based off SwordItem to disable sweeping behaviour by default */
public abstract class BasicWeaponItem extends SwordItem implements Vanishable {
  private final float attackDamage;
  private final Multimap<EntityAttribute, EntityAttributeModifier> attributeModifiers;

  public BasicWeaponItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Item.Settings settings) {
    super(toolMaterial, attackDamage, attackSpeed, settings);
    this.attackDamage = (float) attackDamage + toolMaterial.getAttackDamage();
    ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();
    builder.put(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(ATTACK_DAMAGE_MODIFIER_ID, "Weapon modifier", (double) this.attackDamage, EntityAttributeModifier.Operation.ADD_VALUE));
    builder.put(EntityAttributes.GENERIC_ATTACK_SPEED, new EntityAttributeModifier(ATTACK_SPEED_MODIFIER_ID, "Weapon modifier", (double) attackSpeed, EntityAttributeModifier.Operation.ADD_VALUE));
    this.attributeModifiers = builder.build();
  }

  public float getAttackDamage() {
    return this.attackDamage;
  }

  public boolean canMine(BlockState state, World world, BlockPos pos, PlayerEntity miner) {
    return !miner.isCreative();
  }

  public float getMiningSpeedMultiplier(ItemStack stack, BlockState state) {
    return 1.0f;
  }

  public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
    stack.damage(1, attacker, (e) -> {
      e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND);
    });
    return true;
  }

  public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
    if (state.getHardness(world, pos) != 0.0F) {
      stack.damage(2, miner, (e) -> {
        e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND);
      });
    }
    return true;
  }

  public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot slot) {
    return slot == EquipmentSlot.MAINHAND ? this.attributeModifiers : super.getAttributeModifiers(slot);
  }


  @Override
  public boolean isSuitableFor(BlockState state) {
    return false;
  }

}
