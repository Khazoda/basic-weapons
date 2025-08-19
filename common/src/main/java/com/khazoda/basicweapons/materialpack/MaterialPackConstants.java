package com.khazoda.basicweapons.materialpack;

public class MaterialPackConstants {
  /* Paths with .minecraft (or equivalent) as the root directory */
  public static final String RESOURCEPACK_TARGET;
  public static final String DATAPACK_TARGET;
  public static final String MATERIALPACK_SOURCE;

  /* Paths with a materialpack as the root directory */
  public static final String ASSETS_PATH;
  public static final String DATA_PATH;
  public static final String CUSTOM_MATERIALS_PATH;

  static {
    RESOURCEPACK_TARGET = "config/basicweapons/bwmp_resources";
    DATAPACK_TARGET = "config/basicweapons/bwmp_data";
    MATERIALPACK_SOURCE = "basicweapons_materialpacks";

    ASSETS_PATH = "assets";
    DATA_PATH = "data";
    CUSTOM_MATERIALS_PATH = "custom_materials";
  }
}
