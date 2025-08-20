package com.khazoda.basicweapons.platform;

import com.khazoda.basicweapons.platform.services.IPlatformHelper;
import com.khazoda.basicweapons.registry.WeaponRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.core.RegistryAccess;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.level.storage.LevelResource;

import java.io.File;

public class FabricPlatformHelper implements IPlatformHelper {

  @Override
  public String getPlatformName() {
    return "Fabric";
  }

  @Override
  public boolean isModLoaded(String modId) {
    return FabricLoader.getInstance().isModLoaded(modId);
  }

  @Override
  public boolean isDevelopmentEnvironment() {
    return FabricLoader.getInstance().isDevelopmentEnvironment();
  }

  public static MinecraftServer currentMinecraftServer = null;

  @Override
  public RegistryAccess getCurrentRegistryAccess() {
    try {
      if (currentMinecraftServer == null || !currentMinecraftServer.isSameThread()) {
        return Minecraft.getInstance().getConnection().registryAccess();
      }
    } catch (Throwable ignored) {
    }
    return currentMinecraftServer.registryAccess();
  }

  @Override
  public File getWorldDatapacksDirectory() {
    try {
      if (currentMinecraftServer == null || !currentMinecraftServer.isSameThread()) {
        return Minecraft.getInstance().level != null ?
            Minecraft.getInstance().level.getServer().getWorldPath(LevelResource.DATAPACK_DIR).toFile() :
            null;
      }
      return currentMinecraftServer.getWorldPath(LevelResource.DATAPACK_DIR).toFile();
    } catch (Throwable ignored) {
      return null;
    }
  }

  @Override
  public boolean registerFurnaceFuels() {
    for(Item weapon: WeaponRegistry.getItemsByMaterial(Tiers.WOOD)) {
      FuelRegistry.INSTANCE.add(weapon, 200); // 200 tick burn time like vanilla weapons/tools
    }
    return true;
  }
}
