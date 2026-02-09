package com.khazoda.basicweapons.mixin;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.entity.monster.zombie.Zombie;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Arrays;
import java.util.Optional;

@Mixin({Zombie.class, Piglin.class})
public abstract class MonsterMixin {

  @Unique
  private static final Item[] bw$IRON_WEAPONS = bw$getWeapons("iron_dagger", "iron_hammer", "iron_club");
  @Unique
  private static final Item[] bw$GOLDEN_WEAPONS = bw$getWeapons("golden_dagger", "golden_hammer", "golden_club");

  @Unique
  private static Item[] bw$getWeapons(String... ids) {
    return Arrays.stream(ids).map(id -> {
      String[] s = id.split(":");
      return s.length == 2 ? Identifier.fromNamespaceAndPath(s[0], s[1]) : Identifier.fromNamespaceAndPath("basicweapons", id);
    }).map(BuiltInRegistries.ITEM::getOptional).flatMap(Optional::stream).toArray(Item[]::new);
  }

  @Inject(method = "populateDefaultEquipmentSlots", at = @At("TAIL"))
  protected void injectWeaponsWhenSpawning(RandomSource r, DifficultyInstance d, CallbackInfo ci) {
    Mob self = (Mob) (Object) this;
    if (self instanceof Piglin piglin && !piglin.isBaby()) {
      if (r.nextFloat() < (self.level().getDifficulty() == Difficulty.HARD ? 0.35F : 0.1F)) {
        self.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(bw$GOLDEN_WEAPONS[r.nextInt(bw$GOLDEN_WEAPONS.length)]));
      }
    } else if (self instanceof Zombie zombie && !zombie.isBaby()) {
      if (r.nextFloat() < (self.level().getDifficulty() == Difficulty.HARD ? 0.15F : 0.01F)) {
        self.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(bw$IRON_WEAPONS[r.nextInt(bw$IRON_WEAPONS.length)]));
      }
    }
  }
}
