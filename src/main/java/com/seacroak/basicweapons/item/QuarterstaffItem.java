package com.seacroak.basicweapons.item;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.component.type.ToolComponent;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.Identifier;

import java.util.UUID;

public class QuarterstaffItem extends BasicWeaponItem {
  public static final UUID PLAYER_ENTITY_INTERACTION_RANGE_MODIFIER_ID = UUID.fromString("38ae4a11-e97c-41c1-aaaa-d813103ce10c");

  public QuarterstaffItem(ToolMaterial tier, Settings settings) {
    super(tier, settings.component(DataComponentTypes.TOOL, createToolComponent()));
  }

  private static ToolComponent createToolComponent() {
    return null;
  }

  public static AttributeModifiersComponent createAttributeModifiers(ToolMaterial material, int baseAttackDamage, float attackSpeed) {
    return AttributeModifiersComponent.builder()
        .add(
            EntityAttributes.GENERIC_ATTACK_DAMAGE,
            new EntityAttributeModifier(
                BASE_ATTACK_DAMAGE_MODIFIER_ID, (float) baseAttackDamage + material.getAttackDamage(), EntityAttributeModifier.Operation.ADD_VALUE
            ),
            AttributeModifierSlot.MAINHAND
        )
        .add(
            EntityAttributes.GENERIC_ATTACK_SPEED,
            new EntityAttributeModifier(BASE_ATTACK_SPEED_MODIFIER_ID, attackSpeed, EntityAttributeModifier.Operation.ADD_VALUE),
            AttributeModifierSlot.MAINHAND
        )
        /* Hopefully this isn't hacky and is in fact safe and correct 😓*/
        .add(EntityAttributes.PLAYER_ENTITY_INTERACTION_RANGE,
            new EntityAttributeModifier(Identifier.of(PLAYER_ENTITY_INTERACTION_RANGE_MODIFIER_ID.toString()), 1.25, EntityAttributeModifier.Operation.ADD_VALUE),
            AttributeModifierSlot.MAINHAND)
        .build();
  }
}