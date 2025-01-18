package com.khazoda.basicweapons.mixin.client;

import com.khazoda.basicweapons.materialpack.ResourceAndDatapackCustomLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.RepositorySource;
import org.apache.commons.lang3.ArrayUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

/**
 * Part of the Material Pack system
 * This mixin lets us load resourcepacks from config/basicweapons/bwmp_resources
 * instead of having to clutter the resourcepacks folder (safer too due to our cleanup)
 **/
@Mixin(Minecraft.class)
public class ClientPackFinderMixin {
  @ModifyArg(
      method = "<init>",
      at = @At(
          value = "INVOKE",
          target = "Lnet/minecraft/server/packs/repository/PackRepository;<init>([Lnet/minecraft/server/packs/repository/RepositorySource;)V"
      ),
      index = 0
  )
  private RepositorySource[] addMaterialPackFinder(RepositorySource[] original) {
    ResourceAndDatapackCustomLoader finder = new ResourceAndDatapackCustomLoader(PackType.CLIENT_RESOURCES, true);
    return ArrayUtils.add(original, finder);
  }
} 