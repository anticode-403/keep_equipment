package me.anticode.keep_equipment.fabric;

import me.anticode.keep_equipment.KeepEquipment;
import net.fabricmc.api.ModInitializer;

public final class KeepEquipmentFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        // Run our common setup.
        KeepEquipment.init();
    }
}
