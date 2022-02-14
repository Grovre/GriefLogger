package me.grovre.grieflogger;

import org.bukkit.plugin.java.JavaPlugin;

public final class GriefLogger extends JavaPlugin {

    public static GriefLogger plugin;

    public static GriefLogger getPlugin() {
        return plugin;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();
        plugin = this;

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
