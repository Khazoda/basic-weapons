package com.khazoda.basicweapons.platform;

import com.khazoda.basicweapons.platform.services.IPlatformHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.core.RegistryAccess;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.storage.LevelResource;
import net.neoforged.fml.ModList;
import net.neoforged.fml.loading.FMLLoader;
import net.neoforged.fml.util.thread.EffectiveSide;
import net.neoforged.neoforge.server.ServerLifecycleHooks;

import java.io.File;
import java.nio.file.Path;
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
    return !FMLLoader.getCurrent().isProduction();
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

  @Override
  public File getWorldDatapacksDirectory() {
    MinecraftServer server = ServerLifecycleHooks.getCurrentServer();
    if (server != null) {
      return server.getWorldPath(LevelResource.DATAPACK_DIR).toFile();
    }

    // For singleplayer before world load
    Minecraft client = Minecraft.getInstance();
    if (client.level != null) {
      Path worldPath = client.level.getServer().getWorldPath(LevelResource.DATAPACK_DIR);
      return worldPath.toFile();
    }
    return null;
  }

  @Override
  public boolean registerFurnaceFuels() {
    /* Handled in neoforge data map: data/neoforge/data_maps/item/furnace_fuels.json */
    return true;
  }
}