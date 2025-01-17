package com.khazoda.basicweapons.materialpack;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.khazoda.basicweapons.Constants;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

/**
 * Loads material names during mod initialization, before the world loads.
 * This ensures material information is available for item registration
 * and other early-game systems.
 */
public class PreInitMaterialNameLoader {
  private static final Set<String> MATERIAL_NAMES = new HashSet<>();
  private static final Gson GSON = new Gson();
  private static boolean hasLoaded = false;

  public static void loadMaterialNames() {
    if (hasLoaded) return;

    try {
      // Get all files in custom_material directory
      var resources = PreInitMaterialNameLoader.class.getClassLoader()
          .getResources("data/basicweapons/custom_material");

      while (resources.hasMoreElements()) {
        var url = resources.nextElement();
        try (var is = url.openStream()) {
          var reader = new BufferedReader(new InputStreamReader(is));
          String fileName;
          while ((fileName = reader.readLine()) != null) {
            if (fileName.endsWith(".json")) {
              loadMaterialFile(fileName);
            }
          }
        }
      }

      hasLoaded = true;
      Constants.LOG.info("Pre-init loaded {} material names", MATERIAL_NAMES.size());
    } catch (Exception e) {
      Constants.LOG.error("Failed to load material names during pre-init", e);
    }
  }

  private static void loadMaterialFile(String fileName) {
    try (InputStream is = PreInitMaterialNameLoader.class.getResourceAsStream(
        "/data/basicweapons/custom_material/" + fileName)) {
      if (is != null) {
        JsonObject json = GSON.fromJson(new InputStreamReader(is), JsonObject.class);
        String materialName = json.get("name").getAsString();
        MATERIAL_NAMES.add(materialName);
      }
    } catch (Exception e) {
      Constants.LOG.error("Failed to read material file: {}", fileName, e);
    }
  }
}