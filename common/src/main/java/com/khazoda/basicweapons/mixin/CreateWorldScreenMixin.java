package com.khazoda.basicweapons.mixin;

import com.khazoda.basicweapons.materialpack.MaterialPackFinder;
import net.minecraft.client.gui.screens.worldselection.CreateWorldScreen;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.RepositorySource;
import org.apache.commons.lang3.ArrayUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import java.io.File;

@Mixin(CreateWorldScreen.class)
public class CreateWorldScreenMixin {
  @ModifyArg(
      method = "openFresh",
      at = @At(
          value = "INVOKE",
          target = "Lnet/minecraft/server/packs/repository/PackRepository;<init>([Lnet/minecraft/server/packs/repository/RepositorySource;)V"
      )
  )
  private static RepositorySource[] addMaterialDataPackFinder(RepositorySource[] original) {
    MaterialPackFinder finder = new MaterialPackFinder(new File("resourcepacks"), PackType.SERVER_DATA, true);
    return ArrayUtils.add(original, finder);
  }
} 