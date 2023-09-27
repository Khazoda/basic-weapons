package com.seacroak.basicweapons.item;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolAction;

import java.util.Set;

import static com.google.common.collect.ImmutableSet.of;
import static net.minecraftforge.common.ToolActions.SWORD_DIG;

public class BasicWeaponItem extends TieredItem {
  private final float attackDamage;
  private final Multimap<Attribute, AttributeModifier> defaultModifiers;
  private static final Set<ToolAction> DEFAULT_BASIC_WEAPON_ACTIONS = of(SWORD_DIG);


  public BasicWeaponItem(Tier tier, int attackDamage, float attackSpeed, Item.Properties properties) {
    super(tier, properties);
    this.attackDamage = (float) attackDamage + tier.getAttackDamageBonus();
    ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
    builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", (double) this.attackDamage, AttributeModifier.Operation.ADDITION));
    builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", (double) attackSpeed, AttributeModifier.Operation.ADDITION));
    this.defaultModifiers = builder.build();
  }

  public float getDamage() {
    return this.attackDamage;
  }

  public boolean canAttackBlock(BlockState state, Level world, BlockPos pos, Player player) {
    return !player.isCreative();
  }

  public float getDestroySpeed(ItemStack itemStack, BlockState state) {
    return state.is(BlockTags.SWORD_EFFICIENT) ? 1.5F : 1.0F;
  }

  public boolean hurtEnemy(ItemStack itemStack, LivingEntity target, LivingEntity attacker) {
    itemStack.hurtAndBreak(1, attacker, (p_43296_) -> {
      p_43296_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
    });
    return true;
  }

  public boolean mineBlock(ItemStack stack, Level level, BlockState state, BlockPos pos, LivingEntity miner) {
    if (state.getDestroySpeed(level, pos) != 0.0F) {
      stack.hurtAndBreak(2, miner, (p_43276_) -> {
        p_43276_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
      });
    }

    return true;
  }

  public boolean isCorrectToolForDrops(BlockState state) {
    return false;
  }

  public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot slot) {
    return slot == EquipmentSlot.MAINHAND ? this.defaultModifiers : super.getDefaultAttributeModifiers(slot);
  }

  @Override
  public boolean canPerformAction(ItemStack stack, net.minecraftforge.common.ToolAction toolAction) {
    return DEFAULT_BASIC_WEAPON_ACTIONS.contains(toolAction);
  }
}
