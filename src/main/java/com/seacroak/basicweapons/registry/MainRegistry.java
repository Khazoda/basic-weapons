package com.seacroak.basicweapons.registry;

import com.seacroak.basicweapons.Constants;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.LinkedList;
import java.util.List;

public class MainRegistry {

  public static void init() {
    List<Item> registeredItems = new LinkedList<>();

    for (BWItems.ItemInfo item : BWItems.items
    ) {
      registeredItems.add(registerItem(item.name, item.itemSupplier.get()));
    }

    ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(content -> {
      content.addAfter(Items.NETHERITE_AXE, registeredItems.get(0));
      for (int i = 1; i < registeredItems.size(); i++) {
        content.addAfter(registeredItems.get(i - 1), registeredItems.get(i));
      }
    });
  }

  public static Item registerItem(String name, Item item) {
    return Registry.register(Registries.ITEM, new Identifier(Constants.BW_ID, name), item);
  }

}
