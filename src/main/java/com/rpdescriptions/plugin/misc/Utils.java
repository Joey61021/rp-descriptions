package com.rpdescriptions.plugin.misc;

import com.rpdescriptions.plugin.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class Utils {
	
	public static String color(String string) {
		return ChatColor.translateAlternateColorCodes('&', string);
	}

	public static void noConsole() {
		Bukkit.getServer().getConsoleSender().sendMessage(color(Main.getConfig.getString("Messages.No-Console")));
	}
}
