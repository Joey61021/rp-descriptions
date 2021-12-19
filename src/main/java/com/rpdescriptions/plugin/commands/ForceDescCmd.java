package com.rpdescriptions.plugin.commands;

import com.rpdescriptions.plugin.misc.Config;
import com.rpdescriptions.plugin.misc.Utils;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@RequiredArgsConstructor
public class ForceDescCmd implements CommandExecutor {

	@NonNull
	private final Config config;
	@NonNull
	private final Config databaseConfig;

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!sender.hasPermission("rpdescriptions.forcedesc")) {
			sender.sendMessage(Utils.color(config.getString("General.Messages.No-Permission")));
			return false;
		}
		if (args.length == 0) {
			sender.sendMessage(Utils.color(config.getString("General.Messages.Enter-Player")));
			return false;
		}
		Player target = Bukkit.getServer().getPlayer(args[0]);
		if (target == null || !target.isOnline()) {
			sender.sendMessage(Utils.color(config.getString("General.Messages.No-Player")));
			return false;
		}
		if (args.length == 1) {
			if (databaseConfig.get("Descriptions." + target.getUniqueId()) == null) {
				sender.sendMessage(Utils.color(config.getString("Commands.ForceDesc.No-Description").replace("%player%", target.getName())));
				return false;
			}
			databaseConfig.set("Descriptions." + target.getUniqueId(), null);
			databaseConfig.save();
			sender.sendMessage(Utils.color(config.getString("Commands.ForceDesc.Description-Reset").replace("%player%", target.getName())));
			return false;
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i < args.length; i++)
			sb.append(args[i]).append(" ");
		databaseConfig.set("Descriptions." + target.getUniqueId(), sb.toString());
		databaseConfig.save();
		sender.sendMessage(Utils.color(config.getString("Commands.ForceDesc.Description-Set").replace("%player%", target.getName())));
		return false;
	}
}
