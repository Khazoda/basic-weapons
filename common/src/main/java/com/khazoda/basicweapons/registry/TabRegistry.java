package com.khazoda.basicweapons.registry;

import com.khazoda.basicweapons.BasicWeaponsCommon;
import com.khazoda.basicweapons.struct.WeaponType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;

import java.util.List;
import java.util.function.Supplier;

public class TabRegistry {
  public static final Supplier<CreativeModeTab> BUILTIN_BASIC_WEAPONS_TAB = BasicWeaponsCommon.REGISTRARS.get(Registries.CREATIVE_MODE_TAB).register("main", ()
      -> CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0).title(Component.translatable("itemGroup.basicweapons.main")).icon(() -> WeaponRegistry.getItemsByMaterial(ToolMaterial.IRON).getFirst().getDefaultInstance()).displayItems((parameters, output) -> {
    for (WeaponType.VanillaWeaponType type : WeaponType.VanillaWeaponType.values()) {
      List<Item> items = WeaponRegistry.getItemsByType(WeaponRegistry.ITEMS_BY_TYPE.BUILTIN, type);
      for (Item item : items) {
        if (item != null) {
          output.accept(item);
        }
      }
    }
    for (WeaponType.BasicWeaponType type : WeaponType.BasicWeaponType.values()) {
      List<Item> items = WeaponRegistry.getItemsByType(WeaponRegistry.ITEMS_BY_TYPE.BUILTIN, type);
      for (Item item : items) {
        if (item != null) {
          output.accept(item);
        }
      }
    }
  }).build());

  public static final Supplier<CreativeModeTab> MATERIALPACK_BASIC_WEAPONS_TAB = BasicWeaponsCommon.REGISTRARS.get(Registries.CREATIVE_MODE_TAB).register("materialpack", ()
      -> CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0).title(Component.translatable("itemGroup.basicweapons.materialpack")).icon(() -> WeaponRegistry.getItemsByType(WeaponRegistry.ITEMS_BY_TYPE.MATERIALPACK, WeaponType.BasicWeaponType.DAGGER).getFirst().getDefaultInstance()).displayItems((parameters, output) -> {
    for (WeaponType.VanillaWeaponType type : WeaponType.VanillaWeaponType.values()) {
      List<Item> items = WeaponRegistry.getItemsByType(WeaponRegistry.ITEMS_BY_TYPE.MATERIALPACK, type);
      for (Item item : items) {
        if (item != null) {
          output.accept(item);
        }
      }
    }
    for (WeaponType.BasicWeaponType type : WeaponType.BasicWeaponType.values()) {
      List<Item> items = WeaponRegistry.getItemsByType(WeaponRegistry.ITEMS_BY_TYPE.MATERIALPACK, type);
      for (Item item : items) {
        if (item != null) {
          output.accept(item);
        }
      }
    }
  }).build());

  public static void init() {
  }
}