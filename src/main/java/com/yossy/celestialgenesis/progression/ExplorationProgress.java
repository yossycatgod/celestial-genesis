package com.yossy.celestialgenesis.progression;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
public final class ExplorationProgress {
 private static final String KEY="celestialgenesis_exploration";
 private ExplorationProgress() {}
 public static int get(Player p){ return p.getPersistentData().getInt(KEY); }
 public static void unlock(Player p,int stage){ if(get(p)<stage)p.getPersistentData().putInt(KEY,stage); }
}
