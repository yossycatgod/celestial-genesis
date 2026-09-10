package com.yossy.celestialgenesis.energy;

import com.yossy.celestialgenesis.CelestialGenesis;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import com.yossy.celestialgenesis.forging.GenesisForging;
import net.minecraft.world.item.ItemStack;

@EventBusSubscriber(modid = CelestialGenesis.MODID)
public final class CreationEnergyEvents {
    private CreationEnergyEvents() {}

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        if (event.getEntity() instanceof ServerPlayer player && player.tickCount % 20 == 0) {
            double flow = 0;
            double vitality = 0;
            for (ItemStack armor : player.getArmorSlots()) {
                flow += GenesisForging.total(armor, "energy_flow");
                vitality += GenesisForging.total(armor, "vital_star");
            }
            CreationEnergy.set(player, CreationEnergy.get(player) + 1 + (int) (flow / 10.0));
            if (vitality > 0 && player.tickCount % 100 == 0 && player.getHealth() < player.getMaxHealth()) {
                player.heal((float) Math.max(1.0, vitality / 10.0));
            }
        }
    }
}
