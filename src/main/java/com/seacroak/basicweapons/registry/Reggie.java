package com.seacroak.basicweapons.registry;

import com.seacroak.basicweapons.util.ID;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

import java.util.function.Function;

/**
 * Registry Helper Class
 **/
public class Reggie {

  private static RegistryKey<Block> createBlockKey(String path) {
    return RegistryKey.of(RegistryKeys.BLOCK, ID.of(path));
  }

  private static Block register(String path, AbstractBlock.Settings settings) {
    return Blocks.register(createBlockKey(path), Block::new, settings);
  }

  public static Block register(String path, Function<AbstractBlock.Settings, Block> function,
                               AbstractBlock.Settings settings) {
    return Blocks.register(createBlockKey(path), function, settings);
  }

  private static RegistryKey<Item> createItemKey(String path) {
    return RegistryKey.of(RegistryKeys.ITEM, ID.of(path));
  }

  public static Item register(String path, Item item) {
    return Items.register(createItemKey(path), (itemSettings) -> item);
  }

  public static Item register(String path, Function<Item.Settings, Item> factory, Item.Settings settings) {
    return Items.register(createItemKey(path), factory, settings);
  }

  public static Item register(String path, Function<Item.Settings, Item> factory) {
    return Items.register(createItemKey(path), factory);
  }

  public static Item register(String path, Item.Settings settings) {
    return Items.register(createItemKey(path), Item::new, settings);
  }

  public static Item register(String path) {
    return Items.register(createItemKey(path), Item::new, new Item.Settings());
  }

  public static Item register(Block block, Item.Settings settings) {
    return Items.register(block, settings);
  }
}
