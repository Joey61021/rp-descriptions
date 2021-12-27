package com.rpdescriptions.plugin.commands;

import com.rpdescriptions.plugin.utilities.Config;
import com.rpdescriptions.plugin.services.DescriptionService;
import com.rpdescriptions.plugin.services.message.Message;
import com.rpdescriptions.plugin.services.message.MessageService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@RequiredArgsConstructor
public class SetDescCmd implements CommandExecutor {

	@NonNull
	private final MessageService     messageService;
	@NonNull
	private final DescriptionService descriptionService;
	@NonNull
	private final Config             databaseConfig;

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			messageService.sendMessage(sender, Message.GENERAL_NO_CONSOLE);
			return false;
		}
		Player player = (Player) sender;
		if (args.length == 0) {
			if (!descriptionService.hasDescription(player)) {
				messageService.sendMessage(player, Message.CMD_SETDESC_MESSAGES_ENTER_DESCRIPTION);
				return false;
			}
			databaseConfig.set("Descriptions." + player.getUniqueId(), null);
			databaseConfig.save();
			messageService.sendMessage(player, Message.CMD_SETDESC_MESSAGES_DESCRIPTION_RESET);
			return false;
		}
		StringBuilder sb = new StringBuilder();
		for (String arg : args) sb.append(arg).append(" ");
		databaseConfig.set("Descriptions." + player.getUniqueId(), sb.toString());
		databaseConfig.save();
		messageService.sendMessage(sender, Message.CMD_SETDESC_MESSAGES_DESCRIPTION_SET);
		return false;
	}
}
