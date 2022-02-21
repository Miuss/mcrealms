package cn.mcrealms.realms.utils;

import org.bukkit.ChatColor;

public class ChatFormat {

    public static String t(String text) {
        return ChatColor.translateAlternateColorCodes('&',text);
    }

}