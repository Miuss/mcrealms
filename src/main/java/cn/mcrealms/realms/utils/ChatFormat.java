package cn.mcrealms.realms.utils;

import org.bukkit.ChatColor;

/**
 * 聊天工具类
 */
public class ChatFormat {

    public static String t(String text) {
        return ChatColor.translateAlternateColorCodes('&',text);
    }

}