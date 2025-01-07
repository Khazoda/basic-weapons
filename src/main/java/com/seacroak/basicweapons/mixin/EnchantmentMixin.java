package com.seacroak.basicweapons.mixin;

import com.seacroak.basicweapons.registry.TagRegistry;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.entry.RegistryEntryList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unreachable")
@Mixin(Enchantment.class)
public class EnchantmentMixin {

  @Inject(method = "getApplicableItems", at = @At("RETURN"), cancellable = true)
  private void onGetApplicableItems(CallbackInfoReturnable<RegistryEntryList<Item>> cir) {
    String enchantDesc = ((Enchantment) (Object) this).toString().toLowerCase();
    RegistryEntryList<Item> currentList = cir.getReturnValue();

    // Return early if list is empty (optional?)
    if (currentList == null || currentList.stream().findAny().isEmpty()) return;
    // Create a filtered list that excludes enchantments on certain weapons from this mod
    List<RegistryEntry<Item>> filteredList = new ArrayList<>();

    for (var entry : currentList) {
      ItemStack stack = new ItemStack(entry.value());

      // If item to enchant is not a weapon from this mod add it to the list and go next
      if (!stack.isIn(TagRegistry.BASIC_WEAPONS)) {
        filteredList.add(entry);
        continue;
      }

      // Don't add weapon to the list if the current enchantment is blacklisted on it
      if (stack.isIn(TagRegistry.NO_SWEEPING) && enchantDesc.contains("sweeping")) continue;
      if (stack.isIn(TagRegistry.BLUNT_WEAPONS) && enchantDesc.contains("sharpness")) continue;

      // If it passed all filters, add the weapon
      filteredList.add(entry);
    }
    cir.setReturnValue(RegistryEntryList.of(filteredList));
  }

  @Inject(method = "isAcceptableItem", at = @At("HEAD"), cancellable = true)
  private void onIsAcceptableItem(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
    if (stack.isIn(TagRegistry.BASIC_WEAPONS)) {
      String enchantDesc = ((Enchantment) (Object) this).toString().toLowerCase();

      // Block sweeping edge for weapons that shouldn't have it
      if (stack.isIn(TagRegistry.NO_SWEEPING) && enchantDesc.contains("sweeping")) {
        cir.setReturnValue(false);
        return;
      }

      // Block sharpness only for blunt weapons
      if (stack.isIn(TagRegistry.BLUNT_WEAPONS) && enchantDesc.contains("sharpness")) {
        cir.setReturnValue(false);
      }
    }
  }

  @Inject(method = "isPrimaryItem", at = @At("HEAD"), cancellable = true)
  private void onIsPrimaryItem(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
    if (stack.isIn(TagRegistry.BASIC_WEAPONS)) {
      String enchantDesc = ((Enchantment) (Object) this).toString().toLowerCase();

      // Block sweeping edge for weapons that shouldn't have it
      if (stack.isIn(TagRegistry.NO_SWEEPING) && enchantDesc.contains("sweeping")) {
        cir.setReturnValue(false);
        return;
      }

      // Block sharpness for blunt weapons
      if (stack.isIn(TagRegistry.BLUNT_WEAPONS) && enchantDesc.contains("sharpness")) {
        cir.setReturnValue(false);
      }
    }
  }
}