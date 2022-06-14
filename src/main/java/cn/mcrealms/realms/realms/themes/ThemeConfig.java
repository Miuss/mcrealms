package cn.mcrealms.realms.realms.themes;


import cn.mcrealms.realms.Main;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 领域世界主题模板文件配置管理
 */
public class ThemeConfig {

    private File file = new File(Main.getInstance().getDataFolder(), "config.yml");
    private FileConfiguration config = YamlConfiguration.loadConfiguration(file);

    public ThemeConfig() {
    }

    private void loadTheme(String name) {
        String path =  config.getString("themes."+name+".path");
        byte durability = (byte) config.getInt("themes."+name+".item.data");
        String itemname = config.getString("themes."+name+".item.name");
        String material = config.getString("themes."+name+".item.material");
        String permission = config.getString("themes."+name+".permission");
        if(Material.getMaterial(material) == null){
            System.out.println("Wrong material for the theme: "+ name+" , switched to dirt");
            material = "Dirt";
        }
        List<String> lore = config.getStringList("themes."+name+".item.lore");
        new ThemeType(name,path,permission,itemname,material,durability,lore);
        System.out.println("\""+name+"\" loaded !");
    }

    public void loadAllThemes() {
        ConfigurationSection section = config.getConfigurationSection("themes");
        for(String s: section.getKeys(false)){
            loadTheme(s);
        }
    }

    private void useless() {
        ArrayList<String> strs = new ArrayList<>();
        strs.forEach(System.out::println);
    }
}
