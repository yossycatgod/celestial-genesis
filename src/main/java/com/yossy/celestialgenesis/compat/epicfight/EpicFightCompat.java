package com.yossy.celestialgenesis.compat.epicfight;
import com.yossy.celestialgenesis.CelestialGenesis;
import com.yossy.celestialgenesis.api.GenesisEquipmentApi;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
/** Adapter methods intended for Epic Fight weapon capability/skill patches. */
public final class EpicFightCompat {
    private static final ResourceLocation ATTACK = id("astral_power"), SKILL = id("lunar_edge");
    private EpicFightCompat() {}
    public static boolean hasGenesisData(ItemStack stack) { return GenesisEquipmentApi.isForged(stack); }
    public static double attackDamageBonus(ItemStack stack) { return GenesisEquipmentApi.modifier(stack, ATTACK); }
    public static double skillDamageBonus(ItemStack stack) { return GenesisEquipmentApi.modifier(stack, SKILL); }
    private static ResourceLocation id(String path) { return ResourceLocation.fromNamespaceAndPath(CelestialGenesis.MODID, path); }
}
