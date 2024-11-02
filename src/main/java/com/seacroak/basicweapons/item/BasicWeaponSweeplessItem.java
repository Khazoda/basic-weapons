package com.seacroak.basicweapons.item;

import com.seacroak.basicweapons.mixin.PlayerEntityMixin;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MiningToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static com.seacroak.basicweapons.Constants.PLAYER_ENTITY_INTERACTION_RANGE_MODIFIER_ID;


/**
 * Used by PlayerEntityMixin to determine whether to kill sweeping behaviour or not
 *
 * @see PlayerEntityMixin
 */
public class BasicWeaponSweeplessItem extends MiningToolItem {
  public BasicWeaponSweeplessItem(ToolMaterial toolMaterial, TagKey<Block> effectiveBlocks, float attackDamage, float attackSpeed, Settings settings) {
    super(toolMaterial, effectiveBlocks, attackDamage, attackSpeed, settings);
  }

  public static AttributeModifiersComponent createAttributeModifiers(ToolMaterial material, float baseAttackDamage, float attackSpeed, double extraRange) {
    return AttributeModifiersComponent.builder()
        .add(
            EntityAttributes.ATTACK_DAMAGE,
            new EntityAttributeModifier(
                BASE_ATTACK_DAMAGE_MODIFIER_ID, baseAttackDamage + material.attackDamageBonus(), EntityAttributeModifier.Operation.ADD_VALUE
            ),
            AttributeModifierSlot.MAINHAND
        )
        .add(
            EntityAttributes.ATTACK_SPEED,
            new EntityAttributeModifier(BASE_ATTACK_SPEED_MODIFIER_ID, attackSpeed, EntityAttributeModifier.Operation.ADD_VALUE),
            AttributeModifierSlot.MAINHAND
        )
        .add(EntityAttributes.ENTITY_INTERACTION_RANGE,
            new EntityAttributeModifier(Identifier.of(PLAYER_ENTITY_INTERACTION_RANGE_MODIFIER_ID.toString()), extraRange, EntityAttributeModifier.Operation.ADD_VALUE),
            AttributeModifierSlot.MAINHAND)
        .build();
  }

  @Override
  public boolean canMine(BlockState state, World world, BlockPos pos, PlayerEntity miner) {
    return !miner.isCreative();
  }

  @Override
  public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
    stack.damage(1, miner, EquipmentSlot.MAINHAND);
    return super.postMine(stack, world, state, pos, miner);
  }

  @Override
  public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
    stack.damage(1, attacker, EquipmentSlot.MAINHAND);
    return true;
  }
}
