package com.seacroak.basicweapons.registry;

import com.seacroak.basicweapons.Constants;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.Map;

public class MainRegistry {
  public final static Map<Integer, Item> registeredItems = new HashMap<>();
  public final static boolean bronze_mod_loaded = FabricLoader.getInstance().isModLoaded("bronze");

  public static void init() {


    int x = 0;
    for (BWItems.ItemInfo item : BWItems.items
    ) {
      registeredItems.put(x, registerItem(item.name, item.itemSupplier.get()));
      x += 1;
    }

    ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(content -> {
      content.addAfter(Items.NETHERITE_AXE, registeredItems.get(0));
      for (int i = 1; i < registeredItems.size(); i++) {
        content.addAfter(registeredItems.get(i - 1), registeredItems.get(i));
      }
    });
  }

  public static Item registerItem(String name, Item item) {
    return Registry.register(Registries.ITEM, Identifier.of(Constants.BW_NAMESPACE, name), item);
  }

}
