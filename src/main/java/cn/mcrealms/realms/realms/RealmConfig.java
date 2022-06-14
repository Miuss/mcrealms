package cn.mcrealms.realms.realms;

import cn.mcrealms.realms.Main;
import cn.mcrealms.realms.realms.themes.ThemeType;
import com.google.common.collect.Iterables;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.ArrayList;

/**
 * 领域配置文件管理（后面改为mysql存储配置数据）
 */
public class RealmConfig {
    private File file = new File(Main.getInstance().getDataFolder(), "data/realms.yml");
    private FileConfiguration config = YamlConfiguration.loadConfiguration(file);

    public RealmConfig() {

    }

    public void updateRealm(Realm realm) {
        config.set("realms." + realm.getOwner().getUniqueId() + ".owner.uuid", realm.getOwner().getUniqueId());
        config.set("realms." + realm.getOwner().getUniqueId() + ".owner.name", realm.getOwner().getName());
        config.set("realms." + realm.getOwner().getUniqueId() + ".privacy", realm.getPrivacy());
        config.set("realms." + realm.getOwner().getUniqueId() + ".theme.id", realm.getTheme().getThemeType().getName());
        config.set("realms." + realm.getOwner().getUniqueId() + ".theme.spawn.x", realm.getTheme().getSpawn().getBlockX());
        config.set("realms." + realm.getOwner().getUniqueId() + ".theme.spawn.y", realm.getTheme().getSpawn().getBlockY());
        config.set("realms." + realm.getOwner().getUniqueId() + ".theme.spawn.z", realm.getTheme().getSpawn().getBlockZ());
        config.set("realms." + realm.getOwner().getUniqueId() + ".theme.spawn.yaw", realm.getTheme().getSpawn().getYaw());
        config.set("realms." + realm.getOwner().getUniqueId() + ".theme.spawn.pitch", realm.getTheme().getSpawn().getPitch());
        try {
            config.save(file);
        } catch (Exception e) {
            System.out.println("[AdvancedRealm] Error while loading the realm.yml file, check if it is deleted or try to reinstall the plugin. If you don't sucess at solving the problem you can contact iPazu#3982 at discord");
            e.printStackTrace();
        }
    }

    public void addPlayer(RealmPlayer rplayer, Realm realm) {
        config.set("realms." + realm.getOwner().getUniqueId() + ".players." + rplayer.getUniqueId() + ".name", rplayer.getName());
        try {
            config.save(file);
        } catch (Exception e) {
            System.out.println("[AdvancedRealm] Error while loading the realm.yml file, check if it is deleted or try to reinstall the plugin. If you don't sucess at solving the problem you can contact iPazu#3982 at discord");
            e.printStackTrace();
        }
    }

    public void removePlayer(RealmPlayer rplayer, Realm realm) {
        config.set("realms." + realm.getOwner().getUniqueId() + ".players." + rplayer.getUniqueId(), null);
        try {
            config.save(file);
        } catch (Exception e) {
            System.out.println("[AdvancedRealm] Error while loading the realm.yml file, check if it is deleted or try to reinstall the plugin. If you don't sucess at solving the problem you can contact iPazu#3982 at discord");
            e.printStackTrace();
        }
    }

    public void addNewPlayer(Player p) {
        config.set("realmplayers." + p.getUniqueId() + ".uuid", p.getUniqueId().toString());
        config.set("realmplayers." + p.getUniqueId() + ".name", p.getName());
        try {
            config.save(file);
        } catch (Exception ex) {
            System.out.println("[AdvancedRealm] Error while loading the realm.yml file, check if it is deleted or try to reinstall the plugin. If you don't sucess at solving the problem you can contact iPazu#3982 at discord");
            ex.printStackTrace();
        }

        if (RealmPlayer.getPlayer(p.getUniqueId().toString()) == null)
            new RealmPlayer(p.getUniqueId().toString(), p.getName());

    }

    public void setPrivacy(Realm realm) {
        config.set("realms." + realm.getOwner().getUniqueId() + ".privacy", realm.getPrivacy());
        try {
            config.save(file);
        } catch (Exception ex) {
            System.out.println("[AdvancedRealm] Error while loading the realm.yml file, check if it is deleted or try to reinstall the plugin. If you don't sucess at solving the problem you can contact iPazu#3982 at discord");
            ex.printStackTrace();
        }
    }

