package com.yossy.celestialgenesis.progression;
import com.yossy.celestialgenesis.CelestialGenesis;
import com.yossy.celestialgenesis.entity.*;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
@EventBusSubscriber(modid=CelestialGenesis.MODID)
public final class ExplorationEvents {
 @SubscribeEvent public static void death(LivingDeathEvent e){
  if(!(e.getSource().getEntity() instanceof Player p))return;
  int stage=e.getEntity() instanceof AstralWatcher?3:e.getEntity() instanceof MoonlitGuardian?2:e.getEntity() instanceof AstralSentinel?1:0;
  if(stage>0){ExplorationProgress.unlock(p,stage);p.displayClientMessage(Component.translatable("message.celestialgenesis.progress",stage),false);}
 }
}
