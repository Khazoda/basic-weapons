package com.khazoda.basicweapons.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.enchantment.Enchantment;

import static com.khazoda.basicweapons.Constants.ID;

public class EnchantmentRegistry {

  public static final ResourceKey<Enchantment> MIGHT = of("might");

  private static ResourceKey<Enchantment> of(String name) {
    return ResourceKey.create(Registries.ENCHANTMENT, ID(name));
  }

  public static void init() {
  }

}
