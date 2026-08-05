package me.anticode.keep_equipment;

import net.fabricmc.api.ModInitializer;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class KeepEquipment implements ModInitializer {
    public static final TagKey<Item> KEPT_EQUIPMENT = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("keep_equipment", "kept_equipment"));

    @Override
    public void onInitialize() {
    }
}
