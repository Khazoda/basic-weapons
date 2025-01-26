package com.khazoda.basicweapons.platform.services;

import net.minecraft.core.RegistryAccess;
import org.apache.commons.lang3.NotImplementedException;

import java.io.File;

public interface IPlatformHelper {

    /**
     * Gets the name of the current platform
     *
     * @return The name of the current platform.
     */
    String getPlatformName();

    /**
     * Checks if a mod with the given id is loaded.
     *
     * @param modId The mod to check if it is loaded.
     * @return True if the mod is loaded, false otherwise.
     */
    boolean isModLoaded(String modId);

    /**
     * Check if the game is currently in a development environment.
     *
     * @return True if in a development environment, false otherwise.
     */
    boolean isDevelopmentEnvironment();

    /**
     * Gets the name of the environment type as a string.
     *
     * @return The name of the environment type.
     */
    default String getEnvironmentName() {

        return isDevelopmentEnvironment() ? "development" : "production";
    }

    default RegistryAccess getCurrentRegistryAccess() {
        throw new NotImplementedException("getCurrentRegistryAccess is not implemented!");
    }

  /**
   * Gets the datapacks directory for the current/loading world
   *
   * @return File pointing to the datapacks directory, or null if not available
   */
  File getWorldDatapacksDirectory();

}