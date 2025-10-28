package com.khazoda.basicweapons.registry.helper;

import com.khazoda.basicweapons.Constants;
import it.unimi.dsi.fastutil.objects.Object2ObjectLinkedOpenHashMap;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;

import java.util.Map;
import java.util.Optional;

/**
 * Central registry manager for the Plushables mod.
 * Manages and coordinates multiple {@link Reggie} instances for different
 * registry types.
 * <p>
 * Reginald acts as the top-level registry coordinator, managing individual
 * {@link Reggie}
 * instances for each Minecraft registry type (Items, Blocks, etc.).
 * <br/>
 * Reginald is instantiated in {@link com.khazoda.basicweapons.BasicWeaponsCommon} and that instance is called in all subsequent calls to Reggies
 * <br/>
 * This class
 * is used by
 * {@link com.khazoda.basicweapons.BasicWeaponsCommon} to register all mod content
 *
 * @see Reggie
 * @see com.khazoda.basicweapons.BasicWeaponsCommon
 */
public class Reginald {
  /** Map of registry keys to their corresponding Reggie instances */
  private final Map<ResourceKey<? extends Registry<?>>, Reggie<?>> registrars = new Object2ObjectLinkedOpenHashMap<>();

  /**
   * Gets or creates a Reggie instance for the specified registry type.
   *
   * @param <T> The type of content being registered
   * @param key The registry key to get a Reggie for
   * @return A Reggie instance for the specified registry type
   */
  @SuppressWarnings("unchecked")
  public <T> Reggie<T> get(ResourceKey<? extends Registry<? super T>> key) {
    return (Reggie<T>) registrars.computeIfAbsent(key, Reggie::new);
  }

  /**
   * Registers content to a specific Minecraft registry.
   * Used by mod loaders to register content during initialization.
   *
   * @param registry The Minecraft registry to register content to
   */
  @SuppressWarnings("unchecked")
  public void register(Registry<?> registry) {
    Reggie<?> registrar = registrars.get(registry.key());
    if (registrar == null)
      return;

    registrar.registerAll((Registry<? super Object>) registry);
  }

  /**
   * Registers all content to their respective Minecraft registries.
   * Called during mod initialization to register all mod content at once.
   * <p>
   * Used by the Fabric entrypoint to register
   * content.
   *
   */
  @SuppressWarnings("unchecked")
  public void registerAll() {
    for (var entry : registrars.entrySet()) {
      Optional<Holder.Reference<Registry<Object>>> ref = ((HolderGetter<Registry<Object>>) BuiltInRegistries.REGISTRY)
          .get((ResourceKey<Registry<Object>>) entry.getKey());
      if (ref.isEmpty()) {
        Constants.LOG.error("No registry found with the key {}",
            entry.getKey());
        continue;
      }
      entry.getValue().registerAll(ref.get().value());
    }
  }
}