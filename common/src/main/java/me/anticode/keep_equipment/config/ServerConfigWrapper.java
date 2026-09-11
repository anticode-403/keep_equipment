package me.anticode.keep_equipment.config;

import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry.Category;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;

@Config(name = "keep_equipment")
public class ServerConfigWrapper extends PartitioningSerializer.GlobalData {
    @Category("server")
    public ServerConfig server = new ServerConfig();
}
