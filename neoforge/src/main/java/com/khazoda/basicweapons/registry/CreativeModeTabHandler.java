package com.khazoda.basicweapons.registry;

import com.khazoda.basicweapons.struct.WeaponType;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

import static com.khazoda.basicweapons.Constants.MOD_ID;

@EventBusSubscriber(modid = MOD_ID)
public class CreativeModeTabHandler {

  @SubscribeEvent
  public static void buildContents(BuildCreativeModeTabContentsEvent event) {
    if (event.getTabKey() == CreativeModeTabs.COMBAT) {
      Item lastItem = Items.NETHERITE_AXE;
      for (WeaponType.BasicWeaponType type : WeaponType.BasicWeaponType.values()) {
        for (Item item : WeaponRegistry.getItemsByType(type)) {
          event.insertAfter(new ItemStack(lastItem), new ItemStack(item), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
          lastItem = item;
        }
      }
      for (WeaponType.VanillaWeaponType type : WeaponType.VanillaWeaponType.values()) {
        for (Item item : WeaponRegistry.getItemsByType(type)) {
          event.insertAfter(new ItemStack(lastItem), new ItemStack(item), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
          lastItem = item;
        }
      }
    }
  }
}
