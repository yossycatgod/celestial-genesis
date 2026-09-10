package com.yossy.celestialgenesis.forging;

import com.yossy.celestialgenesis.item.LunarGenesisItem;
import com.yossy.celestialgenesis.api.EquipmentKind;
import com.yossy.celestialgenesis.api.GenesisAffixDefinition;
import com.yossy.celestialgenesis.api.GenesisEquipmentApi;
import java.util.Locale;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;

public final class GenesisForging {
    private static final String ROOT = "GenesisForging";
    private GenesisForging() {}

    public static boolean isEquipment(ItemStack stack) {
        return GenesisEquipmentApi.supports(stack, stack.getItem() instanceof ArmorItem ? EquipmentKind.ARMOR : EquipmentKind.WEAPON);
    }

    public static boolean isNativeWeapon(ItemStack stack) { return stack.getItem() instanceof LunarGenesisItem; }

    public static void reforge(ItemStack stack, net.minecraft.util.RandomSource random) {
        CompoundTag root = new CompoundTag();
        root.putInt("DataVersion", GenesisEquipmentApi.API_VERSION);
        EquipmentRarity rarity = rollRarity(random);
        root.putString("Rarity", rarity.name());
        java.util.List<GenesisAffixDefinition> pool = java.util.List.copyOf(GenesisEquipmentApi.affixes(
                stack.getItem() instanceof ArmorItem ? EquipmentKind.ARMOR : EquipmentKind.WEAPON));
        if (pool.isEmpty()) throw new IllegalStateException("No Genesis affixes registered for equipment type");
        int count = rarity == EquipmentRarity.COMMON ? 1 : rarity == EquipmentRarity.RARE ? 2 : 3;
        for (int i = 0; i < count; i++) {
            GenesisAffixDefinition affix = pool.get(random.nextInt(pool.size()));
            root.putString("Affix" + i, affix.id().toString());
            double value = affix.minimum() + random.nextDouble() * (affix.maximum() - affix.minimum());
            root.putDouble("AffixValue" + i, Math.round(value * 10.0) / 10.0);
        }
        root.putInt("AffixCount", count);
        root.putInt("Sockets", rarity.sockets);
        root.putInt("FilledSockets", 0);
        write(stack, root);
    }

    public static void reforgeDungeon(ItemStack stack, net.minecraft.util.RandomSource random) {
        reforge(stack, random);
        CompoundTag root = read(stack);
        java.util.List<GenesisAffixDefinition> pool = java.util.List.copyOf(GenesisEquipmentApi.dungeonAffixes(
                stack.getItem() instanceof ArmorItem ? EquipmentKind.ARMOR : EquipmentKind.WEAPON));
        GenesisAffixDefinition affix = pool.get(random.nextInt(pool.size()));
        int index = root.getInt("AffixCount");
        root.putString("Affix" + index, affix.id().toString());
        root.putDouble("AffixValue" + index, Math.round((affix.minimum()+random.nextDouble()*(affix.maximum()-affix.minimum()))*10)/10.0);
        root.putInt("AffixCount", index + 1);
        root.putBoolean("DungeonForged", true);
        write(stack, root);
    }

    public static boolean addRandomShard(ItemStack equipment, net.minecraft.util.RandomSource random) {
        CompoundTag root = read(equipment);
        int filled = root.getInt("FilledSockets");
        if (!root.contains("Rarity") || filled >= root.getInt("Sockets")) return false;
        String type = switch (random.nextInt(3)) { case 0 -> "nova"; case 1 -> "aegis"; default -> "flow"; };
        root.putString("Shard" + filled, type);
        root.putInt("FilledSockets", filled + 1);
        write(equipment, root);
        return true;
    }

    public static boolean addAstralShard(ItemStack equipment) {
        CompoundTag root=read(equipment); int filled=root.getInt("FilledSockets");
        if(!root.contains("Rarity")||filled>=root.getInt("Sockets"))return false;
        root.putString("Shard"+filled,"astral_nova");root.putInt("FilledSockets",filled+1);write(equipment,root);return true;
    }

    public static boolean removeLastShard(ItemStack equipment) {
        CompoundTag root = read(equipment);
        int filled = root.getInt("FilledSockets");
        if (filled <= 0) return false;
        root.remove("Shard" + (filled - 1));
        root.putInt("FilledSockets", filled - 1);
        write(equipment, root);
        return true;
    }

    public static CompoundTag read(ItemStack stack) {
        CompoundTag all = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();
        return all.getCompound(ROOT);
    }

    private static void write(ItemStack stack, CompoundTag root) {
        CompoundTag all = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();
        all.put(ROOT, root);
        stack.set(DataComponents.CUSTOM_DATA, CustomData.of(all));
    }

    public static EquipmentRarity rarity(ItemStack stack) {
        String value = read(stack).getString("Rarity");
        try { return EquipmentRarity.valueOf(value.toUpperCase(Locale.ROOT)); }
        catch (IllegalArgumentException ignored) { return EquipmentRarity.COMMON; }
    }

    public static String affixKey(String id) {
        net.minecraft.resources.ResourceLocation key = id.indexOf(':') >= 0
                ? net.minecraft.resources.ResourceLocation.parse(id)
                : net.minecraft.resources.ResourceLocation.fromNamespaceAndPath("celestialgenesis", id);
        return GenesisEquipmentApi.affix(key).map(GenesisAffixDefinition::translationKey).orElse("affix.celestialgenesis.unknown");
    }

    public static double total(ItemStack stack, String id) {
        CompoundTag root = read(stack);
        double value = 0;
        for (int i = 0; i < root.getInt("AffixCount"); i++) {
            String stored = root.getString("Affix" + i);
            if (id.equals(stored) || id.equals(stored.substring(stored.indexOf(':') + 1))) value += root.getDouble("AffixValue" + i);
        }
        for (int i = 0; i < root.getInt("FilledSockets"); i++) {
            String shard = root.getString("Shard" + i);
            if ((id.equals("astral_power") && shard.equals("nova")) || (id.equals("celestial_guard") && shard.equals("aegis")) || (id.equals("energy_flow") && shard.equals("flow"))) value += 5;
            if (id.endsWith("boss_slayer") && shard.equals("astral_nova")) value += 10;
        }
        return value;
    }

    private static EquipmentRarity rollRarity(net.minecraft.util.RandomSource random) {
        int roll = random.nextInt(100);
        if (roll < 5) return EquipmentRarity.CELESTIAL;
        if (roll < 20) return EquipmentRarity.EPIC;
        if (roll < 50) return EquipmentRarity.RARE;
        return EquipmentRarity.COMMON;
    }
}
