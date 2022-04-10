package com.rpdescriptions.plugin.commands;

import com.rpdescriptions.plugin.utilities.Config;
import com.rpdescriptions.plugin.services.DescriptionService;
import com.rpdescriptions.plugin.services.message.Message;
import com.rpdescriptions.plugin.services.message.MessageService;
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
	private final MessageService     messageService;
	@NonNull
	private final DescriptionService descriptionService;
	@NonNull
	private final Config             databaseConfig;

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!sender.hasPermission("rpdescriptions.forcedesc")) {
			messageService.sendMessage(sender, Message.GENERAL_NO_PERMISSION);
			return false;
		}
		if (args.length == 0) {
			messageService.sendMessage(sender, Message.GENERAL_ENTER_PLAYER);
			return false;
		}
		Player target = Bukkit.getServer().getPlayer(args[0]);
		if (target == null || !target.isOnline()) {
			messageService.sendMessage(sender, Message.GENERAL_NO_PLAYER);
			return false;
		}
		if (args.length == 1) {
			if (descriptionService.notSet(target)) {
				messageService.sendMessage(sender,
											Message.CMD_FORCEDESC_MESSAGES_NO_DESCRIPTION,
											(s) -> s.replace("%target%", target.getName()));
				return false;
			}
			databaseConfig.set("Descriptions." + target.getUniqueId(), null);
			databaseConfig.save();
			messageService.sendMessage(sender,
										Message.CMD_FORCEDESC_MESSAGES_DESCRIPTION_RESET,
										(s) -> s.replace("%target%", target.getName()));
			return false;
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i < args.length; i++)
			sb.append(args[i]).append(" ");
		databaseConfig.set("Descriptions." + target.getUniqueId(), sb.toString());
		databaseConfig.save();
		messageService.sendMessage(sender,
									Message.CMD_FORCEDESC_MESSAGES_DESCRIPTION_SET,
									(s) -> s.replace("%target%", target.getName()));
		return false;
	}
}
