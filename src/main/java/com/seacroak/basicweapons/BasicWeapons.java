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
  public BasicWeapons() {
    IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
    MinecraftForge.EVENT_BUS.register(this);
    Constants.BW_LOG.info("You have my sword.");

    BWItems.init();
    MainRegistry.preInit();
    MainRegistry.init(modEventBus);

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
    BW_LOG.info("Mod loading..");
  }

  @SubscribeEvent
  public void onServerStarting(ServerStartingEvent event) {
    BW_LOG.info("Mod loading on server side..");
  }

  @Mod.EventBusSubscriber(modid = BW_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
  public static class ClientModEvents {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
      BW_LOG.info("Mod loading on client side..");
    }
  }
}
