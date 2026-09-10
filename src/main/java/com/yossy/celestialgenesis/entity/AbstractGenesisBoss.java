package com.yossy.celestialgenesis.entity;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.BossEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.level.Level;

public abstract class AbstractGenesisBoss extends Zombie {
    private final ServerBossEvent bossBar = new ServerBossEvent(getDisplayName(), BossEvent.BossBarColor.PURPLE,
            BossEvent.BossBarOverlay.PROGRESS);

    protected AbstractGenesisBoss(EntityType<? extends Zombie> type, Level level) { super(type, level); }

    @Override public void setCustomName(Component name) { super.setCustomName(name); bossBar.setName(getDisplayName()); }
    @Override public void startSeenByPlayer(ServerPlayer player) { super.startSeenByPlayer(player); bossBar.addPlayer(player); }
    @Override public void stopSeenByPlayer(ServerPlayer player) { super.stopSeenByPlayer(player); bossBar.removePlayer(player); }
    @Override protected void customServerAiStep() { super.customServerAiStep(); bossBar.setProgress(getHealth() / getMaxHealth()); }
}
