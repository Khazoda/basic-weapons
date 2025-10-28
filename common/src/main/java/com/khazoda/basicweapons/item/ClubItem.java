package com.khazoda.basicweapons.item;


import com.khazoda.basicweapons.mixinutils.PlayerEntityAccessor;
import com.khazoda.basicweapons.registry.TagRegistry;
import com.khazoda.basicweapons.utils.AllowDenyPass;
import net.minecraft.core.Holder;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.enchantment.Enchantment;

public class ClubItem extends BasicWeaponSweeplessItem {
  public ClubItem(ToolMaterial ToolMaterial, float attackDamage, float attackSpeed, double reach, Item.Properties properties) {
    super(ToolMaterial, BlockTags.AIR, attackDamage, attackSpeed, reach, properties);
  }

  @Override
  public void hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
    Player player = (Player) attacker;
    float f2 = ((PlayerEntityAccessor) player).bw$getCooldown(0.5f);
    if (f2 > 0.9F) {
      target.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 15));
    }
    stack.hurtAndBreak(1, attacker, EquipmentSlot.MAINHAND);
  }

  @Override
  public AllowDenyPass bw$canEnchant(ItemStack itemstack, Holder<Enchantment> enchantment) {
    // The item can't be enchanted by enchantments listed here
    return enchantment.is(TagRegistry.SHARPNESS_ENCHANTABLE) || enchantment.is(TagRegistry.SWEEPING_EDGE_ENCHANTABLE) ? AllowDenyPass.DENY : AllowDenyPass.PASS;
  }
}