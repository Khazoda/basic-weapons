package com.khazoda.basicweapons.registry.helper;

import it.unimi.dsi.fastutil.objects.Object2ObjectLinkedOpenHashMap;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.function.Supplier;

import static com.khazoda.basicweapons.Constants.ID;

/**
 * Registry handler for Minecraft registry types. Reggie is Reginald's little
 * brother.
 * <p>
 * Provides a loader-agnostic way to register content to Minecraft's
 * registries.
 * Works in conjunction with {@link Reginald} to handle mod registration across
 * different mod loaders.
 * Uses lazy initialization through {@link Supplier}s to ensure proper loading
 * order.
 *
 * @param <T> The type being registered (e.g., Item, Block, etc.)
 * @see Reginald
 */
public class Reggie<T> {
  /** The registry key that this Reggie instance handles */
  private final ResourceKey<? extends Registry<? extends T>> key;
  /** Map of registry entries, storing their identifiers and suppliers */
  private final Map<ResourceLocation, Supplier<? extends T>> registryEntries = new Object2ObjectLinkedOpenHashMap<>();

  /**
   * Creates a new Reggie instance for the specified registry type.
   *
   * @param key The {@link ResourceKey} for the registry this instance will handle
   */
  public Reggie(ResourceKey<? extends Registry<? extends T>> key) {
    this.key = key;
  }

  /**
   * Registers a new entry to this registry.
   * Uses lazy initialization through a memoized supplier. 'Registrant' here is used to refer to the thing being registered i.e. a specific Block, Item etc.
   * <br/>
   * This method is called by each static reference in {@link com.khazoda.basicweapons.BasicWeaponsCommon} et al.
   *
   * @param <T2>     The specific type being registered
   * @param path     The registration path for the registrant
   * @param supplier The supplier that provides the registrant instance
   * @return A memoized supplier for the registrant
   * @throws IllegalArgumentException if the path is already registered
   */
  public <T2 extends T> Supplier<T2> register(String path, Supplier<T2> supplier) {
    ResourceLocation name = ID(path);
    if (registryEntries.containsKey(name))
      throw new IllegalArgumentException("<! Can't register " + name + " twice !>");

    Supplier<T2> memoized = new Supplier<>() {
      @Nullable
      private T2 cacheVal;

      @Override
      public T2 get() {
        T2 val = this.cacheVal;
        if (val == null) {
          this.cacheVal = val = supplier.get();
        }
        return val;
      }
    };
    registryEntries.put(name, memoized);
    return memoized;
  }

  /**
   * Registers all entries to the provided registry if it matches this Reggie's
   * key.
   * This method is called during mod initialization by {@link Reginald} to populate the actual
   * Minecraft registries.
   *
   * @param registry The target Minecraft registry to populate
   */
  public void registerAll(Registry<? super T> registry) {
    if (key != registry.key())
      return;
    for (var entry : registryEntries.entrySet()) {
      Registry.register(registry, entry.getKey(), entry.getValue().get());
    }
  }
}