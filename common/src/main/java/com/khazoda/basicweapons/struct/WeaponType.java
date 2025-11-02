package com.khazoda.basicweapons.struct;


import com.khazoda.basicweapons.item.*;
import com.khazoda.basicweapons.materialpack.MaterialPackLoader;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;


public final class WeaponType {

  public interface WeaponTypeInterface {
    String getId();

    Item create(ToolMaterial material, float damageModifier, float speedModifier, float reachModifier, Item.Properties properties);
  }

  public enum BasicWeaponType implements WeaponTypeInterface {
    DAGGER("dagger", 1f, -1.6f, 0, DaggerItem::new),
    HAMMER("hammer", 7f, -3.4f, 0, HammerItem::new),
    CLUB("club", 5f, -3.0f, 0, ClubItem::new),
    SPEAR("spear", 2f, -2.8f, 2, SpearItem::new),
    QUARTERSTAFF("quarterstaff", 1f, -2.3f, 1, QuarterstaffItem::new),
    GLAIVE("glaive", 5f, -3.2f, 1, GlaiveItem::new);

    private final String id;
    private final float baseDamage;
    private final float baseSpeed;
    private final double baseReach;
    private final WeaponFactory factory;

    BasicWeaponType(String id, float baseDamage, float baseSpeed, double baseReach, WeaponFactory factory) {
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

    public Item create(ToolMaterial material, float damageModifier, float speedModifier, float reachModifier, Item.Properties properties) {
      // Add material's attack speed bonus to combat attack speed
      float extraSpeed = speedModifier + MaterialPackLoader.getAttackSpeedBonus(material);
      double extraReach = reachModifier + MaterialPackLoader.getReachBonus(material);

      float finalDamage = baseDamage + damageModifier;
      float finalSpeed = baseSpeed + extraSpeed;
      double finalReach = baseReach + extraReach;

      return factory.create(
          material,
          finalDamage,
          finalSpeed,
          finalReach,
          properties
      );
    }

    /**
     * Gets any special damage modifications for specific material/type combinations (e.g. Hammer)
     */
    public static float getDamageModifier(BasicWeaponType type, ToolMaterial material) {
      if (type == BasicWeaponType.DAGGER && material == ToolMaterial.GOLD) return -1;
      if (type == BasicWeaponType.HAMMER) {
        if (material == ToolMaterial.WOOD) return -6;
        if (material == ToolMaterial.STONE) return -3;
        if (material == ToolMaterial.GOLD) return -6;
        return -1; // All other materials (including material pack ones) use the same modifier
      }
      return 0;
    }

    /**
     * Gets any special speed modifications for specific material/type combinations (e.g. Hammer)
     */
    public static float getSpeedModifier(BasicWeaponType type, ToolMaterial material) {
      if (type == BasicWeaponType.DAGGER && material == ToolMaterial.GOLD) return 1;
      if (type == BasicWeaponType.HAMMER) {
        if (material == ToolMaterial.WOOD) return 0.4f;
        if (material == ToolMaterial.STONE) return 0.2f;
        if (material == ToolMaterial.GOLD) return 0.6f;
        if (material == ToolMaterial.NETHERITE) return 0.2f;
        // All other materials (including custom ones) use the same modifier
        return 0.1f;
      }
      return 0;
    }

    /**
     * Gets any special reach modifications for specific material/type combinations
     */
    public static float getReachModifier(BasicWeaponType type, ToolMaterial material) {
      // No base materials implement implicit reach modifiers yet. This is future proofing (tm)
      return 0;
    }

    @FunctionalInterface
    public interface WeaponFactory {
      Item create(ToolMaterial material, float attackDamage, float attackSpeed, double reach, Item.Properties properties);
    }
  }

  public enum VanillaWeaponType implements WeaponTypeInterface {
    SWORD("sword", 1f, -1.6f, 0, CustomSwordItem::new),
    AXE("axe", 7f, -3.4f, 0, CustomAxeItem::new);

    private final String id;
    private final float baseDamage;
    private final float baseSpeed;
    private final double baseReach;
    private final WeaponFactory factory;

    VanillaWeaponType(String id, float baseDamage, float baseSpeed, double baseReach, WeaponFactory factory) {
      this.id = id;
      this.baseDamage = baseDamage;
      this.baseSpeed = baseSpeed;
      this.baseReach = baseReach;
      this.factory = factory;
    }

    public String getId() {
      return id;
    }

    public Item create(ToolMaterial material, float damageModifier, float speedModifier, float reachModifier, Item.Properties properties) {
      // Add material's attack speed bonus to combat attack speed
      float extraSpeed = speedModifier + MaterialPackLoader.getAttackSpeedBonus(material);
      double extraReach = reachModifier + MaterialPackLoader.getReachBonus(material);

      float finalDamage = baseDamage + damageModifier;
      float finalSpeed = baseSpeed + extraSpeed;
      double finalReach = baseReach + extraReach;

      return factory.create(
          material,
          finalDamage,
          finalSpeed,
          finalReach,
          properties
      );
    }

    @FunctionalInterface
    public interface WeaponFactory {
      Item create(ToolMaterial material, float attackDamage, float attackSpeed, double reach, Item.Properties properties);
    }
  }

  /**
   * Unified modifier methods that work with both BasicWeaponType and VanillaWeaponType
   */
  public static float getDamageModifier(WeaponTypeInterface type, ToolMaterial material) {
    if (type instanceof BasicWeaponType basicType) {
      return BasicWeaponType.getDamageModifier(basicType, material);
    }
    // VanillaWeaponType (swords/axes) don't have special material-based modifiers
    return 0;
  }

  public static float getSpeedModifier(WeaponTypeInterface type, ToolMaterial material) {
    if (type instanceof BasicWeaponType basicType) {
      return BasicWeaponType.getSpeedModifier(basicType, material);
    }
    // VanillaWeaponType (swords/axes) don't have special material-based modifiers
    return 0;
  }

  public static float getReachModifier(WeaponTypeInterface type, ToolMaterial material) {
    if (type instanceof BasicWeaponType basicType) {
      return BasicWeaponType.getReachModifier(basicType, material);
    }
    // VanillaWeaponType (swords/axes) don't have special material-based modifiers
    return 0;
  }
} 