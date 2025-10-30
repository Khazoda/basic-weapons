package com.khazoda.basicweapons.materialpack;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.khazoda.basicweapons.Constants;
import com.khazoda.basicweapons.platform.Services;
import com.khazoda.basicweapons.registry.WeaponRegistry;
import net.minecraft.world.item.ToolMaterial;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import static com.khazoda.basicweapons.materialpack.MaterialPackConstants.*;

/**
 * This class handles detecting a materialpack in basicweapons_materialpacks and sending the files to the
 * right places. It generates resource and datapacks from the assets/ and data/ folders, which it sends to
 * config/basicweapons/bwmp_resources and config/basicweapons/bwmp_data,
 * and reads the material stats from custom_materials/ in loadMaterialsFromPack() which it stores for
 * the WeaponRegistry to use during registration.
 */

public class MaterialPackLoader {
  private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
  private static final Map<String, EarlyLoadedMaterial> loadedMaterials = new HashMap<>();
  private static final Map<ToolMaterial, EarlyLoadedMaterial> toolMaterialMap = new HashMap<>();
  private static final Map<String, String> materialToDatapackName = new HashMap<>();
  private static final Set<String> initiallyLoadedPacks = new HashSet<>();
  private static boolean hasInitialized = false;

  public static void loadPacks() {
    if (hasInitialized) {
      Constants.LOG.warn("Attempted to load material packs after initialization - skipping");
      return;
    }

    File materialPacksFolder = new File(MATERIALPACK_SOURCE);
    File[] packFiles = materialPacksFolder.listFiles(file -> file.isDirectory() || file.getName().endsWith(".zip"));

    if (!materialPacksFolder.exists()) {
      if (materialPacksFolder.mkdir()) {
        Constants.LOG.info("Created material packs folder {}", materialPacksFolder.getName());
      } else {
        Constants.LOG.error("Failed to create basicweapons_materials folder. This should never happen.");
        return;
      }
    } else if(packFiles != null && packFiles.length != 0) {
      cleanTargetFolders(); // On every load the target resource & data folders are cleaned to handle users removing materialpacks
    }

    if (packFiles == null || packFiles.length == 0) {
      Constants.LOG.info("No material packs found in {}", materialPacksFolder.getName());
      return;
    }

    for (File packFile : packFiles) {
      String packName = packFile.getName();
      if (packFile.isDirectory()) {
        processPackFolder(packFile);
      } else {
        // For ZIP files, extract to a temp directory with the same name before processing
        File extractDir = new File(materialPacksFolder, packName.substring(0, packName.length() - 4));
        try {
          if (extractDir.exists()) {
            FileUtils.deleteDirectory(extractDir); // Clean up any previous extraction just in case
          }
          extractZip(packFile, extractDir);
          processPackFolder(extractDir);
          FileUtils.deleteDirectory(extractDir); // Clean up the temporary directory
        } catch (IOException e) {
          Constants.LOG.error("Failed to process ZIP pack {}: {}.", packName, e.getMessage());
        }
      }
    }
    Constants.LOG.info("Loaded the following material packs: [{}]", initiallyLoadedPacks.stream().map(Object::toString).collect(Collectors.joining(", ")));
    hasInitialized = true;
  }

