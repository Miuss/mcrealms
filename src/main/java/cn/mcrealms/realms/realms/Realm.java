package cn.mcrealms.realms.realms;

import cn.mcrealms.realms.Main;
import cn.mcrealms.realms.realms.themes.Theme;
import cn.mcrealms.realms.realms.themes.ThemeType;
import cn.mcrealms.realms.utils.BorderColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import java.util.ArrayList;

/**
 * Realms Entity
 */
public class Realm {
    /**
     * 领域集合
     */
    public static ArrayList<Realm> allrealm = new ArrayList<>();
    /**
     * 私人模式（领域世界是否禁止非领域玩家访问）
     */
    private boolean privacy;
    /**
     * 领域数据
     */
    private Theme theme;
    /**
     * 领域人员列表
     */
    private ArrayList<RealmPlayer> realmmembers = new ArrayList<>();
    /**
     * 领域拉黑人员列表
     */
    private ArrayList<RealmPlayer> banned = new ArrayList<>();
    /**
     * 领域拥有者
     */
    private RealmPlayer owner;

    public Realm(RealmPlayer rp, ThemeType theme) {
        owner = rp;
        privacy = false;
        this.theme = new Theme(theme, rp);
        allrealm.add(this);
        addPlayer(rp);
        rp.setOwned(this);
    }

    public void addPlayer(RealmPlayer p) {
        if (!(realmmembers.size() >= 3)) {
            realmmembers.add(p);
            p.addRealm(this);
            new RealmConfig().addPlayer(p, this);
        }
    }

    public void spawnTheme() {
        theme.spawnTheme();
    }

    public void teleportToSpawn(Player player) {
        player.setFallDistance(0);
        player.teleport(theme.getSpawn().clone().add(0.5, 1, 0.5));
        sendWorldBorderPacket(player);
    }

    public void sendBorderToAll() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            sendWorldBorderPacket(player);
        }
    }

    public Location getCenter() {
        return theme.getSpawn();
    }

    public void sendWorldBorderPacket(Player player) {
        Main.getInstance().getNms().sendWorldBorder(player, BorderColor.BLUE, 100, this.getCenter());
    }

    public Theme getTheme() {
        return theme;
    }

    public void kickPlayer(RealmPlayer player) {
        if (realmmembers.contains(player)) {
            realmmembers.remove(player);
            if (Bukkit.getPlayer(player.getUniqueId()) != null) {
                Player kicked = Bukkit.getPlayer(player.getUniqueId());
            }

            player.remove(this);
            new RealmConfig().removePlayer(player, this);
        }
    }

    public ArrayList<RealmPlayer> getRealmMembers() {
        return realmmembers;
    }

    public RealmPlayer getOwner() {
        return owner;
    }

    public boolean getPrivacy() {
        return privacy;
    }

    public void setPrivacy(boolean b) {
        privacy = b;
        new RealmConfig().setPrivacy(this);
        if (b) {
            for (Player p : Bukkit.getOnlinePlayers()) {
                RealmPlayer realmPlayer = RealmPlayer.getPlayer(p.getUniqueId().toString());
                if (!this.getRealmMembers().contains(realmPlayer)) {
                    //踢出
                }
            }
        }
    }

    public String getPrivacyString() {
        if (this.getPrivacy())
            return "Private";
        else
            return "Public";
    }

    public void setOwner(RealmPlayer owner) {
        this.owner = owner;
    }

    private void useless() {
        ArrayList<String> strs = new ArrayList<>();
        strs.forEach(System.out::println);
    }
}

