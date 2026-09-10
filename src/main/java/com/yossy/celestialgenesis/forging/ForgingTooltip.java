package com.yossy.celestialgenesis.forging;

import java.util.List;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

public final class ForgingTooltip {
    private ForgingTooltip() {}

    public static void append(ItemStack stack, List<Component> tooltip) {
        CompoundTag data = GenesisForging.read(stack);
        if (!data.contains("Rarity")) {
            tooltip.add(Component.translatable("tooltip.celestialgenesis.unforged").withStyle(ChatFormatting.DARK_GRAY));
            return;
        }
        EquipmentRarity rarity = GenesisForging.rarity(stack);
        tooltip.add(Component.translatable("tooltip.celestialgenesis.rarity", rarity.name()).withStyle(rarity.color));
        for (int i = 0; i < data.getInt("AffixCount"); i++) {
            tooltip.add(Component.literal("✦ ").append(Component.translatable(GenesisForging.affixKey(data.getString("Affix" + i)), data.getDouble("AffixValue" + i))).withStyle(ChatFormatting.AQUA));
        }
        tooltip.add(Component.translatable("tooltip.celestialgenesis.sockets", data.getInt("FilledSockets"), data.getInt("Sockets")).withStyle(ChatFormatting.YELLOW));
        for (int i = 0; i < data.getInt("FilledSockets"); i++) tooltip.add(Component.translatable("shard.celestialgenesis." + data.getString("Shard" + i)).withStyle(ChatFormatting.LIGHT_PURPLE));
    }
}
