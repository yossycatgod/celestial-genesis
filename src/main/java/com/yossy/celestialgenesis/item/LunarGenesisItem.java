package com.yossy.celestialgenesis.item;

import com.yossy.celestialgenesis.energy.CreationEnergy;
import com.yossy.celestialgenesis.forging.ForgingTooltip;
import com.yossy.celestialgenesis.forging.GenesisForging;
import java.util.List;
import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public final class LunarGenesisItem extends SwordItem {
    private static final int ENERGY_COST = 25;

    public LunarGenesisItem(Tier tier, Properties properties) { super(tier, properties); }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (level.isClientSide) return InteractionResultHolder.sidedSuccess(stack, true);
        int energyCost = Math.max(5, ENERGY_COST - (int) GenesisForging.total(stack, "energy_surge"));
        if (!CreationEnergy.consume(player, energyCost)) {
            player.displayClientMessage(Component.translatable("message.celestialgenesis.not_enough_energy", energyCost), true);
            return InteractionResultHolder.fail(stack);
        }

        Vec3 look = player.getLookAngle().normalize();
        Vec3 origin = player.getEyePosition();
        AABB area = player.getBoundingBox().inflate(6.0);
        List<LivingEntity> targets = level.getEntitiesOfClass(LivingEntity.class, area,
                target -> target != player && target.isAlive());
        for (LivingEntity target : targets) {
            Vec3 direction = target.getEyePosition().subtract(origin);
            if (direction.length() <= 6.0 && direction.normalize().dot(look) >= 0.45) {
                float damage = (float) (10.0 + GenesisForging.total(stack, "lunar_edge"));
                target.hurt(level.damageSources().playerAttack(player), damage);
                target.push(look.x * 0.8, 0.25, look.z * 0.8);
            }
        }

        ServerLevel serverLevel = (ServerLevel) level;
        for (int i = 1; i <= 12; i++) {
            Vec3 point = origin.add(look.scale(i * 0.5));
            serverLevel.sendParticles(ParticleTypes.END_ROD, point.x, point.y, point.z, 3, 0.35, 0.35, 0.35, 0.02);
        }
        level.playSound(null, player.blockPosition(), SoundEvents.PLAYER_ATTACK_SWEEP, SoundSource.PLAYERS, 1.2F, 0.7F);
        player.getCooldowns().addCooldown(this, 60);
        return InteractionResultHolder.success(stack);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, context, tooltip, flag);
        tooltip.add(Component.translatable("tooltip.celestialgenesis.lunar_genesis.skill").withStyle(ChatFormatting.AQUA));
        tooltip.add(Component.translatable("tooltip.celestialgenesis.creation_energy_cost", ENERGY_COST).withStyle(ChatFormatting.GRAY));
        ForgingTooltip.append(stack, tooltip);
    }
}
