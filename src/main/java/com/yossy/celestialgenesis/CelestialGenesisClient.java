package com.yossy.celestialgenesis;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.Mod;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.minecraft.client.renderer.entity.ZombieRenderer;
import net.minecraft.client.Minecraft;
import com.yossy.celestialgenesis.registry.ModEntities;
import net.neoforged.neoforge.client.event.RenderGuiEvent;
import com.yossy.celestialgenesis.energy.CreationEnergy;
import com.yossy.celestialgenesis.progression.ExplorationProgress;
import com.yossy.celestialgenesis.forging.GenesisForging;

@Mod(value = CelestialGenesis.MODID, dist = Dist.CLIENT)
public final class CelestialGenesisClient {
    @EventBusSubscriber(modid=CelestialGenesis.MODID, value=Dist.CLIENT, bus=EventBusSubscriber.Bus.MOD)
    public static final class ClientEvents {
        @SubscribeEvent public static void renderers(EntityRenderersEvent.RegisterRenderers event) {
            event.registerEntityRenderer(ModEntities.ASTRAL_SENTINEL.get(), ZombieRenderer::new);
            event.registerEntityRenderer(ModEntities.MOONLIT_GUARDIAN.get(), ZombieRenderer::new);
            event.registerEntityRenderer(ModEntities.ASTRAL_WATCHER.get(), ZombieRenderer::new);
        }
    }
    @EventBusSubscriber(modid=CelestialGenesis.MODID, value=Dist.CLIENT)
    public static final class HudEvents {
        @SubscribeEvent public static void hud(RenderGuiEvent.Post event) {
            Minecraft mc=Minecraft.getInstance(); if(mc.player==null||mc.options.hideGui||!GenesisForging.isEquipment(mc.player.getMainHandItem()))return;
            var g=event.getGuiGraphics();
            g.fill(8,8,150,33,0xAA080818);
            g.drawString(mc.font,"Creation Energy: "+CreationEnergy.get(mc.player)+"/"+CreationEnergy.MAX,14,13,0x75E6FF,false);
            g.drawString(mc.font,"Astral Progress: "+ExplorationProgress.get(mc.player)+"/3",14,23,0xDDA0FF,false);
        }
    }
}
