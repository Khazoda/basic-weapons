package com.khazoda.basicweapons.registry;

import com.khazoda.basicweapons.data.WeaponType;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.List;

public class CreativeModeTabHandler {

  public static void buildContents() {
    ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.COMBAT).register(content -> {
      Item lastItem = Items.NETHERITE_AXE;
      for (WeaponType type : WeaponType.values()) {
        List<Item> items = WeaponRegistry.getItemsByType(type);
        for (Item item : items) {
          if (item != null) {
            content.addAfter(lastItem, item);
            lastItem = item;
          }
        }
      }
    });
  }
}
