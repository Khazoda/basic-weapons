package com.seacroak.basicweapons.registry;

import com.seacroak.basicweapons.Constants;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.HashMap;
import java.util.Map;

public class MainRegistry {
  public final static Map<Integer, Item> registeredItems = new HashMap<>();

  public static void init() {
    int x = 0;
    for (BWItems.ItemInfo item : BWItems.items
    ) {
      registeredItems.put(x, registerItem(item.name, item.itemSupplier.get()));
      x += 1;
    }
  }

  public static Item registerItem(String name, Item item) {
    return Registry.register(Registry.ITEM, new Identifier(Constants.BW_ID, name), item);
  }

}
