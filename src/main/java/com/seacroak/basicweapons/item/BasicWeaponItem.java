package com.seacroak.basicweapons.item;

import net.minecraft.block.BlockState;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.component.type.ToolComponent;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/* Class based off SwordItem to disable sweeping behaviour by default */
public abstract class BasicWeaponItem extends SwordItem {

  public BasicWeaponItem(ToolMaterial toolMaterial, Settings settings) {
    super(toolMaterial, settings.component(DataComponentTypes.TOOL, createToolComponent()));
  }

  private static ToolComponent createToolComponent() {
    return null;
  }

  public static AttributeModifiersComponent createAttributeModifiers(ToolMaterial material, int baseAttackDamage, float attackSpeed) {
    return AttributeModifiersComponent.builder()
        .add(
            EntityAttributes.GENERIC_ATTACK_DAMAGE,
            new EntityAttributeModifier(
                ATTACK_DAMAGE_MODIFIER_ID, "Weapon modifier", (double) ((float) baseAttackDamage + material.getAttackDamage()), EntityAttributeModifier.Operation.ADD_VALUE
            ),
            AttributeModifierSlot.MAINHAND
        )
        .add(
            EntityAttributes.GENERIC_ATTACK_SPEED,
            new EntityAttributeModifier(ATTACK_SPEED_MODIFIER_ID, "Weapon modifier", (double) attackSpeed, EntityAttributeModifier.Operation.ADD_VALUE),
            AttributeModifierSlot.MAINHAND
        )
        .build();
  }

  @Override
  public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
    stack.damage(1, miner, EquipmentSlot.MAINHAND);
    return super.postMine(stack, world, state, pos, miner);
  }
}
