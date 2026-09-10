package com.yossy.celestialgenesis.forging;

import net.minecraft.ChatFormatting;

public enum EquipmentRarity {
    COMMON(ChatFormatting.WHITE, 1),
    RARE(ChatFormatting.BLUE, 1),
    EPIC(ChatFormatting.LIGHT_PURPLE, 2),
    CELESTIAL(ChatFormatting.GOLD, 3);

    public final ChatFormatting color;
    public final int sockets;

    EquipmentRarity(ChatFormatting color, int sockets) {
        this.color = color;
        this.sockets = sockets;
    }
}
