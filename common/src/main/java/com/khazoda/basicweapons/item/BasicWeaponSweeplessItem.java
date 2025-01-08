package com.khazoda.basicweapons.item;

import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.List;

import static com.khazoda.basicweapons.BasicWeaponsCommon.bettercombat_mod_loaded;
import static com.khazoda.basicweapons.Constants.ID;
import static com.khazoda.basicweapons.Constants.PLAYER_ENTITY_INTERACTION_RANGE_MODIFIER_ID;

public abstract class BasicWeaponSweeplessItem extends TieredItem {
  public BasicWeaponSweeplessItem(Tier material, TagKey<Block> effectiveBlocks, float attackDamage, float attackSpeed, double extraReach, Properties properties) {
    super(material, properties
        .component(DataComponents.TOOL, createToolProperties(material, effectiveBlocks))
        .component(DataComponents.ATTRIBUTE_MODIFIERS, createAttributes(material, attackDamage, attackSpeed, extraReach)));
  }

  private static Tool createToolProperties(Tier material, TagKey<Block> effectiveBlocks) {
    List<Tool.Rule> rules = new ArrayList<>();
    rules.add(Tool.Rule.minesAndDrops(material.getIncorrectBlocksForDrops(), material.getSpeed()));
    rules.add(Tool.Rule.overrideSpeed(effectiveBlocks, material.getSpeed()));

    if (effectiveBlocks.equals(BlockTags.SWORD_EFFICIENT)) {
      rules.add(Tool.Rule.minesAndDrops(List.of(Blocks.COBWEB), 15.0F));
    }

    return new Tool(rules, 1.0F, 1);
  }

  private static ItemAttributeModifiers createAttributes(Tier tier, float attackDamage, float attackSpeed, double reach) {
    ItemAttributeModifiers.Builder builder = ItemAttributeModifiers.builder()
        .add(
            Attributes.ATTACK_DAMAGE,
            new AttributeModifier(
                BASE_ATTACK_DAMAGE_ID,
                (double) ((float) attackDamage + tier.getAttackDamageBonus()),
                AttributeModifier.Operation.ADD_VALUE
            ),
            EquipmentSlotGroup.MAINHAND
        )
        .add(
            Attributes.ATTACK_SPEED,
            new AttributeModifier(
                BASE_ATTACK_SPEED_ID,
                (double) attackSpeed,
                AttributeModifier.Operation.ADD_VALUE
            ),
            EquipmentSlotGroup.MAINHAND
        );

    if (!bettercombat_mod_loaded) {
      builder.add(
          Attributes.ENTITY_INTERACTION_RANGE,
          new AttributeModifier(
              ID(PLAYER_ENTITY_INTERACTION_RANGE_MODIFIER_ID),
              reach,
              AttributeModifier.Operation.ADD_VALUE
          ),
          EquipmentSlotGroup.MAINHAND
      );
    }

    return builder.build();
  }

  @Override
  public boolean canAttackBlock(BlockState state, Level level, BlockPos pos, Player player) {
    return !player.isCreative();
  }

  @Override
  public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
    return true;
  }

  @Override
  public void postHurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
    stack.hurtAndBreak(1, attacker, EquipmentSlot.MAINHAND);
  }


}