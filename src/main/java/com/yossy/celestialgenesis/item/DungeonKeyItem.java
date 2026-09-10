package com.yossy.celestialgenesis.item;
import com.yossy.celestialgenesis.progression.ExplorationProgress;
import java.util.List;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
public final class DungeonKeyItem extends Item {
 private final int stage;
 public DungeonKeyItem(int stage,Properties p){super(p);this.stage=stage;}
 public int stage(){return stage;}
 @Override public void appendHoverText(ItemStack s,TooltipContext c,List<Component> t,TooltipFlag f){t.add(Component.translatable("tooltip.celestialgenesis.key_stage",stage).withStyle(ChatFormatting.AQUA));}
}
