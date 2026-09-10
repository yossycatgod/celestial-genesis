package com.yossy.celestialgenesis.registry;

import com.yossy.celestialgenesis.CelestialGenesis;
import com.yossy.celestialgenesis.block.GenesisForgeBlock;
import com.yossy.celestialgenesis.block.BossSealBlock;
import com.yossy.celestialgenesis.block.AstralGatewayBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(CelestialGenesis.MODID);
    public static final DeferredBlock<GenesisForgeBlock> GENESIS_FORGE = BLOCKS.register("genesis_forge", () ->
            new GenesisForgeBlock(BlockBehaviour.Properties.of().strength(5F, 12F).sound(SoundType.METAL).requiresCorrectToolForDrops()));
    public static final DeferredBlock<BossSealBlock> BOSS_SEAL = BLOCKS.register("boss_seal", () -> new BossSealBlock(BlockBehaviour.Properties.of().strength(-1F,3600000F).sound(SoundType.METAL)));
    public static final DeferredBlock<AstralGatewayBlock> ASTRAL_GATEWAY = BLOCKS.register("astral_gateway", () -> new AstralGatewayBlock(BlockBehaviour.Properties.of().strength(5F,12F).lightLevel(s->12).sound(SoundType.AMETHYST)));

    private ModBlocks() {}
    public static void register(IEventBus bus) { BLOCKS.register(bus); }
}
