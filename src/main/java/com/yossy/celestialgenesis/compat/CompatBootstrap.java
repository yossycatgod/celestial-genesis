package com.yossy.celestialgenesis.compat;

import com.yossy.celestialgenesis.CelestialGenesis;
import com.yossy.celestialgenesis.api.EquipmentKind;
import com.yossy.celestialgenesis.api.GenesisEquipmentApi;
import net.neoforged.fml.ModList;

/** Loader-safe optional integration bootstrap; never links absent third-party classes. */
public final class CompatBootstrap {
    private CompatBootstrap() {}

    public static void initialize() {
        if (ModList.get().isLoaded("tacz")) {
            GenesisEquipmentApi.registerEquipment(EquipmentKind.WEAPON,
                    stack -> stack.getItem().getClass().getName().startsWith("com.tacz.guns."));
            CelestialGenesis.LOGGER.info("Enabled TaCZ Genesis Forging integration");
        }
        if (ModList.get().isLoaded("epicfight")) CelestialGenesis.LOGGER.info("Enabled Epic Fight capability datapack integration");
        if (ModList.get().isLoaded("apotheosis")) CelestialGenesis.LOGGER.info("Enabled Apotheosis affix loot integration");
    }
}
