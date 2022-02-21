package cn.mcrealms.realms.commands;

import cn.mcrealms.realms.realms.Realm;
import cn.mcrealms.realms.realms.RealmConfig;
import cn.mcrealms.realms.realms.RealmPlayer;
import cn.mcrealms.realms.realms.themes.ThemeType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RealmCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player) sender;
        RealmPlayer rp = RealmPlayer.getPlayer(player.getUniqueId().toString());

        // create world
        if (args.length == 2) {
            if (args[0].equalsIgnoreCase("create")) {
                for(ThemeType t : ThemeType.allthemeTypes){
                    if(player.hasPermission(t.getPermission()) && args[1].equalsIgnoreCase(t.getName())) {
                        Realm realm = new Realm(rp, ThemeType.allthemeTypes.get(0));
                        realm.getTheme().setThemeType(t);
                        realm.spawnTheme();
                        new RealmConfig().updateRealm(rp.getOwned());
                        player.sendMessage("§aSuccessfully changed the type to: §e§l"+t.getName());
                    }
                }
            }
        }

        return true;
    }
}
