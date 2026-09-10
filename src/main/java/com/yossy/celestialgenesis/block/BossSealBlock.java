package com.yossy.celestialgenesis.block;
import com.yossy.celestialgenesis.item.DungeonKeyItem;
import com.yossy.celestialgenesis.progression.ExplorationProgress;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.*;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.phys.BlockHitResult;
public final class BossSealBlock extends Block {
 public BossSealBlock(Properties p){super(p);}
 @Override protected ItemInteractionResult useItemOn(ItemStack held,BlockState state,Level level,BlockPos pos,Player player,InteractionHand hand,BlockHitResult hit){
  if(!(held.getItem() instanceof DungeonKeyItem key))return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
  if(level.isClientSide)return ItemInteractionResult.SUCCESS;
  if(ExplorationProgress.get(player)+1<key.stage()){player.displayClientMessage(Component.translatable("message.celestialgenesis.locked"),true);return ItemInteractionResult.FAIL;}
  if(!player.isCreative())held.shrink(1);level.removeBlock(pos,false);ExplorationProgress.unlock(player,key.stage());
  ((ServerLevel)level).sendParticles(ParticleTypes.ENCHANT,pos.getX()+.5,pos.getY()+.5,pos.getZ()+.5,40,.5,.5,.5,.1);level.playSound(null,pos,SoundEvents.END_PORTAL_SPAWN,SoundSource.BLOCKS,1,1.4F);return ItemInteractionResult.SUCCESS;
 }
}
