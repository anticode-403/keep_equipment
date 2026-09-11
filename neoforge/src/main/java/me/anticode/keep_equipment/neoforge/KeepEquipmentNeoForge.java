package me.anticode.keep_equipment.neoforge;

import me.anticode.keep_equipment.KeepEquipment;
import net.neoforged.fml.common.Mod;
import net.neoforged.bus.api.SubscribeEvent;
import top.theillusivec4.curios.api.event.CurioDropsEvent;

@Mod(KeepEquipment.MOD_ID)
public final class KeepEquipmentNeoForge {
    public KeepEquipmentNeoForge() {

        // Run our common setup.
        KeepEquipment.init();
    }

    @SubscribeEvent
    public void onDead(CurioDropsEvent event) {
        event.setCanceled(true);
    }
}
