package com.khazoda.basicweapons.platform;

import com.khazoda.basicweapons.platform.services.IPlatformHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.fml.ModList;
import net.neoforged.fml.loading.FMLLoader;
import net.neoforged.fml.util.thread.EffectiveSide;
import net.neoforged.neoforge.server.ServerLifecycleHooks;

import java.util.function.BiConsumer;

public class NorgePlatformHelper implements IPlatformHelper {
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

  @Override
  public RegistryAccess getCurrentRegistryAccess() {
    try {
      if (EffectiveSide.get().isClient()) {
        return Minecraft.getInstance().getConnection().registryAccess();
      }
    } catch (Throwable ignored) {
    }
    return ServerLifecycleHooks.getCurrentServer().registryAccess();
  }
}