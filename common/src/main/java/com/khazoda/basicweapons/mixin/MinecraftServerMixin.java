package com.khazoda.basicweapons.mixin;

import com.khazoda.basicweapons.materialpack.MaterialPackLoader;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@SuppressWarnings("all")
@Mixin(MinecraftServer.class)
public class MinecraftServerMixin {
  @Inject(method = "loadLevel", at = @At("TAIL"))
  private void onWorldLoad(CallbackInfo ci) {
    MinecraftServer server = (MinecraftServer) (Object) this;
    MaterialPackLoader.handleWorldLoad(server);
  }
} 