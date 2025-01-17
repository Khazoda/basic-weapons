package com.khazoda.basicweapons.mixin.client;

import com.khazoda.basicweapons.materialpack.MaterialPackFinder;
import net.minecraft.client.Minecraft;
import net.minecraft.server.packs.repository.RepositorySource;
import org.apache.commons.lang3.ArrayUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import java.io.File;

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
    MaterialPackFinder finder = new MaterialPackFinder(new File("resourcepacks"), true);
    return ArrayUtils.add(original, finder);
  }
} 