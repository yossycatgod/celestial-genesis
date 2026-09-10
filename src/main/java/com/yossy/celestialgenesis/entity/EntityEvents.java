package com.yossy.celestialgenesis.entity;
import com.yossy.celestialgenesis.CelestialGenesis;
import com.yossy.celestialgenesis.registry.ModEntities;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.levelgen.Heightmap;
@EventBusSubscriber(modid=CelestialGenesis.MODID, bus=EventBusSubscriber.Bus.MOD)
public final class EntityEvents {
 @SubscribeEvent public static void attributes(EntityAttributeCreationEvent e) {
  e.put(ModEntities.ASTRAL_SENTINEL.get(), AstralSentinel.attributes().build());
  e.put(ModEntities.MOONLIT_GUARDIAN.get(), MoonlitGuardian.attributes().build());
  e.put(ModEntities.ASTRAL_WATCHER.get(), AstralWatcher.attributes().build());
 }
 @SubscribeEvent public static void placements(RegisterSpawnPlacementsEvent e) {
  e.register(ModEntities.ASTRAL_SENTINEL.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
   Monster::checkMonsterSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
 }
}
