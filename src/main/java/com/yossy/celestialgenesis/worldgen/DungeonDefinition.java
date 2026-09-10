package com.yossy.celestialgenesis.worldgen;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.resources.ResourceLocation;
/** Datapack-owned dungeon progression metadata. */
public record DungeonDefinition(ResourceLocation structure,ResourceLocation lootTable,ResourceLocation boss,int requiredStage) {
 public static final Codec<DungeonDefinition> CODEC=RecordCodecBuilder.create(i->i.group(
  ResourceLocation.CODEC.fieldOf("structure").forGetter(DungeonDefinition::structure),
  ResourceLocation.CODEC.fieldOf("loot_table").forGetter(DungeonDefinition::lootTable),
  ResourceLocation.CODEC.fieldOf("boss").forGetter(DungeonDefinition::boss),
  Codec.INT.fieldOf("required_stage").forGetter(DungeonDefinition::requiredStage)).apply(i,DungeonDefinition::new));
}
