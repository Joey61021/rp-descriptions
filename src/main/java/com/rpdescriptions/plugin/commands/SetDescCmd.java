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
	@NonNull
	private final Config             config;

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			messageService.sendMessage(sender, Message.GENERAL_NO_CONSOLE);
			return false;
		}
		Player player = (Player) sender;
		if (args.length == 0) {
			if (descriptionService.notSet(player)) {
				messageService.sendMessage(player, Message.CMD_SETDESC_MESSAGES_ENTER_DESCRIPTION);
				return false;
			}
			databaseConfig.set("Descriptions." + player.getUniqueId(), null);
			databaseConfig.save();
			messageService.sendMessage(player, Message.CMD_SETDESC_MESSAGES_DESCRIPTION_RESET);
			return false;
		}
		StringBuilder description = new StringBuilder();
		for (String arg : args) description.append(arg).append(" ");
		int limit = config.getInt("General.Limit");
		if (description.toString().length() > limit) {
			messageService.sendMessage(player,
										Message.CMD_SETDESC_MESSAGES_TOO_LONG,
										(s) -> s.replace("%limit%", String.valueOf(limit)));
			return false;
		}
		databaseConfig.set("Descriptions." + player.getUniqueId(), description.toString());
		databaseConfig.save();
		messageService.sendMessage(sender, Message.CMD_SETDESC_MESSAGES_DESCRIPTION_SET);
		return false;
	}
}
