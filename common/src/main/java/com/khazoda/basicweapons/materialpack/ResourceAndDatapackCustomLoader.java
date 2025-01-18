package com.khazoda.basicweapons.materialpack;

import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.*;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.server.packs.repository.RepositorySource;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;

import static com.khazoda.basicweapons.materialpack.MaterialPackConstants.*;

/**
 * This class functions as a functionally similar implementation to Minecraft's resource and datapack loading systems.
 * As we're loading both from a custom directory Minecraft doesn't handle them automatically.
 * This class is instantiated in ClientPackFinderMixin, CreateWorldScreenMixin and ServerPacksSourceMixin.
 * There is rudimentary validation to prevent loading non-materialpacks or similar.
 **/
public class ResourceAndDatapackCustomLoader implements RepositorySource {
  private final File packsFolder;
  private final boolean isRequired;
  private final PackType packType;
  private static final PackSource MATERIAL = PackSource.create(Component::copy, true);

  private static final Predicate<Path> IS_VALID_RESOURCE_PACK = (pack) -> Files.isDirectory(pack.resolve(ASSETS_PATH)) && Files.isRegularFile(pack.resolve("pack.mcmeta"));
  private static final Predicate<Path> IS_VALID_DATA_PACK = (pack) -> Files.isDirectory(pack.resolve(DATA_PATH)) && Files.isRegularFile(pack.resolve("pack.mcmeta"));

  public ResourceAndDatapackCustomLoader(PackType packType, boolean isRequired) {
    if (packType == PackType.CLIENT_RESOURCES) {
      this.packsFolder = new File(RESOURCEPACK_TARGET);
    } else {
      this.packsFolder = new File(DATAPACK_TARGET);
    }
    this.packType = packType;
    this.isRequired = isRequired;
  }

  private String formatPackName(String folderName) {
    String[] sections = folderName.split("_");
    if (sections.length >= 2) {
      String materialName = sections[1];
      String[] materialWords = materialName.split("-");

      StringBuilder formatted = new StringBuilder();
      for (String word : materialWords) {
        if (!word.isEmpty()) {
          formatted.append(Character.toUpperCase(word.charAt(0))).append(word.substring(1).toLowerCase()).append(" ");
        }
      }
      return formatted.toString().trim() + (packType == PackType.CLIENT_RESOURCES ? " Material Resources" : " Material Data");
    }
    return folderName + (packType == PackType.CLIENT_RESOURCES ? " Material Resources" : " Material Data");
  }

  @Override
  public void loadPacks(Consumer<Pack> packConsumer) {
    if (!packsFolder.exists() || !packsFolder.isDirectory()) return;

    File[] packs = packsFolder.listFiles(file -> (file.isDirectory() || file.getName().endsWith(".zip"))
        && new File(file, "pack.mcmeta").exists()
        && (packType == PackType.CLIENT_RESOURCES ? IS_VALID_RESOURCE_PACK.test(file.toPath()) : IS_VALID_DATA_PACK.test(file.toPath())));

    if (packs != null) {
      for (File packFile : packs) {
        String packId = "basicweapons:" + (packFile.getName().endsWith(".zip") ? packFile.getName().substring(0, packFile.getName().length() - 4) : packFile.getName());

        Path packPath = packFile.toPath();
        String displayName = formatPackName(packFile.getName());

        PackLocationInfo location = new PackLocationInfo(packId, Component.literal(displayName), MATERIAL, Optional.empty());

        Pack.ResourcesSupplier resources = new Pack.ResourcesSupplier() {
          @Override
          public PackResources openPrimary(PackLocationInfo info) {
            return new PathPackResources(info, packPath);
          }

          @Override
          public PackResources openFull(PackLocationInfo info, Pack.Metadata metadata) {
            return new PathPackResources(info, packPath);
          }
        };

        PackSelectionConfig selectionConfig = new PackSelectionConfig(isRequired, Pack.Position.TOP, true);

        Pack pack = Pack.readMetaAndCreate(location, resources, packType, selectionConfig);

        if (pack != null) packConsumer.accept(pack);
      }
    }
  }
} 