    public void delete(Realm realm) {
        if (Realm.allrealm.size() == 0) {
            config.set("realms", null);
        } else
            config.set("realms." + realm.getOwner().getUniqueId(), null);
        try {
            config.save(file);
        } catch (Exception ex) {
            System.out.println("[AdvancedRealm] Error while loading the realm.yml file, check if it is deleted or try to reinstall the plugin. If you don't sucess at solving the problem you can contact iPazu#3982 at discord");
            ex.printStackTrace();
        }
    }

    public void updatePlayerName(Player player) {
        RealmPlayer rp = RealmPlayer.getPlayer(player.getUniqueId().toString());
        for (Realm r : rp.getAllRealm()) {
            if (r.getOwner().getUniqueId().equals(player.getUniqueId().toString())) {
                config.set("realms." + r.getOwner().getUniqueId() + ".owner.name", player.getName());
            }
            config.set("realms." + r.getOwner().getUniqueId() + ".players." + rp.getUniqueId() + ".name", player.getName());
            rp.setName(player.getName());

        }
        for (String s : config.getConfigurationSection("realmplayers").getKeys(false)) {
            if (player.getUniqueId().toString().equals(s))
                config.set("realmplayers." + s + ".name", player.getName());
        }
        try {
            config.save(file);
        } catch (Exception ex) {
            System.out.println("[AdvancedRealm] Error while loading the realm.yml file, check if it is deleted or try to reinstall the plugin. If you don't sucess at solving the problem you can contact iPazu#3982 at discord");
            ex.printStackTrace();
        }
        rp.setName(player.getName());
    }

    private void loadNewRealmPlayer() {
        for (String s : config.getConfigurationSection("realmplayers").getKeys(false)) {
            if (RealmPlayer.getPlayer(s) == null)
                new RealmPlayer(config.getString("realmplayers." + s + ".uuid"), config.getString("realmplayers." + s + ".name"));
        }
    }

    public void loadRealm(String name) {
        for (String s : config.getConfigurationSection("realms." + name + ".players").getKeys(false)) {
            if (RealmPlayer.getPlayer(s) == null)
                new RealmPlayer(s, config.getString("realms." + name + ".players." + s + ".name"));
        }
        Location spawn = new Location(Bukkit.getWorld(name), config.getInt("realms." + name + ".theme.spawn.x"), config.getInt("realms." + name + ".theme.spawn.y"), config.getInt("realms." + name + ".theme.spawn.z"), (float) config.getInt("realms." + name + ".theme.spawn.yaw"), (float) config.getInt("realms." + name + ".theme.spawn.pitch"));
        Realm realm = new Realm(RealmPlayer.getPlayer(config.getString("realms." + name + ".owner.uuid")), ThemeType.themeTypes.get(config.getString("realms." + name + ".theme.id")));
        for (String s : config.getConfigurationSection("realms." + name + ".players").getKeys(false)) {
            RealmPlayer rp = RealmPlayer.getPlayer(s);
            if (rp.getOwned() != realm) {
                realm.addPlayer(rp);
            }
        }
        realm.setOwner(RealmPlayer.getPlayer(config.getString("realms." + name + ".owner.uuid")));
        realm.setPrivacy(config.getBoolean("realms." + name + ".privacy"));
    }

    public void loadAllRealm() {

        if (config.getConfigurationSection("realms") != null) {
            try {
                for (String s : config.getConfigurationSection("realms").getKeys(false)) {
                    loadRealm(s);
                }
                loadNewRealmPlayer();
                System.out.println("[AdvancedRealm] Succefully loaded all realms !");
            } catch (Exception ex) {
                System.out.println("[AdvancedRealm] failed to load all realms, an error occured , please try to reinstall the plugin or call @iPazu#9120 on discord \n cause: " + ex.getCause() + "\n trace:");
                ex.printStackTrace();
            }
        }
    }

    private void useless()
    {
        ArrayList<String> strs = new ArrayList<>();
        strs.forEach(System.out::println);
    }
}
