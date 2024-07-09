package com.seacroak.basicweapons.item;

import net.minecraft.block.BlockState;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static com.seacroak.basicweapons.Constants.PLAYER_ENTITY_INTERACTION_RANGE_MODIFIER_ID;


// Used for weapons with sweeping. This currently includes the quarterstaff
public abstract class BasicWeaponItem extends SwordItem {

  public BasicWeaponItem(ToolMaterial toolMaterial, Settings settings) {
    super(toolMaterial, settings);
  }

  public static AttributeModifiersComponent createAttributeModifiers(ToolMaterial material, float baseAttackDamage, float attackSpeed, double extraRange) {
    return AttributeModifiersComponent.builder()
        .add(
            EntityAttributes.GENERIC_ATTACK_DAMAGE,
            new EntityAttributeModifier(
                BASE_ATTACK_DAMAGE_MODIFIER_ID, baseAttackDamage + material.getAttackDamage(), EntityAttributeModifier.Operation.ADD_VALUE
            ),
            AttributeModifierSlot.MAINHAND
        )
        .add(
            EntityAttributes.GENERIC_ATTACK_SPEED,
            new EntityAttributeModifier(BASE_ATTACK_SPEED_MODIFIER_ID, attackSpeed, EntityAttributeModifier.Operation.ADD_VALUE),
            AttributeModifierSlot.MAINHAND
        )
        .add(EntityAttributes.PLAYER_ENTITY_INTERACTION_RANGE,
            new EntityAttributeModifier(Identifier.of(PLAYER_ENTITY_INTERACTION_RANGE_MODIFIER_ID.toString()), extraRange, EntityAttributeModifier.Operation.ADD_VALUE),
            AttributeModifierSlot.MAINHAND)
        .build();
  }

  @Override
  public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
    stack.damage(1, miner, EquipmentSlot.MAINHAND);
    return super.postMine(stack, world, state, pos, miner);
  }
}
