package me.anticode.keep_equipment.neoforge;

import me.anticode.keep_equipment.KeepEquipment;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import top.theillusivec4.curios.api.event.CurioDropsEvent;

@Mod(KeepEquipment.MOD_ID)
public final class KeepEquipmentForge {
    public KeepEquipmentForge() {

        // Run our common setup.
        KeepEquipment.init();
    }

    @Mod.EventBusSubscriber(modid = KeepEquipment.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class KeepEquipmentEvents {
        @SubscribeEvent
        public void onDead(CurioDropsEvent event) {
            event.setCanceled(true);
        }
    }
}
