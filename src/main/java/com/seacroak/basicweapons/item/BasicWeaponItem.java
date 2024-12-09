package com.seacroak.basicweapons.item;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.component.type.ToolComponent;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

import static com.seacroak.basicweapons.Constants.PLAYER_ENTITY_INTERACTION_RANGE_MODIFIER_ID;
import static com.seacroak.basicweapons.registry.MainRegistry.bettercombat_mod_loaded;

public abstract class BasicWeaponItem extends Item {
  public BasicWeaponItem(ToolMaterial material, TagKey<Block> effectiveBlocks, float attackDamage, float attackSpeed, double extraReach, Settings settings) {
    super(prepareSettings(material, effectiveBlocks, attackDamage, attackSpeed, extraReach, settings));
  }

  private static Settings prepareSettings(ToolMaterial material, TagKey<Block> effectiveBlocks, float attackDamage, float attackSpeed, double extraReach, Settings settings) {
    Settings weaponSettings = applyToolSettings(material, settings, effectiveBlocks);
    return weaponSettings.attributeModifiers(createAttributeModifiers(material, attackDamage, attackSpeed, extraReach));
  }


  public static Settings applyToolSettings(ToolMaterial material, Settings settings, TagKey<Block> effectiveBlocks) {
    RegistryEntryLookup<Block> registryEntryLookup = Registries.createEntryLookup(Registries.BLOCK);
    List<ToolComponent.Rule> toolComponentRuleList = new ArrayList<>(List.of(
        ToolComponent.Rule.ofNeverDropping(registryEntryLookup.getOrThrow(material.incorrectBlocksForDrops())),
        ToolComponent.Rule.ofAlwaysDropping(registryEntryLookup.getOrThrow(effectiveBlocks), material.speed())));
    if (effectiveBlocks.equals(BlockTags.SWORD_EFFICIENT))
      toolComponentRuleList.add(ToolComponent.Rule.ofAlwaysDropping(RegistryEntryList.of(Blocks.COBWEB.getRegistryEntry()), 15.0F));

    settings.maxDamage(material.durability()).repairable(material.repairItems()).enchantable(material.enchantmentValue())
        .component(DataComponentTypes.TOOL, new ToolComponent(toolComponentRuleList, 1.0F, 1));
    return settings;
  }

  public static AttributeModifiersComponent createAttributeModifiers(ToolMaterial material, float attackDamage, float attackSpeed, double reach) {
    return AttributeModifiersComponent.builder()
        .add(
            EntityAttributes.ATTACK_DAMAGE,
            new EntityAttributeModifier(
                Item.BASE_ATTACK_DAMAGE_MODIFIER_ID, attackDamage + material.attackDamageBonus(), EntityAttributeModifier.Operation.ADD_VALUE
            ),
            AttributeModifierSlot.MAINHAND
        )
        .add(
            EntityAttributes.ATTACK_SPEED,
            new EntityAttributeModifier(
                Item.BASE_ATTACK_SPEED_MODIFIER_ID, attackSpeed, EntityAttributeModifier.Operation.ADD_VALUE
            ),
            AttributeModifierSlot.MAINHAND
        )
        .add(
            EntityAttributes.ENTITY_INTERACTION_RANGE,
            new EntityAttributeModifier(
                Identifier.of(PLAYER_ENTITY_INTERACTION_RANGE_MODIFIER_ID.toString()),
                bettercombat_mod_loaded ? 0 : reach,
                EntityAttributeModifier.Operation.ADD_VALUE
            ),
            AttributeModifierSlot.MAINHAND
        )
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
    return true;
  }

  @Override
  public void postDamageEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
    stack.damage(1, attacker, EquipmentSlot.MAINHAND);
  }
}
