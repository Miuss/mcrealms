package cn.mcrealms.realms;

import cn.mcrealms.realms.commands.Home;
import cn.mcrealms.realms.commands.RealmCommand;
import cn.mcrealms.realms.events.EventManager;
import cn.mcrealms.realms.realms.RealmConfig;
import cn.mcrealms.realms.realms.themes.ThemeConfig;
import cn.mcrealms.realms.utils.NMS;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    private static Main instance;

    public static Main getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        saveResource("data/realms.yml", false);
        System.out.println("[AdvancedRealm] Starting loading themes ...");
        new ThemeConfig().loadAllThemes();
        System.out.println("[AdvancedRealm] Starting loading realms ...");
        new RealmConfig().loadAllRealm();

        // Plugin startup logic
        getLogger().info("Enable Realms Plugin");
        new EventManager(this);
        getCommand("realm").setExecutor(new RealmCommand());
        getCommand("home").setExecutor(new Home());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("Disable Realms Plugin");
    }

    public NMS getNms() {
        return new NMS();
    }
}
