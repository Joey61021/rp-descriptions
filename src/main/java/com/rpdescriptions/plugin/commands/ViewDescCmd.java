package com.rpdescriptions.plugin.commands;

import com.rpdescriptions.plugin.misc.Config;
import com.rpdescriptions.plugin.misc.Utils;
import com.rpdescriptions.plugin.services.DescriptionService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@RequiredArgsConstructor
public class ViewDescCmd implements CommandExecutor {

	@NonNull
	private final Config             config;
	@NonNull
	private final DescriptionService descriptionService;

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
	  	if (!(sender instanceof Player)) {
			sender.sendMessage(Utils.color(config.getString("Messages.No-Console")));
			return false;
		}
		sender.sendMessage(Utils.color(config.getString("Commands.ViewDesc.Description"))
				.replace("%description%", descriptionService.getDescription((Player) sender) == null ?
						Utils.color(config.getString("Commands.ViewDesc.No-Description")) : descriptionService.getDescription((Player) sender)));
	  	return false;
	}
}
