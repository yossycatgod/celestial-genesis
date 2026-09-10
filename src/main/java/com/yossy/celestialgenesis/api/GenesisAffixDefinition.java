package com.yossy.celestialgenesis.api;

import net.minecraft.resources.ResourceLocation;

/** Public, immutable definition that other mods may register during common setup. */
public record GenesisAffixDefinition(ResourceLocation id, String translationKey, EquipmentKind kind,
                                     double minimum, double maximum) {
    public GenesisAffixDefinition {
        if (minimum > maximum) throw new IllegalArgumentException("minimum must not exceed maximum");
    }
}
