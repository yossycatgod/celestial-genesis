package com.yossy.celestialgenesis.api;

import com.yossy.celestialgenesis.CelestialGenesis;
import com.yossy.celestialgenesis.forging.GenesisForging;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;

/**
 * Public compatibility surface for Epic Fight, TaCZ, Apotheosis and addon mods.
 * Consumers should compile against this class instead of reading CustomData/NBT directly.
 */
public final class GenesisEquipmentApi {
    public static final int API_VERSION = 1;
    private static final Map<ResourceLocation, GenesisAffixDefinition> AFFIXES = new LinkedHashMap<>();
    private static final Map<ResourceLocation, GenesisAffixDefinition> DUNGEON_AFFIXES = new LinkedHashMap<>();
    private static final List<Predicate<ItemStack>> WEAPON_PREDICATES = new ArrayList<>();
    private static final List<Predicate<ItemStack>> ARMOR_PREDICATES = new ArrayList<>();

    static {
        registerAffix(builtin("astral_power", "affix.celestialgenesis.astral_power", EquipmentKind.WEAPON, 1, 4));
        registerAffix(builtin("energy_surge", "affix.celestialgenesis.energy_surge", EquipmentKind.WEAPON, 2, 8));
        registerAffix(builtin("lunar_edge", "affix.celestialgenesis.lunar_edge", EquipmentKind.WEAPON, 3, 10));
        registerAffix(builtin("celestial_guard", "affix.celestialgenesis.celestial_guard", EquipmentKind.ARMOR, 2, 8));
        registerAffix(builtin("vital_star", "affix.celestialgenesis.vital_star", EquipmentKind.ARMOR, 1, 5));
        registerAffix(builtin("energy_flow", "affix.celestialgenesis.energy_flow", EquipmentKind.ARMOR, 2, 8));
        registerDungeonAffix(builtin("boss_slayer", "affix.celestialgenesis.boss_slayer", EquipmentKind.WEAPON, 8, 18));
        registerDungeonAffix(builtin("astral_resilience", "affix.celestialgenesis.astral_resilience", EquipmentKind.ARMOR, 5, 12));
    }

    private GenesisEquipmentApi() {}

    public static synchronized void registerAffix(GenesisAffixDefinition definition) {
        if (AFFIXES.putIfAbsent(definition.id(), definition) != null)
            throw new IllegalArgumentException("Duplicate Genesis affix: " + definition.id());
    }

    public static synchronized Collection<GenesisAffixDefinition> affixes(EquipmentKind kind) {
        return AFFIXES.values().stream().filter(a -> a.kind() == kind || a.kind() == EquipmentKind.ANY).toList();
    }

    public static synchronized void registerDungeonAffix(GenesisAffixDefinition definition) {
        if (AFFIXES.containsKey(definition.id()) || DUNGEON_AFFIXES.putIfAbsent(definition.id(), definition) != null)
            throw new IllegalArgumentException("Duplicate Genesis affix: " + definition.id());
    }

    public static synchronized Collection<GenesisAffixDefinition> dungeonAffixes(EquipmentKind kind) {
        return DUNGEON_AFFIXES.values().stream().filter(a -> a.kind() == kind || a.kind() == EquipmentKind.ANY).toList();
    }

    public static Optional<GenesisAffixDefinition> affix(ResourceLocation id) {
        GenesisAffixDefinition value = AFFIXES.get(id);
        return Optional.ofNullable(value != null ? value : DUNGEON_AFFIXES.get(id));
    }

    public static synchronized void registerEquipment(EquipmentKind kind, Predicate<ItemStack> predicate) {
        if (kind == EquipmentKind.WEAPON || kind == EquipmentKind.ANY) WEAPON_PREDICATES.add(predicate);
        if (kind == EquipmentKind.ARMOR || kind == EquipmentKind.ANY) ARMOR_PREDICATES.add(predicate);
    }

    public static boolean supports(ItemStack stack, EquipmentKind kind) {
        if (kind == EquipmentKind.ARMOR && stack.getItem() instanceof ArmorItem) return true;
        if (kind == EquipmentKind.WEAPON && GenesisForging.isNativeWeapon(stack)) return true;
        List<Predicate<ItemStack>> predicates = kind == EquipmentKind.ARMOR ? ARMOR_PREDICATES : WEAPON_PREDICATES;
        return predicates.stream().anyMatch(predicate -> predicate.test(stack));
    }

    public static boolean isForged(ItemStack stack) { return GenesisForging.read(stack).contains("Rarity"); }

    public static Optional<GenesisEquipmentData> get(ItemStack stack) {
        CompoundTag tag = GenesisForging.read(stack);
        if (!tag.contains("Rarity")) return Optional.empty();
        List<GenesisAffixInstance> affixes = new ArrayList<>();
        for (int i = 0; i < tag.getInt("AffixCount"); i++) {
            affixes.add(new GenesisAffixInstance(id(tag.getString("Affix" + i)), tag.getDouble("AffixValue" + i)));
        }
        List<ResourceLocation> shards = new ArrayList<>();
        for (int i = 0; i < tag.getInt("FilledSockets"); i++) shards.add(id(tag.getString("Shard" + i)));
        return Optional.of(new GenesisEquipmentData(tag.getInt("DataVersion"), tag.getString("Rarity"), affixes,
                tag.getInt("Sockets"), shards));
    }

    public static double modifier(ItemStack stack, ResourceLocation affixId) {
        return GenesisForging.total(stack, affixId.toString());
    }

    private static GenesisAffixDefinition builtin(String path, String key, EquipmentKind kind, double min, double max) {
        return new GenesisAffixDefinition(ResourceLocation.fromNamespaceAndPath(CelestialGenesis.MODID, path), key, kind, min, max);
    }

    private static ResourceLocation id(String value) {
        return value.indexOf(':') >= 0 ? ResourceLocation.parse(value) : ResourceLocation.fromNamespaceAndPath(CelestialGenesis.MODID, value);
    }
}
