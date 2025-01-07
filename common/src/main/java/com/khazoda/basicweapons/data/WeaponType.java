package com.khazoda.basicweapons.data;


import com.khazoda.basicweapons.item.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tier;

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
  private final double reach;
  private final WeaponFactory factory;

  WeaponType(String id, float baseDamage, float baseSpeed, double reach, WeaponFactory factory) {
    this.id = id;
    this.baseDamage = baseDamage;
    this.baseSpeed = baseSpeed;
    this.reach = reach;
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

  public double getReach() {
    return reach;
  }

  public Item create(Tier material, float damageModifier, float speedModifier, Item.Properties properties) {
    return factory.create(
        material,
        baseDamage + damageModifier,
        baseSpeed + speedModifier,
        reach,
        properties
    );
  }

  @FunctionalInterface
  public interface WeaponFactory {
    Item create(Tier material, float damage, float speed, double reach, Item.Properties properties);
  }
} 