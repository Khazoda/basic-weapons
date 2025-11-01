package com.khazoda.basicweapons.item;

import net.minecraft.core.component.DataComponents;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.component.ItemAttributeModifiers;

import static com.khazoda.basicweapons.BasicWeaponsCommon.bettercombat_mod_loaded;
import static com.khazoda.basicweapons.Constants.ID;
import static com.khazoda.basicweapons.Constants.PLAYER_ENTITY_INTERACTION_RANGE_MODIFIER_ID;

public class CustomAxeItem extends Item {
  public CustomAxeItem(ToolMaterial material, float attackDamage, float attackSpeed, double reach, Properties properties) {
    super(properties
        .axe(material, attackDamage, attackSpeed)
        .attributes(createAttributesWithReach(material, attackDamage, attackSpeed, reach)));
  }

  private static ItemAttributeModifiers createAttributesWithReach(ToolMaterial toolMaterial, float attackDamage, float attackSpeed, double reach) {
    ItemAttributeModifiers.Builder builder = ItemAttributeModifiers.builder()
        .add(Attributes.ATTACK_DAMAGE,
            new AttributeModifier(Item.BASE_ATTACK_DAMAGE_ID,
                attackDamage + toolMaterial.attackDamageBonus(),
                AttributeModifier.Operation.ADD_VALUE),
            EquipmentSlotGroup.MAINHAND)
        .add(Attributes.ATTACK_SPEED,
            new AttributeModifier(Item.BASE_ATTACK_SPEED_ID,
                attackSpeed,
                AttributeModifier.Operation.ADD_VALUE),
            EquipmentSlotGroup.MAINHAND);

    /* Better Combat handles reach attributes via json */
    if (!bettercombat_mod_loaded) {
      builder.add(Attributes.ENTITY_INTERACTION_RANGE,
          new AttributeModifier(
              ID(PLAYER_ENTITY_INTERACTION_RANGE_MODIFIER_ID),
              reach,
              AttributeModifier.Operation.ADD_VALUE),
          EquipmentSlotGroup.MAINHAND);
    }

    return builder.build();
  }
}
