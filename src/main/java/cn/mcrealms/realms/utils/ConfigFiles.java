package cn.mcrealms.realms.utils;

import cn.mcrealms.realms.Main;
import cn.mcrealms.realms.realms.RealmConfig;
import cn.mcrealms.realms.realms.themes.ThemeConfig;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;

public class ConfigFiles {

    private void checkFolder() {
        if (!Main.getInstance().getDataFolder().exists())
            Main.getInstance().getDataFolder().mkdir();
        File themedir = new File(Main.getInstance().getDataFolder().getPath() + "/theme");
        if (!themedir.exists())
            themedir.mkdir();
        pasteFiles();
    }

    public void init() {
        checkFolder();
        initConfigs();
        loadConfig();
        System.out.println("[Realms] Starting loading themes ...");
        new ThemeConfig().loadAllThemes();
        System.out.println("[Realms] Starting loading realms ...");
        new RealmConfig().loadAllRealm();
    }


    public void loadConfig() {
        YamlConfiguration config = Config.CONFIG.getConfig();
    }

    private void initConfigs() {
        Arrays.stream(Config.values()).forEach(config -> {
            try {
                if (!config.getFile().exists())
                    config.getFile().createNewFile();

                if (config.getCopyDefault())
                    copyDefault(config.getFileName(), config.getFile());

                config.setConfig(YamlConfiguration.loadConfiguration(config.getFile()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void copyDefault(String configname, File file) throws IOException {
        YamlConfiguration config = YamlConfiguration.loadConfiguration(new File(Main.getInstance().getDataFolder(), configname));
        YamlConfiguration sourceconfig = YamlConfiguration.loadConfiguration(new InputStreamReader(Main.getInstance().getResource(configname), StandardCharsets.UTF_8));
        for (String s : sourceconfig.getKeys(true)) {
            if (config.get(s) == null) {
                config.set(s, sourceconfig.get(s));
            }
        }
        config.save(file);
    }

    private void pasteFiles() {
        if (!new File(Main.getInstance().getDataFolder()+"/island.schematic").exists()) {
            System.out.println("[Realms] Creating island schematic");
            copy(getClass().getResourceAsStream("/schematics/island.schematic"),Main.getInstance().getDataFolder().getAbsolutePath() + "/island.schematic");
        }
    }
    public static boolean copy(InputStream source , String destination) {

        boolean success = true;

        System.out.println("Copying ->" + source + "\n\tto ->" + destination);

        try {
            Files.copy(source, Paths.get(destination), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            System.out.println("[AdvancedRealm] Error while creating files");
            ex.printStackTrace();
        }

        return success;

    }
}
