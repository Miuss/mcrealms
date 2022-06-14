package cn.mcrealms.realms.utils;

import cn.mcrealms.realms.Main;
import com.fastasyncworldedit.core.FaweAPI;
import com.fastasyncworldedit.core.util.TaskManager;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormats;
import com.sk89q.worldedit.math.BlockVector3;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.util.Vector;

import javax.security.auth.callback.Callback;
import java.io.File;
import java.io.IOException;

/**
 *  Schematic模板文件工具类
 */
public class SchematicUtils {
    private Location location;
    private File file;

    public SchematicUtils(Location location, File file) {
        this.location = location;
        this.file = file;
    }

    public void paste()  {
        BlockVector3 vector = BlockVector3.at(location.getX(), location.getY(), location.getZ());

        try {
            ClipboardFormats.findByFile(file).load(file).paste(FaweAPI.getWorld(location.getWorld().getName()), vector, true, false, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

