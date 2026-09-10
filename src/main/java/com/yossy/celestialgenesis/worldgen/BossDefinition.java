package com.yossy.celestialgenesis.worldgen;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.resources.ResourceLocation;
/** Datapack-owned boss metadata for future behavior packs and addons. */
public record BossDefinition(double health,double attack,ResourceLocation lootTable,int unlockStage) {
 public static final Codec<BossDefinition> CODEC=RecordCodecBuilder.create(i->i.group(
  Codec.DOUBLE.fieldOf("health").forGetter(BossDefinition::health),Codec.DOUBLE.fieldOf("attack").forGetter(BossDefinition::attack),
  ResourceLocation.CODEC.fieldOf("loot_table").forGetter(BossDefinition::lootTable),Codec.INT.fieldOf("unlock_stage").forGetter(BossDefinition::unlockStage)).apply(i,BossDefinition::new));
}
