package com.seacroak.basicweapons.data;

public record WeaponStats(float damage, float speed, double reach) {
  public static final WeaponStats DAGGER = new WeaponStats(1f, -1.6f, 0);
  public static final WeaponStats HAMMER = new WeaponStats(7f, -3.4f, 0);
  public static final WeaponStats CLUB = new WeaponStats(5f, -3.0f, 0);
  public static final WeaponStats SPEAR = new WeaponStats(2f, -2.8f, 2);
  public static final WeaponStats QUARTERSTAFF = new WeaponStats(1f, -2.3f, 1.25);
  public static final WeaponStats GLAIVE = new WeaponStats(5f, -3.2f, 1.25);

  public WeaponStats withDamage(float modifier) {
    return new WeaponStats(damage + modifier, speed, reach);
  }

  public WeaponStats withSpeed(float modifier) {
    return new WeaponStats(damage, speed + modifier, reach);
  }

  public WeaponStats withReach(double modifier) {
    return new WeaponStats(damage, speed, reach + modifier);
  }
}