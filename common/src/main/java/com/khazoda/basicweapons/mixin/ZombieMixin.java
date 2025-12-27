package com.khazoda.basicweapons.mixin;

import com.khazoda.basicweapons.registry.WeaponRegistry;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.zombie.Zombie;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(Zombie.class)
public class ZombieMixin extends Monster {

  protected ZombieMixin(EntityType<? extends Monster> entityType, Level level) {
    super(entityType, level);
  }

  @Inject(method = "populateDefaultEquipmentSlots", at = @At("TAIL"), cancellable = true)
  protected void injectMyWeapons(RandomSource random, DifficultyInstance difficulty, CallbackInfo ci) {
    if (random.nextFloat() < (this.level().getDifficulty() == Difficulty.HARD ? 0.15F : 0.01F)) {
      List<Item> ironWeapons = WeaponRegistry.getItemsByMaterial(ToolMaterial.IRON);
      this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(ironWeapons.get(random.nextInt(ironWeapons.size()))));
      ci.cancel();
    }
  }
}
