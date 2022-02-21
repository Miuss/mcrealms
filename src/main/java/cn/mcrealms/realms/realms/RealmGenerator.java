package cn.mcrealms.realms.realms;

import cn.mcrealms.realms.Main;
import cn.mcrealms.realms.utils.BorderColor;
import cn.mcrealms.realms.utils.RealmChunkGenerator;
import cn.mcrealms.realms.utils.SchematicUtils;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.entity.Player;

import java.io.File;

public class RealmGenerator {

    public World createWorld(Player player, String schematic) {
        WorldCreator wc = new WorldCreator(player.getUniqueId().toString());
        wc.type(WorldType.FLAT);
        wc.generator(RealmChunkGenerator.getDefaultWorldGenerator());
        World world = wc.createWorld();
        Main.getInstance().getNms().sendWorldBorder(player, BorderColor.BLUE, 100, world.getSpawnLocation());
        pasteWorld(world, schematic);

        return world;
    }

    public void pasteWorld(World world, String schematic) {
        try {
            File file = new File(Main.getInstance().getDataFolder(), schematic);
            new SchematicUtils(world.getSpawnLocation() ,file).paste();
        } catch (Exception e) {
            System.out.println("§c[Realms] failed to load schematic");
            e.printStackTrace();
        }
    }

}
