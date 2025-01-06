package com.khazoda.basicweapons;


import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.registries.RegisterEvent;

@Mod(Constants.MOD_ID)
public class BasicWeaponsNorge {

    public BasicWeaponsNorge(IEventBus eventBus) {

        BasicWeaponsCommon.init();
        eventBus.addListener(this::onInit);
        eventBus.addListener(this::onRegister);
    }

    private void onInit(FMLCommonSetupEvent event) {
        event.enqueueWork(BasicWeaponsCommon::postInit);
    }

    private void onRegister(RegisterEvent event) {
        BasicWeaponsCommon.REGISTRARS.register(event.getRegistry());
    }
}