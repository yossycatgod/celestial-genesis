package com.yossy.celestialgenesis.compat.apotheosis;
import com.yossy.celestialgenesis.api.GenesisAffixDefinition;
import com.yossy.celestialgenesis.api.GenesisEquipmentApi;
import com.yossy.celestialgenesis.api.GenesisEquipmentData;
import java.util.Optional;
import net.minecraft.world.item.ItemStack;
/** Read/registration bridge for Apotheosis affix integration. */
public final class ApotheosisCompat {
    private ApotheosisCompat() {}
    public static Optional<GenesisEquipmentData> equipmentData(ItemStack stack) { return GenesisEquipmentApi.get(stack); }
    public static void registerExternalAffix(GenesisAffixDefinition affix) { GenesisEquipmentApi.registerAffix(affix); }
}
