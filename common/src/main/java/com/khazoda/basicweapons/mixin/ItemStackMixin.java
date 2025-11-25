package com.khazoda.basicweapons.mixin;

import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.annotation.Nullable;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {

  @Unique
  private static final ThreadLocal<DecimalFormat> COMPONENT_FORMAT = ThreadLocal.withInitial(() -> new DecimalFormat("#.##", DecimalFormatSymbols.getInstance(Locale.ROOT)));

  @Inject(method = "getTooltipLines", at = @At("RETURN"))
  private void fixDamageTooltip(Item.TooltipContext tooltipContext, @Nullable Player player, TooltipFlag tooltipFlag, CallbackInfoReturnable<List<Component>> cir) {
    ItemStack stack = (ItemStack) (Object) this;
    List<Component> tooltip = cir.getReturnValue();
    if (stack.isEmpty()) return;

    float damageBonus = basicweapons$calculateDamageBonus(stack);
    if (damageBonus <= 0) return;

    for (int i = 0; i < tooltip.size(); i++) {
      Component line = tooltip.get(i);
      List<Component> siblings = line.getSiblings();

      for (int j = 0; j < siblings.size(); j++) {
        Component sibling = siblings.get(j);

        if (sibling.getContents() instanceof TranslatableContents translatable) {
          String key = translatable.getKey();
          Object[] args = translatable.getArgs();

          if (("attribute.modifier.plus.0".equals(key) || "attribute.modifier.equals.0".equals(key)) && args.length >= 2) {
            if (basicweapons$isAttackDamage(args[1])) {
              try {
                String currentAmountStr = args[0].toString().replace(",", ".");
                double currentAmount = Double.parseDouble(currentAmountStr);
                double newAmount = currentAmount + damageBonus;
                MutableComponent newSibling = Component.translatable(key, COMPONENT_FORMAT.get().format(newAmount), args[1]).withStyle(ChatFormatting.DARK_GREEN);

                MutableComponent newLine = line.plainCopy();

                for (int k = 0; k < siblings.size(); k++) {
                  if (k == j) {
                    newLine.append(newSibling);
                  } else {
                    newLine.append(siblings.get(k));
                  }
                }

                tooltip.set(i, newLine);
                return;
              } catch (NumberFormatException ignored) {
              }
            }
          }
        }
      }
    }
  }

  @Unique
  private boolean basicweapons$isAttackDamage(Object nameArg) {
    if (nameArg instanceof Component nameComponent) {
      if (nameComponent.getContents() instanceof TranslatableContents tc) {
        String key = tc.getKey();
        if ("attribute.name.attack_damage".equals(key)) {
          return true;
        }
      }
      String localizedName = Component.translatable("attribute.name.attack_damage").getString();
      return nameComponent.getString().contains(localizedName);
    }
    return false;
  }

  @Unique
  private float basicweapons$calculateDamageBonus(ItemStack stack) {
    ItemEnchantments enchantments = stack.getEnchantments();
    if (enchantments.isEmpty()) return 0f;

    float totalBonus = 0f;

    for (Map.Entry<Holder<Enchantment>, Integer> entry : enchantments.entrySet()) {
      Optional<ResourceKey<Enchantment>> keyOpt = entry.getKey().unwrapKey();
      if (keyOpt.isPresent()) {
        String id = keyOpt.get().location().toString();
        int level = entry.getValue();

        if (level <= 0) continue;

        if ("minecraft:sharpness".equals(id) || "basicweapons:might".equals(id)) {
          totalBonus += 0.5f * level + 0.5f;
        }
      }
    }
    return totalBonus;
  }
}