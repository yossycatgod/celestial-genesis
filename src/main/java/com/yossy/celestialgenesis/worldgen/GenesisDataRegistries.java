package com.yossy.celestialgenesis.worldgen;
import com.yossy.celestialgenesis.CelestialGenesis;
import net.minecraft.core.Registry;
import net.minecraft.resources.*;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.DataPackRegistryEvent;
@EventBusSubscriber(modid=CelestialGenesis.MODID,bus=EventBusSubscriber.Bus.MOD)
public final class GenesisDataRegistries {
 public static final ResourceKey<Registry<DungeonDefinition>> DUNGEONS=ResourceKey.createRegistryKey(ResourceLocation.fromNamespaceAndPath(CelestialGenesis.MODID,"dungeon"));
 public static final ResourceKey<Registry<BossDefinition>> BOSSES=ResourceKey.createRegistryKey(ResourceLocation.fromNamespaceAndPath(CelestialGenesis.MODID,"boss"));
 @SubscribeEvent public static void register(DataPackRegistryEvent.NewRegistry e){e.dataPackRegistry(DUNGEONS,DungeonDefinition.CODEC);e.dataPackRegistry(BOSSES,BossDefinition.CODEC);}
}
