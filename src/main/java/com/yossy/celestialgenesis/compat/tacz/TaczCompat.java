package com.yossy.celestialgenesis.compat.tacz;
import com.yossy.celestialgenesis.CelestialGenesis;
import com.yossy.celestialgenesis.api.EquipmentKind;
import com.yossy.celestialgenesis.api.GenesisEquipmentApi;
import java.util.function.Predicate;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
/** Bridge usable by a TaCZ integration module without linking TaCZ into the core mod. */
public final class TaczCompat {
    private static final ResourceLocation POWER = ResourceLocation.fromNamespaceAndPath(CelestialGenesis.MODID, "astral_power");
    private TaczCompat() {}
    public static void registerGunPredicate(Predicate<ItemStack> predicate) { GenesisEquipmentApi.registerEquipment(EquipmentKind.WEAPON, predicate); }
    public static double projectileDamageBonus(ItemStack gun) { return GenesisEquipmentApi.modifier(gun, POWER); }
    public static boolean canGenesisForge(ItemStack gun) { return GenesisEquipmentApi.supports(gun, EquipmentKind.WEAPON); }
}
