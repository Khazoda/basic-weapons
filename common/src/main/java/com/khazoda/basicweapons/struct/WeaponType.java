package com.khazoda.basicweapons.struct;


import com.khazoda.basicweapons.item.*;
import com.khazoda.basicweapons.materialpack.EarlyMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;

public enum WeaponType {
  DAGGER("dagger", 1f, -1.6f, 0, DaggerItem::new),
  HAMMER("hammer", 7f, -3.4f, 0, HammerItem::new),
  CLUB("club", 5f, -3.0f, 0, ClubItem::new),
  SPEAR("spear", 2f, -2.8f, 2, SpearItem::new),
  QUARTERSTAFF("quarterstaff", 1f, -2.3f, 1.25, QuarterstaffItem::new),
  GLAIVE("glaive", 5f, -3.2f, 1.25, GlaiveItem::new);

  private final String id;
  private final float baseDamage;
  private final float baseSpeed;
  private final double baseReach;
  private final WeaponFactory factory;

  WeaponType(String id, float baseDamage, float baseSpeed, double baseReach, WeaponFactory factory) {
    this.id = id;
    this.baseDamage = baseDamage;
    this.baseSpeed = baseSpeed;
    this.baseReach = baseReach;
    this.factory = factory;
  }

  public String getId() {
    return id;
  }

  public float getBaseDamage() {
    return baseDamage;
  }

  public float getBaseSpeed() {
    return baseSpeed;
  }

  public double getBaseReach() {
    return baseReach;
  }

  public Item create(Tier material, float damageModifier, float speedModifier, float reachModifier, Item.Properties properties) {
    double totalReach = baseReach + reachModifier;
    if (material instanceof EarlyMaterial.TierWithReach) {
      totalReach += ((EarlyMaterial.TierWithReach) material).getReachBonus();
    }

    return factory.create(
        material,
        baseDamage + damageModifier,
        baseSpeed + speedModifier,
        totalReach,
        properties
    );
  }

  /**
   * Gets any special damage modifications for specific material/type combinations (e.g. Hammer)
   */
  public static float getDamageModifier(WeaponType type, Tier material) {
    if (type == WeaponType.DAGGER && material == Tiers.GOLD) return -1;
    if (type == WeaponType.HAMMER) {
      if (material == Tiers.WOOD) return -6;
      if (material == Tiers.STONE) return -3;
      if (material == Tiers.GOLD) return -6;
      return -1; // All other materials (including material pack ones) use the same modifier
    }
    return 0;
  }

  /**
   * Gets any special speed modifications for specific material/type combinations (e.g. Hammer)
   */
  public static float getSpeedModifier(WeaponType type, Tier material) {
    if (type == WeaponType.DAGGER && material == Tiers.GOLD) return 1;
    if (type == WeaponType.HAMMER) {
      if (material == Tiers.WOOD) return 0.4f;
      if (material == Tiers.STONE) return 0.2f;
      if (material == Tiers.GOLD) return 0.6f;
      if (material == Tiers.NETHERITE) return 0.2f;
      // All other materials (including custom ones) use the same modifier
      return 0.1f;
    }
    return 0;
  }

  /**
   * Gets any special reach modifications for specific material/type combinations
   */
  public static float getReachModifier(WeaponType type, Tier material) {
    // No base materials implement implicit reach modifiers yet
    return 0;
  }

  @FunctionalInterface
  public interface WeaponFactory {
    Item create(Tier material, float damage, float speed, double reach, Item.Properties properties);
  }
} 