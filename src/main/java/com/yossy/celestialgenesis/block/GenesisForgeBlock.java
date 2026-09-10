package com.yossy.celestialgenesis.block;

import com.mojang.serialization.MapCodec;
import com.yossy.celestialgenesis.forging.GenesisForging;
import com.yossy.celestialgenesis.registry.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public final class GenesisForgeBlock extends BaseEntityBlock {
    public static final MapCodec<GenesisForgeBlock> CODEC = simpleCodec(GenesisForgeBlock::new);

    public GenesisForgeBlock(BlockBehaviour.Properties properties) { super(properties); }

    @Override protected MapCodec<? extends BaseEntityBlock> codec() { return CODEC; }
    @Override public net.minecraft.world.level.block.entity.BlockEntity newBlockEntity(BlockPos pos, BlockState state) { return null; }
    @Override public RenderShape getRenderShape(BlockState state) { return RenderShape.MODEL; }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack held, BlockState state, Level level, BlockPos pos,
                                               Player player, InteractionHand hand, BlockHitResult hit) {
        ItemStack other = player.getItemInHand(hand == InteractionHand.MAIN_HAND ? InteractionHand.OFF_HAND : InteractionHand.MAIN_HAND);
        if (level.isClientSide) return ItemInteractionResult.SUCCESS;

        if (GenesisForging.isEquipment(held) && other.is(ModItems.GENESIS_CATALYST.get())) {
            GenesisForging.reforge(held, player.getRandom());
            consume(other, player);
            success(level, pos);
            player.displayClientMessage(Component.translatable("message.celestialgenesis.reforged"), true);
            return ItemInteractionResult.SUCCESS;
        }
        if (GenesisForging.isEquipment(held) && other.is(ModItems.CELESTIAL_INGOT.get())) {
            GenesisForging.reforgeDungeon(held, player.getRandom()); consume(other,player); success(level,pos);
            player.displayClientMessage(Component.translatable("message.celestialgenesis.astral_forged"),true); return ItemInteractionResult.SUCCESS;
        }
        if (held.is(ModItems.ASTRAL_STAR_SHARD.get()) && GenesisForging.isEquipment(other)) {
            if(GenesisForging.addAstralShard(other)){consume(held,player);success(level,pos);player.displayClientMessage(Component.translatable("message.celestialgenesis.socket_added"),true);}
            else player.displayClientMessage(Component.translatable("message.celestialgenesis.no_socket"),true);
            return ItemInteractionResult.SUCCESS;
        }
        if (held.is(ModItems.STAR_SHARD.get()) && GenesisForging.isEquipment(other)) {
            if (GenesisForging.addRandomShard(other, player.getRandom())) {
                consume(held, player);
                success(level, pos);
                player.displayClientMessage(Component.translatable("message.celestialgenesis.socket_added"), true);
            } else player.displayClientMessage(Component.translatable("message.celestialgenesis.no_socket"), true);
            return ItemInteractionResult.SUCCESS;
        }
        if (player.isShiftKeyDown() && GenesisForging.isEquipment(held) && GenesisForging.removeLastShard(held)) {
            player.getInventory().placeItemBackInInventory(new ItemStack(ModItems.STAR_SHARD.get()));
            success(level, pos);
            player.displayClientMessage(Component.translatable("message.celestialgenesis.socket_removed"), true);
            return ItemInteractionResult.SUCCESS;
        }
        player.displayClientMessage(Component.translatable("message.celestialgenesis.forge_help"), true);
        return ItemInteractionResult.SUCCESS;
    }

    private static void consume(ItemStack stack, Player player) { if (!player.isCreative()) stack.shrink(1); }
    private static void success(Level level, BlockPos pos) {
        ((ServerLevel) level).sendParticles(ParticleTypes.FIREWORK, pos.getX() + .5, pos.getY() + 1.1, pos.getZ() + .5, 24, .35, .3, .35, .05);
        level.playSound(null, pos, SoundEvents.ANVIL_USE, SoundSource.BLOCKS, 1F, 1.4F);
    }
}