  private static void processPackFolder(File packFolder) {
    if (!loadMaterialsFromPack(packFolder)) return;
    copyResourcePackContent(packFolder);
    copyDataPackContent(packFolder);
    initiallyLoadedPacks.add(packFolder.getName());
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
          try (InputStream in = zip.getInputStream(entry); FileOutputStream out = new FileOutputStream(entryFile)) {
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
    /* Internal /assets folder inside material pack */
    File assetsFolder = new File(packFolder, ASSETS_PATH);
    if (!assetsFolder.exists()) return;

    /* Destination for resourcepack generated from /assets */
    File resourcepacksFolder = new File(RESOURCEPACK_TARGET);
    if (!resourcepacksFolder.exists()) {
      resourcepacksFolder.mkdirs();
    }

    File targetFolder = new File(resourcepacksFolder, packFolder.getName());
    try {
      // Copy /assets folder contents (excluding pack.mcmeta, that's handled separately)
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
    /* Internal /data folder inside material pack */
    File dataFolder = new File(packFolder, DATA_PATH);
    if (!dataFolder.exists()) return;

    /* Destination for datapack generated from /data */
    File datapacksFolder = new File(DATAPACK_TARGET);
    if (!datapacksFolder.exists()) {
      datapacksFolder.mkdirs();
    }

    File targetFolder = new File(datapacksFolder, packFolder.getName());
    try {
      // Copy /data folder contents (excluding pack.mcmeta, that's handled separately)
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


  /* Returns false if materialpack shouldn't be loaded (loading_requirements.json).
   * This will skip resource and datapack injection for that materialpack */
  private static boolean loadMaterialsFromPack(File packFolder) {
    // Check materialpack loading requirements first
    File requirementsFile = new File(packFolder, "loading_requirements.json");
    if (requirementsFile.exists()) {
      try (BufferedReader reader = new BufferedReader(new FileReader(requirementsFile))) {
        JsonObject json = GSON.fromJson(reader, JsonObject.class);
        if (json.has("requires_mod")) {
          String requiredMod = json.get("requires_mod").getAsString();
          if (!requiredMod.isEmpty() && !Services.PLATFORM.isModLoaded(requiredMod)) {
            Constants.LOG.info("Skipping material pack {} - required mod {} is not loaded",
                packFolder.getName(), requiredMod);
            return false;
          }
        }
      } catch (Exception e) {
        Constants.LOG.error("Failed to read loading requirements for pack {}: {}. It won't be enabled.",
            packFolder.getName(), e.getMessage());
        return false;
      }
    }

    // Check if any materials exist (they should)
    File materialFolder = new File(packFolder, CUSTOM_MATERIALS_PATH);
    if (!materialFolder.exists()) {
      Constants.LOG.warn("Pack {} does not contain materials at expected path", packFolder.getName());
      return false;
    }
    File[] materialFiles = materialFolder.listFiles((dir, name) -> name.endsWith(".json"));
    if (materialFiles == null || materialFiles.length == 0) {
      Constants.LOG.warn("No material files found in pack {}", packFolder.getName());
      return false;
    }

    // Process each material from this materialpack individually
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

        EarlyLoadedMaterial material = new EarlyLoadedMaterial(material_name, durability, attack_damage_bonus, attack_speed_bonus, reach_bonus, enchantability, repair_ingredient);
        ToolMaterial toolMaterial = material.createToolMaterial();
        toolMaterialMap.put(toolMaterial, material);
        loadedMaterials.put(material_name, material);
        materialToDatapackName.put(material_name, packFolder.getName());
        Constants.LOG.info("[{}] material loaded.", material_name);
        // Constants.LOG.info("Loaded material '{}' from '{}' with stats: [durability '{}'], [attack damage bonus '{}'], [attack speed bonus '{}'], [enchantability '{}'], [repair ingredient '{}']", material_name, packFolder.getName(), durability,attack_damage_bonus, attack_speed_bonus, enchantability,repair_ingredient);

        WeaponRegistry.registerAllWeaponsForMaterial(material_name);
      } catch (Exception e) {
        Constants.LOG.error("Failed to load material file {} from pack {}: {}", file.getName(), packFolder.getName(), e.getMessage());
      }
    }
    return true;
  }

  public static ToolMaterial getMaterial(String name) {
    EarlyLoadedMaterial material = loadedMaterials.get(name);
    return material != null ? material.createToolMaterial() : null;
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

  public static float getAttackSpeedBonus(ToolMaterial toolMaterial) {
    EarlyLoadedMaterial material = toolMaterialMap.get(toolMaterial);
    return material != null ? material.getAttackSpeedBonus() : 0f;
  }

  public static float getReachBonus(ToolMaterial toolMaterial) {
    EarlyLoadedMaterial material = toolMaterialMap.get(toolMaterial);
    return material != null ? material.getReachBonus() : 0f;
  }

  private static void cleanTargetFolders() {
    // Clean config/basicweapons/bwmp_resources and config/basicweapons/bwmp_data to make sure materialpacks are always fresh
    if (!ableToDeleteDirectory(new File(RESOURCEPACK_TARGET)))
      Constants.LOG.error("Failed to clean bwmp_resources target folder. This is probably fine but if you experience issues please report this on the Basic Weapons issue tracker.");
    if (!ableToDeleteDirectory(new File(DATAPACK_TARGET)))
      Constants.LOG.error("Failed to clean bwmp_data target folder. This is probably fine but if you experience issues please report this on the Basic Weapons issue tracker.");
  }

  private static boolean ableToDeleteDirectory(File dir) {
    if (dir.exists()) {
      try {
        FileUtils.deleteDirectory(dir);
        return true;
      } catch (IOException e) {
        return false;
      }
    }
    return false;
  }
}