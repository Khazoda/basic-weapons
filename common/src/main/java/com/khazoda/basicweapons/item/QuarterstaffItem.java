package com.khazoda.basicweapons.item;

import com.khazoda.basicweapons.registry.TagRegistry;
import com.khazoda.basicweapons.utils.AllowDenyPass;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.component.BlocksAttacks;
import net.minecraft.world.item.enchantment.Enchantment;

import java.util.List;
import java.util.Optional;

public class QuarterstaffItem extends BasicWeaponItem {
  public QuarterstaffItem(ToolMaterial material, float attackDamage, float attackSpeed, double reach, Item.Properties properties) {
    super(material, BlockTags.AIR, attackDamage, attackSpeed, reach,
        properties.component(DataComponents.BLOCKS_ATTACKS,
            new BlocksAttacks(0.15F, 1.5F, List.of(
                new BlocksAttacks.DamageReduction(90.0F, Optional.empty(), 0.0F, 1.0F)),
                new BlocksAttacks.ItemDamageFunction(3.0F, 1.0F, 1.0F), Optional.of(DamageTypeTags.BYPASSES_SHIELD),
                Optional.of(SoundEvents.SHIELD_BLOCK), Optional.of(SoundEvents.SHIELD_BREAK))).component(DataComponents.BREAK_SOUND, SoundEvents.SHIELD_BREAK));
  }

  @Override
  public AllowDenyPass bw$canEnchant(ItemStack itemstack, Holder<Enchantment> enchantment) {
    // The item can't be enchanted by enchantments listed here
    return enchantment.is(TagRegistry.SHARPNESS_ENCHANTABLE) ? AllowDenyPass.DENY : AllowDenyPass.PASS;
  }
}