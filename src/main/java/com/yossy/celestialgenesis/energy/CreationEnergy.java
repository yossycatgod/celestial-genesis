package com.yossy.celestialgenesis.energy;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;

/** Minimal server-authoritative Creation Energy store for v0.1. */
public final class CreationEnergy {
    public static final int MAX = 100;
    private static final String ROOT_KEY = "celestialgenesis";
    private static final String ENERGY_KEY = "creation_energy";

    private CreationEnergy() {}

    public static int get(Player player) {
        CompoundTag root = player.getPersistentData().getCompound(ROOT_KEY);
        return root.contains(ENERGY_KEY) ? Math.clamp(root.getInt(ENERGY_KEY), 0, MAX) : MAX;
    }

    public static void set(Player player, int amount) {
        CompoundTag data = player.getPersistentData();
        CompoundTag root = data.getCompound(ROOT_KEY);
        root.putInt(ENERGY_KEY, Math.clamp(amount, 0, MAX));
        data.put(ROOT_KEY, root);
    }

    public static boolean consume(Player player, int amount) {
        if (player.isCreative()) return true;
        int current = get(player);
        if (current < amount) return false;
        set(player, current - amount);
        return true;
    }
}
