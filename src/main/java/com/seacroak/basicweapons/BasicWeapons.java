package com.seacroak.basicweapons;

import com.seacroak.basicweapons.registry.BWItems;
import com.seacroak.basicweapons.registry.MainRegistry;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegistryObject;

import static com.seacroak.basicweapons.Constants.BW_ID;
import static com.seacroak.basicweapons.Constants.BW_LOG;
import static com.seacroak.basicweapons.registry.MainRegistry.registeredItems;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(BW_ID)
public class BasicWeapons {

//  // Create a Deferred Register to hold Blocks which will all be registered under the "examplemod" namespace
//  public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, BW_ID);
//  // Create a Deferred Register to hold Items which will all be registered under the "examplemod" namespace
//  public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, BW_ID);
//  // Create a Deferred Register to hold CreativeModeTabs which will all be registered under the "examplemod" namespace
//  public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, BW_ID);
//
//  // Creates a new Block with the id "examplemod:example_block", combining the namespace and path
//  public static final RegistryObject<Block> EXAMPLE_BLOCK = BLOCKS.register("example_block", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE)));
//  // Creates a new BlockItem with the id "examplemod:example_block", combining the namespace and path
//  public static final RegistryObject<Item> EXAMPLE_BLOCK_ITEM = ITEMS.register("example_block", () -> new BlockItem(EXAMPLE_BLOCK.get(), new Item.Properties()));

//    public static final RegistryObject<Item> EXAMPLE_ITEM = ITEMS.register("example_item", () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
//            .alwaysEat().nutrition(1).saturationMod(2f).build())));

//    public static final RegistryObject<CreativeModeTab> EXAMPLE_TAB = CREATIVE_MODE_TABS.register("example_tab", () -> CreativeModeTab.builder()
//            .withTabsBefore(CreativeModeTabs.COMBAT)
//            .icon(() -> EXAMPLE_ITEM.get().getDefaultInstance())
//            .displayItems((parameters, output) -> {
//                output.accept(EXAMPLE_ITEM.get()); // Add the example item to the tab. For your own tabs, this method is preferred over the event
//            }).build());

  public BasicWeapons() {
    IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
    MinecraftForge.EVENT_BUS.register(this);
    Constants.BW_LOG.info("You have my sword.");

    BWItems.init();
    MainRegistry.preInit();
    MainRegistry.init(modEventBus);

//    CREATIVE_MODE_TABS.register(modEventBus);
    modEventBus.addListener(this::addCreative);
    ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
  }

  // Add the example block item to the building blocks tab
  private void addCreative(BuildCreativeModeTabContentsEvent event) {
    if (event.getTabKey() == CreativeModeTabs.COMBAT) {
      for (RegistryObject<Item> registeredItem : registeredItems) {
        event.accept(registeredItem);
      }
    }
  }

  private void commonSetup(final FMLCommonSetupEvent event) {
    // Some common setup code
    BW_LOG.info("Mod loading..");

  }

  // You can use SubscribeEvent and let the Event Bus discover methods to call
  @SubscribeEvent
  public void onServerStarting(ServerStartingEvent event) {
    // Do something when the server starts
    BW_LOG.info("Mod loading on server side..");
  }

  // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
  @Mod.EventBusSubscriber(modid = BW_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
  public static class ClientModEvents {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
      // Some client setup code
      BW_LOG.info("Mod loading on client side..");
    }
  }
}
