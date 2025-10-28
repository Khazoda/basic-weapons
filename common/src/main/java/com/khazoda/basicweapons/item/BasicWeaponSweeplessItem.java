package com.khazoda.basicweapons.item;

import com.khazoda.basicweapons.platform.ItemExtension;
import net.minecraft.core.component.DataComponents;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.Weapon;
import net.minecraft.world.level.block.Block;

import static com.khazoda.basicweapons.BasicWeaponsCommon.bettercombat_mod_loaded;
import static com.khazoda.basicweapons.Constants.ID;
import static com.khazoda.basicweapons.Constants.PLAYER_ENTITY_INTERACTION_RANGE_MODIFIER_ID;

public abstract class BasicWeaponSweeplessItem extends Item implements ItemExtension {

  /* For blunt weapons */
  public BasicWeaponSweeplessItem(ToolMaterial material, TagKey<Block> effectiveBlocks, float attackDamage, float attackSpeed, double extraReach, Properties properties) {
    super(properties.tool(material, effectiveBlocks, attackDamage, attackSpeed, 0.0f)
        .component(DataComponents.ATTRIBUTE_MODIFIERS, createAttributes(material, attackDamage, attackSpeed, extraReach))
        .component(DataComponents.WEAPON, new Weapon(1)));
  }

  /* For sharp weapons */
  public BasicWeaponSweeplessItem(ToolMaterial material, float attackDamage, float attackSpeed, double extraReach, Properties properties) {
    super(properties.sword(material, attackDamage, attackSpeed)
        .component(DataComponents.ATTRIBUTE_MODIFIERS, createAttributes(material, attackDamage, attackSpeed, extraReach)));
  }

  private static ItemAttributeModifiers createAttributes(ToolMaterial ToolMaterial, float attackDamage, float attackSpeed, double reach) {
    ItemAttributeModifiers.Builder builder = ItemAttributeModifiers.builder()
        .add(Attributes.ATTACK_DAMAGE,
            new AttributeModifier(BASE_ATTACK_DAMAGE_ID,
                attackDamage + ToolMaterial.attackDamageBonus(),
                AttributeModifier.Operation.ADD_VALUE),
            EquipmentSlotGroup.MAINHAND)
        .add(Attributes.ATTACK_SPEED,
            new AttributeModifier(BASE_ATTACK_SPEED_ID,
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