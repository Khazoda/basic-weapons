package com.khazoda.basicweapons.materialpack;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.khazoda.basicweapons.Constants;
import com.khazoda.basicweapons.registry.WeaponRegistry;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.storage.LevelResource;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.nio.file.Files;
import java.util.*;

import static com.khazoda.basicweapons.BasicWeaponsCommon.*;

public class MaterialPackLoader {
  private static final String MATERIALS_FOLDER = "basicweapons_materialpacks";
  private static final String DATA_PATH = "data";
  private static final String ASSETS_PATH = "assets";
  private static final String CUSTOM_MATERIALS_PATH = "custom_materials";
  private static final Gson GSON = new GsonBuilder().create();
  private static final Map<String, Tier> loadedMaterials = new HashMap<>();
  private static final Map<String, String> materialToDatapackName = new HashMap<>();
  private static final Set<String> initiallyLoadedPacks = new HashSet<>();
  private static boolean hasInitialized = false;

  public static void loadPacks() {
    if (hasInitialized) {
      Constants.LOG.warn("Attempted to load material packs after initialization - skipping");
      return;
    }

    File materialsFolder = new File(MATERIALS_FOLDER);
    if (!materialsFolder.exists()) {
      if (materialsFolder.mkdir()) {
        Constants.LOG.info("Created materials folder at {}", materialsFolder.getAbsolutePath());
      } else {
        Constants.LOG.error("Failed to create materials folder");
        return;
      }
    }

    File[] packFolders = materialsFolder.listFiles(File::isDirectory);
    if (packFolders == null || packFolders.length == 0) {
      Constants.LOG.info("No material packs found in {}", materialsFolder.getAbsolutePath());
      return;
    }

    for (File packFolder : packFolders) {
      loadMaterialsFromPack(packFolder);
      handleDataContent(packFolder);
      copyResourcePackContent(packFolder);
      initiallyLoadedPacks.add(packFolder.getName());
    }

    hasInitialized = true;
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
        Constants.LOG.info("Loaded material from pack {}: {} with stats: uses={}, speed={}, damage={}", packFolder.getName(), material_name, durability, attack_speed_bonus, attack_damage_bonus);

        WeaponRegistry.registerWeaponsForMaterial(material_name);

        // Store the mapping between material name and datapack folder name
        materialToDatapackName.put(material_name, packFolder.getName());
      } catch (Exception e) {
        Constants.LOG.error("Failed to load material file {} from pack {}: {}", file.getName(), packFolder.getName(), e.getMessage());
      }
    }
  }

  private static void handleDataContent(File packFolder) {
    File dataFolder = new File(packFolder, DATA_PATH);
    if (!dataFolder.exists()) return;

    // If global datapack mods are installed, copy datapacks to respective folders on game launch
    // Vanilla behaviour is handles in MinecraftServerMixin
    if (hasGlobalDatapackLoader) {
      if (openloader_mod_loaded) {
        File openLoaderFolder = new File("config/openloader/packs");
        copyToDatapackFolder(packFolder, dataFolder, openLoaderFolder);
      }
      if (globalpacks_mod_loaded) {
        File globalDatapacksFolder = new File("global_packs/required_data");
        copyToDatapackFolder(packFolder, dataFolder, globalDatapacksFolder);
      }
    }
  }

  private static void copyToDatapackFolder(File packFolder, File sourceDataFolder, File targetParentFolder) {
    if (!targetParentFolder.exists()) {
      targetParentFolder.mkdirs();
    }

    File targetFolder = new File(targetParentFolder, packFolder.getName());
    try {
      FileUtils.copyDirectory(sourceDataFolder, new File(targetFolder, DATA_PATH));

      // Create or update pack.mcmeta
      File packMcmeta = new File(targetFolder, "pack.mcmeta");
      if (!packMcmeta.exists()) {
        JsonObject packMeta = new JsonObject();
        JsonObject pack = new JsonObject();
        pack.addProperty("pack_format", 15);
        pack.addProperty("description", "Data for " + packFolder.getName());
        packMeta.add("pack", pack);

        try (FileWriter writer = new FileWriter(packMcmeta)) {
          GSON.toJson(packMeta, writer);
        }
      }

      Constants.LOG.info("Copied datapack content from {} to {}", packFolder.getName(), targetParentFolder.getName());
    } catch (IOException e) {
      Constants.LOG.error("Failed to copy datapack content from {} to {}: {}", packFolder.getName(), targetParentFolder.getName(), e.getMessage());
    }
  }

  private static void copyResourcePackContent(File packFolder) {
    File assetsFolder = new File(packFolder, ASSETS_PATH);
    if (!assetsFolder.exists()) return;

    File resourcepacksFolder = new File("resourcepacks");
    if (!resourcepacksFolder.exists()) {
      resourcepacksFolder.mkdirs();
    }

    File targetFolder = new File(resourcepacksFolder, packFolder.getName());
    try {
      // Copy assets folder
      FileUtils.copyDirectory(assetsFolder, new File(targetFolder, ASSETS_PATH));

      // Copy pack.png if it exists
      File packIcon = new File(packFolder, "pack.png");
      if (packIcon.exists()) {
        FileUtils.copyFile(packIcon, new File(targetFolder, "pack.png"));
      }

      // Copy or create pack.mcmeta
      File sourcePackMcmeta = new File(packFolder, "pack.mcmeta");
      File targetPackMcmeta = new File(targetFolder, "pack.mcmeta");

      if (sourcePackMcmeta.exists()) {
        FileUtils.copyFile(sourcePackMcmeta, targetPackMcmeta);
      } else if (!targetPackMcmeta.exists()) {
        // Create default pack.mcmeta if none exists
        JsonObject packMeta = new JsonObject();
        JsonObject pack = new JsonObject();
        pack.addProperty("pack_format", 34);
        pack.addProperty("description", "Resources for " + packFolder.getName());
        packMeta.add("pack", pack);

        try (FileWriter writer = new FileWriter(targetPackMcmeta)) {
          GSON.toJson(packMeta, writer);
        }
      }

      // Enable the resource pack in options.txt
      enableResourcePack(packFolder.getName());

      Constants.LOG.info("Copied and enabled resourcepack content from {}", packFolder.getName());
    } catch (IOException e) {
      Constants.LOG.error("Failed to copy resourcepack content from {}: {}", packFolder.getName(), e.getMessage());
    }
  }

  private static void enableResourcePack(String packName) {
    File optionsFile = new File("options.txt");
    List<String> lines = new ArrayList<>();
    boolean foundResourcePacks = false;

    try {
      if (optionsFile.exists()) {
        lines = Files.readAllLines(optionsFile.toPath());
        for (int i = 0; i < lines.size(); i++) {
          String line = lines.get(i);
          if (line.startsWith("resourcePacks:")) {
            foundResourcePacks = true;
            JsonArray packs = GSON.fromJson(line.substring(line.indexOf('[')), JsonArray.class);
            String packPath = "file/" + packName;
            if (!packs.toString().contains(packPath)) {
              packs.add(packPath);
              lines.set(i, "resourcePacks:" + GSON.toJson(packs));
            }
            break;
          }
        }
      }

      if (!foundResourcePacks) {
        JsonArray packs = new JsonArray();
        packs.add("file/" + packName);
        lines.add("resourcePacks:" + GSON.toJson(packs));
      }

      Files.write(optionsFile.toPath(), lines);
    } catch (IOException e) {
      Constants.LOG.error("Failed to enable resourcepack {}: {}", packName, e.getMessage());
    }
  }

  public static Tier getMaterial(String name) {
    return loadedMaterials.get(name);
  }

  public static Collection<String> getMaterialNames() {
    return loadedMaterials.keySet();
  }

  public static void handleWorldLoad(MinecraftServer server) {
    // Only process datapacks that were loaded during initialization
    if (hasGlobalDatapackLoader) {
      for (String datapackName : materialToDatapackName.values()) {
        // Skip if this pack wasn't loaded during initial boot
        if (!wasPackLoadedInitially(datapackName)) {
          Constants.LOG.info("Skipping world load processing for pack {} as it wasn't loaded during initialization. Restart your game to load it.", datapackName);
          continue;
        }

        // Process world load actions for this pack
        File worldDatapack = new File(server.getWorldPath(LevelResource.DATAPACK_DIR).toFile(), datapackName);
        if (worldDatapack.exists()) {
          try {
            // Disable the datapack first
            server.getCommands().performPrefixedCommand(
                server.createCommandSourceStack(),
                "datapack disable \"file/" + datapackName + "\""
            );

            // Then delete it
            FileUtils.deleteDirectory(worldDatapack);
            Constants.LOG.info("Cleaned up world-specific datapack {} as global datapack loader is present", datapackName);
          } catch (IOException e) {
            Constants.LOG.error("Failed to clean up world-specific datapack {}: {}", datapackName, e.getMessage());
          }
        }
      }
    } else {
      // Rescan datapacks before enabling
      server.getCommands().performPrefixedCommand(
          server.createCommandSourceStack(),
          "datapack list"
      );

      // Enable each material pack
      for (String datapackName : MaterialPackLoader.getDatapackNames()) {
        server.getCommands().performPrefixedCommand(
            server.createCommandSourceStack(),
            "datapack enable \"file/" + datapackName + "\""
        );
        Constants.LOG.info("Enabled material pack: {}", datapackName);
      }
    }
  }

  public static Collection<String> getDatapackNames() {
    return materialToDatapackName.values();
  }

  public static boolean wasPackLoadedInitially(String packName) {
    return initiallyLoadedPacks.contains(packName);
  }
}