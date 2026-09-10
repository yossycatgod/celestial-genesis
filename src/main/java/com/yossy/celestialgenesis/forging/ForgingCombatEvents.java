package com.yossy.celestialgenesis.forging;

import com.yossy.celestialgenesis.CelestialGenesis;
import com.yossy.celestialgenesis.energy.CreationEnergy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;

@EventBusSubscriber(modid = CelestialGenesis.MODID)
public final class ForgingCombatEvents {
    private ForgingCombatEvents() {}

    @SubscribeEvent
    public static void modifyDamage(LivingIncomingDamageEvent event) {
        if (event.getSource().getEntity() instanceof Player attacker) {
            double bonus = GenesisForging.total(attacker.getMainHandItem(), "astral_power");
            if (event.getEntity() instanceof com.yossy.celestialgenesis.entity.AbstractGenesisBoss)
                bonus += GenesisForging.total(attacker.getMainHandItem(), "boss_slayer");
            if (bonus > 0) event.setAmount(event.getAmount() + (float) bonus);
        }
        if (event.getEntity() instanceof Player defender) {
            double guard = 0;
            for (ItemStack armor : defender.getArmorSlots()) guard += GenesisForging.total(armor, "celestial_guard");
            for (ItemStack armor : defender.getArmorSlots()) guard += GenesisForging.total(armor, "astral_resilience");
            if (guard > 0) event.setAmount(event.getAmount() * (float) Math.max(0.25, 1.0 - guard / 100.0));
        }
    }
}
