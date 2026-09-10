package com.yossy.celestialgenesis.block;
import com.yossy.celestialgenesis.CelestialGenesis;
import java.util.Set;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.*;
import net.minecraft.server.level.*;
import net.minecraft.world.*;
import net.minecraft.world.entity.RelativeMovement;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.phys.BlockHitResult;
public final class AstralGatewayBlock extends Block {
 public static final ResourceKey<Level> ASTRAL_REALM=ResourceKey.create(Registries.DIMENSION,ResourceLocation.fromNamespaceAndPath(CelestialGenesis.MODID,"astral_realm"));
 public AstralGatewayBlock(Properties p){super(p);}
 @Override protected InteractionResult useWithoutItem(BlockState s,Level level,BlockPos pos,Player player,BlockHitResult hit){
  if(level.isClientSide)return InteractionResult.SUCCESS;
  ServerPlayer sp=(ServerPlayer)player;ServerLevel target=sp.server.getLevel(sp.level().dimension()==ASTRAL_REALM?Level.OVERWORLD:ASTRAL_REALM);
  if(target==null)return InteractionResult.FAIL;
  BlockPos dest=target.getHeightmapPos(net.minecraft.world.level.levelgen.Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,new BlockPos(pos.getX(),64,pos.getZ()));
  sp.teleportTo(target,dest.getX()+.5,dest.getY()+1,dest.getZ()+.5,Set.of(),sp.getYRot(),sp.getXRot());return InteractionResult.SUCCESS;
 }
}
