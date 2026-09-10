package com.yossy.celestialgenesis.api;

import java.util.List;
import net.minecraft.resources.ResourceLocation;

/** Stable read-only snapshot. Collections are detached from the ItemStack NBT. */
public record GenesisEquipmentData(int dataVersion, String rarity, List<GenesisAffixInstance> affixes,
                                   int socketCapacity, List<ResourceLocation> shards) {
    public GenesisEquipmentData {
        affixes = List.copyOf(affixes);
        shards = List.copyOf(shards);
    }
}
