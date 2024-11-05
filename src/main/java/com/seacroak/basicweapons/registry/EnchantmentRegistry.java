package com.seacroak.basicweapons.registry;

import com.seacroak.basicweapons.util.ID;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

public class EnchantmentRegistry {

  public static final RegistryKey<Enchantment> MIGHT = of("might");

  private static RegistryKey<Enchantment> of(String name) {
    return RegistryKey.of(RegistryKeys.ENCHANTMENT, ID.of(name));
  }

  public static void init() {
  }

}
