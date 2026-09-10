package com.yossy.celestialgenesis.registry;
import com.yossy.celestialgenesis.CelestialGenesis;
import com.yossy.celestialgenesis.entity.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.*;
public final class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(Registries.ENTITY_TYPE, CelestialGenesis.MODID);
    public static final DeferredHolder<EntityType<?>, EntityType<AstralSentinel>> ASTRAL_SENTINEL = ENTITIES.register("astral_sentinel", () -> EntityType.Builder.of(AstralSentinel::new, MobCategory.MONSTER).sized(.7F, 2F).build("astral_sentinel"));
    public static final DeferredHolder<EntityType<?>, EntityType<MoonlitGuardian>> MOONLIT_GUARDIAN = ENTITIES.register("moonlit_guardian", () -> EntityType.Builder.of(MoonlitGuardian::new, MobCategory.MONSTER).sized(.9F, 2.5F).fireImmune().build("moonlit_guardian"));
    public static final DeferredHolder<EntityType<?>, EntityType<AstralWatcher>> ASTRAL_WATCHER = ENTITIES.register("astral_watcher", () -> EntityType.Builder.of(AstralWatcher::new, MobCategory.MONSTER).sized(1F, 2.8F).fireImmune().build("astral_watcher"));
    private ModEntities() {}
    public static void register(IEventBus bus) { ENTITIES.register(bus); }
}
