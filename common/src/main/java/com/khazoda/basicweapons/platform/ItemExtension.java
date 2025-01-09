package com.khazoda.basicweapons.platform;

import com.khazoda.basicweapons.utils.AllowDenyPass;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.ApiStatus;

public interface ItemExtension {

  @ApiStatus.Internal
  default Item getBwItem() {
    return (Item) this;
  }

  default void bw$setDamage(ItemStack stack, int damage) {
    stack.set(DataComponents.DAMAGE, Mth.clamp(damage, 0, stack.getMaxDamage()));
  }

  default EquipmentSlot bw$getEquipmentSlot(ItemStack stack) {
    if (stack.getItem() instanceof ArmorItem) {
      return ((ArmorItem) stack.getItem()).getEquipmentSlot();
    }

    return EquipmentSlot.MAINHAND;
  }

  default void bw$onArmorTick(ItemStack itemstack, Level world, Player player) {
  }

  default boolean bw$canPerformAction(ItemStack stack, String toolAction) {
    return false;
  }

  default AllowDenyPass bw$canEnchant(ItemStack itemstack, Holder<Enchantment> enchantment) {
    return AllowDenyPass.PASS;
  }
}