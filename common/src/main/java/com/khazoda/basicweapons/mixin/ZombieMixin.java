package com.khazoda.basicweapons.mixin;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.zombie.Zombie;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;

@Mixin(Zombie.class)
public class ZombieMixin extends Monster {

  @Unique
  private static final Item[] ACCEPTABLE_IRON_WEAPONS;

  static {
    List<Item> weapons = new ArrayList<>();
    Identifier[] weaponIds = {Identifier.fromNamespaceAndPath("basicweapons", "iron_dagger"), Identifier.fromNamespaceAndPath("basicweapons", "iron_hammer"), Identifier.fromNamespaceAndPath("basicweapons", "iron_club")};

    for (Identifier weaponId : weaponIds) {
      BuiltInRegistries.ITEM.getOptional(weaponId).ifPresent(weapons::add);
    }

    ACCEPTABLE_IRON_WEAPONS = weapons.toArray(new Item[0]);
  }

  protected ZombieMixin(EntityType<? extends Monster> entityType, Level level) {
    super(entityType, level);
  }

  @Inject(method = "populateDefaultEquipmentSlots", at = @At("TAIL"))
  protected void injectMyWeapons(RandomSource random, DifficultyInstance difficulty, CallbackInfo ci) {
    if (random.nextFloat() < (this.level().getDifficulty() == Difficulty.HARD ? 0.15F : 0.01F)) {
      if (ACCEPTABLE_IRON_WEAPONS.length > 0) {
        Item selectedWeapon = ACCEPTABLE_IRON_WEAPONS[random.nextInt(ACCEPTABLE_IRON_WEAPONS.length)];
        this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(selectedWeapon));
      }
    }
  }
}