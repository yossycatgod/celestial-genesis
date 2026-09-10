package com.yossy.celestialgenesis;

import com.mojang.logging.LogUtils;
import com.yossy.celestialgenesis.registry.ModCreativeTabs;
import com.yossy.celestialgenesis.registry.ModBlocks;
import com.yossy.celestialgenesis.registry.ModItems;
import com.yossy.celestialgenesis.compat.CompatBootstrap;
import com.yossy.celestialgenesis.registry.ModEntities;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;

@Mod(CelestialGenesis.MODID)
public final class CelestialGenesis {
    public static final String MODID = "celestialgenesis";
    public static final Logger LOGGER = LogUtils.getLogger();

    public CelestialGenesis(IEventBus modEventBus) {
        ModBlocks.register(modEventBus);
        ModEntities.register(modEventBus);
        ModItems.register(modEventBus);
        ModCreativeTabs.register(modEventBus);
        CompatBootstrap.initialize();
        LOGGER.info("Celestial Genesis v0.3 initialized");
    }
}
