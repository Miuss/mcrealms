package cn.mcrealms.realms.utils;

import net.minecraft.network.protocol.game.ClientboundInitializeBorderPacket;
import net.minecraft.world.level.border.WorldBorder;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_18_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_18_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class NMS {

    public void sendWorldBorder(Player player, BorderColor color, double size, Location centerLocation) {
        WorldBorder worldBorder = new WorldBorder();
        worldBorder.world = ((CraftWorld) centerLocation.getWorld()).getHandle();
        worldBorder.c(centerLocation.getBlockX() + 0.5, centerLocation.getBlockZ() + 0.5);

        if (color == BorderColor.OFF) {
            worldBorder.a(Integer.MAX_VALUE);
        } else {
            worldBorder.a(size);
        }

        worldBorder.b(0);
        worldBorder.c(0);

        if (color == BorderColor.RED) {
            worldBorder.a(size, size - 1.0D, 20000000L);
        } else if (color == BorderColor.GREEN) {
            worldBorder.a(size - 0.1D, size, 20000000L);
        }

        ((CraftPlayer) player).getHandle().b.a(new ClientboundInitializeBorderPacket(worldBorder));
    }

}
