package cn.mcrealms.realms.events;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

public class EventManager {
    Plugin plugin;

    public EventManager(Plugin plugin) {
        this.plugin = plugin;
        registerEvents();
    }

    public void registerEvents() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new JoinEvent(),this.plugin);
    }
}
