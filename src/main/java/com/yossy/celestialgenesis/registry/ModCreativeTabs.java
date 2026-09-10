package com.yossy.celestialgenesis.registry;

import com.yossy.celestialgenesis.CelestialGenesis;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModCreativeTabs {
    private static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, CelestialGenesis.MODID);
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> CELESTIAL_GENESIS = TABS.register("celestial_genesis", () ->
            CreativeModeTab.builder().title(Component.translatable("itemGroup.celestialgenesis"))
                    .icon(() -> ModItems.LUNAR_GENESIS.get().getDefaultInstance())
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.LUNAR_GENESIS.get());
                        output.accept(ModItems.CELESTIAL_HOOD.get());
                        output.accept(ModItems.CELESTIAL_COAT.get());
                        output.accept(ModItems.CELESTIAL_LEGGINGS.get());
                        output.accept(ModItems.CELESTIAL_BOOTS.get());
                        output.accept(ModItems.GENESIS_FORGE.get());
                        output.accept(ModItems.GENESIS_CATALYST.get());
                        output.accept(ModItems.STAR_SHARD.get());
                        output.accept(ModItems.BOSS_SEAL.get()); output.accept(ModItems.ASTRAL_GATEWAY.get());
                        output.accept(ModItems.LUNAR_KEY.get()); output.accept(ModItems.OBSERVATORY_KEY.get()); output.accept(ModItems.PALACE_KEY.get());
                        output.accept(ModItems.MOONLIT_CORE.get()); output.accept(ModItems.WATCHER_EYE.get()); output.accept(ModItems.ASTRAL_STAR_SHARD.get());
                        output.accept(ModItems.EPIC_FIGHT_SIGIL.get()); output.accept(ModItems.TACZ_CORE.get()); output.accept(ModItems.APOTHEOSIS_PRISM.get());
                        output.accept(ModItems.CELESTIAL_INGOT.get());
                        output.accept(ModItems.ASTRAL_SENTINEL_SPAWN_EGG.get()); output.accept(ModItems.MOONLIT_GUARDIAN_SPAWN_EGG.get()); output.accept(ModItems.ASTRAL_WATCHER_SPAWN_EGG.get());
                    }).build());

    private ModCreativeTabs() {}
    public static void register(IEventBus bus) { TABS.register(bus); }
}
