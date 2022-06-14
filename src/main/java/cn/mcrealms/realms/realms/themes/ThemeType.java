package cn.mcrealms.realms.realms.themes;

import cn.mcrealms.realms.realms.RealmGenerator;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 领域世界主题模板类型
 */
public class ThemeType {
    private String schematic;   //schematic path
    private String name;    //Thme Name
    List<String> lore;  //GUI des
    private String id;  //GUI Icon item id
    private byte durability;    //GUI Icon item durability
    private String itemname;    //item name
    private String permission;  //permission
    public static HashMap<String,ThemeType> themeTypes = new HashMap<>();
    public static ArrayList<ThemeType> allthemeTypes = new ArrayList<>();

    public ThemeType(String name, String path, String permission, String itemname, String id, byte durability, List<String> lore) {
        this.schematic = "theme/"+path;
        this.name = name;
        this.itemname = itemname.replace("&","§");
        ArrayList<String > newlorelist = new ArrayList<>();
        for(String newlore : lore){
            newlorelist.add(newlore.replace("&","§"));
        }
        this.lore = newlorelist;
        this.durability = durability;
        this.permission = permission;
        this.id = id;
        themeTypes.put(name,this);
        allthemeTypes.add(this);
    }

    public void pasteTheme(Player player) {
        World world = new RealmGenerator().createWorld(player, schematic);
        player.teleport(world.getSpawnLocation());
    }

    public String getName() {
        return name;
    }

    public String getPermission() {
        return permission;
    }

    private void useless()
    {
        ArrayList<String> strs = new ArrayList<>();
        strs.forEach(System.out::println);
    }

}
