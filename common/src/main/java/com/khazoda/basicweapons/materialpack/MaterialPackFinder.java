package com.khazoda.basicweapons.materialpack;

import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.*;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.server.packs.repository.RepositorySource;

import java.io.File;
import java.nio.file.Path;
import java.util.Optional;
import java.util.function.Consumer;

public class MaterialPackFinder implements RepositorySource {
  private final File resourcepacksFolder;
  private final boolean isRequired;
  private static final PackSource MATERIAL = PackSource.create(Component::copy, true);
  private static final String MATERIAL_PACKS_FOLDER = "basicweapons_materialpacks";

  public MaterialPackFinder(File resourcepacksFolder, boolean isRequired) {
    this.resourcepacksFolder = new File(resourcepacksFolder, MATERIAL_PACKS_FOLDER);
    this.isRequired = isRequired;
  }

  private String formatPackName(String folderName) {
    // Split by underscores to separate sections
    String[] sections = folderName.split("_");

    // If we have at least 2 sections (bwmp_materialname_...)
    if (sections.length >= 2) {
      // Get the material name section (index 1)
      String materialName = sections[1];

      // Split material name by hyphens if it exists
      String[] materialWords = materialName.split("-");

      // Capitalize each word and join with spaces
      StringBuilder formatted = new StringBuilder();
      for (String word : materialWords) {
        if (word.length() > 0) {
          formatted.append(Character.toUpperCase(word.charAt(0)))
              .append(word.substring(1).toLowerCase())
              .append(" ");
        }
      }

      // Add "Material" suffix and trim extra spaces
      return formatted.toString().trim() + " Material";
    }

    // Fallback if the name doesn't follow the convention
    return folderName + " Material";
  }

  @Override
  public void loadPacks(Consumer<Pack> packConsumer) {
    if (!resourcepacksFolder.exists() || !resourcepacksFolder.isDirectory()) {
      return;
    }

    File[] packs = resourcepacksFolder.listFiles(file ->
        file.isDirectory() && new File(file, "pack.mcmeta").exists());

    if (packs != null) {
      for (File packFile : packs) {
        String packId = (isRequired ? "basicweapons:" : "basicweaponsopt:") + packFile.getName();
        Path packPath = packFile.toPath();

        // Create a formatted display name
        String displayName = formatPackName(packFile.getName());

        PackLocationInfo location = new PackLocationInfo(
            packId,
            Component.literal(displayName),
            MATERIAL,
            Optional.empty()
        );

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

        PackSelectionConfig selectionConfig = new PackSelectionConfig(
            isRequired,
            Pack.Position.TOP,
            true
        );

        Pack pack = Pack.readMetaAndCreate(
            location,
            resources,
            PackType.CLIENT_RESOURCES,
            selectionConfig
        );

        if (pack != null) {
          packConsumer.accept(pack);
        }
      }
    }
  }
} 