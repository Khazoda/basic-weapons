package com.khazoda.basicweapons.item;

import com.khazoda.basicweapons.platform.ItemExtension;
import net.minecraft.core.HolderSet;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.ArrayList;
import java.util.List;

import static com.khazoda.basicweapons.BasicWeaponsCommon.bettercombat_mod_loaded;
import static com.khazoda.basicweapons.Constants.ID;
import static com.khazoda.basicweapons.Constants.PLAYER_ENTITY_INTERACTION_RANGE_MODIFIER_ID;

public abstract class BasicWeaponItem extends Item implements ItemExtension {

  public BasicWeaponItem(ToolMaterial material, TagKey<Block> effectiveBlocks, float attackDamage, float attackSpeed, double extraReach, Item.Properties properties) {
    super(properties.sword(material, attackDamage, attackSpeed)
//        .component(DataComponents.TOOL, createToolProperties(material, effectiveBlocks))
        .component(DataComponents.ATTRIBUTE_MODIFIERS, createAttributes(material, attackDamage, attackSpeed, extraReach)));
  }

//  private static Tool createToolProperties(ToolMaterial material, TagKey<Block> effectiveBlocks) {
//    List<Tool.Rule> rules = new ArrayList<>();
//
//    HolderSet<Block> incorrectBlocksHolderSet = BuiltInRegistries.BLOCK.getOrThrow(material.incorrectBlocksForDrops());
//    HolderSet<Block> effectiveHolderSet = BuiltInRegistries.BLOCK.getOrThrow(effectiveBlocks);
//
//    rules.add(Tool.Rule.minesAndDrops(incorrectBlocksHolderSet, material.speed()));
//    rules.add(Tool.Rule.overrideSpeed(effectiveHolderSet, material.speed()));
//
//    if (effectiveBlocks.equals(BlockTags.SWORD_EFFICIENT)) {
//      rules.add(Tool.Rule.minesAndDrops(HolderSet.direct(BuiltInRegistries.BLOCK.wrapAsHolder(Blocks.COBWEB)), 15.0F));
//    }
//
//    return new Tool(rules, 1.0F, 1, false);
//  }

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