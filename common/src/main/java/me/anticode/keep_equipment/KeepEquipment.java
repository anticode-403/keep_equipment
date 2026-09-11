package me.anticode.keep_equipment;

import me.anticode.keep_equipment.config.ServerConfig;
import me.anticode.keep_equipment.config.ServerConfigWrapper;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class KeepEquipment {
    public static final TagKey<Item> KEPT_EQUIPMENT = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("keep_equipment", "kept_equipment"));
    public static final String MOD_ID = "keep_equipment";
    public static ServerConfig config;

    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static void init() {
        AutoConfig.register(ServerConfigWrapper.class, PartitioningSerializer.wrap(JanksonConfigSerializer::new));
        config = AutoConfig.getConfigHolder(ServerConfigWrapper.class).getConfig().server;
    }
}
