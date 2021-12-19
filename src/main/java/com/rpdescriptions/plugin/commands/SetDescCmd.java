package com.rpdescriptions.plugin.commands;

import com.rpdescriptions.plugin.misc.Config;
import com.rpdescriptions.plugin.misc.Utils;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@RequiredArgsConstructor
public class SetDescCmd implements CommandExecutor {

	@NonNull
	private final Config config;
	@NonNull
	private final Config databaseConfig;

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(Utils.color(config.getString("Messages.No-Console")));
			return false;
		}
		Player player = (Player) sender;
		if (args.length == 0) {
			if (databaseConfig.get("Descriptions." + player.getUniqueId()) == null) {
				player.sendMessage(Utils.color(config.getString("Commands.SetDesc.Enter-Description")));
				return false;
			}
			databaseConfig.set("Descriptions." + player.getUniqueId(), null);
			databaseConfig.save();
			player.sendMessage(Utils.color(config.getString("Commands.SetDesc.Description-Reset")));
			return false;
		}
		StringBuilder sb = new StringBuilder();
		for (String arg : args) sb.append(arg).append(" ");
		databaseConfig.set("Descriptions." + player.getUniqueId(), sb.toString());
		databaseConfig.save();
		player.sendMessage(Utils.color(config.getString("Commands.SetDesc.Description-Set")));
		return false;
	}
}
