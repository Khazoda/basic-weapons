package com.khazoda.basicweapons.materialpack;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.khazoda.basicweapons.Constants;
import com.khazoda.basicweapons.registry.WeaponRegistry;
import net.minecraft.world.item.Tier;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class MaterialPackLoader {
  private static final String SOURCE_FOLDER = "basicweapons_materialpacks";
  private static final String RESOURCEPACK_TARGET = "materialpacks";
  private static final String DATAPACK_TARGET = "config/basicweapons/bwmp_data";
  private static final String DATA_PATH = "data";
  private static final String ASSETS_PATH = "assets";
  private static final String CUSTOM_MATERIALS_PATH = "custom_materials";
  private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
  private static final Map<String, Tier> loadedMaterials = new HashMap<>();
  private static final Map<String, String> materialToDatapackName = new HashMap<>();
  private static final Set<String> initiallyLoadedPacks = new HashSet<>();
  private static boolean hasInitialized = false;
  private static final String ZIP_EXTENSION = ".zip";

  public static void loadPacks() {
    if (hasInitialized) {
      Constants.LOG.warn("Attempted to load material packs after initialization - skipping");
      return;
    }

    // Clean up target folders first
    cleanTargetFolders();

    File materialPacksFolder = new File(SOURCE_FOLDER);
    if (!materialPacksFolder.exists()) {
      if (materialPacksFolder.mkdir()) {
        Constants.LOG.info("Created material packs folder at {}", materialPacksFolder.getAbsolutePath());
      } else {
        Constants.LOG.error("Failed to create materials folder");
        return;
      }
    }

    File[] packFiles = materialPacksFolder.listFiles(file ->
        file.isDirectory() || file.getName().endsWith(ZIP_EXTENSION)
    );

    if (packFiles == null || packFiles.length == 0) {
      Constants.LOG.info("No material packs found in {}", materialPacksFolder.getAbsolutePath());
      return;
    }

    for (File packFile : packFiles) {
      String packName = packFile.getName();
      if (packFile.isDirectory()) {
        processPackFolder(packFile);
      } else {
        // For ZIP files, extract to a directory with the same name (minus .zip)
        File extractDir = new File(materialPacksFolder, packName.substring(0, packName.length() - 4));
        try {
          if (extractDir.exists()) {
            FileUtils.deleteDirectory(extractDir); // Clean up any previous extraction
          }
          extractZip(packFile, extractDir);
          processPackFolder(extractDir);
          FileUtils.deleteDirectory(extractDir); // Clean up after processing
        } catch (IOException e) {
          Constants.LOG.error("Failed to process ZIP pack {}: {}", packName, e.getMessage());
        }
      }
    }
    Constants.LOG.info("Loaded the following material packs for Basic Weapons: {}", initiallyLoadedPacks.stream().map(Object::toString).collect(Collectors.joining(", ")));
    hasInitialized = true;
  }

  private static void processPackFolder(File packFolder) {
    loadMaterialsFromPack(packFolder);
    copyResourcePackContent(packFolder);
    copyDataPackContent(packFolder);
    // Use original zip name if this was extracted from a zip
    String originalName = packFolder.getName() + (packFolder.getName().endsWith(ZIP_EXTENSION) ? "" : ZIP_EXTENSION);
    initiallyLoadedPacks.add(originalName);
  }

  private static void extractZip(File zipFile, File targetDir) throws IOException {
    try (ZipFile zip = new ZipFile(zipFile)) {
      Enumeration<? extends ZipEntry> entries = zip.entries();
      while (entries.hasMoreElements()) {
        ZipEntry entry = entries.nextElement();
        File entryFile = new File(targetDir, entry.getName());

        if (entry.isDirectory()) {
          entryFile.mkdirs();
        } else {
          entryFile.getParentFile().mkdirs();
          try (InputStream in = zip.getInputStream(entry);
               FileOutputStream out = new FileOutputStream(entryFile)) {
            byte[] buffer = new byte[1024];
            int len;
            while ((len = in.read(buffer)) > 0) {
              out.write(buffer, 0, len);
            }
          }
        }
      }
    }
  }

  private static void copyResourcePackContent(File packFolder) {
    File assetsFolder = new File(packFolder, ASSETS_PATH);
    if (!assetsFolder.exists()) return;

    File resourcepacksFolder = new File("resourcepacks", RESOURCEPACK_TARGET);
    if (!resourcepacksFolder.exists()) {
      resourcepacksFolder.mkdirs();
    }

    File targetFolder = new File(resourcepacksFolder, packFolder.getName());
    try {
      // Copy assets folder contents (excluding pack.mcmeta)
      File[] assetContents = assetsFolder.listFiles(file -> !file.getName().equals("pack.mcmeta"));
      if (assetContents != null) {
        for (File file : assetContents) {
          if (file.isDirectory()) {
            FileUtils.copyDirectory(file, new File(targetFolder, ASSETS_PATH + "/" + file.getName()));
          } else {
            FileUtils.copyFile(file, new File(targetFolder, ASSETS_PATH + "/" + file.getName()));
          }
        }
      }

      // Copy pack.png if it exists
      File packIcon = new File(packFolder, "pack.png");
      if (packIcon.exists()) {
        FileUtils.copyFile(packIcon, new File(targetFolder, "pack.png"));
      }

      // Copy assets/pack.mcmeta to root of target
      File sourcePackMcmeta = new File(assetsFolder, "pack.mcmeta");
      if (sourcePackMcmeta.exists()) {
        FileUtils.copyFile(sourcePackMcmeta, new File(targetFolder, "pack.mcmeta"));
      } else {
        Constants.LOG.warn("No pack.mcmeta found in assets folder for {}", packFolder.getName());
      }
    } catch (IOException e) {
      Constants.LOG.error("Failed to copy resourcepack content from {}: {}", packFolder.getName(), e.getMessage());
    }
  }

  private static void copyDataPackContent(File packFolder) {
    File dataFolder = new File(packFolder, DATA_PATH);
    if (!dataFolder.exists()) return;

    File datapacksFolder = new File(DATAPACK_TARGET);
    if (!datapacksFolder.exists()) {
      datapacksFolder.mkdirs();
    }

    File targetFolder = new File(datapacksFolder, packFolder.getName());
    try {
      // Copy data folder contents (excluding pack.mcmeta)
      File[] dataContents = dataFolder.listFiles(file -> !file.getName().equals("pack.mcmeta"));
      if (dataContents != null) {
        for (File file : dataContents) {
          if (file.isDirectory()) {
            FileUtils.copyDirectory(file, new File(targetFolder, DATA_PATH + "/" + file.getName()));
          } else {
            FileUtils.copyFile(file, new File(targetFolder, DATA_PATH + "/" + file.getName()));
          }
        }
      }

      // Copy pack.png if it exists
      File packIcon = new File(packFolder, "pack.png");
      if (packIcon.exists()) {
        FileUtils.copyFile(packIcon, new File(targetFolder, "pack.png"));
      }

      // Copy data/pack.mcmeta to root of target
      File sourcePackMcmeta = new File(dataFolder, "pack.mcmeta");
      if (sourcePackMcmeta.exists()) {
        FileUtils.copyFile(sourcePackMcmeta, new File(targetFolder, "pack.mcmeta"));
      } else {
        Constants.LOG.warn("No pack.mcmeta found in data folder for {}", packFolder.getName());
      }
    } catch (IOException e) {
      Constants.LOG.error("Failed to copy datapack content from {}: {}", packFolder.getName(), e.getMessage());
    }
  }

  public static void loadMaterialsFromPack(File packFolder) {
    File materialFolder = new File(packFolder, CUSTOM_MATERIALS_PATH);
    if (!materialFolder.exists()) {
      Constants.LOG.warn("Pack {} does not contain materials at expected path", packFolder.getName());
      return;
    }

    File[] materialFiles = materialFolder.listFiles((dir, name) -> name.endsWith(".json"));
    if (materialFiles == null || materialFiles.length == 0) {
      Constants.LOG.warn("No material files found in pack {}", packFolder.getName());
      return;
    }

    for (File file : materialFiles) {
      try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
        JsonObject json = GSON.fromJson(reader, JsonObject.class);

        String material_name = json.get("material_name").getAsString();
        int durability = json.get("durability").getAsInt();
        float attack_damage_bonus = json.get("attack_damage_bonus").getAsFloat();
        float attack_speed_bonus = json.get("attack_speed_bonus").getAsFloat();
        float reach_bonus = json.get("reach_bonus").getAsFloat();
        int enchantability = json.get("enchantability").getAsInt();
        String repair_ingredient = json.get("repair_ingredient").getAsString();

        EarlyMaterial material = new EarlyMaterial(material_name, durability, attack_damage_bonus, attack_speed_bonus, reach_bonus, enchantability, repair_ingredient);
        loadedMaterials.put(material_name, material.createTier());
        materialToDatapackName.put(material_name, packFolder.getName());
        Constants.LOG.info("'{}' material found. smithing new weapons..", material_name);
        //        Constants.LOG.info("Loaded material '{}' from '{}' with stats: [durability '{}'], [attack damage bonus '{}'], [attack speed bonus '{}'], [enchantability '{}'], [repair ingredient '{}']", material_name, packFolder.getName(), durability,attack_damage_bonus, attack_speed_bonus, enchantability,repair_ingredient);

        WeaponRegistry.registerWeaponsForMaterial(material_name);
      } catch (Exception e) {
        Constants.LOG.error("Failed to load material file {} from pack {}: {}", file.getName(), packFolder.getName(), e.getMessage());
      }
    }
  }

  public static Tier getMaterial(String name) {
    return loadedMaterials.get(name);
  }

  public static Collection<String> getMaterialNames() {
    return loadedMaterials.keySet();
  }

  public static Collection<String> getDatapackNames() {
    return materialToDatapackName.values();
  }

  public static boolean wasPackLoadedInitially(String packName) {
    return initiallyLoadedPacks.contains(packName);
  }

  private static void cleanTargetFolders() {
    // Clean resourcepacks/materialpacks to make sure data is always fresh
    File resourcepacksFolder = new File("resourcepacks", RESOURCEPACK_TARGET);
    if (resourcepacksFolder.exists()) {
      try {
        FileUtils.deleteDirectory(resourcepacksFolder);
      } catch (IOException e) {
        Constants.LOG.error("Failed to clean resource pack target folder: {}", e.getMessage());
      }
    }

    // Clean config/basicweapons/bwmp_data to make sure data is always fresh
    File datapacksFolder = new File(DATAPACK_TARGET);
    if (datapacksFolder.exists()) {
      try {
        FileUtils.deleteDirectory(datapacksFolder);
      } catch (IOException e) {
        Constants.LOG.error("Failed to clean data pack target folder: {}", e.getMessage());
      }
    }
  }
}