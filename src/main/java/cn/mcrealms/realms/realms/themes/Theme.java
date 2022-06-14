package cn.mcrealms.realms.realms.themes;

import cn.mcrealms.realms.realms.RealmPlayer;
import org.apache.commons.io.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

/**
 * 领域世界主题模板
 */
public class Theme {

    private ThemeType themeType;
    private RealmPlayer realmPlayer;
    private Player player;

    public Theme(ThemeType themeType, RealmPlayer realmPlayer) {
        this.themeType = themeType;
        this.realmPlayer = realmPlayer;
        this.player = Bukkit.getPlayer(UUID.fromString(realmPlayer.getUniqueId()));
    }

    public void spawnTheme() {
        if (Bukkit.getWorld(player.getUniqueId().toString()) != null) {
            removeTheme();
        }
        themeType.pasteTheme(player);
    }

    public void setThemeType(ThemeType themeType) {
        this.themeType = themeType;
        spawnTheme();
    }

    public ThemeType getThemeType() {
        return themeType;
    }

    public void removeTheme() {
        World world = Bukkit.getWorld(player.getUniqueId().toString());
        Bukkit.unloadWorld(world, false);
        try {
            FileUtils.deleteDirectory(new File(world.getName()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Location getSpawn() {
        if (Bukkit.getWorld(player.getUniqueId().toString()) != null) {
            return Bukkit.getWorld(player.getUniqueId().toString()).getSpawnLocation();
        }

        return player.getLocation();
    }

    private void useless()
    {
        ArrayList<String> strs = new ArrayList<>();
        strs.forEach(System.out::println);
    }
}
