package com.yossy.celestialgenesis.registry;

import com.yossy.celestialgenesis.CelestialGenesis;
import com.yossy.celestialgenesis.item.LunarGenesisItem;
import com.yossy.celestialgenesis.item.CelestialArmorItem;
import com.yossy.celestialgenesis.item.DungeonKeyItem;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(CelestialGenesis.MODID);
    public static final DeferredItem<LunarGenesisItem> LUNAR_GENESIS = ITEMS.register("lunar_genesis",
            () -> new LunarGenesisItem(Tiers.NETHERITE, new Item.Properties()
                    .attributes(LunarGenesisItem.createAttributes(Tiers.NETHERITE, 5, -2.4F)).fireResistant()));
    public static final DeferredItem<ArmorItem> CELESTIAL_HOOD = armor("celestial_hood", ArmorItem.Type.HELMET);
    public static final DeferredItem<ArmorItem> CELESTIAL_COAT = armor("celestial_coat", ArmorItem.Type.CHESTPLATE);
    public static final DeferredItem<ArmorItem> CELESTIAL_LEGGINGS = armor("celestial_leggings", ArmorItem.Type.LEGGINGS);
    public static final DeferredItem<ArmorItem> CELESTIAL_BOOTS = armor("celestial_boots", ArmorItem.Type.BOOTS);

    private ModItems() {}

    private static DeferredItem<ArmorItem> armor(String name, ArmorItem.Type type) {
        return ITEMS.register(name, () -> new CelestialArmorItem(ArmorMaterials.DIAMOND, type,
                new Item.Properties().durability(type.getDurability(33))));
    }

    public static final DeferredItem<Item> STAR_SHARD = ITEMS.registerSimpleItem("star_shard", new Item.Properties().stacksTo(64));
    public static final DeferredItem<Item> GENESIS_CATALYST = ITEMS.registerSimpleItem("genesis_catalyst", new Item.Properties().stacksTo(64));
    public static final DeferredItem<net.minecraft.world.item.BlockItem> GENESIS_FORGE = ITEMS.registerSimpleBlockItem("genesis_forge", ModBlocks.GENESIS_FORGE);
    public static final DeferredItem<net.minecraft.world.item.BlockItem> BOSS_SEAL = ITEMS.registerSimpleBlockItem("boss_seal", ModBlocks.BOSS_SEAL);
    public static final DeferredItem<net.minecraft.world.item.BlockItem> ASTRAL_GATEWAY = ITEMS.registerSimpleBlockItem("astral_gateway", ModBlocks.ASTRAL_GATEWAY);
    public static final DeferredItem<DungeonKeyItem> LUNAR_KEY = ITEMS.register("lunar_key",()->new DungeonKeyItem(1,new Item.Properties().stacksTo(16)));
    public static final DeferredItem<DungeonKeyItem> OBSERVATORY_KEY = ITEMS.register("observatory_key",()->new DungeonKeyItem(2,new Item.Properties().stacksTo(16)));
    public static final DeferredItem<DungeonKeyItem> PALACE_KEY = ITEMS.register("palace_key",()->new DungeonKeyItem(3,new Item.Properties().stacksTo(16)));
    public static final DeferredItem<Item> MOONLIT_CORE = ITEMS.registerSimpleItem("moonlit_core",new Item.Properties().fireResistant());
    public static final DeferredItem<Item> WATCHER_EYE = ITEMS.registerSimpleItem("watcher_eye",new Item.Properties().fireResistant());
    public static final DeferredItem<Item> ASTRAL_STAR_SHARD = ITEMS.registerSimpleItem("astral_star_shard",new Item.Properties());
    public static final DeferredItem<Item> EPIC_FIGHT_SIGIL = ITEMS.registerSimpleItem("epic_fight_sigil",new Item.Properties());
    public static final DeferredItem<Item> TACZ_CORE = ITEMS.registerSimpleItem("tacz_core",new Item.Properties());
    public static final DeferredItem<Item> APOTHEOSIS_PRISM = ITEMS.registerSimpleItem("apotheosis_prism",new Item.Properties());
    public static final DeferredItem<Item> CELESTIAL_INGOT = ITEMS.registerSimpleItem("celestial_ingot",new Item.Properties().fireResistant());
    public static final DeferredItem<net.neoforged.neoforge.common.DeferredSpawnEggItem> ASTRAL_SENTINEL_SPAWN_EGG = ITEMS.register("astral_sentinel_spawn_egg",()->new net.neoforged.neoforge.common.DeferredSpawnEggItem(ModEntities.ASTRAL_SENTINEL,0x15102B,0x75E6FF,new Item.Properties()));
    public static final DeferredItem<net.neoforged.neoforge.common.DeferredSpawnEggItem> MOONLIT_GUARDIAN_SPAWN_EGG = ITEMS.register("moonlit_guardian_spawn_egg",()->new net.neoforged.neoforge.common.DeferredSpawnEggItem(ModEntities.MOONLIT_GUARDIAN,0x202050,0xDDD6FF,new Item.Properties()));
    public static final DeferredItem<net.neoforged.neoforge.common.DeferredSpawnEggItem> ASTRAL_WATCHER_SPAWN_EGG = ITEMS.register("astral_watcher_spawn_egg",()->new net.neoforged.neoforge.common.DeferredSpawnEggItem(ModEntities.ASTRAL_WATCHER,0x080018,0xC040FF,new Item.Properties()));

    public static void register(IEventBus bus) { ITEMS.register(bus); }
}
