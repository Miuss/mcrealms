package cn.mcrealms.realms.events;

import cn.mcrealms.realms.realms.RealmPlayer;
import cn.mcrealms.realms.utils.ChatFormat;
import cn.mcrealms.realms.realms.RealmConfig;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvent implements Listener {

    /**
     * 玩家事件监听器
     * @param e
     */
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){

        Player player = e.getPlayer();
        player.sendMessage(ChatFormat.t("&e欢迎"+player.getName()+"来到McRealms服务器！"));

        if(RealmPlayer.getPlayer(player.getUniqueId().toString()) == null){
            new RealmConfig().addNewPlayer(player);
        }
        RealmPlayer rp = RealmPlayer.getPlayer(player.getUniqueId().toString());
        if(!rp.getName().toLowerCase().equals(player.getName().toLowerCase())){
            new RealmConfig().updatePlayerName(player);
        }

    }

}
