package cn.mcrealms.realms.commands;

import cn.mcrealms.realms.realms.RealmPlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Home implements CommandExecutor {

    /**
     * 玩家默认指令
     * @param sender
     * @param command
     * @param label
     * @param args
     * @return
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("The command is only executable by a player !");
            return false;
        }

        if(command.getName().equalsIgnoreCase("home")){
            Player player = (Player) sender;
            RealmPlayer rp = RealmPlayer.getPlayer(player.getUniqueId().toString());
            if (rp.getOwned() == null || rp.getAllRealm().size() == 0) {
                player.sendMessage("§cYou don't have a realm to teleport.");
                return false;
            }

            if(rp.getOwned() != null && rp.getAllRealm().size() <= 1) {
                rp.getOwned().teleportToSpawn(player);
                player.sendMessage("§aTeleporting to the realm...");
                return true;
            }
        }

        return true;
    }
}
