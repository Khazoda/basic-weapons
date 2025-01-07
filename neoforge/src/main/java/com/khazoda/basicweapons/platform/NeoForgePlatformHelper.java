package com.khazoda.basicweapons.platform;

import com.khazoda.basicweapons.platform.services.IPlatformHelper;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.fml.ModList;
import net.neoforged.fml.loading.FMLLoader;

import java.util.function.BiConsumer;

public class NeoForgePlatformHelper implements IPlatformHelper {
  private BiConsumer<CreativeModeTab, CreativeModeTab.Output> itemGenerator;

  @Override
  public String getPlatformName() {
    return "NeoForge";
  }

  @Override
  public boolean isModLoaded(String modId) {
    return ModList.get().isLoaded(modId);
  }

  @Override
  public boolean isDevelopmentEnvironment() {
    return !FMLLoader.isProduction();
  }
}