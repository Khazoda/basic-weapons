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

public class MaterialPackFinder implements RepositorySource {
    private final File packsFolder;
    private final boolean isRequired;
    private final PackType packType;
    private static final PackSource MATERIAL = PackSource.create(Component::copy, true);
    private static final String SOURCE_FOLDER = "basicweapons_materialpacks";
    private static final String RESOURCEPACK_TARGET = "materialpacks";
    private static final String DATAPACK_TARGET = "config/basicweapons/bwmp_data";
    private static final String ZIP_EXTENSION = ".zip";

    private static final Predicate<Path> IS_VALID_RESOURCE_PACK = (pack) -> {
        return Files.isDirectory(pack.resolve("assets")) &&
            Files.isRegularFile(pack.resolve("pack.mcmeta"));
    };

    private static final Predicate<Path> IS_VALID_DATA_PACK = (pack) -> {
        return Files.isDirectory(pack.resolve("data")) &&
            Files.isRegularFile(pack.resolve("pack.mcmeta"));
    };

    public MaterialPackFinder(File resourcepacksFolder, PackType packType, boolean isRequired) {
        if (packType == PackType.CLIENT_RESOURCES) {
            // For resource packs, use resourcepacks/materialpacks
            this.packsFolder = new File(resourcepacksFolder, RESOURCEPACK_TARGET);
        } else {
            // For datapacks, use config/basicweapons/bwmp_data
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
                if (word.length() > 0) {
                    formatted.append(Character.toUpperCase(word.charAt(0)))
                        .append(word.substring(1).toLowerCase())
                        .append(" ");
                }
            }

            return formatted.toString().trim() + (packType == PackType.CLIENT_RESOURCES ? " Material Resources" : " Material Data");
        }
        return folderName + (packType == PackType.CLIENT_RESOURCES ? " Material Resources" : " Material Data");
    }

    @Override
    public void loadPacks(Consumer<Pack> packConsumer) {
        if (!packsFolder.exists() || !packsFolder.isDirectory()) {
            return;
        }

        File[] packs = packsFolder.listFiles(file ->
            (file.isDirectory() || file.getName().endsWith(ZIP_EXTENSION)) &&
                new File(file, "pack.mcmeta").exists() &&
                (packType == PackType.CLIENT_RESOURCES ?
                    IS_VALID_RESOURCE_PACK.test(file.toPath()) :
                    IS_VALID_DATA_PACK.test(file.toPath()))
        );

        if (packs != null) {
            for (File packFile : packs) {
                String packId = "basicweapons:" + (packFile.getName().endsWith(ZIP_EXTENSION) ?
                    packFile.getName().substring(0, packFile.getName().length() - 4) :
                    packFile.getName());

                Path packPath = packFile.toPath();
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
                    packType,
                    selectionConfig
                );

                if (pack != null) {
                    packConsumer.accept(pack);
                }
            }
        }
    }
} 