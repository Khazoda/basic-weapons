package com.seacroak.basicweapons.registry;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.LinkedList;
import java.util.List;

import static com.seacroak.basicweapons.Constants.BW_ID;

public class MainRegistry {
  public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, BW_ID);
  public static final List<RegistryObject<Item>> registeredItems = new LinkedList<>();

  public static void preInit() {
    for (BWItems.ItemInfo item : BWItems.items
    ) {
      RegistryObject<Item> i = ITEMS.register(item.name, item.itemSupplier);
      registeredItems.add(i);
    }
  }

  public static void init(IEventBus eventBus) {
    ITEMS.register(eventBus);
  }
}
