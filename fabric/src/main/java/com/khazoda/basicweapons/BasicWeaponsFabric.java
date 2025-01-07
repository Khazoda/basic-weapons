package com.khazoda.basicweapons;

import com.khazoda.basicweapons.registry.CreativeModeTabHandler;
import net.fabricmc.api.ModInitializer;

public class BasicWeaponsFabric implements ModInitializer {
    
    @Override
    public void onInitialize() {
        BasicWeaponsCommon.init();
        BasicWeaponsCommon.postInit();
        BasicWeaponsCommon.REGISTRARS.registerAll();
        CreativeModeTabHandler.buildContents();
    }
}
