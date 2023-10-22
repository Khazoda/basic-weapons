package com.seacroak.basicweapons.datagen;

import com.seacroak.basicweapons.Constants;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Constants.BW_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {
  @SubscribeEvent
  public static void onGatherData(GatherDataEvent event) {
    DataGenerator generator = event.getGenerator();
    PackOutput packOutput = generator.getPackOutput();
    generator.addProvider(event.includeServer(), LootTableModification.getProvider(packOutput));
  }
}
