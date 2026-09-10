package com.yossy.celestialgenesis.forging;

public record Affix(String id, String translationKey, double min, double max) {
    public double roll(net.minecraft.util.RandomSource random) {
        return Math.round((min + random.nextDouble() * (max - min)) * 10.0) / 10.0;
    }
}
