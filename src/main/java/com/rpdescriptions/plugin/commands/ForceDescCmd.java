package com.rpdescriptions.plugin.commands;

import com.rpdescriptions.plugin.Main;
import com.rpdescriptions.plugin.misc.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ForceDescCmd implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!sender.hasPermission("rpdescriptions.forcedesc")) {
			sender.sendMessage(Utils.color(Main.getConfig.getString("General.Messages.No-Permission")));
			return false;
		}
		if (args.length == 0) {
			sender.sendMessage(Utils.color(Main.getConfig.getString("General.Messages.Enter-Player")));
			return false;
		}
		Player target = Bukkit.getServer().getPlayer(args[0]);
		if (target == null || !target.isOnline()) {
			sender.sendMessage(Utils.color(Main.getConfig.getString("General.Messages.No-Player")));
			return false;
		}
		if (args.length == 1) {
			if (Main.getDatabase.get("Descriptions." + target.getUniqueId()) == null) {
				sender.sendMessage(Utils.color(Main.getConfig.getString("Commands.ForceDesc.No-Description").replace("%player%", target.getName())));
				return false;
			}
			Main.getDatabase.set("Descriptions." + target.getUniqueId(), null);
			Main.getDatabase.save();
			sender.sendMessage(Utils.color(Main.getConfig.getString("Commands.ForceDesc.Description-Reset").replace("%player%", target.getName())));
			return false;
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i < args.length; i++)
			sb.append(args[i]).append(" ");
		Main.getDatabase.set("Descriptions." + target.getUniqueId(), sb.toString());
		Main.getDatabase.save();
		sender.sendMessage(Utils.color(Main.getConfig.getString("Commands.ForceDesc.Description-Set").replace("%player%", target.getName())));
		return false;
	}
}
