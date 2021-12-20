package com.rpdescriptions.plugin.commands;

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
public class ViewDescCmd implements CommandExecutor {

	@NonNull
	private final MessageService     messageService;
	@NonNull
	private final DescriptionService descriptionService;

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
	  	if (!(sender instanceof Player)) {
			messageService.sendMessage(sender, Message.GENERAL_NO_CONSOLE);
			return false;
		}
		Player player = (Player) sender;
		messageService.sendMessage(sender,
									Message.CMD_VIEWDESC_MESSAGES_DESCRIPTION,
									(s) -> s.replace("%description%", descriptionService.getDescription(player)));
	  	return false;
	}
}
