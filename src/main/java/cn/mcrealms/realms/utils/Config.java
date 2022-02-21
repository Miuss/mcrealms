package cn.mcrealms.realms.utils;

import cn.mcrealms.realms.Main;
import cn.mcrealms.realms.realms.Realm;
import cn.mcrealms.realms.realms.RealmPlayer;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public enum Config {
    CONFIG(Main.getInstance().getDataFolder(),"config.yml",true),
    REALM(Main.getInstance().getDataFolder(),"data/realms.yml",false);

    private String name;
    private YamlConfiguration config;
    private File folder;
    private boolean copydefault;
    Config(File folder, String name, boolean copydefault) {
        this.name = name;
        this.folder = folder;
        this.copydefault = copydefault;
    }

    public String getFileName() {
        return name;
    }

    public YamlConfiguration getConfig() {
        return config;
    }
    public boolean getCopyDefault(){
        return copydefault;
    }
    public File getFolder(){
        return folder;
    }

    public static Material getMaterial(String name){
        return Material.getMaterial(name.toUpperCase());
    }
    public static String getStringWithReplacementPlayer(String oldstring, Realm r, RealmPlayer rp){
        String newstring = getStringWithReplacementRealm(oldstring,r);
        newstring = pushColor(newstring);
        newstring = newstring.replace("%player_name%",rp.getName());
        newstring = newstring.replace("%targeted_player%",rp.getName());

        return newstring;
    }
    public static String getStringWithReplacementRealm(String oldstring, Realm r){
        String newstring = oldstring;
        newstring = pushColor(newstring);
        newstring = newstring.replace("%realm_name%",r.getOwner().getName());
        newstring = newstring.replace("%realm_privacy%",r.getPrivacyString());

        return newstring;
    }

    public static String pushColor(String oldstring){
        String newstring = oldstring;
        newstring = newstring.replace("&","§");
        return newstring;
    }
    public static List<String> getListWithReplacementPlayer(List<String> oldlist, Realm r,RealmPlayer rp){
        List<String> newlist = new ArrayList<>();
        for(String s : oldlist){
            newlist.add(getStringWithReplacementPlayer(s,r,rp));
        }
        return newlist;
    }
    public static List<String> getListWithReplacementRealm(List<String> oldlist, Realm r){
        List<String> newlist = new ArrayList<>();
        for(String s : oldlist){
            newlist.add(getStringWithReplacementRealm(s,r));
        }
        return newlist;
    }
    public static int getCollumFromInt(int i){
      int collum = i -9*getRowFromInt(i);
      return collum;
    }
    public static int getRowFromInt(int i){
       int row = i/9;
       return row;
    }
    public File getFile(){
        return new File(getFolder(),getFileName());
    }
    public void setConfig(YamlConfiguration config) {
        this.config = config;
    }
}
