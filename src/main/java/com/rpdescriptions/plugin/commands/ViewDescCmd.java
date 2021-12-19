package com.rpdescriptions.plugin.commands;

import com.rpdescriptions.plugin.Main;
import com.rpdescriptions.plugin.misc.Utils;
import com.rpdescriptions.plugin.services.DescriptionService;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ViewDescCmd implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
	  	if (!(sender instanceof Player)) {
			Utils.noConsole();
			return false;
		}
		sender.sendMessage(Utils.color(Main.getConfig.getString("Commands.ViewDesc.Description"))
				.replace("%description%", DescriptionService.getDescription((Player) sender) == null ?
						Utils.color(Main.getConfig.getString("Commands.ViewDesc.No-Description")) : DescriptionService.getDescription((Player) sender)));
	  	return false;
	}
}
