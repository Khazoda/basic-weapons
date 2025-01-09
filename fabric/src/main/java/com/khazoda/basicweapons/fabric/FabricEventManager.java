package com.khazoda.basicweapons.fabric;

import com.khazoda.basicweapons.platform.FabricPlatformHelper;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;

public class FabricEventManager {
  public static void init() {
    ServerLifecycleEvents.SERVER_STARTING.register((minecraftServer) -> {
      FabricPlatformHelper.currentMinecraftServer = minecraftServer;
    });
  }
}
