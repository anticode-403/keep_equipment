package me.anticode.keep_equipment.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

@Config(name = "server")
public class ServerConfig implements ConfigData {
    @Comment("""
            When an item with durability is kept, it will take damage equal to this percentage of its max durability.
            For example, a value of 0.15 means that items take 15% durability damage when you die.
            Set this to 0 to disable it.""")
    public float durabilityDamage = 0.15F;

    @Comment("""
            The percentage of XP that is kept on death.""")
    public float xpKeepPercentage = 0.5F;

    @Comment("""
            The percentage of XP that is dropped on death. xpKeepPercentage + xpDropPercentage should not be higher than 1.0.
            If the total is lower than 1.0, that means that some amount of XP will be permanently lost.
            If the total is higher than 1.0, that means XP will be created out of thin air when you die.""")
    public float xpDropPercentage = 0.25F;
}
